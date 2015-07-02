<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/board.js"></script>
<div class="contents">
	<h3 class="contents-title">다즐정보</h3>
	<c:choose>
		<c:when test="${pageType == '01'}">
		 	<h4 class="sub-title">공지사항 상세</h4>
		</c:when>
		<c:otherwise>
		 	<h4 class="sub-title">이벤트 상세</h4>
		</c:otherwise>
	</c:choose>
	<div class="sub-content">
            <table class="board-write box-pd">
				<colgroup>
					 <col width="150px"/>
					 <col width="340px"/>
					 <col width="150px"/>
					 <col width="340px"/>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td>${boardEntity.subject}</td>
						<th class="borL">조회수</th>
						<td><fmt:formatNumber pattern="#,###" value="${boardEntity.hitsCount}"/></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="hidden" name="attachKey" class="attachKey" value="${boardEntity.attachKey}" />
							<input type="hidden" name="fileName" class="fileName" value="${boardEntity.fileName}" />
							<a class="deco-fileDown" href="${contextPath}/common/fileDownload?origFileNm=${boardEntity.fileOrigName}&fileNm=${boardEntity.fileName}"
                               style="line-height: 30px;">${boardEntity.fileOrigName}</a>
                            <c:if test="${not empty boardEntity.fileName}">
                                <a href="#" onclick="boardUtils.deleteFile('${boardEntity.fileName}', ${boardEntity.attachKey}); return false;" class="floatR btn" >파일삭제</a>
                            </c:if>
						</td>
						<th class="borL">등록일</th>
						<td><fmt:formatDate value="${boardEntity.insertDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3" class="view-area">
						<c:if test="${not empty boardEntity.fileOrigName}">
							<img src="${contextPath}/common/imgView/?fileName=${boardEntity.fileName}&fileType=group" class="img-w640"/><br/><br/>
						</c:if>
							${boardEntity.boardContents}
						</td>
					</tr>
				</tbody>
			</table>


		<div class="btn-area">
			<input type="hidden" value="${boardEntity.boardKey}" name="boardKey" id="boardKey"/>
			<c:if test="${pageType eq '02'}">
				<a href="${contextPath}/admin/board/replyList/${boardEntity.boardKey}" class="floatL btn">댓글목록</a>
			</c:if>
			<a href="${contextPath}/admin/board/list/${pageType}" class="floatL btn">목록</a>
			<a href="/admin/board/boardForm/${pageType}?boardKey=${boardEntity.boardKey}" class="floatR btn">수정</a>
			<a href="#" onclick="boardUtils.deleteBoard(${boardEntity.boardKey}, ${pageType}); return false;" class="floatR btn">삭제</a>
		</div>
	</div>
</div>