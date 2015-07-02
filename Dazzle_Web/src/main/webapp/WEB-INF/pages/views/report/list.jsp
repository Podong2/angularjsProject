<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
$(function() {
	datepicker();
});
</script>
<div class="contents">
	<h3 class="contents-title">신고관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">신고관리 목록</h4>
		<form id="searchForm" name="searchForm" action="${contextPath}/admin/report/list">
		<div class="search-box">
			<div class="marB5">
				<span>등록일</span>
				<input type="text" class="inp-cal marL10" id="sd" name="startDate" value="${reportEntity.startDate}" readonly="readonly"/> 
				<span class="icon-bar">-</span>
				<input type="text" class="inp-cal" id="sd" name="endDate" value="${reportEntity.endDate}" readonly="readonly"/>
				<select class="sel-w120 marL5" name="reportTypeCode">
					<option value="">전체</option>
					<option value="01" <c:if test='${reportEntity.reportTypeCode eq "01"}'>selected="selected"</c:if>>문제</option>
					<option value="02" <c:if test='${reportEntity.reportTypeCode eq "02"}'>selected="selected"</c:if>>답글</option>
				</select>	
				<select class="sel-w120 marL5" name="searchKey">
					<option value="">전체</option>
					<option value="reportUserName" <c:if test='${reportEntity.searchKey eq "reportUserName"}'>selected="selected"</c:if>>신고자</option>
					<option value="writeUserName" <c:if test='${reportEntity.searchKey eq "writeUserName"}'>selected="selected"</c:if>>등록자</option>
				</select>
				<input type="text" class="inp-w160 marL10" name="searchValue" value="${reportEntity.searchValue}"/> 
				<input type="submit" class="btn-03" value="검색">
			</div>
		</div>
		</form>
		<h3 class="sub-title-02">&gt; 검색결과 : <fmt:formatNumber value="${reportEntity.dataSize}" pattern="#,###"/>건</h3>
		<table class="board-list">
			<colgroup>
				<col width="60"/>
				<col width="250"/>
				<col width="250"/>
				<col width="170"/>
				<col width="250"/>
				<col width="150"/>
				<col width="150"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>분류</th>
					<th>신고자</th>
					<th>등록자</th>
					<th>등록일</th>
					<th>처리일</th>
					<th>처리상태</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${reportEntity.dataSize eq 0}">
							<tr><td colspan="10">데이터가 존재하지 않습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${reportEntity.reportList}" varStatus="i">
							<tr>
								<td>${reportEntity.dataSize - (reportEntity.pageListSize * (reportEntity.currentPage -1)) - i.index}</td>
								<td><a href="${contextPath}/admin/report/detail/${list.reportKey}">${list.reportTypeCodeName}</a></td>
								<td>
									<a href="${contextPath}/admin/user/detail?userKey=${list.reportUserKey}">
										${list.reportUserName}
									</a>
								</td>
								<td>${list.writeUserName}</td>
								<td><fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${list.reportProcessDate}" pattern="yyyy-MM-dd"/></td>
								<td>${list.reportStateCodeName}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			
				
			</tbody>
		</table>
		<div class="paging-area">
			<dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/report/list" pageNavigationEntity="${reportEntity}" />
		</div>
	</div>
</div>