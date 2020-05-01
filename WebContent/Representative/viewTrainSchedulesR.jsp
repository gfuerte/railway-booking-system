<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Train Schedules</title>
    </head>

    <body>
    
    	<div align="center">
    		<h2>Train Schedules</h2>
    		
 	   		<table><tr>
            	<th>
 	   			<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
					<input type="submit" name="addScheduleR" value="Add Schedule">
				</form>
				</th>
				<th>
				<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
					<input type="submit" name="editScheduleR" value="Edit Schedule">
				</form>
				</th>
				<th>
	    		<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
					<input type="submit" name="deleteScheduleR" value="Delete Schedule">
				</form>
				</th>
			</tr></table>
    	
    	<br>
    	
 	   		<form action="${pageContext.request.contextPath}/representativeFunctions" method="get">
				<input type="submit" name="returnToMainR" value="Exit">
			</form>
    		
    	<br><br>
 
    		<table border="1">
            	<tr>
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
    	</div>
    	
    </body>
</html>