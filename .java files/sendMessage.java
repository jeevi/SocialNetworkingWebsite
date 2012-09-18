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

public class sendMessage extends HttpServlet {
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
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = formatter.format(currentDate.getTime());
		
		String user = (String) request.getSession().getAttribute("curr_user");
		String recv = (String) request.getSession().getAttribute("therecv");
		String sub = request.getParameter("subject");
		String content = request.getParameter("content");
		
		String sql1 = "select count(*) from CSETEAM61.UMESSAGES";
		String msginsert = "insert into CSETEAM61.UMESSAGES values (?, ?, ?, ?, ?)";
		String msgrecv = "insert into CSETEAM61.MSGRECEIVER values (?, ?)";
		ResultSet rs = null;
		int success = 0;
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql1);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1)) + 1;
			
			ps = con.prepareStatement(msginsert);
			ps.setString(1, Integer.toString(count));
			ps.setString(2, dateNow);
			ps.setString(3, sub);
			ps.setString(4, content);
			ps.setString(5, user);
			success = ps.executeUpdate();
			
			ps = con.prepareStatement(msgrecv);
			ps.setString(1, Integer.toString(count));
			ps.setString(2, recv);
			success &= ps.executeUpdate();
			
			if(success!=0)
				System.out.println("message successfully sent");
			
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
