<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete advertisements</title>
</head>
<body>
<% 
         String row [] = null;
		 String row1 [] = null;
         if (request.getSession().getAttribute("COM") != null) {
             row = (String[]) request.getSession().getAttribute("COM");
             row1 = (String[]) request.getSession().getAttribute("ITEM");
             
         }
         
%>

<form action="finalDel" method = "post" >
<TABLE BORDER=2  align = 'center'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	if(row[i]==null)
    		break;
        
        out.println("<TR>");
        out.println("<TD>" + row[i]+ "</TD>");
        out.println("<TD>" + row1[i]+ "</TD>");
        out.println("<TD> <input type = 'checkbox' name = 'deladvts' value = '" + row[i]+ " "+ row1[i] + "'> </TD>");
        out.println("</TR>");
       
    }
%>

<tr>
<TD> <input type = 'submit' name = 'delete' value = 'delete' > </TD>
<tr>
</TABLE>
</form>
</body>
</html>