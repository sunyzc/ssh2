<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h2>Login</h2>
	<form id="loginForm" action="login!login" method="post">
		<table>
			<s:actionerror />
			<tr>
				<td><label>登录名：</label></td>
				<td><s:textfield id="loginName" name="loginName" size="16" maxlength="16" /></td>
			</tr>
			<tr>
				<td><label>密&nbsp;&nbsp;码：</label></td>
				<td><s:password id="password" name="password" size="16" /></td>
			</tr>
			<tr>
				<td><label>验证码：</label></td>
				<td><s:textfield id="verificationCode" name="verificationCode" size="6" maxlength="6" /><img id="verificationImage" src="login!getCode"
					onclick="this.src='login!getCode?ts='+new Date().getTime()" /></td>
			</tr>
			<tr>
				<td colspan='2' align="right"><s:checkbox name="rememberMe" />Remember me <input value="Login" type="submit" class="button" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
