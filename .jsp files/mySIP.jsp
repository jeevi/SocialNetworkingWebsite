<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Special Interest Pages</title>
</head>
<body>
<form action='createSIP' method = 'post'>
<h2 align = 'left'>SIP</h2>
<table align = 'left'>
<tr>
<td><input type = 'text' name = 'sipname'>SIP Name</td>
</tr>
<tr>
<td><input type = 'text' name = 'sipid'>SIP ID</td>
</tr>
<tr>
<td><input type = 'submit' name = 'Create' value = 'Create'></td>
</tr>
</table>
</form>

<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'Invite' value = 'Invite' onClick="location.href='InviteFriends.jsp';"></td>
</tr>
<tr>
<td><input type = 'submit' name = 'Search' value = 'Search' onClick="location.href='searchSIP.jsp';"></td>
</tr>
</table>

<form action = 'manageSipInvite' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'manage invites' value = 'manage invites'>
</table>
</form>

<form action = 'manageModerator' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'manage moderators' value = 'delete moderators'>
</table>
</form>

<form action = 'manageModerator' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'manage moderators' value = 'add moderators'>
</table>
</form>

<form action = 'manageRequests' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'manage requests' value = 'manage requests'>
</table>
</form>

<form action = 'sipWall' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'submit' name = 'Wall' value = 'Wall'>
</table>
</form>

</body>
</html>


