<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src=https://code.jquery.com/jquery-1.12.4.js></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta charset="ISO-8859-1">
<title>Add Train Schedule</title>

</head>
<body>

<h1 align="center">Add Train Schedule</h1>

<div align="center">
	
	<form method="post" action="${pageContext.request.contextPath}/representativeFunctions" >
		
		<h3>Select Transit Line</h3>	
			<select name="selectTransitLine">
				<option disabled selected value="">-   Transit Lines   -</option>
				<c:forEach var="i" items="${transitLineList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h3>Select Origin</h3>	
			<select name="selectOrigin">
				<option disabled selected value="">-     Origins     -</option>
				<c:forEach var="i" items="${originList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h3>Select Train</h3>	
			<select name="selectTrain">
				<option disabled selected value="">-     Trains     -</option>
				<c:forEach var="i" items="${trainList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		<h3>Select Date</h3>
			<input type="date" id="datepicker" name="selectDate" value="Date">
			<script>
				$( function() {
    				$.datepicker.setDefaults({
        				onClose:function(date, inst){
        	    			$("#selectedDtaeVal").html(date);
        				}
    				});
					$( "#datepicker" ).datepicker();
				});
			</script>
		<br><br>
		
		<h3>Select Time</h3>
			<select name="selectTime">
				<option disabled selected value="">-     Times     -</option>
				<c:forEach var="i" items="${timeList}">
					<option><c:out value="${i}" /></option>
				</c:forEach>
			</select>
		<br><br>
		
		
		<br><br><br>
		<input type="submit" name="addNewSchedule" value="Add New Schedule">
	</form>
		
	<br><br>
	
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
			<input type="submit" name = "returnToScheduleViewR" value="Return">
	</form>
	<br><br>${message}
	
</div>

</body>
</html>

