<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alerts</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/QA">
<div align="center">
<table border="1">
    		<h2>Alerts</h2>
    		<caption> Set Date: </caption>
			<tr>    
				<td>Date (YYYY-MM-DD)</td><td><input type="text" name="date"></td>
			</tr>
</table>
<br><br>
<table border="1">
    	<tr>
        	<th>Select One</th>
			<th>Transit Line</th>
        </tr>
        <c:forEach var="x" items="${list}">
            <tr>
                <td><input type="radio" name="tLine" value = "${x.uuid}"/></td>
            	<td><c:out value="${x.question}" /></td>   
             </tr>
        </c:forEach>
</table>
<br><br>
<table>
			<tr>    
				<td>Type Alert Message Here</td><td><input type="text" name="Alert"></td>
			</tr>
</table>
<br><br>${message}<br>

<input type="submit" name = "submitAlertR" value="Alert">
</div>
</form>

<form method="get" action="${pageContext.request.contextPath}/QA">
<input type="submit" name = "goBackR" value="Exit">
</form>
</body>

</html>