package com.chiru;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
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
		
		int i;
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String address = request.getParameter("addr");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String telephone = request.getParameter("telephone");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String pref[] = request.getParameterValues("pref");
		
		boolean success = false;
		
		String sql1 = "insert into CSETEAM61.ZIPLOCATION values (?, ?, ?)";
		String sql2 = "insert into CSETEAM61.USERS values (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql3 = "insert into CSETEAM61.PREFERENCES values (?, ?)";
		String sql4 = "insert into CSETEAM61.LOGININFO values (?, ?)";
		String sql5 = "insert into CSETEAM61.PHONENUMBER values (?,?,?)";
		String page = "insert into CSETEAM61.USERTOPAGE values (?, ?)";
		String page1 = "select max(PAGEID) from CSETEAM61.USERTOPAGE";
		
		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			PreparedStatement ps3 = con.prepareStatement(sql3);
			PreparedStatement ps4 = con.prepareStatement(sql4);
			PreparedStatement ps5 = con.prepareStatement(sql5);
			
			
			ps1.setString(1, zip);
			ps1.setString(2, city);
			ps1.setString(3, state);
			
			ps2.setString(1, username);
			ps2.setString(2, firstname);
			ps2.setString(3, lastname);
			ps2.setString(4, sex);
			ps2.setString(5, email);
			ps2.setString(6, dob);
			ps2.setString(7, address);
			ps2.setString(8, zip);
			
			ps4.setString(1, username);
			ps4.setString(2, password);
			
			ps5.setString(1, username);
			ps5.setString(2, "home");
			ps5.setString(3, telephone);
			
			int rs1 = ps1.executeUpdate();
			int rs2 = ps2.executeUpdate();
			int rs3 = 1;
			
			for (i = 0; i<pref.length; i++){
				
				ps3.setString(1, username);
				ps3.setString(2, pref[i]);
				rs3 &= ps3.executeUpdate();
			}
			
			int rs4 = ps4.executeUpdate();
			int rs5 = ps5.executeUpdate();
			
			ResultSet rs = null;
			ps1 = con.prepareStatement(page1);
			rs = ps1.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1)) + 1;
			
			ps1 = con.prepareStatement(page);
			ps1.setString(1, Integer.toString(count));
			ps1.setString(2, username);
			rs5 &= ps1.executeUpdate();
						
			if((rs1 & rs2 & rs3 & rs4 & rs5)!=0){
				success = true;
				System.out.println("database entry success");
				
				response.sendRedirect("Login.jsp");
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
