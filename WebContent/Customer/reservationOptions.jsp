<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Options</title>
</head>
<body>
<div align="center">	
	<form action = "${pageContext.request.contextPath}/reservationOptions" method = "post">
		<h2>Select Origin</h2>
		<select name="origin">
			<option disabled selected value="0">-- Choose Origin --</option>
			<c:forEach var="x" items="${stations}">
				<option><c:out value="${x}" /></option>
			</c:forEach>
		</select>
		<br><br>
		<h2>Select Destination</h2>
		<select name="destination">
			<option disabled selected value="0">-- Choose Destination --</option>
			<c:forEach var="x" items="${stations}">
				<option><c:out value="${x}" /></option>
			</c:forEach>
		</select>

		<br><br>${optionsMessage}<br><br>
		<input type ="submit" name = "submitOptions" value = "Submit Options">
	</form>
</div>

<br><br><br><br>
<div id="backButton" align="center">
	<form method="get" action="${pageContext.request.contextPath}/reservationOptions">
		<input type="submit" name = "goBack" value="Back">
	</form>
</div>
</body>
</html>