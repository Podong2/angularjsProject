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
		<col width="20%"/>
		<col width="20%"/>
		<col width="20%"/>
	</colgroup>
	<tbody>
<%-- 		<c:if test="${gradeEntity.dataSize eq 0}"> --%>
<!-- 			<tr> -->
<!-- 				<td colspan="4" class="Last">데이터가 존재하지 않습니다.</td> -->
<!-- 			</tr> -->
<%-- 		</c:if> --%>
		<tr align="center">
			<th>총 활동점수</th>
			<th>회원 수</th>
			<th>비율</th>
		</tr>
		<c:forEach items="${gradeEntity.gradeList}" var="ActivityScoreEntity" varStatus="i">
			<tr align="center">
				<td>${ActivityScoreEntity.sectionScore}</td>
				<td>${ActivityScoreEntity.activityScore}명</td>
				<td>${ActivityScoreEntity.userRate}%</td>
			</tr>
		</c:forEach>
	</tbody>
</table>