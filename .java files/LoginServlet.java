package com.chiru;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sql = "Select * from CSETEAM61.LOGININFO where USERID = ? and PASSWORD = ?";
		Boolean login = false;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			login = rs.next();
			rs.close();
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		if(login){
			System.out.println("success");
			ResultSet rs1 = null;
			ResultSet rs2 = null;

			String cfriend = "select count(*) from (select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIEND where USERID = ?))";
			String friend = "select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIEND where USERID = ?)";
			String creq = "select count(*) from (select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIENDREQ where USERID = ?))";
			String req = "select FIRSTNAME, LASTNAME FROM CSETEAM61.USERS WHERE USERID IN (select FRIENDID from CSETEAM61.FRIENDREQ where USERID = ?)";
			try {
				
				PreparedStatement fr = con.prepareStatement(cfriend);
				PreparedStatement re = con.prepareStatement(creq);
				fr.setString(1, username);
				re.setString(1, username);
				rs1 = fr.executeQuery();
				rs2 = re.executeQuery();
				rs1.next();
				int count1 = Integer.parseInt(rs1.getString(1));
				rs2.next();
				int count2 = Integer.parseInt(rs1.getString(1));
				
			    	    
			    int i = 0;
			    
			    if(count1!=0){
			    	
			    	String names [] = new String[count1];
			    	fr = con.prepareStatement(friend);
			    	fr.setString(1, username);
			    	rs1 = fr.executeQuery();
			    	while (rs1.next()){
			    		
			    		names[i] = rs1.getString(1) + " " + rs1.getString(2);
			    		i++;
			    	}
			    	request.getSession().setAttribute("friendList", names);
			    }
				
			    if(count2!=0){
			    	
			    	String fnames [] = new String[count2];
			    	re = con.prepareStatement(req);
			    	re.setString(1, username);
			    	rs2 = re.executeQuery();
			    		    
			    	i = 0;
			    	while (rs2.next()){
			    		
			    		fnames[i] = rs2.getString(1) + " " + rs2.getString(2);
			    		i++;
			    	}
			    	request.getSession().setAttribute("requestList", fnames);
			    }
			    
			    System.out.println("about to redirect");
			    request.getSession().setAttribute("curr_user", username);
			    
			    if(username.substring(0, 5).equalsIgnoreCase("admin")){
			    	
			    	RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
			    	requestDispatcher.forward(request,response);
			    }
			    else{
			    					    
				    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			    	requestDispatcher.forward(request,response);
			    }
			    
			    
			 }
			
			catch (Exception e){
				
			}
			
			finally{
				try{
				   rs1.close();
				    rs2.close();
				    con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
					
		}
		else{
			request.setAttribute("message", "unknown username/ password");
			response.sendRedirect("Login.jsp");
		}

	}

}

