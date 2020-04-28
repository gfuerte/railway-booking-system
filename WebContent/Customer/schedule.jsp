<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Schedule</title>
</head>
<body>
<div align="center">
	<form method="post" action="${pageContext.request.contextPath}/search">
	<table border="1">
    		<h2>Train Schedule</h2>
    		<caption> Search for by: </caption>
			<tr>    
				<td>Date (YYYY-MM-DD)</td><td><input type="text" name="date"></td>
			</tr>
			<tr>
				<td>Origin</td><td><input type="text" name="origin"></td>
			</tr>
			<tr>
				<td>Destination</td><td><input type="text" name="destination"></td>
			</tr>
	</table>
	<input type="submit" name = "searchconditions" value="Search">
	</form>

<br>
Or
<br>
	<form method="post" action="${pageContext.request.contextPath}/search">
	
		<p>
		View stops for train number: 
		<select name="trainNumber">
			<option disabled selected value="0">-- Choose Number --</option>
			<c:forEach var="x" items="${list}">
				<option><c:out value="${x.trainnum}" /></option>
			</c:forEach>
		</select>
		<input type="submit" name="showStops" value="See Stops">
		<br>
		<input type="submit" name="showTrains" value="See All Trains">
		<br><br>
		</p>
	</form>	
Sort ALL trains by:
<br><br>
	<form method="post" action="${pageContext.request.contextPath}/search">
	<table>
	    <input type="submit" name = "sortorigin" value="Origin">
    	<input type="submit" name = "sortdestination" value="Destination">
	    <input type="submit" name = "sortdeparture" value="Departure">
    	<input type="submit" name = "sortarrival" value="Arrival">
    	<input type="submit" name = "sortfare" value="Fare">
	</table>
	</form>
</div>
<div align="center">
	<table border="1">
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
</div>    

<form method="get" action="${pageContext.request.contextPath}/search">
<input type="submit" name = "goBack" value="Back">
</form>
</body>
</html>