<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Customer Representative</title>
</head>
<body>
Please Enter Information to Below to Update a Customer Representative:
<br>
	<form method="post" action="${pageContext.request.contextPath}/adminFunctions">
	<table>
			<tr>    
				<td>Customer Representative Username (Required):</td><td><input type="text" name="modification"></td>
			</tr>
		</table>
		Fill out the information you wish to update below.
		Note: Not all fields must be filled. 
	
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
		<input type="submit" name = "saveChangesRep" value="Save Changes">
	</form>
	<br>
	<form method="get" action="${pageContext.request.contextPath}/adminFunctions">
		<input type="submit" name = "goBack" value="Return">
	</form>
	<br>${message}<br>
<br>

</body>
</html>