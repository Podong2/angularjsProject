<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/board.js"></script>
<div class="contents">
	<h3 class="contents-title">이벤트</h3>
	<div class="sub-content">
		<h4 class="sub-title">댓글 목록</h4>
		<form id="searchForm" name="searchForm" action="${contextPath}/admin/board/list">
	        <table class="board-list">
				<colgroup>
					<col width="60"/>
					<col width="60"/>
					<col width="150"/>
					<col width="150"/>
					<col width="60"/>
					<col width="300"/>
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
	                    <th>이름</th>
	                    <th>휴대폰</th>
	                    <th>학년</th>
	                    <th>댓글</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${boardReplyList.dataSize eq 0}">
					<tr>
						<td colspan="6" class="Last">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
	            <c:forEach items="${boardReplyList.boardReplyEntity}" var="boardReplyEntity" varStatus="i">
	                <tr>
	                    <td>${boardReplyList.dataSize-(boardReplyList.pageListSize*(boardReplyList.currentPage-1))-i.index}</td>
	                    <td>${boardReplyEntity.id}</td>
	                    <td>${boardReplyEntity.name}</td>
	                    <td>${boardReplyEntity.phoneNumber}</td>
	                    <td>${boardReplyEntity.userGrade}</td>
	                    <td>${boardReplyEntity.boardReplyContents}</td>
	                </tr>
	            </c:forEach>
	            </tbody>
	        </table>
        </form>
        <div class="paging-area">
            <dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/board/replyList/${boardReplyList.boardReplyEntity[0].boardKey}" pageNavigationEntity="${boardReplyList}" />
        </div>
		<div class="padT30 clear">
			<c:if test="${boardReplyList.dataSize > 0}">
				<a href="#" class="floatR btn excel" onclick="boardUtils.excelBoardList(${boardKey});return false;">엑셀출력</a>
			</c:if>
			<a href="${contextPath}/admin/board/detail/02?boardKey=${boardKey}" class="floatR btn">확인</a>
		</div>
    </div>
</div>

