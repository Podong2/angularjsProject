<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="logo"><a href="${contextPath}/admin/user/list"><img src="${contextPath}/resources/img/logo.png"/></a></div>
<div class="left-bar">
	<ul>
		<li class="menu-01" data-menu="user"><a href="${contextPath}/admin/user/list">회원관리</a></li>
		<li class="menu-02" data-menu="group"><a href="${contextPath}/admin/group/list">그룹관리</a></li>
		<li class="menu-03" data-menu="question"><a href="${contextPath}/admin/question/list/all">문제등록관리</a>
			<ul class="depth">
				<li data-depth="all"><a href="${contextPath}/admin/question/list/all">전체문제관리</a></li>
				<li data-depth="daq"><a href="${contextPath}/admin/question/list/daq">다Q문제관리</a></li>
			</ul>
		</li>
		<li class="menu-04" data-menu="report"><a href="${contextPath}/admin/report/list">신고관리</a></li>
		<li class="menu-05" data-menu="board"><a href="${contextPath}/admin/board/list/01">다즐정보</a>
			<ul class="depth">
				<li data-depth="01"><a href="${contextPath}/admin/board/list/01">공지사항</a></li>
				<li data-depth="02"><a href="${contextPath}/admin/board/list/02">이벤트</a></li>
				<li data-depth="03"><a href="${contextPath}/admin/board/list/03">한줄알림</a></li>
			</ul>
		</li>
		<li class="menu-05" data-menu="setting"><a href="${contextPath}/admin/setting/activityScoreForm">다즐설정</a>
			<ul class="depth">
				<li data-depth=""><a href="${contextPath}/admin/setting/activityScoreForm">활동점수 설정</a></li>
				<li data-depth=""><a href="${contextPath}/admin/setting/userGradeForm">회원등급 설정</a></li>
			</ul>
		</li>
		<li class="menu-06" data-menu="statistics"><a href="#">통계관리</a>
			<ul class="depth">
				<li data-depth="grade"><a href="${contextPath}/admin/statistics/list/grade">학년별 통계</a></li>
				<li data-depth="period"><a href="${contextPath}/admin/statistics/list/period">기간별 통계</a></li><!-- /admin/statistics/list/period  javascript:alert('준비중 입니다.'); -->
				<li data-depth="activityScore"><a href="/admin/statistics/list/activityScore">활동점수 통계</a></li><!-- /admin/statistics/list/activityScore -->
				<li data-depth="activityCount"><a href="${contextPath}/admin/statistics/list/activityCount">활동개수 통계</a></li>
				<li data-depth=""><a href="javascript:alert('준비중 입니다.');">구글웹로그분석</a></li>
			</ul>
		</li>
	</ul>
</div>