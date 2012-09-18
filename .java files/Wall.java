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
 * Servlet implementation class Wall
 */
public class Wall extends HttpServlet {
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
		String sql = "select C.POSTID, C.WALLOWNER, C.DATENTIME, C.CONTENT, A.POSTAUTHOR from CSETEAM61.POSTS A, CSETEAM61.USERTOPAGE B, CSETEAM61.WALL C where  A.POSTID = C.POSTID and C.WALLOWNER = ? and B.USERID = ?";
		String csql = "select count(*) from (select C.POSTID, C.WALLOWNER, C.DATENTIME, C.CONTENT, A.POSTAUTHOR from CSETEAM61.POSTS A, CSETEAM61.USERTOPAGE B, CSETEAM61.WALL C where  A.POSTID = C.POSTID and C.WALLOWNER = ? and B.USERID = ?)";
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(csql);
			ps.setString(1, user);
			ps.setString(2, user);
			rs = ps.executeQuery();
			rs.next();
			
			int count = Integer.parseInt(rs.getString(1));
			String posts[][] = new String[count][5];
			
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, user);
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				
				for(int j = 0; j<5; j++){
					
					posts[i][j] = rs.getString(j+1);
				}
				
				i++;
			}
			
			request.getSession().setAttribute("posts", posts);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/seePosts.jsp");
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
