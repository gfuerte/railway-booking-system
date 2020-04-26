<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Customer Representative</title>
</head>
<body>
Please Enter Information Below:
<br>
	<form method="post" action="${pageContext.request.contextPath}/adminFunctions">
		<table>
			<tr>    
				<td>Username</td><td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Password</td><td><input type="password" name="pswd"></td>
			</tr>
			<tr>    
				<td>First Name</td><td><input type="text" name="fname"></td>
			</tr>
			<tr>
				<td>Last Name</td><td><input type="text" name="lname"></td>
			</tr>
			<tr>    
				<td>Social Security Number</td><td><input type="text" name="ssn"></td>
			</tr>
		</table>
		<input type="submit" name = "addEmployee" value="Add">
	</form>
	<br>
	<form method="get" action="${pageContext.request.contextPath}/adminFunctions">
		<input type="submit" name = "goBack" value="Return">
	</form>
	<br>${message}<br>
<br>

</body>
</html>