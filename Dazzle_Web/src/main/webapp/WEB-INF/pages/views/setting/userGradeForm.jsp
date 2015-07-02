<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/setting.js"></script>
<div class="contents">
	<h3 class="contents-title">다즐설정</h3>
	<div class="sub-content">
		<h4 class="sub-title">회원등급 설정</h4>
			<div class="list-02">활동점수 수치에 따라 레벨 기준을 조정할 수 있습니다.</div>
			<form id="activityScoreEntity" name="activityScoreEntity">
			<table class="board-list al-center">
				<colgroup>
					<col width="150"/>
					<col width="415"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<th>등급</th>
						<th>개인 레벨 설정</th>
						<th class="borR-none">그룹 레벨 설정</th>
					</tr>
					<c:forEach items="${GradeList}" var="gradeList">
						<tr class="GradeGroup">
							<th>${gradeList.gradeContent}</th>
							<td class="borR">
								<input type="hidden" class="code" value="${gradeList.code}"/>
								<input type="text" class="inp-w100 marL10 marR5 al-center userLowScore" value="${gradeList.userLowScore}"/>&nbsp;~
								<input type="text" class="inp-w100 marL10 al-center userHighScore" value="${gradeList.userHighScore}"/>
							</td>
							<td>
								<input type="text" class="inp-w100 marL10 marR5 al-center groupLowScore" value="${gradeList.groupLowScore}"/>&nbsp;~
								<input type="text" class="inp-w100 marL10 al-center groupHighScore" value="${gradeList.groupHighScore}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</form>
			<div class="btn-area">
				<a href="#" class="floatR btn Activitysubmit" data-state="1" data-action="/admin/setting/UpdateGradeScoreForm">저장</a>
			</div>
	</div>
</div>