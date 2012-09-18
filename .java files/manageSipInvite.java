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
 * Servlet implementation class manageSipInvite
 */
public class manageSipInvite extends HttpServlet {
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
		String sql = "select SIPID from CSETEAM61.SIPINVITE where USERID = ?";
		String sql1 = "select count(*) from (select SIPID from CSETEAM61.SIPINVITE where USERID = ?)";
		ResultSet rs = null;
		
				
		try {
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			String sipInviteList[] = new String[count];
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			int i = -1;
			while(rs.next()){
				
				sipInviteList[++i] = rs.getString(1);
				
			}
			
			if(i==-1){
				System.out.println(user + " you have no invites");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			    requestDispatcher.forward(request,response);
				
			}
			else{
			request.getSession().setAttribute("sipInviteList", sipInviteList);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/chooseFromInvited.jsp");
		    requestDispatcher.forward(request,response);
			}
			
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
