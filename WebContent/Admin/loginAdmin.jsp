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
<table>
<tr>    
<td>Month:</td><td><input type="text" name="month"></td>
</tr>
</table>
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
<select id="optionsRevenue">
  <option value="tl">Transit Line</option>
  <option value="dc">Destination City</option>
  <option value="cn2">Customer Name</option>
</select>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "getRevenues" value = "Go">
</form>
<br><br>

<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>