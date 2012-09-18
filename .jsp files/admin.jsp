<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator</title>
</head>
<body>
<form action = 'createAdvt' method = 'post'>
<table align = 'left'>
<tr>
<td><input type = 'text' name = 'company'>company</td>
</tr>

<tr>
<td><input type = 'text' name = 'itemname'>item name</td>
</tr>

<tr>
<td><input type = 'text' name = 'unitprice'>unit price</td>
</tr>
<tr>
<td><input type = 'text' name = 'availableunits'>no. of units</td>
</tr>
<tr>
<td><input type="radio" value = 'life insurance' name = 'pref'/> Life Insurance</td>
<td><input type="radio" value = 'cars' name = 'pref'/> Cars </td>
<td><input type="radio" value = 'clothing' name = 'pref'/> Clothing </td>
<td><input type="radio" value = 'toys' name = 'pref'/> Toys </td>
</tr>
<tr>
<td><input type = 'submit' name = 'create advertisement' value = 'create advertisement'></td>
</tr>
</table>
</form>
<form action = 'salesRep' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'text' name = 'month' size = '3'></td>
<td><input type = 'submit' name = 'sales report' value = 'sales report'>
</td>
</tr>
</table>
</form>
<form action = 'transaction' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'text' name = 'customer' ></td>
<td><input type = 'submit' name = 'list transactions' value = 'list transactions'>
</td></tr>
</table>
</form>
<form action = 'revenue' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'text' name = 'listrevenue' ></td>
<td><input type = 'submit' name = 'list revenue' value = 'list revenue'>
</td>
</tr>
</table>
</form>
<form action = 'purchaseItem' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'text' name = 'itempurname' ></td>
<td><input type = 'submit' name = 'list customers' value = 'list customers'></td>
</tr>
</table>
</form>
<form action = 'listSuggestion' method = 'post'>
<table align = 'right'>
<tr>
<td><input type = 'text' name = 'userName' ></td>
<td><input type = 'submit' name = 'list suggestions' value = 'list suggestions'></td>
</tr>
</table>
</form>

<br>
<br><br>
<form action = 'customerMail' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'mailing list' value = 'mailing list'></td>
</tr>
</table>
</form>
<form action = 'delAdvt' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'delete advertisements' value = 'delete advertisements'>
</td></tr>
</table>
</form>
<form action = 'listAdvt' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'list items' value = 'list items'></td>
</tr>
</table>
</form>
<form action = 'listSoldItems' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'list sold items' value = 'list sold items'></td>
</tr>
</table>
</form>
<form action = 'mostRevenue' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'max revenue customer' value = 'max revenue customer' ></td>
</tr>
</table>
</form>
<form action = 'mostRevenueProd' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'max revenue product' value = 'max revenue product' ></td>
</tr>
</table>
</form>

<form action = 'listForCompany' method = 'post'>
<table align = 'center'>
<tr>
<td><input type = 'submit' name = 'list advertisements' value = 'list advertisements'>
</td></tr>
</table>
</form>

</body>
</html>