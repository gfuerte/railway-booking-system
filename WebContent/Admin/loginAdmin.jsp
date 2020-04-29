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
Add New User:
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "AddCus" value = "Add New Customer ">
<input type ="submit" name = "AddEmp" value = "Add New Customer Representative">
</form>
<br><br>

<body>
Modify/Delete User:
<br>

<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "ModifyCus" value = "Modify Customer">
<input type ="submit" name = "ModifyRep" value = "Modify Customer Representative">
<input type ="submit" name = "DeleteUser" value = "Delete User">
</form>
<br><br>

<body>
Generate Monthly Sales Report:
<br>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<select name="optionsMonth">
  <option value="0"></option>
  <option value="1">January</option>
  <option value="2">February</option>
  <option value="3">March</option>
  <option value="4">April</option>
  <option value="5">May</option>
  <option value="6">June</option>
  <option value="7">July</option>
  <option value="8">August</option>
  <option value="9">September</option>
  <option value="10">October</option>
  <option value="11">November</option>
  <option value="12">December</option>
</select>
<input type ="submit" name = "getMonthlySales" value = "Go">
</form>
<br><br>


<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
List Reservations By:
<table>
<tr>    
<td>Transit Line:</td><td><input type="text" name="transitLine"></td>
<td>  AND  Train Number:</td><td><input type="text" name="trainNum"></td>
</tr>
</table>
	OR
<br>
<table>
<tr>    
<td>Customer Name:</td><td><input type="text" name="cname"></td>
</tr>
</table>
<input type ="submit" name = "getReservations" value = "Go">
</form>
<br><br>

<body>
Listing of Revenue Per:

<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<select name="optionsRevenue">
  <option value="transitLine">Transit Line</option>
  <option value="destination">Destination City</option>
  <option value="customerUsername">Customer Username</option>
</select>
<input type ="submit" name = "getRevenues" value = "Go">
</form>
<br><br>

<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>