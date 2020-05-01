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
<div align="center">"

	<h2>Welcome ${sessionScope.Name}! Successful Login of Level ${sessionScope.Level}</h2>
	<br>

	<form action = "${pageContext.request.contextPath}/QA" method = "post">
	<input type ="submit" name = "QAR" value = "View Questions">
	</form>

	<br><br>

	<form action = "${pageContext.request.contextPath}/QA" method = "post">
	<input type ="submit" name = "AlertsR" value = "View Alerts">
	</form>

	<br><br>

	<form action = "${pageContext.request.contextPath}/representativeFunctions" method = "post">
	<input type ="submit" name = "viewReservationsR" value = "View Reservations">
	</form>

	<br><br>

	<form action = "${pageContext.request.contextPath}/representativeFunctions" method = "post">
	<input type ="submit" name = "viewSchedulesR" value = "View Train Schedules">
	</form>

	<br><br>

	<form method="get" action="${pageContext.request.contextPath}/redirect">
	<input type="submit" name = "logoutButton" value="Logout">
	</form>

</div>
</body>
</html>