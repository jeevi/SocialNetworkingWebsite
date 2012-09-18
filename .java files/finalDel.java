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
 * Servlet implementation class finalDel
 */
public class finalDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://db2serv01.cs.stonybrook.edu:50000/teamdb61:retrieveMessagesFromServerOnGetMessage=true;", "cseteam61", "Balawat61");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String temp[] = request.getParameterValues("deladvts");
		String sql = "delete from CSETEAM61.ADVERTISEMENTS where COMPANY = ? and ITEMNAME = ?";
		PreparedStatement ps = null;
		String temp1[] = null;
		int success = 1;
		for(int i = 0; i<temp.length;i++){
			
			try {
				System.out.println(temp[i]);
				ps = con.prepareStatement(sql);
				temp1 = temp[i].split(" ");
				System.out.println(temp1[0]);
				System.out.println(temp1[1]);
				ps.setString(1, temp1[0]);
				ps.setString(2, temp1[1]);
				success &= ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(success!=0){
			System.out.println("selected items deleted successfully");
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
	    requestDispatcher.forward(request,response);
		try{
			con.close();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
	}

}
