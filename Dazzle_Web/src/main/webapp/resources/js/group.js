var groupUtils = (function () {
	"use strict";
	
	// 그룹 리스트 엑셀 출력
	var excelGroupList = function() {
		$('#searchForm').attr('action',contextPath + '/admin/group/excel/excelList');
		$('#searchForm').submit();
		$('#searchForm').attr('action',contextPath + '/admin/group/list');
	};
	// 그룹 삭제
	var deleteGroup = function(groupKey) {
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {groupKey : groupKey},
			type : 'POST',
			dataType : 'json',
			url : contextPath + '/admin/group/rest/deleteGroup',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/group/list';
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};
	
	return {
		excelGroupList : excelGroupList,
		deleteGroup    : deleteGroup
	}
})();