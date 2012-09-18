<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
         String row [] = null;

         if (request.getSession().getAttribute("membersO") != null) {
             row = (String[]) request.getSession().getAttribute("membersO");
             
         }
         
%>
<form action="FINAL" method = 'post'>
<TABLE BORDER=2  align = 'center'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	
    	out.println("<TR>");
    	out.println("<td><input type = 'checkbox' name = 'todeletef' value = '" + row[i]+ "'></td>");
    	out.println("<td>" + row[i] + "</td");
    	out.println("</TR>");
    }
%>
<tr>
<td><input type = 'submit' name = 'delete' value = 'delete'>

</TABLE>
</form>

</body>
</html>