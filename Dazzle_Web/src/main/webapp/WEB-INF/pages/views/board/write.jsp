<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/board.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/question.js"></script>
<script>
$(function() {
	datepicker();

	$('.btn-add-file').click(function() {
		$('#mainFile').click();
		return false;
	});

	$("#topExposeYn").click(function(){
			$("#sevendaysExposeYn").removeAttr('checked');
			$(this).attr('checked', 'checked');
	});
	$("#sevendaysExposeYn").click(function(){
			$("#topExposeYn").removeAttr('checked');
			$(this).attr('checked', 'checked');
	});
});
</script>
<div class="contents">
	<h3 class="contents-title">다즐정보</h3>
	<c:choose>
		<c:when test="${pageType == '01'}">
		 	<h4 class="sub-title">공지사항 등록</h4>
		</c:when>
		<c:otherwise>
		 	<h4 class="sub-title">이벤트 등록</h4>
		</c:otherwise>
	</c:choose>
	<div class="sub-content">
		<form id="boardSubmit" name="boardSubmit" method="post" enctype="multipart/form-data">
			<table class="board-write box-pd">
				<colgroup>
					<col width="85"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td class="al-left" style="padding-left:15px;">
							<input type="text" class="subject" name="subject" id="subject" value="${boardEntity.subject}" style="width:639px;"/>
						</td>
					</tr>
					<c:if test="${pageType eq '02'}">
						<tr>
							<th>기간</th>
							<td>
			                    <input type="text" class="inp-cal marL10 startDate" name="startDate" id="sd" value="<fmt:formatDate value="${boardEntity.eventStartDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
			                    <span class="icon-bar">-</span>
			                    <input type="text" class="inp-cal endDate" name="endDate" id="ed" value="<fmt:formatDate value="${boardEntity.eventEndDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr class="eventRate">
	                    	<th>조건설정</th>
							<td>
								<input type="checkbox" id="leaves" name="leaves" <c:if test="${boardEntity.ratingLeaf eq 'Y' || boardEntity.ratingLeaf eq null}"> checked = "checked"</c:if> value="01" class=""/><LABEL for="leaves">화이트</LABEL>
								<input type="checkbox" id="tree" name="tree" <c:if test="${boardEntity.ratingWood eq 'Y' || boardEntity.ratingWood eq null}"> checked = "checked"</c:if> value="02" class="marL10"/><LABEL for="tree">옐로우</LABEL>
								<input type="checkbox" id="stone" name="stone" <c:if test="${boardEntity.ratingStone eq 'Y' || boardEntity.ratingStone eq null}"> checked = "checked"</c:if> value="03" class="marL10"/><LABEL for="stone">그린</LABEL>
								<input type="checkbox" id="copper" name="copper" <c:if test="${boardEntity.ratingCopper eq 'Y' || boardEntity.ratingCopper eq null}"> checked = "checked"</c:if> value="04" class="marL10"/><LABEL for="copper">블루</LABEL>
								<input type="checkbox" id="silver" name="silver" <c:if test="${boardEntity.ratingSilver eq 'Y' || boardEntity.ratingSilver eq null}"> checked = "checked"</c:if> value="05" class="marL10"/><LABEL for="silver">레드</LABEL>
								<input type="checkbox" id="gold" name="gold" <c:if test="${boardEntity.ratingGold eq 'Y' || boardEntity.ratingGold eq null}"> checked = "checked"</c:if> value="06" class="marL10"/><LABEL for="gold">블랙</LABEL>
								<input type="checkbox" id="crystal" name="crystal" <c:if test="${boardEntity.ratingCrystal eq 'Y' || boardEntity.ratingCrystal eq null}"> checked = "checked"</c:if> value="07" class="marL10"/><LABEL for="crystal">레인보우</LABEL>
							</td>
						</tr>
						<tr>
	                    	<th>댓글제한</th>
							<td>
								<input type="checkbox" id="replyYn" name="replyYn" <c:if test="${boardEntity.replyYn eq 'Y'}"> checked = "checked"</c:if> value="Y" class=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${pageType eq '01'}">
						<tr>
							<th>메인노출</th>
							<td class="al-left" style="padding-left:15px;" colspan="">
								<input type="checkbox" class="topExposeYn" style="" name="topExposeYn" id="topExposeYn" <c:if test="${boardEntity.topExposeYn eq 'Y'}"> checked="checked" </c:if> value="Y"/>
								<LABEL for="topExposeYn">메인 상단에 노출됩니다.</LABEL>
								<input type="checkbox" class="sevendaysExposeYn" style="margin-left: 30px;" name="sevendaysExposeYn" id="sevendaysExposeYn" <c:if test="${boardEntity.sevendaysExposeYn eq 'Y'}"> checked="checked" </c:if> value="Y" />
								<LABEL for="sevendaysExposeYn">회원가입 시 일주일간 메인 상단에 노출됩니다.</LABEL>
							</td>
						</tr>
					</c:if>
					<tr>
						<th>사진첨부</th>
						<td colspan="3" style="padding-left:15px;" >
<!-- 							<a href="#" class="btn btn-add-file">사진첨부</a> -->
							<c:if test="${not empty boardEntity.fileName}">
								<input type="hidden" name="fileName" value="${boardEntity.fileName}" />
								<input type="hidden" name="attachKey" value="${boardEntity.attachKey}" />
							</c:if>
<!-- 							<input id="mainFile" name="file" type="file" accept="image/*" class="ajaxFileUploadInput fake-hide" style="display: none;"/> -->
							<span class="upload-img" style="">사진첨부
								<input id="mainFile" name="file" type="file" accept="image/*" class="ajaxFileUploadInput" style=""/>
							</span>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="board-textarea" style="padding-top: 10px; padding-left:15px; padding-bottom: 10px; " colspan="3">
							<textarea name="boardContents" id="boardContents" class="boardContents" rows="20" cols="100">${boardEntity.boardContents}</textarea>
						</td>
					</tr>
				</tbody>
			</table>

			<div class="btn-area">
				<c:if test ="${boardEntity.boardKey ne null}">
					<input type="hidden" value="${boardEntity.boardKey}" name="boardKey" id="boardKey"/>
				</c:if>

				<a href="${contextPath}/admin/board/list/${pageType}" class="floatL btn">목록</a>
				<a href="#"
				onclick="boardUtils.insertBoard($(this), '${pageType}'); return false;" class="floatR btn">저장</a>
			</div>
		</form>


	</div>
</div>
<script type="text/javascript" src="${contextPath}/resources/js/smarteditor/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "boardContents",
	    sSkinURI: "${contextPath}/resources/js/smarteditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});
</script>
