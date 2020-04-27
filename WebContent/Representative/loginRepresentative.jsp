<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Representative</title>
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
<input type ="submit" name = "QAR" value = "Questions">
</form>

<form action = "${pageContext.request.contextPath}/QA" method = "post">
<input type ="submit" name = "AlertsR" value = "Alerts">
</form>

<form action = "${pageContext.request.contextPath}/representativeFunctions" method = "post">
<input type ="submit" name = "viewReservationsR" value = "Reservations">
</form>

<form action = "${pageContext.request.contextPath}/representativeFunctions" method = "post">
<input type ="submit" name = "viewSchedulesR" value = "Train Schedules">
</form>

<br><br>
<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>