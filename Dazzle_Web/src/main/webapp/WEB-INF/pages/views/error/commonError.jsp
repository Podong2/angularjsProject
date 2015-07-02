<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css"/>
<script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery-1.11.1.js"></script>
<script>
	var contextPath = "${contextPath}";
	$(function() {
		alert($('#errorMessage').val());
		location.href = contextPath + $('#errorUrl').val();
	});
</script>
</head>
<body>
	<input type="hidden" name="errorCode" id="errorCode" value="${errorCode}" />
	<input type="hidden" name="errorMessage" id="errorMessage" value="${errorMessage}" />
	<input type="hidden" name="errorUrl" id="errorUrl" value="${errorUrl}" />
</body>
</html>