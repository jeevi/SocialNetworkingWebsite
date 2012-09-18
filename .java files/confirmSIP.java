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
 * Servlet implementation class confirmSIP
 */
public class confirmSIP extends HttpServlet {
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
		String sips[] = request.getParameterValues("invite");
		String sql = "insert into CSETEAM61.SIPMEMBER values (?, ?)";
		String sql1 = "select count(*) from CSETEAM61.SIPREQUEST where SIPID = ? and USERID = ?";
		String sql3 = "delete from CSETEAM61.SIPREQUEST where SIPID = ? and USERID = ?";
		String sql4= "delete from CSETEAM61.SIPINVITE where SIPID = ? and USERID = ?";
		String sql2 = "select count(*) from CSETEAM61.SIPMEMBER where SIPID = ? and USERID = ?";
		int delete = 0;
		int add = 0;
		ResultSet rs = null;
		PreparedStatement ps = null;
		for(int i = 0; i< sips.length;i++){
			try {
				ps = con.prepareStatement(sql1);
				ps.setString(1, sips[i]);
				ps.setString(2, user);
				rs = ps.executeQuery();
				rs.next();
				if(Integer.parseInt(rs.getString(1))==1){
					
					ps = con.prepareStatement(sql3);
					ps.setString(1, sips[i]);
					ps.setString(2, user);
					delete = ps.executeUpdate();
					if(delete!=0)
						System.out.println(user + " had also requested to be added to " + sips[i] + " now deleted th request since a member");
				}
				
				ps = con.prepareStatement(sql2);
				ps.setString(1, sips[i]);
				ps.setString(2, user);
				rs = ps.executeQuery();
				rs.next();
				if(Integer.parseInt(rs.getString(1))==1){
					System.out.println(user+" already a member");
				}
				else{
					ps = con.prepareStatement(sql);
					ps.setString(1, sips[i]);
					ps.setString(2, user);
					add = ps.executeUpdate();
					if(add!=0){
						System.out.println(user + " successfully added to " +sips[i]);
					}
					ps = con.prepareStatement(sql4);
					ps.setString(1, sips[i]);
					ps.setString(2, user);
					delete = ps.executeUpdate();
					if(delete!=0)
						System.out.println(user+"'s invite deleted as he/she is now a member");
				}
				
			} 
			catch (SQLException e) {

				e.printStackTrace();
			}
		
		}
		
		
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		    requestDispatcher.forward(request,response);
		
	}

}
