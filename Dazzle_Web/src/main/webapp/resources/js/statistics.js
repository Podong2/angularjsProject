var statisticsUtils = (function() {
	"use strict";

	// 학년별 대단원 목록
	var selectClassCodeLarge = function() {
		var reportKey = $('#reportKey').val();
		$.ajax ({
			data : {
				classCodeLarge : reportKey
			},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/statistics/selectClassCodeLarge',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/report/detail/' + reportKey;
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};

	// 회원목록 엑셀 출력
	var excelGradeList = function() {
		$('#searchForm').attr('action',contextPath + '/admin/statistics/excel/gradeList');
		$('#searchForm').submit();
		$('#searchForm').attr('action',contextPath + '/admin/statistics/list/grade');
	};

	// 기간별 엑셀 출력
	var excelPeriodList = function() {
		$('#searchForm').attr('action',contextPath + '/admin/statistics/excel/periodList');
		$('#searchForm').submit();
		$('#searchForm').attr('action',contextPath + '/admin/statistics/list/period');
	};

	// 활동점수 엑셀 출력
	var excelScoreList = function() {
		$('#searchForm').attr('action',contextPath + '/admin/statistics/excel/scoreList');
		$('#searchForm').submit();
		$('#searchForm').attr('action',contextPath + '/admin/statistics/list/activityScore');
	};

	// 활동개수 엑셀 출력
	var excelCountList = function() {
		$('#searchForm').attr('action',contextPath + '/admin/statistics/excel/countList');
		$('#searchForm').submit();
		$('#searchForm').attr('action',contextPath + '/admin/statistics/list/activityCount');
	};

	return {
		selectClassCodeLarge : selectClassCodeLarge,
		excelGradeList : excelGradeList,
		excelPeriodList : excelPeriodList,
		excelScoreList : excelScoreList,
		excelCountList : excelCountList

	}
})();