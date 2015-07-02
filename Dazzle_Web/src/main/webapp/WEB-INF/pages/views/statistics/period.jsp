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
		var date = '${gradeEntity.searchValue}';
		if(date == '' || date =='day'){
			datepickerUtils.datepicker();
			$('input[name=startDay]').val('${gradeEntity.startDay}');
			$('input[name=endDay]').val('${gradeEntity.endDay}');
		}else if(date == 'month'){
			datepickerUtils.monthDatePicker();
			$('input[name=startMonth]').val('${gradeEntity.startMonth}');
			$('input[name=endMonth]').val('${gradeEntity.endMonth}');
		}else if(date =='year'){
			datepickerUtils.yearDatePicker();
			$('input[name=startYear]').val('${gradeEntity.startYear}');
			$('input[name=endYear]').val('${gradeEntity.endYear}');
		}
		
		// 기간별 선택 시 달력 변경
		$('.period').change(function() {
			date = $('.period').val();
			if(date == 'year'){
				datepickerUtils.yearDatePicker();
			}else if(date == 'month'){
				datepickerUtils.monthDatePicker();

			}else{
				datepickerUtils.datepicker();
			}

		});
    });
</script>
<style>
/*   .ui-datepicker-calendar { display: none; } */
.hide-calendar .ui-datepicker-calendar {
    display: none;
}
</style>
<div class="contents">
	<h3 class="contents-title">통계 관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">학년별 통계</h4>
        <form id="searchForm" name="searchForm" action="${contextPath}/admin/statistics/list/period">
            <div class="search-box">
					<select class="sel-w120 marL5 period searchValue" name="searchValue">
						<option value="day">선택</option>
						<option value="day" <c:if test="${gradeEntity.searchValue eq 'day'}">selected="selected"</c:if> >일별</option>
						<option value="month" <c:if test="${gradeEntity.searchValue eq 'month'}">selected="selected"</c:if>>월별</option>
						<option value="year" <c:if test="${gradeEntity.searchValue eq 'year'}">selected="selected"</c:if>>년별</option>
					</select>
					<span>가입일</span>
					<input type="text" class="inp-cal marL10 periodInput datepicker" id="sd" data-calendar="true" name="startDay" value="${gradeEntity.startDay}" readonly="readonly"/>
					<input type="text" class="inp-cal marL10 periodInput monthpicker" id="monthpicker" data-calendar="false" style="display: none;" name="startMonth" value="${gradeEntity.startMonth}" readonly="readonly"/>
					<input type="text" class="inp-cal marL10 periodInput yearpicker" id="yearpicker" data-calendar="false" style="display: none;" name="startYear" value="${gradeEntity.startYear}" readonly="readonly"/>
					<span class="icon-bar">-</span>
					<input type="text" class="inp-cal periodInput datepicker" id="ed" data-calendar="true" name="endDay" value="${gradeEntity.endDay}" readonly="readonly"/>
					<input type="text" class="inp-cal periodInput monthpicker" id="monthpicker" data-calendar="false" style="display: none;" name="endMonth" value="${gradeEntity.endMonth}" readonly="readonly"/>
					<input type="text" class="inp-cal periodInput yearpicker" id="yearpicker" data-calendar="false" style="display: none;" name="endYear" value="${gradeEntity.endYear}" readonly="readonly"/>
					<input type="submit" class="btn-03" value="검색"/>
            </div>
        </form>
        <table class="board-list">
			<colgroup>
				<col width="200"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<thead>
				<tr>
					<th></th>
					<th>문제 수</th>
					<th>답글 수</th>
					<th>미답글 수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty gradeEntity.gradeList}">
					<tr>
						<td colspan="4" class="Last">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
            <c:forEach items="${gradeEntity.gradeList}" var="periodEntity" varStatus="i">
					<tr>
						<td>
							${periodEntity.resultDate}
						</td>
						<td>${periodEntity.questionCount}</td>
						<td>${periodEntity.answerCount}</td>
						<td>${periodEntity.noAnswerCount}</td>
					</tr>
            </c:forEach>
            </tbody>
        </table>
		<div class="btn-area">
			<a href="#" class="floatR btn excel" onclick="statisticsUtils.excelPeriodList();return false;">엑셀출력</a>
		</div>
    </div>
</div>
<script type="text/javascript">

</script>
