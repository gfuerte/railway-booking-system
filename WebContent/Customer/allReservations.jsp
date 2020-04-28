<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservations</title>
</head>
<body>
<div align="center">
	<table border="1">
		<h2 align="center">Current Reservations</h2>
    	<tr>
    		<th>Date Created</th>
        	<th>Reservation Number</th>
        	<th>Train Number</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Fare</th>
            <th>Transit Line</th>
        </tr>
        <c:forEach var="x" items="${current}">
            <tr>
            	<td><c:out value="${x.created}" /></td>
            	<td><c:out value="${x.rid}" /></td>
            	<td><c:out value="${x.trainNum}" /></td>
                <td><c:out value="${x.origin}" /></td>
                <td><c:out value="${x.destination}" /></td>
                <td><c:out value="${x.departure}" /></td>
                <td><c:out value="${x.arrival}" /></td>
                <td><c:out value="${x.fare}" /></td>
                <td><c:out value="${x.line}" /></td>
             </tr>
        </c:forEach>
	</table>
	<br><br><br>
	<table border="1">
		<h2 align="center">Past Reservations</h2>
    	<tr>
    		<th>Date Created</th>
        	<th>Reservation Number</th>
        	<th>Train Number</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Fare</th>
            <th>Transit Line</th>
        </tr>
        <c:forEach var="x" items="${past}">
            <tr>
            	<td><c:out value="${x.created}" /></td>
            	<td><c:out value="${x.rid}" /></td>
            	<td><c:out value="${x.trainNum}" /></td>
                <td><c:out value="${x.origin}" /></td>
                <td><c:out value="${x.destination}" /></td>
                <td><c:out value="${x.departure}" /></td>
                <td><c:out value="${x.arrival}" /></td>
                <td><c:out value="${x.fare}" /></td>
                <td><c:out value="${x.line}" /></td>
             </tr>
        </c:forEach>
	</table>
	
</div>
	
	
	
	
	
	
	
		
<br><br>
<div id="backButton" align="center">
	<form method="get" action="${pageContext.request.contextPath}/allReservations">
		<input type="submit" name = "goBack" value="Back">
	</form>
</div>
</body>
</html>