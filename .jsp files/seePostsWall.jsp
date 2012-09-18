<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>see sip posts</title>
</head>
<body>
<% 
         String row [][] = null;

         if (request.getSession().getAttribute("siposts") != null) {
             row = (String[][]) request.getSession().getAttribute("siposts");
             
         }
         
%>

<form action = 'commentPostSip' method = 'post'>
<TABLE BORDER=2  align = 'center'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	
    	out.println("<TR>");
    	out.println("<td><input type = 'radio' name = 'comment' value = '" + row[i][0] + "'></td>");

    	for(int j = 0;j < row[0].length; j++){
 		
    		out.println("<TD>" + row[i][j]+ "</TD>");
	   	}
    	
    	out.println("</TR>");
    }
%>
<tr>
<td><input type = 'submit' name = 'comment sip' value = 'comment sip'></td>
</tr>

</TABLE>
</form>

<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'delPost' value = 'delPost' onClick = "location.href = 'deletePostSip.jsp';"></td>
</tr>
</table>

<form action="makePostSip" method= 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td> <input type = 'text' name = 'content'> content</td>
</tr>
<tr>
<td> <input type = 'submit' name = 'post' value = 'post'></td>
</tr>
</TABLE>
</form>

</body>
</html>