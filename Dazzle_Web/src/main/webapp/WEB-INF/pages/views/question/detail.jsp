<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/question.js"></script>
<script>
	$(function() {
		// 문제유형 선택 select box
		$('.grade, .large, .medium, .small').change(function() {
			questionUtils.getClassCodeList($(this));
		});
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
		<h4 class="sub-title">
			<c:if test="${questionType eq 'all'}">전체문제 관리 상세</c:if>
			<c:if test="${questionType eq 'daq'}">다Q문제 관리 상세</c:if>
		</h4>
		<div class="marB40">
			<p class="sub-title-02">문제</p>
			<form id="questionForm" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="questionKey" value="${questionEntity.questionKey}" />
			<table class="board-write">
				<colgroup>
					<col width="320"/>
					<col width="130"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<td rowspan="4">
							<div class="question-img">
								<c:if test="${questionEntity.questionImageName ne null}">
									<a href="#" onclick="callPopup('/common/popup/image/${questionEntity.questionImageName}/question', '600', '500', 'none');return false;">
										<img src="${contextPath}/common/imgView/?fileName=${questionEntity.questionImageName}&fileType=question" class="img-w320"/>
									</a>
								</c:if>
							</div>
						</td>
						<th class="borL">이름</th>
						<td>
						<a href="${contextPath}/admin/user/detail?userKey=${questionEntity.userKey}">
							<strong class="f-blue bold">${questionEntity.name}</strong>
						</a>
							<c:if test="${questionType eq 'all'}">
							 | <span>문제${questionEntity.questionCount}</span>
							<span class="marL15">답글${questionEntity.userAnswerCount}</span>
							<span class="marL15">활동 ${questionEntity.activityScore}</span></c:if>
						</td>
					</tr>
					<c:if test="${questionType eq 'daq'}">
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
					<c:if test="${questionType eq 'all'}">
					<tr>
						<th class="borL">문제유형</th>
						<td>
							<p>
								<span class="sapn-w100">학년</span>
								<select class="sel-w180 marB5 grade" name="questionGrade" data-next-class="large" data-type="grade">
									<option value="">선택</option>
									<c:forEach var="list" items="${gradeCodeList}">
										<option value="${list.commCdVal}" <c:if test="${list.commCdVal eq questionEntity.questionGrade}">selected="selected"</c:if>>${list.commCdNm}</option>
									</c:forEach>
								</select>
							</p>
							<p>
								<span class="sapn-w100">대단원</span>
								<select class="sel-w180 marB5 large" name="questionTypeLarge" data-next-class="medium" data-type="large">
									<option value="">선택</option>
									<c:forEach var="list" items="${classTypeLargeList}">
										<option value="${list.classCode}" data-class-code="${list.class7}" <c:if test="${list.classCode eq questionEntity.questionTypeLarge}">selected="selected"</c:if>>${list.name1}</option>
									</c:forEach>
								</select>
							</p>
							<p>
								<span class="sapn-w100">중단원</span>
								<select class="sel-w180 marB5 medium" name="questionTypeMedium" data-next-class="small" data-type="medium">
									<option value="">선택</option>
									<c:forEach var="list" items="${classTypeMediumList}">
										<option value="${list.classCode}" data-class-code="${list.class7}" <c:if test="${list.classCode eq questionEntity.questionTypeMedium}">selected="selected"</c:if>>${list.name1}</option>
									</c:forEach>
								</select>
							</p>
							<p>
								<span class="sapn-w100">소단원</span>
								<select class="sel-w180 marB5 small" name="questionTypeSmall" data-next-class="category" data-type="small">
									<option value="">선택</option>
									<c:forEach var="list" items="${classTypeSmallList}">
										<option value="${list.classCode}" data-class-code="${list.class7}" data-class9-code="${list.class9}" <c:if test="${list.classCode eq questionEntity.questionTypeSmall}">selected="selected"</c:if>>${list.name1}</option>
									</c:forEach>
								</select>
							</p>
							<p>
								<span class="sapn-w100">유형</span>
								<select class="sel-w180 category" name="questionTypeCategory">
									<option value="">선택</option>
									<c:forEach var="list" items="${classTypeCategoryList}">
										<option value="${list.classCode}" <c:if test="${list.classCode eq questionEntity.questionTypeCategory}">selected="selected"</c:if>>${list.name1}</option>
									</c:forEach>
								</select>
							</p>
						</td>
					</tr>
					</c:if>
					<tr>
						<th class="borL">문제내용</th>
						<td>${questionEntity.questionContents}</td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="btn-area">
				<div class="floatR">
					<a href="#" class="btn" onclick="questionUtils.updateQuestion('${questionType}','${questionEntity.questionKey}'); return false;">수정</a>
					<a href="#" class="btn" onclick="questionUtils.deleteQuestion('${questionType}','${questionEntity.questionKey}'); return false;">삭제</a>
					<a href="${contextPath}/admin/question/list/${questionType}" class="btn">목록</a>
				</div>
			</div>
		</div>

		<c:forEach var="list" items="${answerEntity.answerList}" varStatus="i">
		<div class="marB40">
			<c:if test="${i.index eq 0}"><p class="sub-title-02">답글</p></c:if>
			<table class="board-write">
				<colgroup>
					<col width="320"/>
					<col width="130"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<td rowspan="4">
							<div class="question-img">
								<c:if test="${list.fileName ne null}">
									<a href="#" onclick="callPopup('/common/popup/image/${list.fileName}/question', '500', '500', 'none');return false;">
										<img src="${contextPath}/common/imgView/?fileName=${list.fileName}&fileType=question" class="img-w320"/>
									</a>
								</c:if>
								<c:if test="${list.chooseQuestionerKey > 0}">
									<label class="stamp user-stamp"></label>
								</c:if>
								<c:if test="${list.chooseAdminKey > 0}">
									<label class="stamp admin-stamp"></label>
								</c:if>
							</div>

						</td>
						<th class="borL">이름</th>
						<td>
							<strong class="f-blue">${list.name}</strong> |
							<span>문제${list.answerUserQuestionCount}</span><span class="marL15">답글${list.answerUserAnswerCount}</span>
							<span class="marL15">활동${list.answerUserActivityScore}</span>
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
				<div class="floatR">
					<c:if test="${questionEntity.adminChooseCount eq 0}">
						<a href="#" class="btn" onclick="questionUtils.chooseAnswer('${questionType}','${questionEntity.questionKey}', '${list.answerKey}', 'Y'); return false;">채택</a>
					</c:if>
					<c:if test="${questionEntity.adminChooseCount > 0 && list.chooseAdminKey ne 0}">
						<a href="#" class="btn" onclick="questionUtils.chooseAnswer('${questionType}','${questionEntity.questionKey}', '${list.answerKey}', 'N'); return false;">채택취소</a>
					</c:if>
					<a href="#" class="btn" onclick="questionUtils.deleteAnswer('${questionType}','${questionEntity.questionKey}', '${list.answerKey}'); return false;">삭제</a>
				</div>
			</div>
		</div>
		</c:forEach>

		<c:if test="${questionType eq 'all'}">
		<form id="answerForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="questionKey" value="${questionEntity.questionKey}" />
		<div>
			<p class="sub-title-02">문제풀기</p>
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
									<input type="hidden" name="fileName" class="main imgSrc" value=""/>
									<input type="hidden" name="fileOrigName" class="imgAttach" value=""/>
								</label>
								<!-- <a href=" " class="del"></a> -->
							</div>
							<%-- <img src="${contextPath}/resources/img/no-image02.gif" class="img-w320" /> --%>
						</td>
						<th class="borL v-alT">답글내용</th>
						<td>
							<textarea class="txt-h145" id="answerContents" name="answerContents"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn-area">
				<div class="floatR">
					<a href="#" class="btn btn-add-file">사진첨부</a>
					<a href="#" class="btn" onclick="questionUtils.insertAnswer('${questionType}','${questionEntity.questionKey}'); return false;">답글등록</a>
				</div>
			</div>
		</div>
		</form>
		<form id="uploadForm" enctype="multipart/form-data" style="display: none;">
			<input type="hidden" name="temporary" value="Y" />
			<input type="hidden" name="type" value="question" />
			<input id="mainFile" type="file" accept="image/*" class="ajaxFileUploadInput fake-hide" data-action="${contextPath}/common/uploadImage"/>
		</form>
		</c:if>
	</div>
</div>