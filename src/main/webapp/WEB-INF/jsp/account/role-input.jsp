<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>角色信息</title>
</head>
<body>
	<h3>
		<s:if test="id == null">添加</s:if>
		<s:else>编辑</s:else>
		角色
	</h3>
	<form id="inputForm" action="role!save" method="post">
		<input type="hidden" name="id" value="${id}" />
		<table>
			<tr>
				<td>Role Name:</td>
				<td><s:textfield id="roleName" name="roleName" /></td>
			</tr>
			<tr>
				<td>Authorities:</td>
				<td><s:checkboxlist name="checkedAuthorityIds" list="allAuthorityList" listKey="id" listValue="authorityName" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交" /><input type="button" value="返回" onclick="history.back()" /></td>
			</tr>
		</table>
		<sx:tree id="ServiceList" rootNode="root" nodeTitleProperty="name" nodeIdProperty="id" childCollectionProperty="children" treeSelectedTopic="treeSelected" />
		1
		<sx:tree id=".1." label=".2.">
			<sx:treenode id="..." label="..." />
			<sx:treenode id="..." label="...">
				<sx:treenode id="..." label="..." />
				<sx:treenode id="..." label="..." />
			</sx:treenode>
			<sx:treenode id="..." label="..." />
		</sx:tree>
		2
	</form>
</body>
</html>
