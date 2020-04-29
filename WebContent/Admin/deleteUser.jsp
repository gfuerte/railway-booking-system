<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete User</title>
</head>
<body>
Please enter the username of the customer or customer representative you would like to delete:
<br>
	<form method="post" action="${pageContext.request.contextPath}/adminFunctions">
		<table>
				<tr>    
					<td></td><td><input type="text" name="deleteThisUser"></td>
				</tr>
		</table>
		<input type="submit" name = "delete" value="Delete">
	</form>
	<br>
	<form method="get" action="${pageContext.request.contextPath}/adminFunctions">
		<input type="submit" name = "goBack" value="Return">
	</form>
	<br>${message}<br>
<br>

</body>
</html>