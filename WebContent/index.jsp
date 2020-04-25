<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
Please login below
<br>
	<form method="post" action="${pageContext.request.contextPath}/redirect">
		<table>
			<tr>    
				<td>Username</td><td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Password</td><td><input type="password" name="pswd"></td>
			</tr>
		</table>
		<input type="submit" name = "LoginButton" value="Login">
		<input type="submit" name = "CustomerButton" value="Customer Signup">
		<input type="submit" name = "RepButton" value="Representative Signup">
	</form>
<br>
	<br>${message}<br>
<br>
</body>
</html>