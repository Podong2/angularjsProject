<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/resources/js/setting.js"></script>
<div class="contents">
	<h3 class="contents-title">다즐설정</h3>
	<div class="sub-content">
		<h4 class="sub-title">활동점수 설정</h4>
			<div class="list-02">활동내용에 따라 점수계산 기준을 조정할 수 있습니다.</div>
        <form id="activityScoreEntity" name="activityScoreEntity">
            <table class="board-list al-center">
                <colgroup>
                    <col width="150"/>
                    <col width="415"/>
                    <col width="*"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>개인활동내용</th>
                    <th>개인부여 점수</th>
                    <th class="borR-none">그룹부여 점수</th>
                </tr>
                <c:forEach items="${activityList}" var="activityList">

                    <tr class="activiryGroup">
                        <th>
                                ${activityList.activityContent}
                            <input type="hidden" name="code" id="code" class="code" value="${activityList.code}"/>
                        </th>
                        <td class="borR"><input type="text" class="inp-w160 marL10 al-center userScore" id="userScore" name="userScore" value="${activityList.userScore}"/> 점</td>
                        <td><input type="text" class="inp-w160 marL10 al-center groupScore" id="groupScore" name="groupScore" value="${activityList.groupScore}"/> 점</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <div class="btn-area">
                <a href="#" class="floatR btn Activitysubmit" data-state="0" data-action="/admin/setting/UpdateactivityScoreForm">저장</a>
            </div>

        </form>
	</div>
</div>

{
{1row},{
2row},
{3row}

}