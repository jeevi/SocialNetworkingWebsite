<%@ page language="java" contentType="text/html; chsarset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>choose Sip</title>
</head>
<body>
<% 
         String row [][] = null;

         if (request.getSession().getAttribute("mysiplist") != null) {
             row = (String[][]) request.getSession().getAttribute("mysiplist");
             
         }
         
%>
<form action="dispSipWall" method = "post" >
<TABLE BORDER=2  align = 'center'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	
    	out.println("<TR>");
    	out.println("<TD> <input type = 'radio' name = 'sip' value = '"+ row[i][0]+"'> </TD>");
    	
    	for(int j = 0;j<row[0].length;j++){
        	
    		out.println("<TD>" + row[i][j]+ "</TD>");
        
    	}
        out.println("</TR>");
       
    }
%>
<tr>
<TD> <input type = 'submit' name = 'ok' value = 'ok' > </TD>
</tr>
</TABLE>
</form>

</body>
</html>