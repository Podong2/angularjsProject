<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="popup-area">
	<div class="popup-tit">사진보기
		<a href="#" onclick="customPopup.layerClose($(this)); return false;"><img src="${contextPath}/resources/img/btn-close.png" class="floatR"/></a>
	</div>
	<div class="popup-photo-cont">
		<div>
			<img src="${contextPath}/common/imgView/?fileName=${fileName}&fileType=${fileType}"/>
		</div>
	</div>
</div>