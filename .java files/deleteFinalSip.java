package com.chiru;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteFinalSip
 */
public class deleteFinalSip extends HttpServlet {
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
		
		String deleteable[] = request.getParameterValues("okToDel");
		String sql = "delete from CSETEAM61.SIPOSTS where SIPOSTID = ?";
		String sql2 = "delete from CSETEAM61.SIPCOMMENTS where SIPOSTID = ?";
		PreparedStatement ps = null;
		int success = 1;
		try {
			
			for(int i = 0; i< deleteable.length;i++){
				
				ps = con.prepareStatement(sql2);
				ps.setString(1, deleteable[i]);
				success &= ps.executeUpdate();
				
				ps = con.prepareStatement(sql);
				ps.setString(1, deleteable[i]);
				success &= ps.executeUpdate();
				
				
			}
			
			if(success!=0){
				System.out.println("successfully deleted selected messages");
			}
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
	    	requestDispatcher.forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
