package com.chiru;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class delCircle
 */
public class delCircle extends HttpServlet {
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
		String sql = "select CIRCLEID, CIRCLENAME from CSETEAM61.CIRCLES where OWNER = ?";
		String sql1 = "select count(*) from (select CIRCLEID, CIRCLENAME from CSETEAM61.CIRCLES where OWNER = ?)";
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql1);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			int count  = Integer.parseInt(rs.getString(1));
			if(count==0){
				System.out.println("you are not a owner of any circle");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			    requestDispatcher.forward(request,response);
			    
			}
			else{
				
			
			String mycircles[][] = new String [count][2];
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next()){
				mycircles[i][0] = rs.getString(1);
				mycircles[i][1] = rs.getString(2);
				i++;
			}
			
			request.getSession().setAttribute("myowncircle", mycircles);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/delselcir.jsp");
		    requestDispatcher.forward(request,response);
		    
			}
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
