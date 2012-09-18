package com.chiru;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createAdvt
 */
public class createAdvt extends HttpServlet {
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
		
		String company = request.getParameter("company");
		String itemname = request.getParameter("itemname");
		String unitprice = request.getParameter("unitprice");
		String availunits = request.getParameter("availableunits");
		String advttype = request.getParameter("pref");
		
		String sql = "select max(ADVTID) from CSETEAM61.ADVERTISEMENTS";
		String sql1 = "insert into CSETEAM61.ADVERTISEMENTS values (?, ?, ?, ?, ?, ?, ?)";
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = formatter.format(currentDate.getTime());
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			int advtid = Integer.parseInt(rs.getString(1)) + 1;
			
			ps = con.prepareStatement(sql1);
			ps.setString(1,  Integer.toString(advtid));
			ps.setString(2, advttype);
			ps.setString(3, dateNow);
			ps.setString(4, company);
			ps.setString(5, itemname);
			ps.setString(6, unitprice);
			ps.setString(7, availunits);
			
			int success = ps.executeUpdate();
			if(success!=0){
				System.out.println("successfully created the advertisement");
			}
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
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
