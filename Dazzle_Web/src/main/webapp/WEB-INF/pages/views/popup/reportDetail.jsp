<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
$(function() {
	// 문제 삭제
	$('.delete_question').click(function() {
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {questionKey : $('input[name=questionKey]').val()},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/question/rest/deleteQuestion',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					//customPopup.layerClose();
					window.location.reload();
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	});
	
	// 답글 삭제
	$('.delete_answer').click(function() {
		var _this = $(this);
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {answerKey : _this.data('answer-key')},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/question/rest/deleteAnswer',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					//callPopup('/admin/report/popup/reportDetail/' + questionKey, '770', '767', 'none');
					customPopup.layerClose();
					window.location.reload();
				}
			},
			error : function(result) {
				alert(result);
			}
		});
		return false;
	});
});
</script>
<div class="popup-area">
<div class="popup-tit">신고관리
	<a href="#" onclick="customPopup.layerClose($(this)); return false;"><img src="${contextPath}/resources/img/btn-close.png" class="floatR"/></a>
</div>
<div class="popup-cont">
	<div class="marB40">
		<input type="hidden" name="questionKey" value="${questionEntity.questionKey}"/>
		<p class="sub-title-02">문제</p>
		<table class="board-write">
			<colgroup>
				<col width="320"/>
				<col width="130"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<td rowspan="4">
						<img src="${contextPath}/common/imgView/?fileName=${questionEntity.questionImageName}&fileType=question" class="img-w320"/>
					</td>
					<th class="borL">이름</th>
					<td>${questionEntity.name}</td>
				</tr>
				<c:if test="${questionEntity.questionTypeCode eq '03'}">
				<tr>
					<th class="borL">노출기간</th>
					<td>
						<fmt:formatDate value="${questionEntity.exposeStartDate}" pattern="yyyy-MM-dd"/> ~
						<fmt:formatDate value="${questionEntity.exposeEndDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				</c:if>
				<tr>
					<th class="borL">등록일</th>
					<td><fmt:formatDate value="${questionEntity.insertDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
				<tr>
					<th class="borL">문제내용</th>
					<td>${questionEntity.questionContents}</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-area">
			<a href="#" class="floatR btn delete_question">삭제</a>
		</div>
	</div>
	<c:forEach var="list" items="${answerEntity.answerList}" varStatus="i">
	<div class="marB40">
		<p class="sub-title-02">답글</p>
		<table class="board-write">
			<colgroup>
				<col width="320"/>
				<col width="130"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<td rowspan="4">
						<img src="${contextPath}/common/imgView/?fileName=${list.fileName}&fileType=group" class="img-w320"/>
					</td>
					<th class="borL">이름</th>
					<td>
						<strong class="f-blue">${list.name}</strong> | 
						<span>문제${list.answerUserQuestionCount}</span><span class="marL15">답글${list.answerUserAnswerCount}</span>
						<span class="marL15">활동</span>
					</td>
				</tr>
				<tr>
					<th class="borL">등록일</th>
					<td><fmt:formatDate value="${list.insertDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
				<tr>
					<th class="borL">답글내용</th>
					<td>${list.answerContents}</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-area">
			<a href="#" class="floatR btn delete_answer" data-answer-key="${list.answerKey}">삭제</a>
		</div>
	</div>
	</c:forEach>
	</div>
</div>