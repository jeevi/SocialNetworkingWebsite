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
 * Servlet implementation class Circle
 */
public class Circle extends HttpServlet {
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
			
			e1.printStackTrace();
		}
		
		String cname = request.getParameter("circlename");
		String cid = request.getParameter("circleid");
		System.out.println(cname);
		System.out.println(cid);
		System.out.println((String) request.getSession().getAttribute("curr_user"));
		PreparedStatement ps = null;
		int i = 0;
		String sql1 = "Insert into CSETEAM61.CIRCLES values (?, ?, ?)";
		String sql2 = "Insert into CSETEAM61.CIRCLEMEMBER values (?, ?)";
		try {
			
			ps = con.prepareStatement(sql1);
			ps.setString(1, cid);
			ps.setString(2, cname);
			ps.setString(3, (String) request.getSession().getAttribute("curr_user"));
			i = ps.executeUpdate();
			
			ps = con.prepareStatement(sql2);
			ps.setString(1, cid);
			ps.setString(2, (String) request.getSession().getAttribute("curr_user"));
			i &= ps.executeUpdate();
			
			
			if(i!=0){
				System.out.println("both the insert statements executed perfectly!");
			}
			else{
				System.out.println("insert failed in either/ both tables");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
	    requestDispatcher.forward(request,response);
	    
		
	}

}
