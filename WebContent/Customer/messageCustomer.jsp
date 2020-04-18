<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Questions</title>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Questions - ${filter}</h2></caption>
            <tr>
                <th>Question</th>
                <th>Answer</th>
                <th>Answered By</th>
            </tr>
            <c:forEach var="qa" items="${list}">
                <tr>
                    <td><c:out value="${qa.question}" /></td>
                    <td><c:out value="${qa.answer}" /></td>
                    <td><c:out value="${qa.aauthor}" /></td>
                </tr>
            </c:forEach>
        </table>
</div>    
<br>
	<form method="post" action="${pageContext.request.contextPath}/QA">
		<table>
			<tr>    
				<td>Search</td><td><input type="text" name="Search"></td>
			</tr>
		</table>
		<input type="submit" name = "searchButton" value="Search">
	</form>
<br>
    
<br>
	<form method="post" action="${pageContext.request.contextPath}/QA">
		<table>
			<tr>    
				<td>Type Question Here</td><td><input type="text" name="Question"></td>
			</tr>
		</table>
		<input type="submit" name = "questionButton" value="Ask">
	</form>
<br>

<form method="get" action="${pageContext.request.contextPath}/QA">
<input type="submit" name = "goBack" value="Exit">
</form>

</body>
</html>