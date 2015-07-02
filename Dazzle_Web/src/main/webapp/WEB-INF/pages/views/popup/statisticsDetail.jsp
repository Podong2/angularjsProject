<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="popup-area">
	<div class="popup-tit">
		${gradeEntity.commCdNm }
		<a href="#" onclick="customPopup.layerClose($(this)); return false;">
			<img src="${contextPath}/resources/img/btn-close.png" class="floatR"/>
		</a>
	</div>
	<div class="popup-cont" style="height: 527px;">
		<div class="marB40">
			<table class="board-write" style="text-align: center;">
				<colgroup>
					<col width="200"/>
					<col width="160"/>
					<col width="160"/>
					<col width="*"/>
				</colgroup>
				<thead>
					<tr>
						<th>대단원명</th>
						<th>문제수</th>
						<th>답글수</th>
						<th>미답글수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gradeEntity.gradeList}" var="gradeEntity" varStatus="i">
						<tr>
							<td>${gradeEntity.className}</td>
							<td>${gradeEntity.questionCount}</td>
							<td>${gradeEntity.answerCount}</td>
							<td>${gradeEntity.noAnswerCount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>