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
 * Servlet implementation class buyChosen
 */
public class buyChosen extends HttpServlet {
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
		String chosen = (String) request.getSession().getAttribute("chosenProd");
		String num = request.getParameter("number");
		System.out.println(num);
		System.out.println(chosen);
		System.out.println(user);
		String csql = "select NOOFAVAILUNIT from CSETEAM61.ADVERTISEMENTS where ADVTID = ?";
		String upAdvt = "update CSETEAM61.ADVERTISEMENTS  set NOOFAVAILUNIT = NOOFAVAILUNIT - ? where ADVTID = ? ";
		String insSales = "insert into CSETEAM61.SALES values (?, ?, ?, ?, ?)";
		String sql = "select max(TRANSACTIONID) from CSETEAM61.SALES";
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = formatter.format(currentDate.getTime());
		ResultSet rs = null;
		PreparedStatement ps = null;
		int success = 1;
		try {
			
			ps = con.prepareStatement(csql);
			ps.setString(1, chosen);
			rs = ps.executeQuery();
			rs.next();
			int number = Integer.parseInt(rs.getString(1));
			
			if(Integer.parseInt(num) <= number){
				
						ps = con.prepareStatement(upAdvt);
						ps.setString(2, chosen);
						ps.setString(1, num);
						success = ps.executeUpdate();
			
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						rs.next();
						
						int count  = Integer.parseInt(rs.getString(1)) + 1;
						
						ps = con.prepareStatement(insSales);
						ps.setString(1, Integer.toString(count));
						ps.setString(2, chosen);
						ps.setString(3, dateNow);
						ps.setString(4, num);
						ps.setString(5, user);
						
						success &= ps.executeUpdate();
						System.out.println("chosen product bought and database updated successfully");
			}
			
			else{
				
				System.out.println("selected number of units ="+num+" not available");
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
