<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<jsp:useBean id="now" class="java.util.Date"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<table class="board-list sort" cellpadding="0" cellspacing="0" border="1">
	<colgroup>
		<col width="60"/>
		<col width="250"/>
		<col width="250"/>
		<col width="170"/>
		<col width="250"/>
		<col width="250"/>
		<col width="250"/>
		<col width="170"/>
		<col width="250"/>
	</colgroup>
	<thead>
		<tr>
			<th style="background-color: #B3C5F3;">번호</th>
			<th style="background-color: #B3C5F3;">그룹명</th>
			<th style="background-color: #B3C5F3;">공개상태</th>
			<th style="background-color: #B3C5F3;">리더명</th>
			<th style="background-color: #B3C5F3;">멤버수</th>
			<th style="background-color: #B3C5F3;">문제</th>
			<th style="background-color: #B3C5F3;">답글</th>
			<th style="background-color: #B3C5F3;">활동</th>
			<th style="background-color: #B3C5F3;">등록</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${groupEntity.dataSize eq 0}">
			<tr>
				<td colspan="10" class="Last">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="list" items="${groupEntity.groupList}" varStatus="i">
			<tr align="center">
				<td>${i.index+1}</td>
				<td>${list.groupName}</td>
				<td>${list.groupTypeCodeName}</td>
				<td>${list.leaderName}</td>
				<td>${list.groupMemberCount}</td>
				<td>${list.questionCount}</td>
				<td>${list.answerCount}</td>
				<td>${list.activityScore}</td>
				<td><fmt:formatDate value="${list.insertGroupDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>