<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>finish adding members</title>
</head>
<body>

<% 
         String row [] = null;

         if (request.getSession().getAttribute("memreqs") != null) {
             row = (String[]) request.getSession().getAttribute("memreqs");
             
         }
         
%>
<form action="FinalReqs" method = "post" >
<TABLE BORDER=2  align = 'center'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	if(row[i]==null)
    		break;
        
        out.println("<TR>");
        out.println("<TD>" + row[i]+ "</TD>");
        out.println("<TD> <input type = 'checkbox' name = 'newreqsip' value = '"+ row[i]+"'> </TD>");
        out.println("</TR>");
       
    }
%>
<tr>
<TD> <input type = 'submit' name = 'finish' value = 'finish' > </TD>
</tr>
</TABLE>
</form>

</body>
</html>