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
<br>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "ModifyInfo" value = "Modify User Information">
</form>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "MonthSales" value = "Monthly Sales">
</form>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "Reservations" value = "Reservations">
</form>
<form action = "${pageContext.request.contextPath}/adminFunctions" method = "post">
<input type ="submit" name = "Revenue" value = "Revenue">
</form>
<br><br>
<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>