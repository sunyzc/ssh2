<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>用户管理</title>
</head>
<body>
	<h3>用户管理</h3>
	<table>
		<tr>
			<th width="10%" align="center">#</th>
			<th width="10%" align="center">Id</th>
			<th width="15%" align="center">Login Name</th>
			<th width="15%" align="center">User Name</th>
			<th width="30%" align="center">Email</th>
			<th width="20%" align="center">Operate</th>
		</tr>
		<s:iterator value="page.recordList" status="rowStatus">
			<tr>
				<td align="center">${rowStatus.count}</td>
				<td align="center">${id}</td>
				<td align="center">${loginName}</td>
				<td align="center">${userName}</td>
				<td>${email}</td>
				<td><a href="user!input?id=${id}">Edit</a> | <a href="user!delete?id=${id}" onclick="return confirm('Delete?');">Delete</a></td>
			</tr>
		</s:iterator>
	</table>
	<s:form action="user" />
	<%@ include file="/WEB-INF/jsp/common/pageView.jsp"%>
</body>
</html>
