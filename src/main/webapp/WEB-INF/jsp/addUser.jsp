<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>Add User</title>
</head>
<body>
	<h4>Add User</h4>
	<form method="post" action="user!saveUser">
		User Name:&nbsp;<input type="text" name="user.userName" /><br /> Password:&nbsp; <input type="password" name="user.password" /><br /> <input type="submit" value="Add User" /> <input
			type="reset" value="Reset" /><br />
	</form>
</body>
</html>
