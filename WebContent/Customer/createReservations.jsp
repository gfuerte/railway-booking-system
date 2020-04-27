<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Reservations</title>
</head>
<body>
<div align="center">
	<form method="post" action="${pageContext.request.contextPath}/createReservations">
		<p>Search Origin <input type="text" name="origin"></p>
		<p>Search Destination <input type="text" name="destination"></p>
		<input type="submit" name = "filter" value="Filter">
		<input type="submit" name="reset" value="Reset Filter">
	</form>
	
	<br>
	<table border="1">
		<h2 align="center">Available Trains</h2>
    	<tr>
        	<th>Train Number</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Fare</th>
            <th>Transit Line</th>
        </tr>
        <c:forEach var="x" items="${list}">
            <tr>
            	<td><c:out value="${x.trainnum}" /></td>
                <td><c:out value="${x.origin}" /></td>
                <td><c:out value="${x.destination}" /></td>
                <td><c:out value="${x.departure}" /></td>
                <td><c:out value="${x.arrival}" /></td>
                <td><c:out value="${x.fare}" /></td>
                <td><c:out value="${x.transitline}" /></td>    
             </tr>
        </c:forEach>
	</table>
	
	<br>
	
	<form method="post" action="${pageContext.request.contextPath}/createReservations">
	
		<p>
		Select Train Number 
		<select name="trainNumber">
			<option disabled selected value="0">-- Choose Number --</option>
			<c:forEach var="x" items="${list}">
				<option><c:out value="${x.trainnum}" /></option>
			</c:forEach>
		</select>
		<input type="submit" name="reserve" value="Create Reservation">
		</p>
	</form>
	
	
	
</div>    
<br><br>

<div id="backButton" align="center">
	<form method="get" action="${pageContext.request.contextPath}/createReservations">
		<input type="submit" name = "goBack" value="Back">
	</form>
</div>
</body>
</html>
