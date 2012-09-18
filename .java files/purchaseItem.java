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
 * Servlet implementation class purchaseItem
 */
public class purchaseItem extends HttpServlet {
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
		
		String cus = request.getParameter("itempurname");
		System.out.println(cus);
		String sql = "SELECT B.USERID FROM CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A WHERE A.ADVTID = B.ADVTID AND ITEMNAME = ? ";
		String csql = "select count(*) from (SELECT B.USERID FROM CSETEAM61.SALES B, CSETEAM61.ADVERTISEMENTS A WHERE A.ADVTID = B.ADVTID AND ITEMNAME = ?)";
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(csql);
			ps.setString(1, cus);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			
			ps = con.prepareStatement(sql);
			ps.setString(1, cus);
			rs = ps.executeQuery();
			int j = 0;
			String customers[] = new String[count];
			while(rs.next()){
				customers[j] = rs.getString(1);
				j++;
			}
			
			request.getSession().setAttribute("customersitem", customers);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispcustomers.jsp");
		    requestDispatcher.forward(request,response);
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
