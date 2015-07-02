<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/login.js"></script>
<script type="text/javascript">
$(function() {
	var result = "${resultCode}";
	if(result == "authFail") alert("로그인 후 이용해 주세요.");
});
</script>
<div class="login-area">
	<div class="login-bg">
	<form id="loginForm" name="loginForm">
		<input type="hidden" name="loginType" value="admin" />
		<div class="login-box">
			<h1 class="logo"><img src="${contextPath}/resources/img/logo.png"/></h1>
			<p class="title">관리자 페이지</p>
			<div class="login-form">
				<div class="marB5">
					<input type="text" name="id" value="" id="id" class="inp-w240 id watermark" placeholder="아이디" onkeydown="if(event.keyCode == '13'){$('.login').trigger('click'); return false;}"/>
				</div>
				<div>
					<input type="password" name="password" value="" id="password" class="inp-w240 password watermark" placeholder="비밀번호"  onkeydown="if(event.keyCode == '13'){$('.login').trigger('click'); return false;}"/>
				</div>
				<div class="btn-area"><a href="javascript:;" data-action="${contextPath}/doLogin" class="login">로그인</a></div>
			</div>
		</div>
	</form>
	</div>
</div>