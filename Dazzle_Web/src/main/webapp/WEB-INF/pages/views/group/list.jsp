<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<%@ taglib prefix="dgCutText" uri="/WEB-INF/tlds/CutText.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/group.js"></script>
<script>
$(function() {
	datepicker();
	commonUtils.sortInit();
});
</script>
<div class="contents">
	<h3 class="contents-title">그룹관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">그룹관리 목록</h4>
		<form id="searchForm" name="searchForm" action="${contextPath}/admin/group/list">
		<input type="hidden" name="sortBy" value="${groupEntity.sortBy}" />
		<input type="hidden" name="sortOrder" value="${groupEntity.sortOrder}" />
		<div class="search-box">
			<div class="marB5">
				<span>등록일</span>
				<input type="text" class="inp-cal marL10" id="sd" name="startDate" value="${groupEntity.startDate}" readonly="readonly"/>
				<span class="icon-bar">-</span>
				<input type="text" class="inp-cal" id="ed" name="endDate" value="${groupEntity.endDate}" readonly="readonly"/>
				<select class="sel-w120 marL5" name="groupTypeCode">
					<option value="">전체</option>
					<option value="01" <c:if test='${groupEntity.groupTypeCode eq "01"}'>selected="selected"</c:if>>공개</option>
					<option value="02" <c:if test='${groupEntity.groupTypeCode eq "02"}'>selected="selected"</c:if>>비공개</option>
				</select>
				<select class="sel-w120 marL5" name="searchKey">
					<option value="">전체</option>
					<option value="groupName" <c:if test='${groupEntity.searchKey eq "groupName"}'>selected="selected"</c:if>>그룹명</option>
					<option value="leaderName" <c:if test='${groupEntity.searchKey eq "leaderName"}'>selected="selected"</c:if>>리더명</option>
				</select>
				<input type="text" class="inp-w160 marL10" name="searchValue" value="${groupEntity.searchValue}"/>
				<input type="submit" class="btn-03" value="검색">
			</div>
		</div>
		</form>
		<h3 class="sub-title-02">&gt; 검색결과 : <fmt:formatNumber value="${groupEntity.dataSize}" pattern="#,###"/>건</h3>
		<table class="board-list">
			<colgroup>
				<col width="60"/>
				<col width="250"/>
				<col width="100"/>
				<col width="250"/>
				<col width="200"/>
				<col width="170"/>
				<col width="170"/>
				<col width="170"/>
				<col width="170"/>
				<col width="250"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>그룹명</th>
					<th>등급</th>
					<th>공개상태</th>
					<th>리더명</th>
					<th>멤버수</th>
					<th>문제</th>
					<th>답글</th>
					<th>활동 <a href="#" class="btnSort" data-sort-by="activityScore"></a></th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${groupEntity.dataSize eq 0}">
							<tr><td colspan="10">데이터가 존재하지 않습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${groupEntity.groupList}" varStatus="i">
							<tr>
								<td>${groupEntity.dataSize - (groupEntity.pageListSize * (groupEntity.currentPage -1)) - i.index}</td>
								<td>
									<a href="${contextPath}/admin/group/detail/${list.groupKey}">
										<dgCutText:cutTextTag text="${list.groupName}" length="15" ellipsis="..." />
									</a>
								</td>
								<td><p class="group-level ${list.groupRatingClassName}"></p></td>
								<td>${list.groupTypeCodeName}</td>
								<td><a href="${contextPath}/admin/user/detail?userKey=${list.userKey}">${list.leaderName}</a></td>
								<td><fmt:formatNumber value="${list.groupMemberCount}" pattern="#,###"/></td>
								<td><fmt:formatNumber value="${list.questionCount}" pattern="#,###"/></td>
								<td><fmt:formatNumber value="${list.answerCount}" pattern="#,###"/></td>
								<td><fmt:formatNumber value="${list.activityScore}" pattern="#,###"/></td>
								<td><fmt:formatDate value="${list.insertGroupDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<c:if test="${groupEntity.dataSize ne 0}">
			<div class="btn-area">
				<a href="#" class="floatR btn excel" onclick="groupUtils.excelGroupList();return false;">엑셀출력</a>
			</div>
		</c:if>
		<div class="paging-area">
			<dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/group/list" pageNavigationEntity="${groupEntity}" />
		</div>
	</div>
</div>