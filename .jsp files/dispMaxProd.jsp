<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>max revenue by product</title>
</head>
<body>
<% 
         String row [] = null;

         if (request.getSession().getAttribute("dispmaxrevp") != null) {
             row = (String[]) request.getSession().getAttribute("dispmaxrevp");
             
         }
         
%>
<TABLE BORDER=2 align = 'center'>
<%
        
        out.println("<TR>");
        out.println("<TD>" + row[0]+ "</TD>");
        out.println("<TD>" + row[1]+ "</TD>");
        out.println("</TR>");
       
%>
<tr>
<td><input type = 'submit' name = 'go home ' value = 'go home' onClick="location.href='admin.jsp';"></td>
</tr>

</TABLE>

</body>
</html>