<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/group.js"></script>
<script>
$(function() {
	commonUtils.sortInit();
});
</script>
<div class="contents">
	<h3 class="contents-title">그룹관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">그룹관리 상세</h4>
		<table class="board-write">
			<colgroup>
				<col width="150"/>
				<col width="150"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<td rowspan="6">
						<c:if test="${groupEntity.groupCoverImageName ne null}">
							<a href="#" onclick="callPopup('/common/popup/image/${groupEntity.groupCoverImageName}/group', '600', '500', 'none');return false;">
								<img src="${contextPath}/common/imgView/?fileName=${groupEntity.groupCoverImageName}&fileType=group" class="img-w320"/>
							</a>
						</c:if>

					</td>
					<th class="borL">그룹명</th>
					<td>${groupEntity.groupName}</td>
				</tr>
				<tr>
					<th class="borL">리더명</th>
					<td>${groupEntity.leaderName}</td>
				</tr>
				<tr>
					<th class="borL">그룹소개</th>
					<td>${groupEntity.groupCoverLine}</td>
				</tr>
				<tr>
					<th class="borL">활동점수</th>
					<td>문제 : ${groupEntity.questionCount} <span class="marL15">답글 : ${groupEntity.answerCount}</span><span class="marL15">활동 : ${groupEntity.activityScore}</span></td>
				</tr>
				<tr>
					<th class="borL">공개상태</th>
					<td>${groupEntity.groupTypeCodeName}</td>
				</tr>
				<tr>
					<th class="borL">등록일</th>
					<td><fmt:formatDate value="${groupEntity.insertGroupDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</tbody>
		</table>
		<div class="btn-area marB40">
			<div class="floatR">
				<a href="#" class="btn" onclick="groupUtils.deleteGroup(${groupEntity.groupKey}); return false;">삭제</a>
				<a href="${contextPath}/admin/group/list" class="btn">목록</a>
			</div>
		</div>
		<p class="search marB5">멤버 수 : <fmt:formatNumber value="${groupMemberEntity.dataSize}" pattern="#,###"/>명</p>
		<table class="board-list">
			<colgroup>
				<col width="50"/>
				<col width="70"/>
				<col width="70"/>
				<col width="80"/>
				<col width="70"/>
				<col width="80"/>
				<col width="100"/>
				<col width="80"/>
				<col width="50"/>
				<col width="50"/>
				<col width="60"/>
				<col width="100"/>
				<col width="*"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>회원분류</th>
					<th>학년</th>
					<th>레벨</th>
					<th>핸드폰</th>
					<th>프랜차이즈</th>
					<th>문제</th>
					<th>답글</th>
					<th>활동 <a href="#" class="btnSort" data-sort-by="activityScore"></a></th>
					<th>가입일</th>
					<th>회원상태</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${groupMemberEntity.dataSize eq 0}">
						<tr><td colspan="13">데이터가 존재하지 않습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${groupMemberEntity.groupMemberList}" varStatus="i">
							<tr>
								<td>${groupMemberEntity.dataSize - (groupMemberEntity.pageListSize * (groupMemberEntity.currentPage -1)) - i.index}</td>
								<td><a href="${contextPath}/admin/user/detail?userKey=${list.userKey}">${list.id}</a></td>
								<td>${list.memberName}</td>
								<td>${list.userTypeCodeName}</td>
								<td>${list.userGradeName}</td>
								<td><p class="personal-level ${list.userRatingClassName}"></p></td>
								<td>${list.phoneNumber}</td>
								<td>${list.franchiseCodeName}</td>
								<td>${list.questionCount}</td>
								<td>${list.answerCount}</td>
								<td><fmt:formatNumber value="${list.activityScore}" pattern="#,###"/></td>
								<td><fmt:formatDate value="${list.acceptDate}" pattern="yyyy-MM-dd"/></td>
								<td class="<c:choose><c:when test="${list.memberStateCode eq '04'}">f-red</c:when><c:otherwise>f-green</c:otherwise></c:choose> bold">
									${list.userStateCodeName}
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="paging-area">
			<dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/group/detail/${groupEntity.groupKey}" pageNavigationEntity="${groupMemberEntity}" />
		</div>
		<form id="searchForm" name="searchForm" action="${contextPath}/admin/group/detail/${groupEntity.groupKey}">
			<input type="hidden" name="sortBy" value="${groupMemberEntity.sortBy}" />
			<input type="hidden" name="sortOrder" value="${groupMemberEntity.sortOrder}" />
		</form>
	</div>
</div>