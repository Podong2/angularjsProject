$(function() {

	$(".Activitysubmit").off('click').on('click', function(event) {
        if(event.preventDefault){ // a테그 클릭이벤트 # 막기
            event.preventDefault(); //FF
        } else {
        }
        event.returnValue = false; //IE
        var _param ={ };

        var state = $(this).data("state");
        var url = $(this).data("action");

        if(state ==0){
        	_param.activityScoreList = [];
        	$('.activiryGroup').each(function () {
        		var dataObj = {
        				code:$(this).find('.code').val(),
        				userScore:$(this).find('.userScore').val(),
        				groupScore:$(this).find('.groupScore').val()
        		}
        		_param.activityScoreList.push(dataObj);
        	});

        } else {
        	_param.gradeScoreList = [];
        	$('.GradeGroup').each(function () {
        		var dataObj = {
        				code:$(this).find('.code').val(),
        				userLowScore:$(this).find('.userLowScore').val(),
        				userHighScore:$(this).find('.userHighScore').val(),
        				groupLowScore:$(this).find('.groupLowScore').val(),
        				groupHighScore:$(this).find('.groupHighScore').val()
        		}
        		_param.gradeScoreList.push(dataObj);
        	});
        }
        _data = JSON.stringify(_param); //jsonObject로 변환
        _url = url;
        $.ajax({
            type : 'POST',
            url : contextPath+_url,
            cache: false,
            dataType: "json",
            data: _data,
            processData: false,
            contentType: "application/json; charset=utf-8",
            success : function(r){
                alert('활동점수를 변경하였습니다.');
                window.location.reload();
            },
            error: function() {
                alert("변경실패");
            }
         });
    });
});


var lineNoticeUtils  = (function () {
	"use strict";

	// 한줄알림 삭제
	var deleteLineNotice = function(lineNoticeKey) {
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {lineNoticeKey : lineNoticeKey},
			type : 'POST',
			dataType : 'json',
			url : contextPath + '/admin/board/deleteLineNotice',
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

	// 한줄알림 수정
	var updateLineNotice = function() {
		if (!confirm("수정 하시겠습니까?")) {
			return false;
		}
		var startDate = $(".startDate").val();
		var endDate = $(".endDate").val();
		if(startDate == '' || endDate == ''){
			alert("날짜를 입력 해 주세요.");
			return false;
		}
        $('#lineSubmit').ajaxSubmit ({
            type: 'POST',
            dataType: 'json',
            url: contextPath +'/admin/board/updateLineNotice',
            success: function (r) {
                alert(r.resultMsg);
                location.href = contextPath + '/admin/board/list/03'
            }
        });
	};

	// 한줄알림 작성
	var insertLineNotice = function() {
		var startDate = $(".startDate").val();
		var endDate = $(".endDate").val();
		var subject = $(".subject").val();
		if(startDate == '' || endDate == ''){
			alert("날짜를 입력 해 주세요.");
			return false;
		}
    	if(subject == ''){
    		alert('제목을 입력하세요.');
    		$(".subject").focus();
    		return false;
    	}
        $('#insertForm').ajaxSubmit ({
            type: 'POST',
            dataType: 'json',
            url: contextPath +'/admin/setting/insertNotice',
            success: function (r) {
                alert(r.resultMsg);
                window.location.reload();
            }
        });
	};


	return {
		deleteLineNotice : deleteLineNotice,
		updateLineNotice : updateLineNotice,
		insertLineNotice : insertLineNotice
	}
})();


