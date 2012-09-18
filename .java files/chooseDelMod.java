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
 * Servlet implementation class chooseDelMod
 */
public class chooseDelMod extends HttpServlet {
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
		
		
		String sql1 = "select count(*) from (select USERID from CSETEAM61.SIPMODERATOR WHERE SIPID = ?)";
		String sql = "select USERID from CSETEAM61.SIPMODERATOR WHERE SIPID = ?";
		
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, request.getParameter("modsip"));
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			String[] moderators = new String[count];
			ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("modsip"));
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				
				moderators[i] = rs.getString(1);
				i++;
			}
			request.getSession().setAttribute("the_SIP", request.getParameter("modsip"));
			request.getSession().setAttribute("theMods", moderators);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/delMod.jsp");
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
