<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KoNNecT</title>
</head>
<body style="background-color:white;">

<form action="SignUp"  method="post">
<h2 align = 'center'>Sign up form</h2>
<table align = 'right'>
<tr>
<td>User ID: </td>
<td><input type="text" name="username"></td>
</tr>
<tr>
<td>First Name: </td>
<td><input type="text" name="firstname"></td>
</tr>
<tr>
<td>Last Name: </td>
<td><input type="text" name="lastname"></td>
</tr>
<tr>
<td>Email ID: </td>
<td><input type="text" name="email"></td>
</tr>
<tr>
<td>Date of Birth: </td>
<td><input type="text" name="dob">yyyy-mm-dd</td>
</tr>
<tr>
<th>Sex: </th>
<td><input type="radio" value= "M" name = 'sex'/> Male </td>
<td><input type="radio" value = "F" name = 'sex'/> Female</td>
</tr>
<tr>
<td>Address: </td>
<td><input type="text" name="addr"> <br></br></td>
</tr>
<tr>
<td>City: </td>
<td><input type="text" name="city"> <br></br></td>
</tr>
<tr>
<td>State: </td>
<td><input type="text" name="state"> <br></br></td>
</tr>
<tr>
<td>Zip Code: </td>
<td><input type="text" name="zip"> <br></br></td>
</tr>
<tr>
<td>Telephone no: </td>
<td><input type="text" name="telephone"> < 10-digits <br></br></td>
</tr>
<tr>
<td><input type="checkbox" value = 'life insurance' name = 'pref'/> Life Insurance</td>
<td><input type="checkbox" value = 'cars' name = 'pref'/> Cars </td>
<td><input type="checkbox" value = 'clothing' name = 'pref'/> Clothing </td>
<td><input type="checkbox" value = 'toys' name = 'pref'/> Toys </td>
</tr>
<tr>
<td>Password </td>
<td><input type="password" name="password"> <br></br></td>
</tr>
<tr>
<td><input type="submit" value = "Done"/><br></br></td>
</tr>
</table>
</form>
</body>
</html>