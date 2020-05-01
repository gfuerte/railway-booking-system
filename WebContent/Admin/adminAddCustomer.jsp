<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Customer</title>
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
				<td>Address</td><td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>City</td><td><input type="text" name="city"></td>
			</tr>
			<tr>    
				<td>State</td><td><input type="text" name="state"></td>
			</tr>
			<tr>
				<td>Zip Code</td><td><input type="text" name="zcode"></td>
			</tr>
			<tr>    
				<td>Phone</td><td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>Email</td><td><input type="text" name="email"></td>
			</tr>
		</table>
		<input type="submit" name = "addCustomer" value="Add">
	</form>
	<br>
	<form method="get" action="${pageContext.request.contextPath}/adminFunctions">
		<input type="submit" name = "goBack" value="Return">
	</form>
	<br>${message}<br>
<br>
</body>
</html>