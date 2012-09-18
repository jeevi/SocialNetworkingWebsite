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
 * Servlet implementation class FINAL
 */
public class FINAL extends HttpServlet {
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
		
		String cir[] = request.getParameterValues("todeletef");
		String sql = "delete from CSETEAM61.CIRCLEMEMBER where CIRCLEID = ? and USERID = ?";
		String O = (String) request.getSession().getAttribute("circleO");
		int success = 1;
		
		PreparedStatement ps = null;
		
		for(int i  = 0; i<cir.length;i++){
			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, O);
				ps.setString(2, cir[i]);
				success &= ps.executeUpdate();
				System.out.println(success);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		if(success!=0){
			System.out.println("all delete ops executed successfully");
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
	    requestDispatcher.forward(request,response);
	    
	}
	}


