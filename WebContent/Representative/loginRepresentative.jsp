<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Representative</title>
</head>
<body>
Welcome ${sessionScope.Name}! Successful Login of Level ${sessionScope.Level}
<form method="get" action="${pageContext.request.contextPath}/redirect">
<input type="submit" name = "logoutButton" value="logout">
</form>
</body>
</html>