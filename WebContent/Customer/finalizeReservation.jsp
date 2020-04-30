<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finalize Reservation</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<h2 align="center">Train Info</h2>
	    	<tr>
	        	<th>Train Number</th>
	        	<th>Available Seats</th>
	        	<th>Transit Line</th>
	            <th>Origin</th>
	            <th>Destination</th>
	            <th>Number of Stops</th>
	            <th>Departure Time</th>
	            <th>Arrival Time</th>
	            <th>Minutes Travel</th>
	            <th>Fare</th>
	        </tr>
	        <c:forEach var="x" items="${selectedTrainRequest}">
	            <tr>
	            	<td><c:out value="${x.trainNum}" /></td>
	            	<td><c:out value="${x.availableSeats}" /></td> 
	                <td><c:out value="${x.line}" /></td>
	                <td><c:out value="${x.origin}" /></td>
	                <td><c:out value="${x.destination}" /></td>
	                <td><c:out value="${x.numStops}" /></td>
	                <td><c:out value="${x.departure}" /></td>
	                <td><c:out value="${x.arrival}" /></td>    
	                <td><c:out value="${x.minTravel}" /></td> 
	                <td><c:out value="${x.fare}" /></td> 
	             </tr>
	        </c:forEach>
		</table>
		<br>
		
		<form method="post" action="${pageContext.request.contextPath}/finalizeReservation">
			<p>
			Select Ticket
			<select name="ticket">
				<option disabled selected value="0">-- Choose Ticket --</option>
				<fmt:setLocale value = "en_US"/>
				<option value="1">One Way Adult - <fmt:formatNumber value = "${owa}" type = "currency"/></option>
				<option value="2">One Way Child - <fmt:formatNumber value = "${owc}" type = "currency"/></option>
				<option value="3">One Way Sr/Dis - <fmt:formatNumber value = "${owd}" type = "currency"/></option>
				<option value="4">Round Trip Adult - <fmt:formatNumber value = "${rta}" type = "currency"/></option>
				<option value="5">Round Trip Child - <fmt:formatNumber value = "${rtc}" type = "currency"/></option>
				<option value="6">Round Trip Sr/Dis - <fmt:formatNumber value = "${rtd}" type = "currency"/></option>
				<option value="7">Weekly Ticket - <fmt:formatNumber value = "${wt}" type = "currency"/></option>
				<option value="8">Monthly Ticket - <fmt:formatNumber value = "${mt}" type = "currency"/></option>
			</select><br><br>
			Select Class
			<select name="class">
				<option disabled selected value="0">-- Choose Ticket --</option>
				<option value="Economy">Economy Class</option>
				<option value="Business">Business Class +30%</option>
				<option value="First">First Class +50%</option>
			</select><br><br>
			<input type="submit" name="select" value="Create Reservation">
			<br><br>${finalizeMessage}
			</p>
		</form>
	</div>
	<br><br><br><br>
	<div id="backButton" align="center">
		<form method="get" action="${pageContext.request.contextPath}/finalizeReservation">
			<input type="submit" name = "goBack" value="Back">
		</form>
	</div>
</body>
</html>