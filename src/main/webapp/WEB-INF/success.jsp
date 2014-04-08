<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE>
<html>
<body>
	<h2>Success!</h2>
	<s:actionmessage />
	<s:property value="message" />
	<s:debug />
</body>
</html>
