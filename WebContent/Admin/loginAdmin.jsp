<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Admin</title>
<style>
form {
	display: inline-block; //Or display: inline; 
}
</style>
</head>
<body>
Welcome ${sessionScope.Name}! Successful Login of Level ${sessionScope.Level}
<br><br>

<body>
Modify User Information:
<br>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<table>
<tr>    
<td>Type in the username of the user you wish to modify:</td><td><input type="text" name="month"></td>
</tr>
</table>
<input type ="submit" name = "ModifyCusInfo" value = "Modify Customer Information">
<input type ="submit" name = "ModifyEmpInfo" value = "Modify Employee Information">
</form>
<br><br>

<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<table>
<tr>    
<td>Monthly Sale Report for:</td><td><input type="text" name="month"></td>
</tr>
</table>
<input type ="submit" name = "getMonthlySales" value = "Get Monthly Sales">
</form>
<br><br>

<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<table>
<tr>    
<td>List Reservations By:</td><td><input type="text" name="info1"></td>
</tr>
</table>
<input type ="submit" name = "getReservationListT" value = "Transit Line and Train Number">
<input type ="submit" name = "getReservationListCn" value = "Customer Name">
</form>
<br><br>

<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<table>
<tr>    
<td>List Revenue By:</td><td><input type="text" name="info2"></td>
</tr>
</table>
<input type ="submit" name = "getRevenueListTl" value = "Transit Line">
<input type ="submit" name = "getRevenueListDc" value = "Destination City">
<input type ="submit" name = "getRevenueListCn" value = "CustomerName">
</form>
<br><br>

<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>