<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.*"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<% 
         String row [] = null;

         if (request.getSession().getAttribute("friendList") != null) {
             row = (String[]) request.getSession().getAttribute("friendList");
             
         }
         
%>
<% 
         String frow [] = null;

         if (request.getSession().getAttribute("requestList") != null) {
             frow = (String[]) request.getSession().getAttribute("requestList");
             
         }
         
%>

<form action="sendMessageMod" method = "post" >
<TABLE BORDER=2  align = 'right'>

<%
    for ( int i = 0; i < row.length; i++ ) {
    	if(row[i]==null)
    		break;
        
        out.println("<TR>");
        out.println("<TD> <input type = 'radio' name = 'msg' value = '"+ row[i]+"'> </TD>");
        out.println("<TD>" + row[i]+ "</TD>");
        out.println("</TR>");
       
    }
%>

<tr>
<TD> <input type = 'submit' name = 'message' value = 'message' > </TD>
</tr>
</TABLE>
</form>

<form action="friendRequest" method = 'post'>
<TABLE BORDER=2 align = 'left'>
<%
    for ( int i = 0; i < frow.length; i++ ) {
    	if(frow[i]==null)
    		break;
        
        out.println("<TR>");
        out.println("<TD>" + frow[i]+ "</TD>");
        out.println("<TD> <input type = 'checkbox' name = 'decision' value = '"+ frow[i]+"'> </TD>");
        out.println("<TD> <input type = 'submit' name = 'Go!' value = 'Go!' > </TD>");
        out.println("</TR>");
       
    }
%>
</TABLE>
</form>

<form action="searchFriend" method = 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td> <input type = 'text' name = 'friendsearch'> ENTER NAME:</td>
<td> <input type = 'submit' name = 'Search' value = 'Search'></td>
</tr>
</TABLE>
</form>

<TABLE BORDER=2 align = 'center'>
<tr>
<td><input type = 'submit' name = 'Circles' value = 'Circles' onClick = "location.href='circle.jsp';"></td>
</tr>
</TABLE>

<form action="ViewMessages" method = 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td><input type = 'submit' name = 'view messages' value = 'view messages'></td>
</tr>
</TABLE>
</form>

<form action="Wall" method = 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td><input type = 'submit' name = 'go to wall' value = 'wall'></td>
</tr>
</TABLE>
</form>

<TABLE BORDER=2 align = 'center'>
<tr>
<td><input type = 'submit' name = 'SIP' value = 'SIP' onClick="location.href='mySIP.jsp';"></td>
</tr>
</TABLE>
<form action="BuyStuff" method = 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td><input type = 'submit' name = 'Buy Stuff' value = 'Buy Stuff'></td>
</tr>
</TABLE>
</form>
<form action="delFriend" method = 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td><input type = 'submit' name = 'delete friend' value = 'delete friend'></td>
</tr>
</TABLE>
</form>

</body>
</html>