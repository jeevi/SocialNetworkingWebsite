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
 * Servlet implementation class chooseAddMod
 */
public class chooseAddMod extends HttpServlet {
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
		String addSip = request.getParameter("addsip");
		String sql = "select count(*) from (select USERID from CSETEAM61.SIPMEMBER where SIPID = ? and USERID not in(Select USERID from CSETEAM61.SIPMODERATOR where SIPID = ?))";
		String sql1 = "select USERID from CSETEAM61.SIPMEMBER where SIPID = ? and USERID not in(Select USERID from CSETEAM61.SIPMODERATOR where SIPID = ?)";
		int count = 0;
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, addSip);
			ps.setString(2, addSip);
			rs = ps.executeQuery();
			rs.next();
			count = Integer.parseInt(rs.getString(1));
			String sipmembers[] = new String[count];
			int i = 0;
			ps = con.prepareStatement(sql1);
			ps.setString(1, addSip);
			ps.setString(2, addSip);
			rs = ps.executeQuery();
			while(rs.next()){
				
				sipmembers[i] = rs.getString(1);
				i++;
			}
			request.getSession().setAttribute("the_AddSIP", addSip);
			request.getSession().setAttribute("sipmembers", sipmembers);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addNewMod.jsp");
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
