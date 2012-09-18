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
 * Servlet implementation class salesRep
 */
public class salesRep extends HttpServlet {
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
		
		String month = request.getParameter("month");
		String sql = "select TRANSACTIONID, ADVTID, DATENTIME, NOOFUNITSOLD, USERID from CSETEAM61.SALES where DATENTIME like ?";
		String sql1 = "select count(*) from (select TRANSACTIONID, ADVTID, DATENTIME, NOOFUNITSOLD, USERID from CSETEAM61.SALES where DATENTIME like ?)";
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, "_____"+month+"___");
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			
			String values[][] = new String [count][5];
			ps = con.prepareStatement(sql);
			ps.setString(1, "_____"+month+"___");
			rs = ps.executeQuery();
			int j = 0;
			while(rs.next()){
				
				for(int i = 0;i<5;i++){
					values[j][i] = rs.getString(i+1);
				}
				j++;
			}
			
			request.getSession().setAttribute("dispvalues", values);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispSales.jsp");
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
