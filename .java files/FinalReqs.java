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
 * Servlet implementation class FinalReqs
 */
public class FinalReqs extends HttpServlet {
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
		
		String siphere = (String) request.getSession().getAttribute("insReqSip");
		String [] newMem = request.getParameterValues("newreqsip");
		String sql = "insert into CSETEAM61.SIPMEMBER values (?, ?)";
		String sql1 = "delete from CSETEAM61.SIPREQUEST where SIPID = ? and USERID = ?";
		String sql2 = "delete from CSETEAM61.SIPINVITE where SIPID = ? and USERID = ?";
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			for(int i = 0;i<newMem.length;i++){
			
					int j = 1;
					ps = con.prepareStatement(sql2);
					ps.setString(1, siphere);
					ps.setString(2, newMem[i]);
					j &= ps.executeUpdate();
					
					ps = con.prepareStatement(sql1);
					ps.setString(1, siphere);
					ps.setString(2, newMem[i]);
					j &= ps.executeUpdate();
					
					ps = con.prepareStatement(sql);
					ps.setString(1, siphere);
					ps.setString(2, newMem[i]);
					j &= ps.executeUpdate();
					
				
					System.out.println(newMem[i] +" was successfully added to member table of "+ siphere);
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
