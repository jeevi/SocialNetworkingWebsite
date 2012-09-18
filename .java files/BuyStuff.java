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
 * Servlet implementation class BuyStuff
 */
public class BuyStuff extends HttpServlet {
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
		
		String sql = "select * from CSETEAM61.ADVERTISEMENTS where NOOFAVAILUNIT <> 0 ";
		String csql = "select count(*) from CSETEAM61.ADVERTISEMENTS where NOOFAVAILUNIT <> 0 ";
				
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(csql);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			String products[][] = new String[count][7];
			int j = 0;
			while(rs.next()){
				
				for(int i = 0;i<7;i++){
					
					products[j][i] = rs.getString(i+1);
				}
				j++;
			}
			
			request.getSession().setAttribute("products", products);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispProd.jsp");
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
