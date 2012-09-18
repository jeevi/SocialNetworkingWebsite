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
 * Servlet implementation class revenue
 */
public class revenue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sql = "SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ITEMNAME = ?";
		String sql1 = "SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ADVTTYPE = ?";
		String sql2 = "SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND A.USERID = ?";
		String checkip = "select COUNT(USERID) from CSETEAM61.USERS WHERE USERID = ?";
		String checkip1 = "SELECT COUNT(DISTINCT ITEMNAME) FROM CSETEAM61.ADVERTISEMENTS WHERE ITEMNAME = ?";
		String checkip2 = "SELECT COUNT(DISTINCT ADVTTYPE) FROM CSETEAM61.ADVERTISEMENTS WHERE ADVTTYPE = ?";
		String csql = "SELECT COUNT(*) FROM (SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ITEMNAME = ?)";
		String csql1 = "SELECT COUNT(*) FROM (SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND B.ADVTTYPE = ?)";
		String csql2 = "SELECT COUNT(*) FROM (SELECT A.DATENTIME, A.NOOFUNITSOLD, B.UNITPRICE FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID AND A.USERID = ?)";
		
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://db2serv01.cs.stonybrook.edu:50000/teamdb61:retrieveMessagesFromServerOnGetMessage=true;", "cseteam61", "Balawat61");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String input = request.getParameter("listrevenue");
		
		checking(input, con, checkip, csql2, sql2, request, response);
		checking(input, con, checkip1, csql, sql, request, response);
		checking(input, con, checkip2, csql1, sql1, request, response);
		
	}
	
	public void doNext(ResultSet rs, int num, HttpServletRequest request, HttpServletResponse response, String input){
		
		String retvalues[][] = new String [num][3];
		int j = 0;
		try {
			while(rs.next()){
				
				for(int i = 0;i<3;i++){
					retvalues[j][i] = rs.getString(i+1);
				}
				j++;
			}
			
			request.getSession().setAttribute("retvalues", retvalues);
			request.getSession().setAttribute("retinput", input);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/disprevenue.jsp");
		    requestDispatcher.forward(request,response);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checking(String input, Connection con, String checkip, String csql, String sql, HttpServletRequest request, HttpServletResponse response){
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		int count = 0;
		int num = 0;
		try {
			ps = con.prepareStatement(checkip);
			ps.setString(1, input);
			rs = ps.executeQuery();
			rs.next();
			
			count = Integer.parseInt(rs.getString(1));
			if(count>0){
				ps = con.prepareStatement(csql);
				ps.setString(1, input);
				rs = ps.executeQuery();
				rs.next();
				num = Integer.parseInt(rs.getString(1));
				ps = con.prepareStatement(sql);
				ps.setString(1, input);
				rs = ps.executeQuery();
				doNext(rs, num, request, response, input);
			}
			
		}
		 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
}