<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Reservations</title>
    </head>

    <body>
    
    	<div align="center">
    		<h2>Reservations</h2>
    		
    		<table border="1">
    			<tr>
    				<th width=250px>Add Reservation</th>
    				<th width=250px>Edit Reservation</th>
    				<th width=250px>Delete Reservation</th>
    			</tr>
    			<tr>
    				<td align="center">
    					<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
							<input type="submit" name="addReservationR" value="Add Reservation">
						</form>
					</td>				
					<td align="center">
    					<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
							<input type="submit" name="editReservationR" value="Edit Reservation">
						</form>
					</td>
					<td align="center">
						<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
							<select name="selectRes">
								<option disabled selected value="">-     Reservations     -</option>
									<c:forEach var="i" items="${resList}">
										<option><c:out value="${i}" /></option>
								</c:forEach>
							</select>
							<br>
							<input type="submit" name="deleteReservationR" value="Delete Reservation">
						</form>
					</td>
    			</tr>
    		</table>
    		
    		<br>
    		
			<form action="${pageContext.request.contextPath}/representativeFunctions" method="get">
				<input type="submit" name="returnToMainR" value="Exit">
			</form>
    		
    		<br><br>${message}
    		<br><br>
    		
    		<table border="1">
            	<tr>
               		<th>RID</th>
                	<th>Fee</th>
                	<th>Date</th>
                	<th>Train</th>
                	<th>Transit Line</th>
                	<th>Origin</th>
                	<th>Destination</th>
                	<th>Departure Time</th>
                	<th>Arrival Time</th>
                	<th>Customer</th>
                	<th>Representative</th>
            		<th>Class</th>
            		<th>Note</th>
            		<th>Ticket Type</th>
            		<th>Seat</th>
            	</tr>
            	<c:forEach var="reservation" items="${allReservation}">
                <tr>
                    <td><c:out value="${reservation.getrid()}" /></td>
                    <td><c:out value="${reservation.getFee()}" /></td>
					<td><c:out value="${reservation.getDate()}" /></td>
					<td><c:out value="${reservation.getTrain()}" /></td>
					<td><c:out value="${reservation.getTransitLine()}" /></td>
					<td><c:out value="${reservation.getOrigin()}" /></td>
					<td><c:out value="${reservation.getDestination()}" /></td>
					<td><c:out value="${reservation.getDepartureTime()}" /></td>
					<td><c:out value="${reservation.getArrivalTime()}" /></td>
                    <td><c:out value="${reservation.getCustomer()}" /></td>
                    <td><c:out value="${reservation.getRepresentative()}" /></td>
                    <td><c:out value="${reservation.gettClass()}" /></td>
                    <td><c:out value="${reservation.getNote()}" /></td>
                    <td><c:out value="${reservation.getTicketType()}" /></td>
                    <td><c:out value="${reservation.getSeat()}" /></td>
                </tr>
            	</c:forEach>
        	</table>
    	</div>
    	
    </body>
</html>