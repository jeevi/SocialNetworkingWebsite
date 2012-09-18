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
 * Servlet implementation class ViewMessages
 */
public class ViewMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://db2serv01.cs.stonybrook.edu:50000/teamdb61:retrieveMessagesFromServerOnGetMessage=true;", "cseteam61", "Balawat61");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String user = (String) request.getSession().getAttribute("curr_user");
		
		String sql1 = "select count(*) from (select MESSAGEID, DATENTIME, SUBJECT, CONTENT, SENDER from CSETEAM61.UMESSAGES where MESSAGEID in (select MESSAGEID from CSETEAM61.MSGRECEIVER where RECEIVER = ?))";
		String sql = "select MESSAGEID, DATENTIME, SUBJECT, CONTENT, SENDER from CSETEAM61.UMESSAGES where MESSAGEID in (select MESSAGEID from CSETEAM61.MSGRECEIVER where RECEIVER = ?)";
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs.next();
			
			int size = Integer.parseInt(rs.getString(1));
			
			String[] id = new String[size];
			String[] date = new String[size];
			String[] subject = new String[size];
			String[] content = new String[size];
			String[] senderid = new String[size];
			
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				
				id[i] = rs.getString(1);
			
				date[i] = rs.getString(2);
			
				subject[i] = rs.getString(3);
			
				content[i] = rs.getString(4);
			
				senderid[i] = rs.getString(5);
			
				i++;
			}
			
			request.getSession().setAttribute("mid", id);
			request.getSession().setAttribute("date", date);
			request.getSession().setAttribute("sub", subject);
			request.getSession().setAttribute("con", content);
			request.getSession().setAttribute("uid", senderid);
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/message.jsp");
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
