<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>Edit Reservation</title>
</head>
<body>

<h1 align="center">Edit Reservation</h1>

<div align="center">
	
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
		
		<h3>Select Reservation to Edit</h3>	
			<select name="selectRes">
				<option disabled selected value="">-     Reservation     -</option>
				<c:forEach var="i" items="${resList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h4>Edits to be made to Reservation Above</h4>
			<table>
			<tr>
				<th>Change fee</th>
				<td><input type="text" name="fee" placeholder="Fee"></td>
			</tr>
			<tr>
				<th>Change class</th>
				<td>
					<select name="selectClass">
					<option disabled selected value="">-     Class     -</option>
					<option><c:out value="Economy" /></option>
					<option><c:out value="Business" /></option>
					<option><c:out value="First" /></option>										
					</select>
				</td>
			</tr>
			<tr>
				<th>Change seat</th>
				<td><input type="text" name="seat" placeholder="Seat Number"></td>
			</tr>
			</table>
		
		<br><br>
		<input type="submit" name="editReservation" value="Edit">
	</form>
	
	<br>
	
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
			<input type="submit" name = "returnToReservationViewR" value="Return">
	</form>
	
	<br><br><br>${message}
	
</div>

</body>
</html>

