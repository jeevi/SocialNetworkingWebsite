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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class friendRequest
 */
public class friendRequest extends HttpServlet {
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
		
		
		String Decision = request.getParameter("decision");
		ResultSet rs = null;
		String names[] = Decision.split(" ");
		String fr = "select USERID from CSETEAM61.USERS where FIRSTNAME = ? and LASTNAME = ?";
		try {
			PreparedStatement ps = con.prepareStatement(fr);
			ps.setString(1, names[0]);
			ps.setString(2, names[1]);
			
			rs = ps.executeQuery();
			rs.next();
			
			String user = (String) request.getSession().getAttribute("curr_user");
			
			String fr1 = "insert into CSETEAM61.FRIEND values (?,?)";
			ps = con.prepareStatement(fr1);
			ps.setString(1, user);
			ps.setString(2, rs.getString(1));
			
			int i = ps.executeUpdate();
			
			ps.setString(1, rs.getString(1));
			ps.setString(2, user);
			
			i &= ps.executeUpdate(); 
			
			String fr2 = "delete from CSETEAM61.FRIENDREQ where USERID = ? and FRIENDID = ?";
			ps = con.prepareStatement(fr2);
			ps.setString(1, user);
			ps.setString(2, rs.getString(1));
			int j = ps.executeUpdate();
			
			if(i!=0 && j!=0){
				System.out.println("insert and delete success");
			}
			con.close();
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		    requestDispatcher.forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
	
	}

}
