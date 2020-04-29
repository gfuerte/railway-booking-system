<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alerts</title>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Alerts</h2></caption>
            <tr>
                <th>Route</th>
                <th>Date</th>
                <th>Alert</th>
            </tr>
            <c:forEach var="a" items="${list}">
                <tr>
                    <td><c:out value="${a.question}" /></td>
                    <td><c:out value="${a.qauthor}" /></td>
                    <td><c:out value="${a.answer}" /></td>
                </tr>
            </c:forEach>
        </table>
</div>    
<form method="get" action="${pageContext.request.contextPath}/QA">
<input type="submit" name = "goBack" value="Exit">
</form>

</body>
</html>