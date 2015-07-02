<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/setting.js"></script>
<script>
	$(function() {
		datepicker();
	});
</script>
<div class="contents">
	<h3 class="contents-title">다즐정보</h3>
	<div class="sub-content">
		<h4 class="sub-title">한줄알림</h4>
        <form id="insertForm" name="insertForm" >
			<table class="board-write box-pd">
				<colgroup>
					<col width="85"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td class="al-left" style="padding-left:15px;" colspan="">
							<input type="text" class="subject" maxlength="25" name="lineNoticeContents" id="lineNoticeContents"  style="width:639px;"/>
							<br/>
							<span class="f-gray">※ 25자 이내로 입력해주세요.</span>
						</td>
					</tr>
					<tr>
						<th>기간</th>
						<td>
		                    <input type="text" class="inp-cal marL10 startDate" name="startDate" id="sd" >
		                    <span class="icon-bar">-</span>
		                    <input type="text" class="inp-cal endDate" name="endDate" id="ed"/>
		                    <a href="#" class="btn-03 floatR"
		                    onclick="lineNoticeUtils.insertLineNotice(); return false;" >등록</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
        <table class="board-list">
			<colgroup>
				<col width="60"/>
				<col width="350"/>
				<col width="150"/>
				<col width="50"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>기간</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${lineNoticeList.dataSize eq 0}">
						<tr><td colspan="3" class="al-center">데이터가 존재하지 않습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${lineNoticeList.lineNoticeList}" var="lineNoticeEntity" varStatus="i">
							<tr>
								<td>${lineNoticeList.dataSize-(lineNoticeList.pageListSize*(lineNoticeList.currentPage-1))-i.index}</td>
								<td>
									<a href="${contextPath}/admin/board/lineNoticeEdit/03/${lineNoticeEntity.lineNoticeKey}">
										${lineNoticeEntity.lineNoticeContents}
									</a>
								</td>
								<td><fmt:formatDate value="${lineNoticeEntity.eventStartDate}" pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${lineNoticeEntity.eventEndDate}" pattern="yyyy-MM-dd"/></td>
								<td>
									<a href="#" class="btn" onclick="lineNoticeUtils.deleteLineNotice(${lineNoticeEntity.lineNoticeKey}); return false;">삭제</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
        </table>
	    <div class="paging-area">
	        <dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/board/list/03" pageNavigationEntity="${lineNoticeList}" />
	    </div>

	</div>
</div>

