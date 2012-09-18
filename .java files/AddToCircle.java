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
 * Servlet implementation class AddToCircle
 */
public class AddToCircle extends HttpServlet {
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
		String mycircle = request.getParameter("circleid");
		String add = "select FRIENDID from CSETEAM61.FRIEND where USERID = ? and FRIENDID not in (select USERID from CSETEAM61.CIRCLEMEMBER where CIRCLEID = ?)";
		System.out.println(user);
		System.out.println(mycircle);
		ResultSet rs = null;
		
		try {
			int j = 1;
			PreparedStatement ps = con.prepareStatement(add);
			ps.setString(1, user);
			ps.setString(2, mycircle);
			rs = ps.executeQuery();
			
				
				String enter = "insert into CSETEAM61.CIRCLEMEMBER values (?, ?)";
				ps = con.prepareStatement(enter);
				while(rs.next()){
					
					ps.setString(1, mycircle);
					System.out.println(rs.getString(1));
					ps.setString(2, rs.getString(1));
					j &= ps.executeUpdate();
							
				}
				if(j!=0){
					System.out.println("successfully added all the friends to the group");
				
				}
							
			
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
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
