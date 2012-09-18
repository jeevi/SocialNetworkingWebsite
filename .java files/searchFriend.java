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
 * Servlet implementation class searchFriend
 */
public class searchFriend extends HttpServlet {
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
		
		String name = request.getParameter("friendsearch");
		System.out.println(name);
		String sql = "select USERID from CSETEAM61.USERS where FIRSTNAME = ?";
		
		try {
			int i=0 ;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String sql1 = "insert into CSETEAM61.FRIENDREQ values (?,?)";
				ps = con.prepareStatement(sql1);
				ps.setString(1, rs.getString(1));
				ps.setString(2, (String) request.getSession().getAttribute("curr_user"));
				System.out.println(rs.getString(1));
				i = ps.executeUpdate();
				
			}
			
			if(i!=0){
				System.out.println("friend request sent");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			    requestDispatcher.forward(request,response);
			}
			else{
				System.out.println("no such friend found");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			    requestDispatcher.forward(request,response);
			}
			rs.close();
			ps.close();
			con.close();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
