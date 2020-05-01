<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Reservations</title>
</head>
<body>
<div align="center">
	<table border="1">
		<h2 align="center">Available Trains</h2>
    	<tr>
        	<th>Train Number</th>
        	<th>Available Seats</th>
        	<th>Transit Line</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Number of Stops</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Fare</th>
        </tr>
        <c:forEach var="x" items="${availableTrainsRequest}">
            <tr>
            	<td><c:out value="${x.trainNum}" /></td>
            	<td><c:out value="${x.availableSeats}" /></td> 
                <td><c:out value="${x.line}" /></td>
                <td><c:out value="${x.origin}" /></td>
                <td><c:out value="${x.destination}" /></td>
                <td><c:out value="${x.numStops}" /></td>
                <td><c:out value="${x.departure}" /></td>
                <td><c:out value="${x.arrival}" /></td>    
                <fmt:setLocale value = "en_US"/>           
                <c:set var ="fare" value="${x.fare}"/>
                <td><fmt:formatNumber value = "${fare}" type = "currency"/></td>
             </tr>
        </c:forEach>
	</table>
	
	<br>
	
	<form method="post" action="${pageContext.request.contextPath}/createReservations">
	
		<p>
		Train Numbers
		<select name="trainNumber">
			<option disabled selected value="0">-- Choose Number --</option>
			<c:forEach var="x" items="${availableTrainsRequest}">
				<option><c:out value="${x.trainNum}" /></option>
			</c:forEach>
		</select>
		<input type="submit" name="reserve" value="Select Train">
		<br><br>${confirmation}
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
