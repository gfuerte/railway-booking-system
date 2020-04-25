<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Customer</title>
<style>
form {
	display: inline-block; //Or display: inline; 
}
</style>
</head>
<body>
Welcome ${sessionScope.Name}! Successful Login of Level ${sessionScope.Level}
<br>
<form action = "${pageContext.request.contextPath}/QA" method = "post">
<input type ="submit" name = "QA" value = "Questions">
</form>
<form action = "${pageContext.request.contextPath}/QA" method = "post">
<input type ="submit" name = "Alerts" value = "Alerts">
</form>
<br><br>
<form action = "${pageContext.request.contextPath}/search" method = "post">
<input type ="submit" name = "Schedules" value = "See Schedules">
</form>
<br><br>
<form action = "${pageContext.request.contextPath}/createReservations" method = "post">
<input type ="submit" name = "Reservations" value = "Create Reservations">
</form>
<form>
<input type="submit" value="Current/Past Reservations">
</form>
<br><br>
<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>