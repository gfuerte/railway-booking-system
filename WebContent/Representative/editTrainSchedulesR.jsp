<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>Edit Train Schedule</title>
</head>
<body>

<h1 align="center">Edit Train Schedule</h1>

<div align="center">
	
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
		
		<h3>Select Train to Edit a Scheduled Route</h3>	
			<select name="selectTrain">
				<option disabled selected value="">-     Trains     -</option>
				<c:forEach var="i" items="${trainList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h4>Edits to be Made to Train Selected Above</h4>
			<table>
			<tr>
				<th>Delay Train Route (Minutes)</th>
				<td><input type="text" name="minDelay" placeholder="Minutes Delayed">
			</tr>
			<tr>
				<th>Mark Train Route Early (Minutes)</th>
				<td><input type="text" name="minEarly" placeholder="Minutes Early">
			</tr>
			<tr>
				<th>Add Discount: Reduce Prices</th>
				<td><input type="text" name="discountPrice" placeholder="Price Discount">
			</tr>
			<tr>
				<th>Raise Route Ticket Prices</th>
				<td><input type="text" name="raisedPrice" placeholder="Price Increase">
			</tr>
			<tr>
				<th>Assign New Train to Route</th>
				<td>
					<select name="selectNewTrain">
						<option disabled selected value="">-     Trains     -</option>
						<c:forEach var="i" items="${newTrainList}">
							<option><c:out value="${i}" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</table>
		
		<br><br>
		<input type="submit" name="editTrainSchedule" value="Edit">
	</form>
	
	<br>
	
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
			<input type="submit" name = "returnToScheduleViewR" value="Return">
	</form>
	
	<br><br><br>${message}
	
</div>

</body>
</html>

