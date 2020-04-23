<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Schedule</title>
</head>

<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Train Schedule</h2></caption>
            <tr>
                <th>Train Number</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Date</th>
                <th>Arrival Time</th>
                <th>Departure Time</th>
                <th>Fare</th>
                <th>Transit Line</th>
            </tr>
            <c:forEach var="x" items="${list}">
                <tr>

                </tr>
            </c:forEach>
        </table>
</div>    

<form method="get" action="${pageContext.request.contextPath}/QA">
<input type="submit" name = "goBack" value="Back">
<body>

</body>
</html>