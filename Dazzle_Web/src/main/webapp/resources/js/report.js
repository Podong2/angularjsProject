var reportUtils = (function() {
	"use strict";
	
	// 신고 처리상태 변경
	var updateReportState = function() {
		var reportKey = $('#reportKey').val();
		$.ajax ({
			data : {
				reportKey : reportKey,
				reportStateCode : $('#reportStateCode option:selected').val()
			},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/report/rest/updateReportState',
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
	// 신고 메모 등록
	var insertReportMemo = function () {
		var reportKey = $('#reportKey').val();
		if (!$('input[name=memoContents]').val()) {
			alert('메모 내용을 입력해 주세요.');
			$('input[name=memoContents]').focus();
			return false;
		}
		$.ajax ({
			data : {
				reportKey : reportKey,
				memoContents : $('input[name=memoContents]').val()
			},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/report/rest/insertReportMemo',
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
	// 신고 메모 삭제
	var deleteReportMemo = function (memoKey) {
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {reportMemoKey : memoKey},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/report/rest/deleteReportMemo',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					window.location.reload();
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};
	// 한줄 토크 등록
	var insertLineTalk = function () {
		if ($('#talkContents').val() == null || $('#talkContents').val() == '') {
			alert('한줄토크를 입력해주세요.');
			$('#talkContents').focus();
			return false;
		}
		$.ajax ({
			data : {
				userKey : $('input[name=writeUserKey]').val(),
				writerKey : $('input[name=adminKey]').val(),
				talkContents : $('input[name=talkContents]').val()
			},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/insertTalk',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					window.location.reload();
					$('input[name=talkContents]').val('');
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};
	
	return {
		updateReportState : updateReportState,
		insertReportMemo  : insertReportMemo,
		deleteReportMemo  : deleteReportMemo,
		insertLineTalk    : insertLineTalk
	}
})();