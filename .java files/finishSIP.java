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
 * Servlet implementation class finishSIP
 */
public class finishSIP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://db2serv01.cs.stonybrook.edu:50000/teamdb61:retrieveMessagesFromServerOnGetMessage=true;", "cseteam61", "Balawat61");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] chosen = (String[]) request.getSession().getAttribute("uidList");
		String chosenSip = request.getParameter("sip");
		System.out.println(chosenSip);
		String checkQuery1 = "select count(*) from CSETEAM61.SIPMEMBER where SIPID = ? and USERID = ?";
		String checkQuery2 = "select count(*) from CSETEAM61.SIPINVITE where SIPID = ? and USERID = ?";
		String insertQuery = "insert into CSETEAM61.SIPINVITE values (?, ?)";
		ResultSet rs = null;
		PreparedStatement ps = null;
		int j = 1;
		for(int i = 0; i<chosen.length;i++){
			
			try {
				ps = con.prepareStatement(checkQuery1);
				ps.setString(1, chosenSip);
				ps.setString(2, chosen[i]);
				
				rs = ps.executeQuery();
				rs.next();
				int temp = Integer.parseInt(rs.getString(1));
				
				ps = con.prepareStatement(checkQuery2);
				ps.setString(1, chosenSip);
				ps.setString(2, chosen[i]);
				
				rs = ps.executeQuery();
				rs.next();
				temp |= Integer.parseInt(rs.getString(1));
				
				if(temp==1){
					System.out.println(chosen[i] + " already a member/ already has been invited");
				}
				else{
					ps = con.prepareStatement(insertQuery);
					ps.setString(1, chosenSip);
					ps.setString(2, chosen[i]);
					j &= ps.executeUpdate();
					if(j!=0)
						System.out.println("successfully added " + chosen[i]);
					
				}
				
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
	    requestDispatcher.forward(request,response);
	
		
	}

}
