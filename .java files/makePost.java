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
 * Servlet implementation class makePost
 */
public class makePost extends HttpServlet {
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
		
		String user = (String) request.getSession().getAttribute("curr_user");
		String monamis[] = (String[]) request.getSession().getAttribute("friendList");
		String content = request.getParameter("content");
		
		String pageid = "select PAGEID from CSETEAM61.USERTOPAGE where USERID = ?";
		String insQ = "insert into CSETEAM61.POSTS values (? , ?, ? ,?, ?)";
		String amiwall = "insert into CSETEAM61.WALL values (? , ?, ? ,? )";
		String cinsQ = "select max(POSTID) from CSETEAM61.POSTS";
		String userfr = "select USERID from CSETEAM61.USERS where FIRSTNAME = ? and LASTNAME = ?";
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = formatter.format(currentDate.getTime());
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(pageid);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			
			String temp = rs.getString(1);
			
			ps = con.prepareStatement(cinsQ);
			rs = ps.executeQuery();
			rs.next();
			int count;
			
			if(rs.getString(1)==(null))
				count = 1;
			else
				count = Integer.parseInt(rs.getString(1)) + 1;
			
			ps = con.prepareStatement(insQ);
			ps.setString(1, Integer.toString(count));
			ps.setString(2, dateNow);
			ps.setString(3, content);
			ps.setString(4, user);
			ps.setString(5, temp);
			
			int success = ps.executeUpdate();
						
			for(int i = 0; i<monamis.length;i++){
				
				ps = con.prepareStatement(userfr);
				ps.setString(1, monamis[i].split(" ")[0]);
				ps.setString(2, monamis[i].split(" ")[1]);
				rs = ps.executeQuery();
				rs.next();
				String friend = rs.getString(1);
				
				ps = con.prepareStatement(amiwall);
				ps.setString(1, Integer.toString(count));
				ps.setString(2, friend);
				ps.setString(3, dateNow);
				ps.setString(4, content);
				success &= ps.executeUpdate();
				
			}
			
			
			ps = con.prepareStatement(amiwall);
			ps.setString(1, Integer.toString(count));
			ps.setString(2, user);
			ps.setString(3, dateNow);
			ps.setString(4, content);
			success &= ps.executeUpdate();
			
			if(success!=0)
				System.out.println("post now visible to you and your friends");
			
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
