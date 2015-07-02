<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dgPageNav" uri="/WEB-INF/tlds/PageNavigation.tld" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- <script type="text/javascript" src="${contextPath}/resources/js/board.js"></script> --%>
<script type="text/javascript" src="${contextPath}/resources/js/statistics.js"></script>
<script type="text/javascript">
    $(function() {
        datepicker();
		$('.period').change(function() {
			statisticsUtils.selectPeriod($(this));
		});
    });
</script>
<div class="contents">
	<h3 class="contents-title">통계 관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">활동점수 통계</h4>
			<form id="searchForm" name="searchForm" action="${contextPath}/admin/statistics/list/activityScore">
				<div class="search-box">
					<span>점수 구간</span>
					<select class="sel-w120 marL5 period activityScore" name="activityScore">
						<option value="50" <c:if test="${gradeEntity.activityScore eq 50}">selected="selected"</c:if>>50점</option>
						<option value="100" <c:if test="${gradeEntity.activityScore eq 100}">selected="selected"</c:if>>100점</option>
					</select>
					<span>기간검색</span>
					<input type="text" class="inp-cal marL10 datepicker" name="startDate" value="${gradeEntity.startDate}" readonly="readonly"/>
					<span class="icon-bar">-</span>
					<input type="text" class="inp-cal datepicker" name="endDate" value="${gradeEntity.endDate}" readonly="readonly"/>
					<input type="submit" class="btn-03" value="검색"/>
				</div>
			</form>
        <table class="board-list">
			<colgroup>
				<col width="200"/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<thead>
				<tr>
					<th>총 활동점수</th>
					<th>회원 수</th>
					<th>비율</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty gradeEntity.gradeList}">
					<tr>
						<td colspan="7" class="Last">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
	            <c:forEach items="${gradeEntity.gradeList}" var="ActivityScoreEntity" varStatus="i">
						<tr>
							<td>${ActivityScoreEntity.sectionScore}</td>
							<td>${ActivityScoreEntity.activityScore}명</td>
							<td>${ActivityScoreEntity.userRate}%</td>
						</tr>
	            </c:forEach>
            </tbody>
        </table>
		<div class="btn-area">
			<a href="#" class="floatR btn excel" onclick="statisticsUtils.excelScoreList();return false;">엑셀출력</a>
		</div>
    </div>
</div>
<script type="text/javascript">

</script>
