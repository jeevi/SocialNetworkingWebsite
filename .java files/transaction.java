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
 * Servlet implementation class transaction
 */
public class transaction extends HttpServlet {
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
		
		String cus = request.getParameter("customer");
		String sql1 = "select count(USERID) from CSETEAM61.USERS where USERID = ?";
		String sql2 = "select * from CSETEAM61.SALES where USERID = ? order by ADVTID";
		String sql = "select * from CSETEAM61.SALES where ADVTID in (select ADVTID from CSETEAM61.ADVERTISEMENTS  where ITEMNAME = ?)";
		String sql3 = "select count(*) from (select * from CSETEAM61.SALES where USERID = ? order by ADVTID)";
		String sql4 = "select count(*) from (select * from CSETEAM61.SALES where ADVTID in (select ADVTID from CSETEAM61.ADVERTISEMENTS  where ITEMNAME = ?))";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql1);
			ps.setString(1, cus);
			rs = ps.executeQuery();
			rs.next();
			int num = 0;
			int count = Integer.parseInt(rs.getString(1));
			if(count==1){
				
				ps = con.prepareStatement(sql3);
				ps.setString(1, cus);
				rs = ps.executeQuery();
				rs.next();
				num = Integer.parseInt(rs.getString(1));
				ps = con.prepareStatement(sql2);
				ps.setString(1, cus);
				rs = ps.executeQuery();
							
			}
			else {
				
				ps = con.prepareStatement(sql4);
				ps.setString(1, cus);
				rs = ps.executeQuery();
				rs.next();
				num = Integer.parseInt(rs.getString(1));
				ps = con.prepareStatement(sql);
				ps.setString(1, cus);
				rs = ps.executeQuery();
				
			}
			
			String retValues[][] = new String [num][5];
			int j = 0;
			while(rs.next()){
				

				for(int i = 0;i<5;i++){
					retValues[j][i] = rs.getString(i+1);
				}
				j++;
			}
			
			request.getSession().setAttribute("retvalues", retValues);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispTrans.jsp");
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
