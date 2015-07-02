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
    });
</script>
<div class="contents">
	<h3 class="contents-title">통계 관리</h3>
	<div class="sub-content">
		<h4 class="sub-title">학년별 통계</h4>
        <form id="searchForm" name="searchForm" action="${contextPath}/admin/statistics/list/grade">
            <div class="search-box">
					<span>가입일</span>
					<input type="text" class="inp-cal marL10 datepicker" name="startDate" value="${gradeEntity.startDate}" readonly="readonly"/>
					<span class="icon-bar">-</span>
					<input type="text" class="inp-cal datepicker" name="endDate" value="${gradeEntity.endDate}" readonly="readonly"/>
					<input type="submit" class="btn-03" value="검색"/>
            </div>
        </form>
        <table class="board-list">
			<colgroup>
				<col width="250"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<thead>
				<tr>
					<th>학년</th>
					<th>문제 수</th>
					<th>답글 수</th>
					<th>미답글 수</th>
				</tr>
			</thead>
			<tbody>
<!-- 				<tr> -->
<!-- 					<td colspan="7" class="Last">데이터가 존재하지 않습니다.</td> -->
<!-- 				</tr> -->
					<tr>
						<td>전체
						</td>
						<td>${gradeEntity.questionCountTotal}</td>
						<td>${gradeEntity.answerCountTotal}</td>
						<td>${gradeEntity.noAnswerCountTotal}</td>
					</tr>
            <c:forEach items="${gradeEntity.gradeList}" var="gradeEntity" varStatus="i">
					<tr>
						<td>
							<c:choose>
								<c:when test="${gradeEntity.commCdVal eq '00' }">
									${gradeEntity.commCdNm}
								</c:when>
								<c:otherwise>
									<a href="" name="statisticsPopup" onclick="callStatisticsPopup(
										'/admin/statistics/gradeDetail/${gradeEntity.commCdVal}/${gradeEntity.commCdNm}' , '770', '655', 'none');return false;">
									${gradeEntity.commCdNm}
									</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${gradeEntity.questionCount}</td>
						<td>${gradeEntity.answerCount}</td>
						<td>${gradeEntity.noAnswerCount}</td>
					</tr>
            </c:forEach>
            </tbody>
        </table>
		<div class="btn-area">
			<a href="#" class="floatR btn excel" onclick="statisticsUtils.excelGradeList();return false;">엑셀출력</a>
		</div>
    </div>
</div>

