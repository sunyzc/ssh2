<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h2>Login</h2>
	<form id="loginForm" action="user!login" method="post">
		<table>
			<s:actionerror />
			<tr>
				<td><label>Login Name:</label></td>
				<td><s:textfield id="loginName" name="loginName" /></td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><s:password id="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan='2' align="right"><input type="checkbox" name="_spring_security_remember_me" />Remember me <input value="Login" type="submit" class="button" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
