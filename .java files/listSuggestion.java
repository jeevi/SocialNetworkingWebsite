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
 * Servlet implementation class listSuggestion
 */
public class listSuggestion extends HttpServlet {
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
		
		String sql = "select distinct(B.ADVTTYPE) from CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B where A.ADVTID = B.ADVTID and A.USERID = ?";
		String nsql = "select * from CSETEAM61.ADVERTISEMENTS where ADVTTYPE = ?";
		String user = request.getParameter("userName");
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		String output[][] = new String[100][7];
		try {
						
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				
				ps = con.prepareStatement(nsql);
				ps.setString(1, rs.getString(1));
				rs1 = ps.executeQuery();
				
				while(rs1.next()){
					
					for(int j = 0;j<7;j++){
						
						output[i][j] = rs1.getString(j+1);
					}
					
					i++;
				}
			}
			
			request.getSession().setAttribute("output", output);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispSug.jsp");
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
