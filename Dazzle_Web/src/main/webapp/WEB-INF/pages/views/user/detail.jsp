<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
		commonUtils.checkNumberType();
	});
</script>
<div class="contents">
	<h3 class="contents-title">회원관리</h3>
    <h4 class="sub-title">회원 상세</h4>
	<div class="sub-content">
        <form id="lineTalkForm" name="lineTalkForm">
            <table class="board-write box-pd">
                <colgroup>
                    <col width="150"/>
                    <col width="*"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>가입일</th>
                    <td>${userEntity.insertDate}</td>
                </tr>
                <tr>
                    <th>탈퇴일</th>
                    <td>
                        <c:if test="${not empty userEntity.deleteDate}">
                            ${userEntity.deleteDate}
                            <input type="hidden" id="deleteDate" name="deleteDate" value="${userEntity.deleteDate}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td>${userEntity.id}</td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>${userEntity.name}</td>
                </tr>
                <tr>
                    <th>학년</th>
                    <td>${userEntity.userGrade}</td>
                </tr>
                <tr>
                    <th>핸드폰</th>
                    <td>${userEntity.phoneNumber}</td>
                </tr>
                <tr>
                    <th>프랜차이즈</th>
                    <td>${userEntity.franchiseCodeName}</td>
                </tr>
                <tr>
                    <th>회원분류</th>
                    <td>
                        <select class="sel-w100" id="typeCode" name="typeCode">
                            <option <c:if test = "${userEntity.typeCode eq '01'}">selected="selected" </c:if> value="01">일반</option>
                            <option <c:if test = "${userEntity.typeCode eq '02'}">selected="selected" </c:if> value="02">전문가</option>
                            <option <c:if test = "${userEntity.typeCode eq '03'}">selected="selected" </c:if> value="03">관리자</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>활동점수</th>
                    <td>
	                    문제 : <fmt:formatNumber pattern="#,###" value="${userEntity.questionCount}"/><span class="marL15">
	                    답글 : <fmt:formatNumber pattern="#,###" value="${userEntity.answerCount}"/></span><span class="marL15">
	                    활동 : <fmt:formatNumber pattern="#,###" value="${userEntity.activityScore}"/></span><span class="marL15">
	                    활동점수</span><input type="text" class="inp-w160 marL5 numChk" name="activityScore"/>
                    	<a href="#" data-action="/admin/updateUserActivityScore" data-activity="true" class="btn-03 updateSubmit">추가</a></td>
                </tr>
                <tr>
                    <th>한줄 토크</th>
                    <td>
                        <input type="hidden" id="userKey" name="userKey" value="${userEntity.userKey}"/>
                        <input type="hidden" id="writerKey" name="writerKey" value="${adminKey}"/>
                        <input type="text" class="inp-w90per" id="talkContents" name="talkContents"/>
                        <a href="#" class="btn-03 linetalksubmit">전송</a>
                    </td>
                </tr>
                <tr>
                    <th>회원상태</th>
                    <td>
                        <select class="sel-w100" id="stateCode" name="stateCode">
                            <option <c:if test = "${userEntity.stateCode eq '01'}">selected="selected" </c:if> value="01">정상</option>
                            <option <c:if test = "${userEntity.stateCode eq '02'}">selected="selected" </c:if> value="02">탈퇴</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>

		<div class="btn-area">
			<a href="${contextPath}/admin/user/list" class="floatL btn">목록</a>
			<a href="#" data-action="/admin/updateUser" class="floatR btn updateUserSubmit">저장</a>
		</div>
	</div>
</div>