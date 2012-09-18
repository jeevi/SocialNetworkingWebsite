<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>sending message</title>
</head>
<body>

<%
	String receiver = null;

	if (request.getSession().getAttribute("therecv") != null) {
    	receiver = (String) request.getSession().getAttribute("therecv");
    
	}

%>

<form action="sendMessage" method = 'post'>
<TABLE BORDER=2 align = 'center'>
<tr>
<td> <input type = 'text' name = 'subject'> subject</td>
</tr>
<tr>
<td> <input type = 'text' name = 'content'> content</td>
</tr>
<tr>
<td> <input type = 'submit' name = 'send' value = 'send'></td>
</tr>
</TABLE>
</form>

</body>
</html>