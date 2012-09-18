<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create your circle</title>
</head>
<body>
<form action='Circle' method = 'post'>
<h2 align = 'left'>Circle</h2>
<table align = 'left'>
<tr>
<td><input type = 'text' name = 'circlename'>Circle Name</td>
</tr>
<tr>
<td><input type = 'text' name = 'circleid'>Circle ID</td>
</tr>
<tr>
<td><input type = 'submit' name = 'Create' value = 'Create'></td>
</tr>
</table>
</form>

<form action = 'AddFriends' method = 'post'>

<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'Add Friends' value = 'Add Friends'></td>
</tr>
</table>
</form>

<form action = 'delCircle' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'delete circles' value = 'delete circles'></td>
</tr>
</table>
</form>

<form action = 'delFriendO' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'delete friends' value = 'delete friends'></td>
</tr>
</table>
</form>


</body>
</html>