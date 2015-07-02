<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/question.js"></script>
<script>
$(function() {
	datepicker();
	// 이미지 업로드 호출
	$('.btn-add-file').click(function() {
		$('#mainFile').click();
		return false;
	});
	// ajaxFileUpLoad 미리보기
	questionUtils.ajaxFileUpload();
});
</script>
<div class="contents">
	<h3 class="contents-title">문제등록관리</h3>
	<div class="sub-content">
		<c:choose>
			<c:when test="${questionEntity.questionKey > 0}">
				<h4 class="sub-title">다Q문제 관리 상세</h4>
			</c:when>
			<c:otherwise>
				<h4 class="sub-title">다Q문제 관리 등록</h4>
			</c:otherwise>
		</c:choose>
			<p class="sub-title-02">문제</p>
			<form id="daqForm" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="questionKey" value="${questionEntity.questionKey}" />
				<input type="hidden" name="userKey" value="${sessionScope.adminLoginSession.userKey}" />
				<input type="hidden" name="questionTypeCode" value="03" />
				<table class="board-write">
					<colgroup>
						<col width="320"/>
						<col width="130"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<td rowspan="4" class="photo">
								<div class="question-img">
									<label for="mainFile" class="preview">
										<input type="hidden" name="fileName" class="main imgSrc" value="${questionEntity.questionImageName}"/>
										<input type="hidden" name="fileOrigName" class="imgAttach" value=""/>
										<c:if test="${questionEntity.questionKey > 0}">
											<img class="img-preview" src="/common/imgView?fileType=question&amp;fileName=${questionEntity.questionImageName}&amp;" onerror="this.src=&quot;/resources/img/no-image02.gif&quot;" alt="이미지 추가" style="width: 320px; height: 200px;">
										</c:if>
										<%-- <img class="img-preview" src="${contextPath}/common/imgView/?fileName=${questionEntity.questionImageName}&fileType=question" alt="이미지 추가" style="width: 320px; height: 200px;"> --%>
									</label>
									<!-- <a href=" " class="del"></a> -->
								</div>
								<%-- <img src="${contextPath}/resources/img/no-image02.gif"/> --%>
							</td>
							<th class="borL">노출기간</th>
							<td>
								<input type="text" class="inp-cal marL10" id="sd" name="exposeStartDate" value="<fmt:formatDate value="${questionEntity.exposeStartDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/> 
								<span class="icon-bar">-</span>
								<input type="text" class="inp-cal" id="ed" name="exposeEndDate" value="<fmt:formatDate value="${questionEntity.exposeEndDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th class="borL">문제내용</th>
							<td>
								<textarea class="txt-h145" name="questionContents">${questionEntity.questionContents}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="btn-area">
					<div class="floatR">
						<a href="#" class="btn btn-add-file">사진첨부</a>
						<c:choose>
							<c:when test="${questionEntity.questionKey > 0}">
								<a href="#" class="btn" onclick="questionUtils.updateDaqQuestion(${questionEntity.questionKey}); return false;">수정</a>
							</c:when>
							<c:otherwise>
								<a href="#" class="btn" onclick="questionUtils.insertQuestion(); return false;">등록</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form>
			<form id="uploadForm" enctype="multipart/form-data" style="display: none;">
				<input type="hidden" name="temporary" value="Y" />
				<input type="hidden" name="type" value="question" />
				<input id="mainFile" type="file" accept="image/*" class="ajaxFileUploadInput fake-hide" data-action="${contextPath}/common/uploadImage"/>
			</form>
	</div>
</div>