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
 * Servlet implementation class almostDone
 */
public class almostDone extends HttpServlet {
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
		
		String SQL = "select DISTINCT A.USERID, B.EMAILID from CSETEAM61.SALES A, CSETEAM61.USERS B  where A.ADVTID = ? and A.USERID = B.USERID";
		String cSQL = "select count(*) from (select DISTINCT A.USERID, B.EMAILID from CSETEAM61.SALES A, CSETEAM61.USERS B  where A.ADVTID = ? and A.USERID = B.USERID)";
		
		String theP = request.getParameter("choose");
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(cSQL);
			ps.setString(1, theP);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			
			String maillist[][] = new String[count][2];
			
			ps = con.prepareStatement(SQL);
			ps.setString(1, theP);
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				
				for(int j = 0;j<2;j++){
					maillist[i][j] = rs.getString(j+1);
				}
				i++;
			}
			
			request.getSession().setAttribute("mlist", maillist);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispMlist.jsp");
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
