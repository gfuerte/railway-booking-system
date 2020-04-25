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
	<form method="get" action="${pageContext.request.contextPath}/createReservations">
		<input type="submit" name = "goBack" value="Exit">
	</form><br>
	<div align="center">
		<h3>Select Origin</h3>
		<form>
			<select>
				<option disabled selected value="0">-- Choose Origin --</option>
			</select>
		</form>
		<h3>Select Destination</h3>
		<form>
			<select>
				<option disabled selected value="0">-- Choose Destination --</option>
			</select>
		</form>
		<h3>Select Date</h3>
		<form>
			<select>
				<option disabled selected value="0">-- Choose Date --</option>
			</select>
		</form>
	</div>
	<br><br>
	<div align="center">
		<h2>Train Schedule</h2>
		<table border="1">
            <tr>
            	<th></th>
                <th>Train Number</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Arrival Time</th>
                <th>Departure Time</th>
                <th>Fare</th>
                <th>Transit Line</th>
            </tr>
            <c:forEach var="x" items="${list}">
                <tr>
                	<td><input type="checkbox"/>&nbsp;</td>
                    <td><c:out value="${x.trainnum}" /></td>
                    <td><c:out value="${x.origin}" /></td>
                    <td><c:out value="${x.destination}" /></td>
                    <td><c:out value="${x.arrival}" /></td>
                    <td><c:out value="${x.departure}" /></td>
                    <td><c:out value="${x.fare}" /></td>
                    <td><c:out value="${x.transitline}" /></td>
                    
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>