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
 * Servlet implementation class makePostSip
 */
public class makePostSip extends HttpServlet {
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
				
		String user = (String) request.getSession().getAttribute("curr_user");
		String theSip = (String) request.getSession().getAttribute("chosenSipWall");
		String content = request.getParameter("content");
		System.out.println(user);
		System.out.println(theSip);
		System.out.println(content);
		String pageid = "select PAGEID from CSETEAM61.SIPTOPAGE where SIPID = ?";
		String insQ = "insert into CSETEAM61.SIPOSTS values (? , ?, ? ,?, ?)";
		String cinsQ = "select max(SIPOSTID) from CSETEAM61.SIPOSTS";
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = formatter.format(currentDate.getTime());
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(pageid);
			ps.setString(1, theSip);
			rs = ps.executeQuery();
			rs.next();		
			String temp = rs.getString(1);
			
			
			ps = con.prepareStatement(cinsQ);
			rs = ps.executeQuery();
			rs.next();
			
			
			int count = Integer.parseInt(rs.getString(1)) + 1;
			
			ps = con.prepareStatement(insQ);
			ps.setString(1, Integer.toString(count));
			ps.setString(2, dateNow);
			ps.setString(3, content);
			ps.setString(4, user);
			ps.setString(5, temp);
			
			int success = ps.executeUpdate();
						
			if(success!=0)
				System.out.println("post now visible to you and your friends");
			
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
