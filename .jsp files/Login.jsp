<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KoNNecT</title>
</head>
<body style="background-color:white;">
<form action="LoginServlet" method="post">

<h2 align = 'right'> Login Form</h2>
<table align = "right">

<tr>
<td>User ID : </td>
<td><input type="text" name="username" size = '20'></td>
</tr>
<tr>
<td>Password :</td>
<td><input type="password" name="password" size = '20'></td>
</tr>
<tr>
<td><input type="submit" value = "Login"/></td>
<td><input type="button" value="Sign up!" onClick="location.href='newUser.jsp';"></td>
</tr>
</table>
</form>

</body>
</html>