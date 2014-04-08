<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>用户信息</title>
</head>
<body>
	<h3>
		<s:if test="id == null">添加</s:if>
		<s:else>编辑</s:else>
		用户
	</h3>
	<form id="inputForm" action="user!save" method="post">
		<input type="hidden" name="id" value="${id}" />
		<table>
			<tr>
				<td>登录名：</td>
				<td><s:textfield id="loginName" name="loginName" maxlength="1" /></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><s:textfield id="userName" name="userName" /></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><s:password id="password" name="password" /></td>
			</tr>
			<tr>
				<td>密码确认：</td>
				<td><s:password id="passwordConfirm" name="passwordConfirm" /></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><s:textfield id="email" name="email" /></td>
			</tr>
			<tr>
				<td>角色：</td>
				<td><s:checkboxlist name="checkedRoleIds" list="allRoleList" listKey="id" listValue="roleName" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交" /><input type="button" value="返回" onclick="history.back()" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
