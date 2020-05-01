<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Train Schedules</title>
    </head>

    <body>		
		<sql:setDataSource
        	var="myDS"
        	driver="com.mysql.jdbc.Driver"
        	url="jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem?zeroDateTimeBehavior=convertToNull"
        	user="admin" password="dbgroup20"
    	/>
		
		<sql:query var="listSchedules" dataSource="${myDS}">
			SELECT * FROM Schedule;
    	</sql:query>
    
    	<div align="center">
    		<h2>Train Schedules</h2>
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
            	<c:forEach var="schedule" items="${listSchedules.rows}">
                <tr>
                    <td><c:out value="${schedule.origin}" /></td>
                    <td><c:out value="${schedule.destination}" /></td>
                    <td><c:out value="${schedule.transitLine}" /></td>
                    <td><c:out value="${schedule.avaliableSeats}" /></td>
                    <td><c:out value="${schedule.stops}" /></td>
                    <td><c:out value="${schedule.departureDatetime}" /></td>
                    <td><c:out value="${schedule.arrivalDatetime}" /></td>
					<td><c:out value="${schedule.travelTime}" /></td>
                    <td><c:out value="${schedule.fare}" /></td>
                    <td><c:out value="${schedule.train}" /></td>
					
                </tr>
            	</c:forEach>
        	</table>
    	</div>
    	
    	<br><br>
    	<div align="center">
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
    	</div>
    	
    	<br><br>
    	<div align="center">
 	   		<form action="${pageContext.request.contextPath}/representativeFunctions" method="get">
				<input type="submit" name="returnToMainR" value="Exit">
			</form>
    	</div>
    	
    </body>
</html>