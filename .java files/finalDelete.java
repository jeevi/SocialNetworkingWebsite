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
 * Servlet implementation class finalDelete
 */
public class finalDelete extends HttpServlet {
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
		
		String friends[] = request.getParameterValues("dfriend");
		
		String user = (String) request.getSession().getAttribute("curr_user");
		String sql = "delete from CSETEAM61.FRIEND where FRIENDID = ? and USERID = ?";
		String sql1 = "delete from CSETEAM61.FRIEND where FRIENDID = ? and USERID = ?";
		
		PreparedStatement ps = null;
		
		int success = 1;
		String friend;
			
		for (int i = 0;i <friends.length;i++){
		
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, friends[i]);
				ps.setString(2, user);
				success = ps.executeUpdate();
				
				ps = con.prepareStatement(sql1);
				ps.setString(2, friends[i]);
				ps.setString(1, user);
				success &= ps.executeUpdate(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
					
		if(success!=0)
			System.out.println("all selected friends deleted successfully");
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
    	requestDispatcher.forward(request,response);
		 
		
	}

}
