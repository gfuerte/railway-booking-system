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
	<table border="1">
    		<caption><h2>Train Schedule</h2></caption>
            <tr>
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

<form method="get" action="${pageContext.request.contextPath}/search">
<input type="submit" name = "goBack" value="Back">
</form>
</body>
</html>