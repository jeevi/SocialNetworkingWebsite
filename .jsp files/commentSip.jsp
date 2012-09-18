<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>comment on the sip post</title>
</head>
<body>
<% 
         String row [][] = null;

         if (request.getSession().getAttribute("commsip") != null) {
             row = (String[][]) request.getSession().getAttribute("commsip");
             
         }
         
%>

<form action = 'commentFinalSip' method = 'post'>
<TABLE BORDER=2  align = 'center'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	
    	out.println("<TR>");
    	

    	for(int j = 0;j < row[0].length; j++){
 		
    		out.println("<TD>" + row[i][j]+ "</TD>");
	   	}
    	
    	out.println("</TR>");
    }
%>
<tr>
<td><input type = 'text' name = 'commentf' ></td>
<tr>
<td><input type = 'submit' name = 'ok ' value = 'OK'></td>
</tr>
</TABLE>
</form>


</body>
</html>