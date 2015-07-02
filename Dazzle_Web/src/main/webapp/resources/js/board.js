
var boardUtils  = (function () {
	"use strict";

	// 이벤트게시판 댓글 액셀출력
	var excelBoardList = function(boardKey) {
		$('#searchForm').attr('action',contextPath + '/admin/board/excel/excelList/'+boardKey);
		$('#searchForm').submit();
		$('#searchForm').attr('action',contextPath + '/admin/board/list/02');
	};

	// 게시판 글쓰기
	var insertBoardSubmit = function(_this, boardType){
    	oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
		var content_text = commonUtils.stripHTMLtag($('#boardContents').val());
    	var subject = $(".subject").val();
    	var eventRate='';
    	if(boardType == '02'){
    		var startDate = $(".startDate").val();
    		var endDate = $(".endDate").val();
    		if(startDate == '' || endDate == ''){
    			alert("날짜를 입력 해 주세요.");
    			return false;
    		}
    	}
    	if(subject == ''){
    		alert('제목을 입력하세요.');
    		$(".subject").focus();
    		return false;
    	}
    	if(content_text == ''){
			alert('내용을 입력하세요.');
			$("#boardContents").focus();
			return false;
    	}
		var grade_leng = $(".eventRate").find("input").length;
		for (var i = 0; i < grade_leng; i++) {
			if($(".eventRate").find("input:eq("+i+")").is(":checked")){
				eventRate += $(".eventRate").find("input:eq("+i+")").val()+",";
			}
		}
        $('#boardSubmit').ajaxSubmit ({
            type: 'POST',
            data : { eventRate : eventRate},
            dataType: 'json',
            url: contextPath +'/admin/board/updateBoard/'+boardType,
            success: function (r) {

                alert(r.resultMsg);
    			location.href = contextPath + "/admin/board/list/"+boardType;
            }
        });
	};

	// 게시판 삭제
	var deleteBoardSubmit = function(boardKey, boardType) {
    	var fileName = $(".fileName").val();
    	var attachKey = $(".attachKey").val();

		if (!confirm("삭제한 자료는 복구할 방법이 없습니다. 정말 삭제하시겠습니까?")) {
			return false;
		}
        var options = {
            url : contextPath+'/admin/board/deleteBoard/' + boardType,
            data : {
            	boardKey : boardKey,
            	fileName : fileName,
            	attachKey : attachKey
            },
            type : "post",
            dataType : "json",/* xml, html, script, json */
            success: function (r) {
            	if (r.hasOwnProperty("resultMsg")){
            		alert(r.resultMsg);
            	}
    			if (r.hasOwnProperty("resultCode") && r.resultCode == "success") {
    				location.href = contextPath + "/admin/board/list/"+boardType;
    			}
            }
        };
        $.ajax(options);
	};

    // 게시판 파일 삭제
    var deleteFileSubmit = function(fileName, attachKey) {

        if (!confirm("삭제한 자료는 복구할 방법이 없습니다. 정말 삭제하시겠습니까?")) {
            return false;
        }
        var options = {
            url : contextPath+'/admin/board/deleteBoardImage/' + fileName +'/'+ attachKey,
            type : "get",
            dataType : "json",/* xml, html, script, json */
            success: function (r) {
                if (r.hasOwnProperty("resultMsg")){
                    alert(r.resultMsg);
                }
                if (r.hasOwnProperty("resultCode") && r.resultCode == "success") {
                    window.location.reload();
                }
            }
        };
        $.ajax(options);
    };

	return {
		excelBoardList : excelBoardList,
		insertBoard : insertBoardSubmit,
		deleteBoard : deleteBoardSubmit,
        deleteFile : deleteFileSubmit
	}
})();

