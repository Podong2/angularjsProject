<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
		datepicker();
	});
</script>
<div class="contents">
	<h3 class="contents-title">회원관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">회원 목록</h4>
        <form id="searchForm" name="searchForm" action="${contextPath}/admin/user/list">
            <div class="search-box">
                <div class="marB5">
                    <span>가입일</span>
                    <input type="text" class="inp-cal marL10" id="sd" name="startDate" value="${userList.startDate}" readonly="readonly"/>
                    <span class="icon-bar">-</span>
                    <input type="text" class="inp-cal" id="ed" name="endDate" value="${userList.endDate}" readonly="readonly"/>
                    <span class="marL10">회원분류</span>
                    <select class="sel-w120 marL5" name="typeCode">
                        <option value="">전체</option>
                        <option value="01" <c:if test='${userList.typeCode eq "01"}'>selected="selected"</c:if>>일반</option>
                        <option value="02" <c:if test='${userList.typeCode eq "02"}'>selected="selected"</c:if>>전문가</option>
                        <option value="03" <c:if test='${userList.typeCode eq "03"}'>selected="selected"</c:if>>관리자</option>
                    </select>
                    <span class="marL10">학년</span>
                    <select class="sel-w120 marL5" name="userGrade">
                        <option value="">전체</option>
                        <option value="10" <c:if test='${userList.userGrade eq "10"}'>selected="selected"</c:if>>초등1</option>
                        <option value="20" <c:if test='${userList.userGrade eq "20"}'>selected="selected"</c:if>>초등2</option>
                        <option value="30" <c:if test='${userList.userGrade eq "30"}'>selected="selected"</c:if>>초등3</option>
                        <option value="40" <c:if test='${userList.userGrade eq "40"}'>selected="selected"</c:if>>초등4</option>
                        <option value="50" <c:if test='${userList.userGrade eq "50"}'>selected="selected"</c:if>>초등5</option>
                        <option value="60" <c:if test='${userList.userGrade eq "60"}'>selected="selected"</c:if>>초등6</option>
                        <option value="70" <c:if test='${userList.userGrade eq "70"}'>selected="selected"</c:if>>중등1</option>
                        <option value="80" <c:if test='${userList.userGrade eq "80"}'>selected="selected"</c:if>>중등2</option>
                        <option value="90" <c:if test='${userList.userGrade eq "90"}'>selected="selected"</c:if>>중등3</option>
                        <option value="C0" <c:if test='${userList.userGrade eq "C0"}'>selected="selected"</c:if>>고등</option>
                        <option value="00" <c:if test='${userList.userGrade eq "00"}'>selected="selected"</c:if>>일반</option>
                    </select>
                    <span class="marL10">프렌차이즈</span>
                    <select class="sel-w120 marL5" name="franchiseType">
                        <option value="">전체</option>
                        <option value="01" <c:if test='${userList.franchiseType eq "01"}'>selected="selected"</c:if>>해법수학</option>
                        <option value="02" <c:if test='${userList.franchiseType eq "02"}'>selected="selected"</c:if>>셀파</option>
                    </select>

                </div>
                <select class="sel-w120 marL10" name="searchKey">
                    <option value="">전체</option>
                    <option value="id" <c:if test='${userList.searchKey eq "id"}'>selected="selected"</c:if>>아이디</option>
                    <option value="name" <c:if test='${userList.searchKey eq "name"}'>selected="selected"</c:if>>이름</option>
                    <option value="phoneNumber" <c:if test='${userList.searchKey eq "phoneNumber"}'>selected="selected"</c:if>>핸드폰</option>
                </select>
                <select class="sel-w120 marL10" name="stateCode">
                    <option value="">전체</option>
                    <option value="01" <c:if test='${userList.stateCode eq "01"}'>selected="selected"</c:if>>정상</option>
                    <option value="02" <c:if test='${userList.stateCode eq "02"}'>selected="selected"</c:if>>탈퇴</option>
                    <option value="03" <c:if test='${userList.stateCode eq "03"}'>selected="selected"</c:if>>강퇴</option>
                </select>
                <input type="text" class="inp-w160 marL10" name="searchValue" value="${userList.searchValue}"/> <input type="submit" class="btn-03" value="검색"/>
            </div>
        </form>
        <h3 class="sub-title-02">&gt; 총 회원수 : ${userList.dataSize}명</h3>
        <table class="board-list">
			<colgroup>
				<col width="60"/>
				<col width="250"/>
				<col width="250"/>
				<col width="170"/>
				<col width="140"/>
				<col width="100"/>
				<col width="250"/>
				<col width="190"/>
				<col width="250"/>
				<col width="250"/>
				<col width="120px"/>
				<col width="130"/>
				<col width="230"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>회원분류</th>
					<th>학년</th>
					<th>등급</th>
					<th>핸드폰</th>
					<th>프렌차이즈</th>
					<th>문제</th>
					<th>답글</th>
					<th><span style="display:inline-block;">활동</span>
					<form id="searchForm" name="searchForm" action="${contextPath}/admin/user/list" style="display:inline-block;">
						<input type="submit" class="ActivitySubmit" id="ActivitySubmit" style="display: none;"/>
						<c:if test="${empty arrayType || arrayType eq 'asc'}">
							<label for="ActivitySubmit" style="cursor: pointer;">▲</label>
							<input type="hidden" name="arrayType" value="desc"/>
						</c:if>
						<c:if test="${arrayType eq 'desc'}">
							<label for="ActivitySubmit" style="cursor: pointer;">▼</label>
							<input type="hidden" name="arrayType" value="asc"/>
						</c:if>
					</form>
					</th>
					<th>가입상태</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${userList.dataSize eq 0}">
				<tr>
					<td colspan="13" class="Last">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
            <c:forEach items="${userList.userEntity}" var="userEntity" varStatus="i">
                <tr>
                    <td>${userList.dataSize - (userList.pageListSize * (userList.currentPage -1)) - i.index}</td>
                    <td><a href="${contextPath}/admin/user/detail?userKey=${userEntity.userKey}" data-action="${contextPath}/admin/user/detail" data-userkey="${userEntity.userKey}">${userEntity.id}</a></td>
                    <td>${userEntity.name}</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${userEntity.typeCode == '01'}">일반</c:when>
                    		<c:when test="${userEntity.typeCode == '02'}">전문가</c:when>
                    		<c:when test="${userEntity.typeCode == '03'}">관리자</c:when>
                    		<c:otherwise>-</c:otherwise>
                    	</c:choose>
                    </td>
                    <td>${userEntity.userGrade}</td>
                    <td>
                    	<p class="personal-level ${userEntity.userRating}"></p>
                    </td>
                    <td>
                    	<c:choose>
                    		<c:when test="${not empty userEntity.phoneNumber}">
                    		${userEntity.phoneNumber}</c:when>
                    		<c:otherwise>-</c:otherwise>
                    	</c:choose>
                    </td>
                    <td>
                    	<c:choose>
                    		<c:when test="${userEntity.franchiseType == '01'}">해법수학</c:when>
                    		<c:when test="${userEntity.franchiseType == '02'}">셀파</c:when>
                    		<c:otherwise>-</c:otherwise>
                    	</c:choose>
                    </td>
                    <td><fmt:formatNumber pattern="#,###" value="${userEntity.questionCount}"/></td>
                    <td><fmt:formatNumber pattern="#,###" value="${userEntity.answerCount}"/></td>
                    <td><fmt:formatNumber pattern="#,###" value="${userEntity.activityScore}"/></td>
                    <td>
                    	<c:choose>
                    		<c:when test="${ not empty userEntity.coercionDeleteDate }">
                    			강퇴
                    		</c:when>
                    		<c:otherwise>
    		                ${userEntity.stateCode}
                    		</c:otherwise>
                    	</c:choose>
                    </td>
                    <td>${userEntity.insertDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="btn-area">
			<a href="#" class="floatR btn excel" onclick="userUtils.excelUserList();return false;">엑셀출력</a>
		</div>
        <div class="paging-area">
            <dgPageNav:PageNavigation pageParamName="currentPage" linkUrl="${contextPath}/admin/user/list" pageNavigationEntity="${userList}" />
        </div>
    </div>
</div>