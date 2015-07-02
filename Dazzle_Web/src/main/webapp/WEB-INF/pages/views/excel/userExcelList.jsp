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
		<col width="3%"/>
		<col width="10%"/>
		<col width="5%"/>
		<col width="10%"/>
		<col width="5%"/>
		<col width="5%"/>
		<col width="7%"/>
		<col width="10%"/>
		<col width="5%"/>
		<col width="5%"/>
		<col width="5%"/>
		<col width="10%"/>
		<col width="20%"/>
	</colgroup>
	<thead>
		<tr>
			<th style="background-color: #B3C5F3;">번호</th>
			<th style="background-color: #B3C5F3;">아이디(이메일)</th>
			<th style="background-color: #B3C5F3;">이름</th>
			<th style="background-color: #B3C5F3;">회원분류</th>
			<th style="background-color: #B3C5F3;">학년</th>
			<th style="background-color: #B3C5F3;">등급</th>
			<th style="background-color: #B3C5F3;">핸드폰</th>
			<th style="background-color: #B3C5F3;">프렌차이즈</th>
			<th style="background-color: #B3C5F3;">문제</th>
			<th style="background-color: #B3C5F3;">답글</th>
			<th style="background-color: #B3C5F3;">활동</th>
			<th style="background-color: #B3C5F3;">가입상태</th>
			<th style="background-color: #B3C5F3;">가입일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${userList.dataSize eq 0}">
			<tr>
				<td colspan="10" class="Last">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${userList.userEntity}" var="userEntity" varStatus="i">
			<tr align="center">
				<td>${i.index+1}</td>
                <td>${userEntity.id}</a></td>
                <td>${userEntity.name}</td>
                <td>${userEntity.typeCode}</td>
                <td>${userEntity.userGrade}</td>
                <td>유저등급 이미지</td>
                <td>${userEntity.phoneNumber}</td>
                <td>
                    <c:if test="${userEntity.franchiseType eq '01'}">해법수학</c:if>
                    <c:if test="${userEntity.franchiseType eq '02'}">셀파</c:if>
                </td>
                <td>${userEntity.questionCount}</td>
                <td>${userEntity.answerCount}</td>
                <td>${userEntity.activityScore}</td>
                <td>${userEntity.stateCode}</td>
                <td>${userEntity.insertDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>