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
 * Servlet implementation class manageModerator
 */
public class manageModerator extends HttpServlet {
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
		String sql = "select count(*) from CSETEAM61.SIPMODERATOR where USERID = ?";
		String sql1 = "select SIPID from CSETEAM61.SIPMODERATOR where USERID = ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			if(count==0){
					System.out.println(user+" is not a moderator of any group");
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
				    requestDispatcher.forward(request,response);
			}
			else{
				String [] rsips = new String[count];
				int i = 0;
				ps = con.prepareStatement(sql1);
				ps.setString(1, user);
				rs = ps.executeQuery();
				while(rs.next()){
					
					rsips[i] = rs.getString(1);
					i++;
				}
				System.out.println(request.getParameter("manage moderators"));
				request.getSession().setAttribute("moderatorSipList", rsips);
				if(request.getParameter("manage moderators").equalsIgnoreCase("add moderators")){
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addMod.jsp");
					requestDispatcher.forward(request,response);
				}
				else if(request.getParameter("manage moderators").equalsIgnoreCase("delete moderators")){
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/choosefromMod.jsp");
					requestDispatcher.forward(request,response);
				}
				
				
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
