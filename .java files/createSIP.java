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
 * Servlet implementation class createSIP
 */
public class createSIP extends HttpServlet {
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
		
		String user = (String) request.getSession().getAttribute("curr_user");
		String sipname = request.getParameter("sipname");
		String sipid = request.getParameter("sipid");
		
		String sql = "insert into CSETEAM61.SIPAGES values (?, ?)";
		String sql1 = "insert into CSETEAM61.SIPTOPAGE values (?, ?)";
		String sql2 = "insert into CSETEAM61.SIPMEMBER values (?, ?)";
		String sql3 = "insert into CSETEAM61.SIPMODERATOR values (?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sipid);
			ps.setString(2, sipname);
			
			int i = ps.executeUpdate();
			
			ps = con.prepareStatement(sql1);
			ps.setString(1, sipid + "page");
			ps.setString(2, sipid);
			i &= ps.executeUpdate();
			
			ps = con.prepareStatement(sql2);
			ps.setString(1, sipid);
			ps.setString(2, user);
			i &= ps.executeUpdate();
			
			ps = con.prepareStatement(sql3);
			ps.setString(1, sipid);
			ps.setString(2, user);
			i &= ps.executeUpdate();
			
			if(i!=0){
				System.out.println("SIP successfully created, member and moderator updated");
			}
			else{
				System.out.println("one or more operation failed, please try again...");
			}
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/mySIP.jsp");
		    requestDispatcher.forward(request,response);
			
			
		} catch (SQLException e) {
			
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
