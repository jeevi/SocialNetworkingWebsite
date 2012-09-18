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
 * Servlet implementation class mostRevenueProd
 */
public class mostRevenueProd extends HttpServlet {
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
		
		String sql = "Select  ITEMNAME, sum(rev) as total from ( select A.ITEMNAME, B.NOOFUNITSOLD* A.UNITPRICE as rev from CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A where A.ADVTID = B.ADVTID) group by ITEMNAME order by total DESC FETCH FIRST 1 ROWS ONLY";
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
			String dumpp[] = new String[2];
			
			dumpp[0] = rs.getString(1);
			dumpp[1] = rs.getString(2);
			
			request.getSession().setAttribute("dispmaxrevp", dumpp);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispMaxProd.jsp");
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
