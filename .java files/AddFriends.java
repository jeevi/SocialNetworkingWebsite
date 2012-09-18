
																APPENDIX A


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

import com.ibm.db2.jcc.b.SqlException;

/**
 * Servlet implementation class AddFriends
 */
public class AddFriends extends HttpServlet {
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
		System.out.println(user);
		String count = "select count(*) from (select  CIRCLEID from CSETEAM61.CIRCLES where OWNER = ? order by CIRCLEID)";
		String sql = "select CIRCLEID from CSETEAM61.CIRCLES where OWNER = ?";
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(count);
			ps.setString(1, user);
			ps1.setString(1, user);
			rs1 = ps1.executeQuery();
			rs1.next();
			int c = Integer.parseInt(rs1.getString(1));
			System.out.println(c);
			
			rs = ps.executeQuery();
			
			String Circles[] = new String[c];
		
			int i = 0;
			
			while(rs.next()){
		
				Circles[i] = rs.getString(1);
		
				i++;
			}
		
			request.getSession().setAttribute("mycircles", Circles);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/AddFriends.jsp");
		    requestDispatcher.forward(request,response);
		    
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			rs.close();
			con.close();
			
			}
			catch (SQLException e1){
				e1.printStackTrace();
			}
		}
		
		
		
	}

}
