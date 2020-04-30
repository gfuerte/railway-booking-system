<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Train Schedule</title>
</head>
<body>

<h1 align="center">Add Train Schedule</h1>

<div align="center">
	
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
		
		<h3>Select Transit Line</h3>	
			<select name="selectTransitLine">
				<option disabled selected value="">-   Transit Lines   -</option>
				<c:forEach var="i" items="${transitLineList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h3>Select Origin</h3>	
			<select name="selectOrigin">
				<option disabled selected value="">-     Origins     -</option>
				<c:forEach var="i" items="${originList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h3>Select Train</h3>	
			<select name="selectTrain">
				<option disabled selected value="">-     Trains     -</option>
				<c:forEach var="i" items="${trainList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h3>Select Date</h3>
			<input type="date" name="selectDate" value="Date">
		<br>Date MUST be in format YYYY-MM-DD
		<br><br>
		
		<h3>Select Time</h3>
			<input type="time" name="selectTime" value="Time">
		<br>Time MUST be in format HH:MM
		<br><br>
		
		<input type="submit" name="addNewSchedule" value="Confirm">
	</form>
		
	<br><br>
	
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
			<input type="submit" name = "returnToScheduleViewR" value="Return">
	</form>
	<br><br>${message}
	
</div>

</body>
</html>

