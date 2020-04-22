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
        <form method="post" action="${pageContext.request.contextPath}/QA">	
        <table border="1" cellpadding="5">
            <caption><h2>Pending Questions - ${filter}</h2></caption>
            <tr>
            	<th>Select</th>
                <th>Question</th>
                <th>Asked By</th>
            </tr>
            
            <c:forEach var="qa" items="${list}">
                <tr>
                    <td><input type="radio" name="Questions" value = "${qa.uuid}"/></td>
                    <td><c:out value="${qa.question}" /></td>
                    <td><c:out value="${qa.qauthor}" /></td>
                </tr>
            </c:forEach>
            
            <tr>    
				<td>Type Answer Here</td><td><input type="text" name="AnswerR"></td>
			</tr>
        </table>
        <input type="submit" name = "questionButtonR" value="Answer">
        </form>
        <br>${message}<br>
</div>



    
<br>
	<form method="post" action="${pageContext.request.contextPath}/QA">
		<table>
			<tr>    
				<td>Search</td><td><input type="text" name="SearchR"></td>
			</tr>
		</table>
		<input type="submit" name = "searchButtonR" value="Search">
	</form>
<br>
    
<form method="get" action="${pageContext.request.contextPath}/QA">
<input type="submit" name = "goBackR" value="Exit">
</form>

</body>
</html>