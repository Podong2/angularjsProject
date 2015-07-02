<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="contents">
	<h3 class="contents-title">비밀번호 설정</h3>
	<div class="sub-content">
		<h4 class="sub-title">비밀번호 변경</h4>
			<table class="board-list al-center">
				<colgroup>
					<col width="15%"/>
					<col width="85%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>현재 비밀번호</th>
						<td class="align-left" style="border-top:1px solid #d0d0d0;"><input type="password" class="inp-w220 marL10 oldPassword"/></td>
					</tr>
					<tr>
						<th>새 비밀번호</th>
						<td class="align-left"><input type="password" class="inp-w220 marL10 newPassword1"/></td>
					</tr>
					<tr>
						<th>새 비밀번호 확인</th>
						<td class="align-left"><input type="password" class="inp-w220 marL10 newPassword2"/></td>
					</tr>
				</tbody>
			</table>
			<div class="btn-area">
				<a href="#" class="floatR btn passswordBtn" data-action="${contextPath }/admin/doUpdatePassword" data-id="${sessionScope.adminLoginSession.id}" data-type="ajax">저장</a>
			</div>
	</div>
</div>