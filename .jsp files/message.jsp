<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Messages</title>
</head>
<body>

<% 
		String id[] = null;
         String date [] = null;
		 String sub [] = null;
		 String con [] = null;
		 String uid [] = null;

         if (request.getSession().getAttribute("date") != null) {
             date = (String[]) request.getSession().getAttribute("date");
             
         }
         if (request.getSession().getAttribute("sub") != null) {
             sub = (String[]) request.getSession().getAttribute("sub");
             
         }
         if (request.getSession().getAttribute("con") != null) {
             con = (String[]) request.getSession().getAttribute("con");
             
         }
         if (request.getSession().getAttribute("uid") != null) {
             uid = (String[]) request.getSession().getAttribute("uid");
             
         }
         if (request.getSession().getAttribute("mid") != null) {
             id = (String[]) request.getSession().getAttribute("mid");
             
         }
         
%>
<form action="delMsgs" method = 'post'>
<table border = 2 align = 'center'>
<%

		for(int i = 0; i< date.length;i++){
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<input type = 'checkbox' value = '" + id[i] + "' name = 'delmsgs'");
			out.println("</td>");
			out.println("<td>");
			out.println(id[i]);
			out.println("</td>");
			out.println("<td>");
			out.println(date[i]);
			out.println("</td>");
			out.println("<td>");
			out.println(sub[i]);
			out.println("</td>");
			out.println("<td>");
			out.println(con[i]);
			out.println("</td>");
			out.println("<td>");
			out.println(uid[i]);
			out.println("</td>");
			out.println("</tr>");
			
			
		}


%>

<tr>

<td><input type = 'submit' value = 'delete' name = 'delete' ></td>
</tr>
</table>
</form>
<input type = 'submit' value = 'go home' name = 'go home' onClick="location.href='home.jsp';">
</body>
</html>