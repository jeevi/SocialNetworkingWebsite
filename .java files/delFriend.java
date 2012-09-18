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
 * Servlet implementation class delFriend
 */
public class delFriend extends HttpServlet {
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
		String sql = "select FRIENDID from CSETEAM61.FRIEND where USERID = ?";
		String sql1 = "select count(FRIENDID) from CSETEAM61.FRIEND where USERID = ?";
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql1);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			System.out.println(count);
			String myamigos[] = new String[count];
			
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			int i = -1;
			while(rs.next()){
				
				myamigos[++i] = rs.getString(1);
				
			}
		
			request.getSession().setAttribute("amigos", myamigos);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/listfriends.jsp");
	    	requestDispatcher.forward(request,response);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}
