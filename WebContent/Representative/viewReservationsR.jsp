<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Reservations</title>
    </head>

    <body>		
		<sql:setDataSource
        	var="myDS"
        	driver="com.mysql.jdbc.Driver"
        	url="jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem?zeroDateTimeBehavior=convertToNull"
        	user="admin" password="rutgerscs336"
    	/>
		
		<sql:query var="listReservations" dataSource="${myDS}">
			SELECT * FROM Reservations;
    	</sql:query>
    
    	<div align="center">
    		<h2>Reservations</h2>
    		<table border="1">
            	<tr>
               		<th>RID</th>
                	<th>Fare</th>
                	<th>Customer Username</th>
                	<th>Representative Username</th>
            		<th>Date</th>
            	</tr>
            	<c:forEach var="reservation" items="${listReservations.rows}">
                <tr>
                    <td><c:out value="${reservation.rid}" /></td>
                    <td><c:out value="${reservation.fare}" /></td>
                    <td><c:out value="${reservation.usernameCustomer}" /></td>
                    <td><c:out value="${reservation.usernameRepresentative}" /></td>
                    <td><c:out value="${reservation.date}" /></td>
                </tr>
            	</c:forEach>
        	</table>
    	</div>
    	
    	<br><br>
    	<div align="center">
 	   		<table><tr>
            	<th>
 	   			<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
					<input type="submit" name="addReservationR" value="Add Reservation">
				</form>
				</th>
				<th>
				<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
					<input type="submit" name="editReservationR" value="Edit Reservation">
				</form>
				</th>
				<th>
	    		<form action="${pageContext.request.contextPath}/representativeFunctions" method="post">
					<input type="submit" name="deleteReservationR" value="Delete Reservation">
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