<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>角色管理</title>
</head>
<body>
	<h3>角色管理</h3>
	<table>
		<tr>
			<th width="10%" align="center">#</th>
			<th width="10%" align="center">Id</th>
			<th width="20%" align="center">Role Name</th>
			<th width="30%" align="center">Description</th>
			<th width="30%" align="center">Operate</th>
		</tr>
		<s:iterator value="page.recordList" status="rowStatus">
			<tr>
				<td align="center">${rowStatus.count}</td>
				<td align="center">${id}</td>
				<td align="center">${roleName}</td>
				<td align="center">${description}</td>
				<td><a href="role!input?id=${id}">Edit</a> | <a href="role!delete?id=${id}" onclick="return confirm('Delete?');">Delete</a></td>
			</tr>
		</s:iterator>
	</table>
	<s:form action="role" />
	<%@ include file="/WEB-INF/jsp/common/pageView.jsp"%>
</body>
</html>
