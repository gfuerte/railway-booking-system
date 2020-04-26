<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${Month} Sales Report</title>
</head>
<body>
<div align="center">
        <form method="post" action="${pageContext.request.contextPath}/QA">	
        <table border="1" cellpadding="5">
            <caption><h2>${Month} Sales Report</h2></caption>
            <tr>
            	<th>Reservation ID</th>
                <th>Date</th>
                <th>Fare</th>
            </tr>
            
            <c:forEach var="qa" items="${list}">
                <tr>
                    <td><input type="radio" name="Questions" value = "${qa.uuid}"/></td>
                    <td><c:out value="${qa.question}" /></td>
                    <td><c:out value="${qa.qauthor}" /></td>
                </tr>
            </c:forEach>
            
        </table>
        </form>
        <br>${message}<br>
</div>

</body>
</html>