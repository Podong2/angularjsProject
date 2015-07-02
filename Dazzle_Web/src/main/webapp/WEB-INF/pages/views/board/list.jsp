<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/board.js"></script>
<div class="contents">
	<h3 class="contents-title">다즐정보</h3>
	<div class="sub-content">
		<c:choose>
			<c:when test="${pageType == '01'}">
			 	<h4 class="sub-title">공지사항 목록</h4>
			</c:when>
			<c:otherwise>
			 	<h4 class="sub-title">이벤트 목록</h4>
			</c:otherwise>
		</c:choose>
        <form id="searchForm" name="searchForm" action="${contextPath}/admin/board/list/${pageType}">
            <div class="search-box">
                <select class="sel-w120 marL10" name="searchKey">
                    <option value="subject" <c:if test='${boardList.searchKey eq "subject"}'>selected="selected"</c:if>>제목</option>
                    <option value="contents" <c:if test='${boardList.searchKey eq "contents"}'>selected="selected"</c:if>>내용</option>
                </select>
                <input type="text" class="inp-w160 marL10" name="searchValue" value="${boardList.searchValue}"/> <input type="submit" class="btn-03" value="검색"/>
            </div>
        </form>
        <table class="board-list">
			<colgroup>
				<col width="60"/>
				<col width="350"/>
				<col width="150"/>
				<c:if test="${pageType eq '02'}">
					<col width="150"/>
					<col width="150"/>
					<col width="150"/>
				</c:if>
				<col width="60"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
                    <th>등록일</th>
                    <c:if test="${pageType eq '02'}">
	                    <th>시작일</th>
	                    <th>종료일</th>
	                    <th>진행상태</th>
                    </c:if>
                    <th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${boardList.dataSize eq 0}">
				<tr>
					<td colspan="7" class="Last">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${pageType eq '01'}">
				<c:forEach items="${boardList.noticeList}" var="noticeEntity" varStatus="i">
	                <tr>
	                    <td><b style="font-size: 13px;">[메인노출]</b></td>
	                    <td><a href="${contextPath}/admin/board/detail/${pageType}?boardKey=${noticeEntity.boardKey}"  data-action="${contextPath}/admin/board/detail" data-boardKey="${noticeEntity.boardKey}">${noticeEntity.subject}</a></td>
	                    <td><fmt:formatDate value="${noticeEntity.insertDate}" pattern="yyyy-MM-dd"/></td>
	                    <td><fmt:formatNumber pattern="#,###" value="${noticeEntity.hitsCount}"/></td>
	                </tr>
               </c:forEach>
			</c:if>
            <c:forEach items="${boardList.boardEntity}" var="boardEntity" varStatus="i">
                <tr>
                    <td>
	                    <c:if test="${pageType eq '01'}">
	                    	<c:choose>
	                    		<c:when test="${boardEntity.topExposeYn eq 'Y'}">
	                    			[메인노출]
	                    		</c:when>
	                    		<c:otherwise>
			                    	${boardList.dataSize-(boardList.pageListSize*(boardList.currentPage-1))-i.index}
	                    		</c:otherwise>
	                    	</c:choose>
	    				</c:if>
	    				<c:if test="${pageType eq '02'}">
	    					${boardList.dataSize-(boardList.pageListSize*(boardList.currentPage-1))-i.index}
	    				</c:if>
                    </td>
                    <td><a href="${contextPath}/admin/board/detail/${pageType}?boardKey=${boardEntity.boardKey}" data-action="${contextPath}/admin/board/detail" data-boardKey="${boardEntity.boardKey}">${boardEntity.subject}</a></td>
                    <td><fmt:formatDate value="${boardEntity.insertDate}" pattern="yyyy-MM-dd"/></td>
                    <c:if test="${pageType eq '02'}">
	                    <td><fmt:formatDate value="${boardEntity.eventStartDate}" pattern="yyyy-MM-dd"/></td>
	                    <td><fmt:formatDate value="${boardEntity.eventEndDate}" pattern="yyyy-MM-dd"/></td>
	                    <td>
	                    	<c:choose>
		                    	<c:when test="${not empty boardEntity.insertState}"><span style="color:#009900;">진행중</span></c:when>
		                    	<c:otherwise><span style="color:#9A9A9A;">-</span></c:otherwise>
	                    	</c:choose>
	                    </td>
                    </c:if>
                    <td><fmt:formatNumber pattern="#,###" value="${boardEntity.hitsCount}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
		<div class="padT30 clear">
			<a href="${contextPath}/admin/board/boardForm/${pageType}" class="floatR btn">글쓰기</a>
		</div>
        <div class="paging-area">
            <dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/board/list/${pageType}" pageNavigationEntity="${boardList}" />
        </div>
    </div>
</div>

