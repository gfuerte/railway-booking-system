<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Train Schedule</title>
</head>
<body>

<h1 align="center">Add Train Schedule</h1>

<br>
	<div align="center">
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions">
		<table >
			<tr>    
				<td>Origin</td><td><input type="text" name="origin"></td>
			</tr>
			<tr>
				<td>Destination</td><td><input type="text" name="destination"></td>
			</tr>
			<tr>    
				<td>Transit Line</td><td><input type="text" name="transitLine"></td>
			</tr>
			<tr>
				<td>Available Seats</td><td><input type="text" name="availableSeats"></td>
			</tr>
			<tr>    
				<td>Stops</td><td><input type="text" name="stops"></td>
			</tr>
			<tr>    
				<td>Departure Time</td><td><input type="text" name="departureTime"></td>
			</tr>
			<tr>    
				<td>Arrival Time</td><td><input type="text" name="arrivalTime"></td>
			</tr>
			<tr>    
				<td>Travel Time</td><td><input type="text" name="travelTime"></td>
			</tr>
			<tr>    
				<td>Fare</td><td><input type="text" name="fare"></td>
			</tr>
			<tr>    
				<td>Train</td><td><input type="text" name="train"></td>
			</tr>
		</table>
		<br>
		<input type="submit" name = "submitAddScheduleR" value="Add Schedule">
	</form>
	<br><br>
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
			<input type="submit" name = "returnToScheduleViewR" value="Return">
	</form>
	</div>
	
	<br>${message}<br>
	
<br>

</body>
</html>