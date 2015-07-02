<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", "no-cache, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("returnURI", request.getRequestURI());
	response.setContentType("text/html; charset=UTF-8");
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Dazzle</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css"/>
  <link rel="stylesheet" href="${contextPath}/resources/css/jquery/jquery-ui.min-1.11.2.css">

  <script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery-1.11.1.js"></script>
  <script type="text/javascript" src="${ contextPath }/resources/js/jquery/jquery.blockUI.js"></script>
  <script type="text/javascript" src="${ contextPath }/resources/js/jquery/jquery.form.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/js/common.js"></script>
  <script type="text/javascript">var contextPath = "${contextPath}";</script>
</head>
<body>
  <tiles:insertAttribute name="body" />
</body>
</html>