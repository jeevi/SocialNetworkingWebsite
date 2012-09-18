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
 * Servlet implementation class searchSip
 */
public class searchSip extends HttpServlet {
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
		String sipreq = request.getParameter("sipname");
		int success;
		String sql = "Insert into CSETEAM61.SIPREQUEST values (? ,?)";
		String checkQuery1 = "select count(*) from CSETEAM61.SIPMEMBER where SIPID = ? and USERID = ?";
		String checkQuery2 = "select count(*) from CSETEAM61.SIPREQUEST where SIPID = ? and USERID = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(checkQuery1);
			ps.setString(1, sipreq);
			ps.setString(2, user);
			
			rs = ps.executeQuery();
			rs.next();
			int temp = Integer.parseInt(rs.getString(1));
			
			ps = con.prepareStatement(checkQuery2);
			ps.setString(1, sipreq);
			ps.setString(2, user);
			
			rs = ps.executeQuery();
			rs.next();
			temp |= Integer.parseInt(rs.getString(1));
			
			if(temp==1){
				System.out.println(user + " already a member/ already has submitted a request");
			}
			else{
			ps = con.prepareStatement(sql);
			ps.setString(1, sipreq);
			ps.setString(2, user);
			success = ps.executeUpdate();
			
			if(success!=0){
				System.out.println(user + " successfully added to request list of "+ sipreq);
			}
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
