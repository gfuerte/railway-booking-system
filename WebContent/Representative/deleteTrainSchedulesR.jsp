<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>Delete Train Schedule</title>
</head>
<body>

<h1 align="center">Delete Train Schedule</h1>

<div align="center">
	
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
		
		<h3>Delete by Transit Line</h3>	
			<select name="selectTransitLine">
				<option disabled selected value="">-   Transit Lines   -</option>
				<c:forEach var="i" items="${transitLineList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h4>OR</h4>
		
		<h3>Delete by Train</h3>	
			<select name="selectTrain">
				<option disabled selected value="">-     Trains     -</option>
				<c:forEach var="i" items="${trainList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<br><br>
		<input type="submit" name="deleteFromSchedule" value="Delete">
	</form>
	
	<br>
	
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
			<input type="submit" name = "returnToScheduleViewR" value="Return">
	</form>
	
	<br><br><br>${message}
	
</div>

</body>
</html>

