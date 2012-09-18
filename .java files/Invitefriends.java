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
 * Servlet implementation class Invitefriends
 */
public class Invitefriends extends HttpServlet {
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
		String selected[] = request.getParameterValues("invite");
		String userID = "select USERID from CSETEAM61.USERS where FIRSTNAME = ? and LASTNAME =?";
		ResultSet RS = null;
		for(int j = 0; j<selected.length;j++){
			
			String[] temp = selected[j].split(" ");
			try {
				PreparedStatement r = con.prepareStatement(userID);
				r.setString(1, temp[0]);
				r.setString(2, temp[1]);
				RS = r.executeQuery();
				RS.next();
				selected[j] = RS.getString(1);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		try {
			RS.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "select SIPID from CSETEAM61.SIPMODERATOR where USERID = ?";
		String countsql = "select count(*) from (select SIPID from CSETEAM61.SIPMODERATOR where USERID = ?)";
		
		ResultSet rs = null;
				
		try {
			PreparedStatement ps = con.prepareStatement(countsql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			
			int count = Integer.parseInt(rs.getString(1));
			
			if(count==0){
				System.out.println("you are not a moderator to any group");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/mySIP.jsp");
			    requestDispatcher.forward(request,response);
			
			}
			else{
				
				String mysips[] = new String[count];
			
				ps = con.prepareStatement(sql);
				ps.setString(1, user);
				rs = ps.executeQuery();
				int i = 0;
				while(rs.next()){
					
					mysips[i] = rs.getString(1);
					
				}
				
				request.getSession().setAttribute("sipList", mysips);
				request.getSession().setAttribute("uidList", selected);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/SIP.jsp");
			    requestDispatcher.forward(request,response);
			
			}
			
		} catch (SQLException e) {
			
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
