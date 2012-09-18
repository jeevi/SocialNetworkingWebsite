package com.chiru;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class commentFinal
 */
public class commentFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = null;
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://db2serv01.cs.stonybrook.edu:50000/teamdb61:retrieveMessagesFromServerOnGetMessage=true;", "cseteam61", "Balawat61");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String user =  (String) request.getSession().getAttribute("curr_user");
		String entry = request.getParameter("commentf");
		String thePost = (String) request.getSession().getAttribute("comOnPost");
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = formatter.format(currentDate.getTime());
		String sql = "select max(COMMENTID) from CSETEAM61.COMMENTS";
		String sql1 = "insert into CSETEAM61.COMMENTS values (?, ?, ?, ?, ?)";
		
		ResultSet rs = null;
		PreparedStatement p = null;
		
		
		try {
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			rs.next();
			int count;
			
			if(rs.getString(1).equals(null))
				count = 1;
			else
				count = Integer.parseInt(rs.getString(1)) + 1;
			
			p = con.prepareStatement(sql1);
			p.setString(1, Integer.toString(count));
			p.setString(2, dateNow);
			p.setString(3, entry);
			p.setString(4, user);
			p.setString(5, thePost);
			
			int success = p.executeUpdate();
			
			if(success!=0)
				System.out.println("commented on post successfully");
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
	    	requestDispatcher.forward(request,response);
	    	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
