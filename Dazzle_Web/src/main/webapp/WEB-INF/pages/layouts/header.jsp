<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${contextPath}/resources/js/user.js"></script>
<script type="text/javascript">
$(function() {
	$('.sub-login').on('click', function() {
		$(this).toggleClass("on");
		$('.sub-login-form').toggle();
	});
});
</script>
<div class="top-gnb">
	<span>안녕하세요. <span class="bold color-b">${sessionScope.adminLoginSession.id}</span> (관리자)님</span>
	<a href="#" class="sub-login btn">관리자설정</a>
	<ul class="sub-login-form" style="display: none;">
		<li>
			<a href="${contextPath}/admin/updatePassword" class="a" data-msg="비밀번호변경">비밀번호변경</a>
		</li>
		<li>
			<a href="#" class="submitBtn" data-msg="로그아웃"
			   data-action="${contextPath}/admin/doLogout" data-id="${sessionScope.adminLoginSession.id}" data-type="ajax">로그아웃</a>
		</li>
	</ul>
</div>