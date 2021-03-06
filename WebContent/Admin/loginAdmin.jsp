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

<div align="center">
<h2>Add New User:</h2>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "AddCus" value = "Add New Customer ">
<input type ="submit" name = "AddEmp" value = "Add New Customer Representative">
</form>
<br><br>

<h2>Modify/Delete User:</h2>

<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "ModifyCus" value = "Modify Customer">
<input type ="submit" name = "ModifyRep" value = "Modify Customer Representative">
<input type ="submit" name = "DeleteUser" value = "Delete User">
</form>
<br><br>

<h2>Generate Monthly Sales Report:</h2>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<select name="optionsMonth">
  <option value="none"></option>
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
<br><br>${message1}
<br><br>

<h2>List Reservations By:</h2>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<h3>Train Information</h3>
<table>
<tr>    
<td>Transit Line:</td><td><input type="text" name="transitLine"></td>
</tr>
</table>
<table>
<tr>
<td>Train Number:</td><td><input type="text" name="trainNum"></td>
</tr>
</table>
<input type ="submit" name = "getReservationsByTrain" value = "Go">
<br><br>${message2}
<br>
<h3>Customer Information</h3>
<table>
<tr>    
<td>Customer Username:</td><td><input type="text" name="cname"></td>
</tr>
</table>
<input type ="submit" name = "getReservationsByCus" value = "Go">
</form>
<br><br>${message3}
<br><br>

<h2>Listing of Revenue Per:</h2>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<select name="optionsRevenue">
  <option value="none"></option>
  <option value="transitLine">Transit Line</option>
  <option value="destination">Destination City</option>
  <option value="customerUsername">Customer Username</option>
</select>
<input type ="submit" name = "getRevenues" value = "Go">
</form>
<br><br>${message4}
<br><br>

<h2>Other Actions:</h2>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "bestCus" value = "Best Customer">
<input type ="submit" name = "mostActive" value = "Top 5 Active Transit Lines">
</form>
<br><br>

<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="Logout">
</form>
</div>
</html>