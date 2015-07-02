<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/setting.js"></script>
<script>
$(function() {
	datepicker();
});
</script>
<div class="contents">
	<h3 class="contents-title">다즐정보</h3>
 	<h4 class="sub-title">한줄알림 수정</h4>
	<div class="sub-content">
		<form id="lineSubmit" name="lineSubmit" method="post">
			<table class="board-write box-pd">
				<colgroup>
					<col width="85"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td class="al-left" style="padding-left:15px;">
							<input type="text" class="subject" maxlength="25" name="lineNoticeContents" id="lineNoticeContents" value="${lineNoticeEntity.lineNoticeContents}" style="width:639px;"/>
							<br/>
							<span class="f-gray">※ 25자 이내로 입력해주세요.</span>
						</td>
					</tr>
					<tr>
						<th>기간</th>
						<td>
		                    <input type="text" class="inp-cal marL10 startDate" name="startDate" id="sd" value="<fmt:formatDate value="${lineNoticeEntity.eventStartDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
		                    <span class="icon-bar">-</span>
		                    <input type="text" class="inp-cal endDate" name="endDate" id="ed" value="<fmt:formatDate value="${lineNoticeEntity.eventEndDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
						</td>
					</tr>
				</tbody>
			</table>

			<div class="btn-area">
			<input type="hidden" name="lineNoticeKey" value="${lineNoticeEntity.lineNoticeKey}" />
				<a href="${contextPath}/admin/board/list/03" class="floatL btn">목록</a>
				<a href="#" onclick="lineNoticeUtils.updateLineNotice(); return false;" class="floatR btn">저장</a>
			</div>
		</form>


	</div>
</div>

