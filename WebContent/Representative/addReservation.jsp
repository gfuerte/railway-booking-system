<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>Add Reservation</title>
</head>
<body>

<h1 align="center">Add Reservation</h1>

<div align="center">
	
    <div style="width:100px; display:inline-block;">
    	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
			<input type="submit" name="addReservation" value="Add">
		</form>
	</div>
    <div style="width:100px; display:inline-block;">
		<form method="get" action="${pageContext.request.contextPath}/representativeFunctions" >
			<input type="submit" name="returnToReservationViewR" value="Return"">
		</form>	
	</div>

	<br>${message}<br><br>
	
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
		
		<h3>Reservation Number</h3>	
		<input type="text" readonly name="resNum" value="${assignedResNum}">
		<br>
		
		<h3>Choose customer to make reservation for</h3>	
		<select name="selectCustomer">
			<option disabled selected value="">-     Customers     -</option>
				<c:forEach var="i" items="${customerList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
		</select>
		<br>
		
		<h3>Choose select following reservation options</h3>
		<table border="1">
			<tr>
				<th width=200px align="center">Fee</th>
				<th width=200px align="center">Class</th>
				<th width=200px align="center">Note</th>
				<th width=200px align="center">Ticket</th>
				<th width=200px align="center">Seat</th>
			</tr>
			<tr>
				<td align="center">
					<input type="text" name="fee" placeholder="Fee">
				</td>
				<td align="center">
					<select name="selectClass">
						<option disabled selected value="">-     Class     -</option>
						<option><c:out value="Economy" /></option>
						<option><c:out value="Business" /></option>
						<option><c:out value="First" /></option>										
					</select>
				</td>
				<td align="center">
					<select name="selectNote">
						<option disabled selected value="">-     Note     -</option>
						<option><c:out value="Adult" /></option>
						<option><c:out value="Child" /></option>
						<option><c:out value="Sr/Dis" /></option>										
					</select>
				</td>
				<td align="center">
					<select name="selectTicket">
						<option disabled selected value="">-     Ticket     -</option>
						<option><c:out value="One Way" /></option>
						<option><c:out value="Monthly Ticket" /></option>
						<option><c:out value="Weekly Ticket" /></option>
						<option><c:out value="Round Trip" /></option>										
					</select>
				</td>
				<td align="center">
					<input type=text name="seat" placeholder="Seat">
				</td>
			</tr>
		</table>
		
		<br><br>
		
		<h3>Select a schedule below</h3>

    	<table border="1">
        	<tr>
        		<th>Select</th>
        		<th>Origin</th>
        		<th>Destination</th>
        		<th>Transit Line</th>
        		<th>Available Seats</th>
        		<th>Stops</th>
        		<th>Departure</th>
            	<th>Arrival</th>
            	<th>Travel Time</th>
           		<th>Fare</th>
            	<th>Train Number</th>
			</tr>
        	<c:forEach var="stop" items="${scheduleList}">
			<tr>
				<td><input type="radio" name="selectedSched" value = "${qa.uuid}"/></td>
        		<td><c:out value="${stop.getOrigin()}" /></td>
            	<td><c:out value="${stop.getDestination()}" /></td>
            	<td><c:out value="${stop.getTransitLine()}" /></td>
            	<td><c:out value="${stop.getSeats()}" /></td>
            	<td><c:out value="${stop.getStops()}" /></td>
            	<td><c:out value="${stop.getDepartureTime()}" /></td>
            	<td><c:out value="${stop.getArrivalTime()}" /></td>
				<td><c:out value="${stop.getTravelTime()}" /></td>
            	<td><c:out value="${stop.getFare()}" /></td>
            	<td><c:out value="${stop.getTrain()}" /></td>	
			</tr>
        	</c:forEach>
        </table>		
        
	</form>
	
</div>

</body>
</html>

