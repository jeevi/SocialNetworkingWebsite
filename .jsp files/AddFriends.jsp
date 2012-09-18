

															APPENDIX B

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add friends to specific circles</title>
</head>
<body>
<% 
         String Circles [] = null;

         if (request.getSession().getAttribute("mycircles") != null) {
             Circles = (String[]) request.getSession().getAttribute("mycircles");
             
         }
         
         
%>


<form action = 'AddToCircle' method = 'post'>
<h2 align = 'center'>Choose a circle and select friends to add to it</h2>
<table align = 'center'>
<% 
		for(int i = 0; i < Circles.length;i++){
			out.println("<tr>");
			out.println("<td><input type = 'radio' name = 'circleid' value = '" + Circles[i]+"'></td>");
			out.println("<td>" + Circles[i] + "</td>");
			out.println("</tr>");
		}
	
%>

<tr>
<td><input type = 'submit' name = 'add to group' value = 'add to group'></td>
</tr>
</table>
</form>
</body>
</html>