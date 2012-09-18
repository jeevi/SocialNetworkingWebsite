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
 * Servlet implementation class delMsgs
 */
public class delMsgs extends HttpServlet {
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
		
		String delM[] = request.getParameterValues("delmsgs");
		
		
		String sql = "delete from CSETEAM61.UMESSAGES where MESSAGEID = ?";
		String sql1 = "delete from CSETEAM61.MSGRECEIVER where MESSAGEID = ?";
		int success = 1;
		PreparedStatement ps = null;
		
		for (int i =0; i<delM.length;i++){
			
			try {
				ps = con.prepareStatement(sql1);
				ps.setString(1, delM[i]);
				success &= ps.executeUpdate();
				
				ps = con.prepareStatement(sql);
				ps.setString(1, delM[i]);
				success &= ps.executeUpdate();
				
			} catch (SQLException e) {
			// 	TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(success!=0){
			System.out.println("all selected messages deleted successfully");
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
	    requestDispatcher.forward(request,response);
	 
	}

}
