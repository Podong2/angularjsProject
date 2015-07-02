$(function() {

	$(".submitBtn").off('click').on('click', function(event) {
		if(event.preventDefault){ // a테그 클릭이벤트 # 막기
			event.preventDefault(); //FF
		} else {
			event.returnValue = false; //IE
		}
		var formNm = $(this).parents("form").attr("id");
		var url = $(this).data("action");
		var type = $(this).data("type");
		var msg = $(this).data("msg");
		var id = $(this).data("id");
		if (!confirm(msg + ' 하시겠습니까?')) {
			return false;
		}
		var options = {
				url : url,
				type : "post",
				dataType : "json",/* xml, html, script, json */
				success : function (r) {
					alert(r.resultMsg);

					if (r.hasOwnProperty("resultCode") && r.resultCode == "success") {
						window.location.reload();
					}

				}
			};
			$.ajax(options);
	});

	$(".passswordBtn").off('click').on('click', function(event) {
		if(event.preventDefault){ // a테그 클릭이벤트 # 막기
			event.preventDefault(); //FF
		} else {
			event.returnValue = false; //IE
		}
		var oldPassword = $(".oldPassword").val();
		var newPassword1 = $(".newPassword1").val();
		var newPassword2 = $(".newPassword2").val();
		var url = $(this).data("action");
		var id = $(this).data("id");
//		if(newPassword1 != newPassword2){
//			alert("새 비밀번호가 일치하지 않습니다.")
//			return false;
//		}

		if (!confirm('정말로 비밀번호를 변경하시겠습니까?')) {
			return false;
		}
		var options = {
				data : {
					oldPassword : oldPassword,
					password : newPassword1,
					PasswordCheck : newPassword2
				},
				url : url,
				type : "post",
				dataType : "json",/* xml, html, script, json */
				success : function (r) {
					alert(r.resultMsg);
					window.location.reload();
				}
			};
			$.ajax(options);

	});

    $(".selectBtn").off('click').on('click', function(event) {
        if(event.preventDefault){ // a테그 클릭이벤트 # 막기
            event.preventDefault(); //FF
        } else {
            event.returnValue = false; //IE
        }

        var url = $(this).data("action");
        var userKey = $(this).data("userkey");
        var options = {
            data : {
                userKey : userKey
            },
            url : url,
            type : "post",
            dataType : "json",/* xml, html, script, json */
            success : this.responseSuccess,
            error : this.responseError
        };
        $.ajax(options);
    });


    $(".linetalksubmit").off('click').on('click', function() {
    	var talkContents = $("#talkContents").val();
    	var userKey = $("#userKey").val();
        $.ajax ({

            beforeSerialize: function() {
                if ($('#talkContents').val() == null || $('#talkContents').val() == '') {
                    alert('한줄토크를 입력해주세요.');
                    $('#talkContents').focus();
                    return false;
                }
            },
            data: {
            	talkContents : talkContents,
            	userKey : userKey
            },
            type: 'POST',
            dataType: 'json',
            url: contextPath +'/admin/insertTalk',
            success: function (r) {

                    alert(r.resultMsg);
                    window.location.reload();
            }
        });

    });

    $(".updateSubmit").off('click').on('click', function() {
    	var url = $(this).data("action");
    	var score = $(".numChk").val();
    	var activity = $(this).data("activity");
    	if( typeof activity != "undefined") {
    		if(score == "") {
    			alert("활동점수를 입력해주세요.");
    			return false;
    		}else{
    			if(score <= 0){
    				alert("0보다 높은 점수만 부여 가능합니다.");
    				return false;
    			}
    		}
    	}

		if (!confirm("변경된 회원등록정보를 저장하시겠습니까?")) {
			return false;
		}
        $('#lineTalkForm').ajaxSubmit ({

            type: 'POST',
            dataType: 'json',
            url: contextPath +url,
            success: function (r) {

                alert(r.resultMsg);
                window.location.reload();
            }
        });

    });

    $(".updateUserSubmit").off('click').on('click', function() {
    	var url = $(this).data("action");
    	var typeCode = $("#typeCode").val();
    	var stateCode = $("#stateCode").val();
    	var userKey = $("#userKey").val();


		if (!confirm("변경된 회원등록정보를 저장하시겠습니까?")) {
			return false;
		}
        $.ajax ({
        	data: {
        		userKey: userKey,
        		typeCode : typeCode,
        		stateCode : stateCode
        	},
            type: 'POST',
            dataType: 'json',
            url: contextPath +url,
            success: function (r) {

                alert(r.resultMsg);
                window.location.reload();
            }
        });

    });

    $(".arraySubmit").off('click').on('click', function() {
        if(event.preventDefault){ // a테그 클릭이벤트 # 막기
            event.preventDefault(); //FF
        } else {
            event.returnValue = false; //IE
        }

    	var url = $(this).data("action");
        $('#searchForm').ajaxSubmit ({

            type: 'GET',
            dataType: 'json',
            url: contextPath +url,
            success : this.responseSuccess,
            error : this.responseError
        });
    });
});

var userUtils = {
		// 회원목록 엑셀 출력
		excelUserList : function() {
			$('#searchForm').attr('action',contextPath + '/admin/user/excel/excelList');
			$('#searchForm').submit();
			$('#searchForm').attr('action',contextPath + '/admin/user/list');
		}
}