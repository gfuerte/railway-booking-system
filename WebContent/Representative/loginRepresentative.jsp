<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<div align="center">

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
	
	<form action = "${pageContext.request.contextPath}/representativeFunctions" method = "post">
	<h3>List Customer Reservations By Train Information</h3>
	<table>
	<tr>    
	<td>Transit Line:</td>
	<td>
		<input type="text" name="transitLine">
		<%-- <select name="transitLine">
			<option disabled selected value="">-     Transit Line     -</option>
			<c:forEach var="i" items="${tlList}">
				<option><c:out value="${i}" /></option>
			</c:forEach>
		</select> --%>
	</td>
	</tr>
	</table>
	<table>
	<tr>
	<td>Train Number:</td>
	<td>
		<input type="text" name="trainNum">
		<%-- <select name="trainNum">
			<option disabled selected value="">-     Train Number     -</option>
			<c:forEach var="i" items="${tnList}">
				<option><c:out value="${i}" /></option>
			</c:forEach>
		</select> --%>
	</td>
	</tr>
	</table>
	<input type ="submit" name = "getReservationsByTrain" value = "Go">
	<br><br>${message}
	</form>
	
	<br><br>
	
	<h3>List Schedule By:</h3>
	<form action = "${pageContext.request.contextPath}/representativeFunctions" method = "post">
	<h4>Origin and Destination</h4>
	<table>
	<tr>    
	<td>Origin:</td>
	<td>
		<input type="text" name="origin">
		<%-- <select name="origin">
			<option disabled selected value="">-     Origin     -</option>
			<c:forEach var="i" items="${oList}">
				<option><c:out value="${i}" /></option>
			</c:forEach>
		</select>  --%>
	</td>
	</tr>
	</table>
	<table>
	<tr>
	<td>Destination:</td>
	<td>
		<input type="text" name="destination">
		<%-- <select name="destination">
			<option disabled selected value="">-     Destination     -</option>
			<c:forEach var="i" items="${dList}">
				<option><c:out value="${i}" /></option>
			</c:forEach>
		</select>  --%>
	</td>
	</tr>
	</table>
	<input type ="submit" name = "getSchedule" value = "Go">
	<br><br>${message2}
	<br>
	<h4>Station</h4>
	<table>
	<tr>    
	<td>Station:</td>
	<td>
		<input type="text" name="station">
		<%-- <select name="station">
			<option disabled selected value="">-     Station     -</option>
			<c:forEach var="i" items="${sList}">
				<option><c:out value="${i}" /></option>
			</c:forEach>
		</select> --%>
	</td>
	</tr>
	</table>
	<input type ="submit" name = "getScheduleByStation" value = "Go">
	</form>
	<br><br>${message3}
	<br><br>

	<form method="get" action="${pageContext.request.contextPath}/redirect">
	<input type="submit" name = "logoutButton" value="Logout">
	</form>

</div>
</body>
</html>