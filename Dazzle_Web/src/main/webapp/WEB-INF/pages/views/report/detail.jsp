<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/report.js"></script>
<div class="contents">
	<h3 class="contents-title">신고관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">신고관리 상세</h4>
		<input type="hidden" id="reportKey" name="reportKey" value="${reportEntity.reportKey}" />
		<table class="board-write box-pd">
			<colgroup>
				<col width="150"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<th>신고분류</th>
					<td>${reportEntity.reportTypeCodeName}</td>
				</tr>
				<tr>
					<th>신고자</th>
					<td>${reportEntity.reportUserName}</td>
				</tr>
				<tr>
					<th>등록자</th>
					<td>${reportEntity.writeUserName}</td>
				</tr>
				<tr>
					<th>신고내용</th>
					<td>${reportEntity.reportContent}</td>
				</tr>
				<tr>
					<th>처리상태</th>
					<td>
						<select class="sel-w100" id="reportStateCode" name="reportStateCode">
							<option value="01" <c:if test='${reportEntity.reportStateCode eq "01"}'>selected="selected"</c:if>>대기</option>
							<option value="02" <c:if test='${reportEntity.reportStateCode eq "02"}'>selected="selected"</c:if>>완료</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>한줄토크</th>
					<td>
						<input type="text" class="inp-w90per" id="talkContents" name="talkContents" value="" />
						<input type="hidden" name="adminKey" value="${adminKey}" /> 
						<input type="hidden" name="writeUserKey" value="${reportEntity.writeUserKey}" />
						<a href="#" class="btn-03" onclick="reportUtils.insertLineTalk(); return false;">전송</a>
					</td>
				</tr>
				<tr>
					<th>메모 남기기</th>
					<td>
						<input type="text" class="inp-w90per" name="memoContents"/>
						<a href="#" class="btn-03" onclick="reportUtils.insertReportMemo(); return false;">등록</a>
					</td>
				</tr>
				<tr>
					<th>메모내용</th>
					<td>
						<div class="floatL">
							<c:if test="${fn:length(reportMemoList) eq 0}">
								<p>등록된 메모가 없습니다.</p>
							</c:if>
							<c:forEach var="list" items="${reportMemoList}" varStatus="i">
								<p><fmt:formatDate value="${list.insertDate}" pattern="yyyy-MM-dd"/><span class="icon-bar">|</span>${list.memoContents}</p>
							</c:forEach>
						</div>
						<div class="floatR">
							<c:forEach var="list" items="${reportMemoList}" varStatus="i">
								<p><a href="#" class="f-gray bold" onclick="reportUtils.deleteReportMemo('${list.reportMemoKey}'); return false;">삭제</a></p>
							</c:forEach>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-area">
			<a href="" class="floatL btn" onclick="callPopup('/admin/report/popup/reportDetail/${reportEntity.questionKey}', '770', '767', 'none');return false;">신고문제보기</a>
			<a href="${contextPath}/admin/report/list" class="floatL btn">목록</a>
			<a href="#" class="floatR btn" onclick="reportUtils.updateReportState(); return false;">저장</a>
		</div>
	</div>
</div>