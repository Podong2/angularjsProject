<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<table class="board-list sort" cellpadding="0" cellspacing="0" border="1">
	<colgroup>
		<col width="5%"/>
		<col width="15%"/>
		<col width="10%"/>
		<col width="20%"/>
		<col width="10%"/>
		<col width="40%"/>
	</colgroup>
	<thead>
		<tr>
			<th style="background-color: #B3C5F3;">번호</th>
			<th style="background-color: #B3C5F3;">아이디(이메일)</th>
			<th style="background-color: #B3C5F3;">이름</th>
			<th style="background-color: #B3C5F3;">핸드폰</th>
			<th style="background-color: #B3C5F3;">학년</th>
			<th style="background-color: #B3C5F3;">댓글</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${boardReplyList.dataSize eq 0}">
			<tr>
				<td colspan="10" class="Last">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${boardReplyList.boardReplyEntity}" var="boardReplyEntity" varStatus="i">
			<tr align="center">
				<td>${i.index+1}</td>
				<td>${boardReplyEntity.id}</td>
				<td>${boardReplyEntity.name}</td>
				<td>${boardReplyEntity.phoneNumber}</td>
				<td>${boardReplyEntity.userGrade}</td>
				<td>${boardReplyEntity.boardReplyContents}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>