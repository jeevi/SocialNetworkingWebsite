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
 * Servlet implementation class listSoldItems
 */
public class listSoldItems extends HttpServlet {
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
		
		String sql = "SELECT B.COMPANY, B.ITEMNAME, A.NOOFUNITSOLD FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID ORDER BY A.NOOFUNITSOLD";
		String sql1 = "select count(*) from (SELECT B.COMPANY, B.ITEMNAME, A.NOOFUNITSOLD FROM CSETEAM61.SALES A, CSETEAM61.ADVERTISEMENTS B WHERE A.ADVTID = B.ADVTID ORDER BY A.NOOFUNITSOLD)";
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql1);
			rs = ps.executeQuery();
			rs.next();
			int count = Integer.parseInt(rs.getString(1));
			
			String advtval[][] = new String [count][3];
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int j = 0;
			while(rs.next()){
				
				for(int i = 0; i <3;i++){
					
					advtval[j][i] = rs.getString(i+1);
				}
				j++;
			}
			
			request.getSession().setAttribute("soldvaluescom", advtval);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dispSoldcom.jsp");
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
