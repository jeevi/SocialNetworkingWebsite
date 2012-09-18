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
 * Servlet implementation class mostRevenue
 */
public class mostRevenue extends HttpServlet {
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
		
		String sql = "Select  USERID, sum(rev) as total from ( select B.USERID, B.NOOFUNITSOLD* A.UNITPRICE as rev from CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A where A.ADVTID = B.ADVTID) group by USERID order by total DESC FETCH FIRST 1 ROWS ONLY";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
			String dump[] = new String[2];
			
			dump[0] = rs.getString(1);
			dump[1] = rs.getString(2);
			
			request.getSession().setAttribute("dispmaxrevc", dump);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispMaxCus.jsp");
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
