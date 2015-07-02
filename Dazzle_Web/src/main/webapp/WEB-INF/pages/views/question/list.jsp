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
	<h3 class="contents-title">문제등록관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">
		<c:if test="${questionType eq 'all'}">전체문제 관리 목록</c:if>
		<c:if test="${questionType eq 'daq'}">다Q문제 관리 목록</c:if>
		</h4>
		<form id="searchForm" name="searchForm" action="${contextPath}/admin/question/list/${questionType}">
		<div class="search-box">
			<span>등록일</span>
			<input type="text" class="inp-cal marL10" id="sd" name="startDate" value="${questionEntity.startDate}" readonly="readonly"/>
			<span class="icon-bar">-</span>
			<input type="text" class="inp-cal" id="ed" name="endDate" value="${questionEntity.endDate}" readonly="readonly"/>
			<c:if test="${questionType eq 'all'}">
				<select class="sel-w120 marL10" name="searchQuestionType">
					<option value="01" <c:if test='${questionEntity.searchQuestionType eq "01"}'>selected="selected"</c:if>>전체문제</option>
					<option value="02" <c:if test='${questionEntity.searchQuestionType eq "02"}'>selected="selected"</c:if>>안풀린문제</option>
				</select>
				<select class="sel-w120 marL10" name="searchKey">
					<option value="">전체</option>
					<option value="name" <c:if test='${questionEntity.searchKey eq "name"}'>selected="selected"</c:if>>이름</option>
					<option value="questionTypeLargeName">문제유형</option>
				</select>
				<input type="text" class="inp-w160 marL10" name="searchValue" value="${questionEntity.searchValue}"/>
			</c:if>
			<input type="submit" class="btn-03" value="검색">
		</div>
		</form>
		<ul class="list-03">
			<c:choose>
				<c:when test="${questionEntity.dataSize eq 0}">
					<div class="al-center">데이터가 존재하지 않습니다.</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${questionEntity.questionList}" varStatus="i">
						<li <c:if test="${(i.index % 5) eq 0}">class="first"</c:if>>
							<a href="${contextPath}/admin/question/detail/${questionType}/${list.questionKey}">
								<c:choose>
									<c:when test="${empty list.questionImageName}">
										<img src="/resources/img/no-image02.gif"/>
									</c:when>
									<c:otherwise>
										<img src="${contextPath}/common/imgView/?fileName=${list.questionImageName}&fileType=question"/>
									</c:otherwise>
								</c:choose>
								<ul>
									<li><span class="f-blue">${list.name}</span></li>
									<li><fmt:formatDate value="${list.insertDate}" pattern="yyyy-MM-dd"/></li>
									<c:if test="${questionType eq 'daq'}">
										<li>
											<span <c:if test="${list.exposeRemainDay > 0}">class="f-green"</c:if>>
											<fmt:formatDate value="${list.exposeStartDate}" pattern="yyyy-MM-dd"/> ~
											<fmt:formatDate value="${list.exposeEndDate}" pattern="yyyy-MM-dd"/>
											</span>
										</li>
										<li><span class="f-red">답글${list.answerCount}</span></li>
									</c:if>
									<c:if test="${questionType eq 'all'}">
									<li>${list.questionGradeCodeName} ${list.questionTypeLargeName}</li>
									<li>
										<span class="f-blue">${list.questionTypeCodeName}</span> |
										<span class="f-red">답글${list.answerCount}</span></li>
									</c:if>
								</ul>
							</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
		<c:if test="${questionType eq 'daq'}">
			<div class="padT30 clear">
				<a href="${contextPath}/admin/question/daqForm/0" class="floatR btn">문제등록</a>
			</div>
		</c:if>

		<div class="paging-area">
			<dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/question/list/${questionType}" pageNavigationEntity="${questionEntity}" />
		</div>
	</div>
</div>