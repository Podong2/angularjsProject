var questionUtils = (function () {
	"use strict";

	// 다Q문제 등록
	var insertQuestion = function () {
		$('#daqForm').ajaxSubmit({
			beforeSerialize: function() {
				if (!$('input[name=exposeStartDate]').val() || !$('input[name=exposeEndDate]').val()) {
					alert('노출기간을 지정해 주세요.');
					return false;
				}
				if (!$('textarea[name=questionContents]').val()) {
					alert('문제 내용을 입력해 주세요.');
					return false;
				}
			},
			dateType: 'json',
			type: 'POST',
			url: contextPath + '/admin/question/rest/insertQuestion',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/question/list/daq';
				}
			},
			error: function () {
				alert('오류가 발생하였습니다.');
			}
		});
	};
	// 문제 유형 불러오기
	var getClassCodeList = function(_this) {
		var data = {};
		var type = _this.data('type');		// 타입(grade:학년, large:대단원, medium:중단원, small:소단원)
		var gradeCode = $('.grade').find('option:selected').val();	// 학년코드
		var nextClassNm = _this.data('next-class');
		var class8 = _this.find('option:selected').data('class-code');
		var class9 = _this.find('option:selected').data('class9-code');
		// 학년 일반(00)일 경우-> 대단원 '전체'로 세팅 / 중단원, 소단원, 유형 삭제
		if (type == 'grade' && gradeCode == '00') {
			$('.large').empty();
			$('.large').append('<option value="0000000000">전체</option>');
			$('.medium, .small, .category').attr('disabled', true);
		}
		else {
			$('.medium, .small, .category').attr('disabled', false);
			if (type == 'grade') {
				data = {
					gradeCode : gradeCode,
					type : nextClassNm,
					class8 : ""
				};
			} else if(type == 'small'){
				data = {
					gradeCode : gradeCode,
					type : nextClassNm,
					class8 : class8.replace(/(^\s*)|(\s*$)/gi, ""),
					class9 : class9.replace(/(^\s*)|(\s*$)/gi, "")
				};
			} else {
				data = {
						gradeCode : gradeCode,
						type : nextClassNm,
						class8 : class8.replace(/(^\s*)|(\s*$)/gi, "")
				};
			}
			$.ajax({
				data : data,
				type : 'GET',
				dataType : 'json',
				url : contextPath + '/admin/comm/rest/getClassCode',
				success : function(r) {
					if (r.resultCd == 'FAIL') {
						return false;
					}
					try {
						var codeList = '<option value="">선택</option>';
						for(var i=0 in r.classCodeList) {
							var data = r.classCodeList[i];
							codeList +=  '<option value="'+ data.classCode+'" data-class-code="'+ data.class7+'" data-class9-code="'+ data.class9+'">' + data.name1 + '</option>';
						}
						$('.' + nextClassNm).empty();
						$('.' + nextClassNm).append(codeList);
					} catch(err) {
						alert(err);
						alert('오류가 발생하였습니다.');
					}
				}
			});
		}
	};
	// 문제 수정버튼 클릭시
	var updateQuestion = function(questionType, questionKey) {
		// 전체문제 -> 문제유형 수정
		if (questionType == 'all') {
			$('#questionForm').ajaxSubmit({
				beforeSerialize: function() {
					if (!$('.grade').val()) {
						alert('학년을 선택해 주세요.');
						return false;
					}
					if (!$('.large').val()) {
						alert('대단원을 선택해 주세요.');
						return false;
					}
					if ($('.grade').val() != '00') {
						if (!$('.medium').val()) {
							alert('중단원을 선택해 주세요.');
							return false;
						}
						if (!$('.small').val()) {
							alert('소단원을 선택해 주세요.');
							return false;
						}
						if (!$('.category').val()) {
							alert('유형을 선택해 주세요.');
							return false;
						}
					}
				},
				dateType: 'json',
				type: 'POST',
				url: contextPath + '/admin/question/rest/updateQuestion',
				success : function(result) {
					if (result.hasOwnProperty("resultMsg")) {
						alert(result.resultMsg);
					}
					if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
						location.href = contextPath + '/admin/question/detail/' + questionType + '/'+ questionKey;
					}
				},
				error: function () {
					alert('오류가 발생하였습니다.');
				}
			});
		}
		// 다Q문제 -> 수정페이지로 이동
		else if (questionType == 'daq') {
			location.href = contextPath + '/admin/question/daqForm/' + questionKey;
		}
	};
	// 다Q문제 수정
	var updateDaqQuestion = function(questionKey) {
		$('#daqForm').ajaxSubmit ({
			beforeSerialize: function() {
				if (!$('input[name=exposeStartDate]').val() || !$('input[name=exposeEndDate]').val()) {
					alert('노출기간을 지정해 주세요.');
					return false;
				}
				if (!$('textarea[name=questionContents]').val()) {
					alert('문제 내용을 입력해 주세요.');
					return false;
				}
			},
			dateType: 'json',
			type: 'POST',
			url: contextPath + '/admin/question/rest/updateDaqQuestion',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin//question/detail/daq/' + questionKey;
				}
			},
			error: function () {
				alert('오류가 발생하였습니다.');
			}
		});
	}

	// 문제 삭제
	var deleteQuestion = function(questionType, questionKey) {
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {questionKey : questionKey},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/question/rest/deleteQuestion',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/question/list/' + questionType;
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};
	// 답글 등록
	var insertAnswer = function(questionType, questionKey) {
		$('#answerForm').ajaxSubmit({
			beforeSerialize: function() {
				if (!$('#answerContents').val()) {
					alert('답글을 입력해 주세요.');
					$('#answerContents').focus();
					return false;
				}
			},
			dateType: 'json',
			type: 'POST',
			url: contextPath + '/admin/question/rest/insertAnswer',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/question/detail/' + questionType + '/'+ questionKey;
				}
			},
			error: function () {
				alert('오류가 발생하였습니다.');
			}
		});
	};
	// 답글 삭제
	var deleteAnswer = function(questionType, questionKey, answerKey) {
		if (!confirm("삭제 하시겠습니까?")) {
			return false;
		}
		$.ajax ({
			data : {answerKey : answerKey},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/question/rest/deleteAnswer',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/question/detail/' + questionType + '/' + questionKey;
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};
	// 답글 채택/취소
	var chooseAnswer = function(questionType, questionKey, answerKey, chooseYn) {
		$.ajax ({
			data : {answerKey : answerKey, chooseYn : chooseYn},
			type : 'POST',
			dataType : 'json',
			url :contextPath + '/admin/question/rest/chooseAnswer',
			success : function(result) {
				if (result.hasOwnProperty("resultMsg")) {
					alert(result.resultMsg);
				}
				if (result.hasOwnProperty("resultCode") && result.resultCode == "success") {
					location.href = contextPath + '/admin/question/detail/' + questionType + '/' + questionKey;
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	};
	//ajaxFileUpLoad 미리보기
	var ajaxFileUpload = function() {
		$("form").delegate(".ajaxFileUploadInput", "change", function(event) {
			var that = $(this);
			var imgArea = $("label[for='"+that.attr('id')+"']");
			var _parent = imgArea.closest('td');
			that.attr('name', 'file');
			$('#uploadForm').ajaxSubmit ({
				dateType: 'JSON',
				type: 'POST',
				url: contextPath + '/common/uploadImage',
				success: function (result) {
					var resultMap = JSON.parse(result);
					if(resultMap.resultCode == 'success') {
						_parent.find('a.del').trigger('click');
						_parent.find('.imgAttach').val(resultMap.origFileName);
						questionUtils.previewImage(resultMap.fileName, imgArea, 1);
					} else {
						alert('오류가 발생하였습니다.');
					}
				},
				error: function () {
					alert('오류로 인해 저장이 실패하였습니다.');
				}
			});
			that.removeAttr('name');
			return false;
		});
	};
	// 업로드한 이미지 미리보기 (type -> 1:임시, 0:저장된 사진)
	var previewImage = function(src, imgArea, type) {
		var imgObj;
		var aObj;
		var thisSrc;
		$('.img-preview').remove();
		if(typeof src == 'undefined' || src.length < 0) {
			imgObj = $('<img class="img-preview">').attr({'src' : contextPath + '/resources/img/no-image02.gif'});
		} else {
			if(type == 1) {
				thisSrc = contextPath + '/common/imgView?fileType=question&fileName=' + src + '&temporary=Y';
			} else {
				thisSrc = contextPath + '/common/imgView?fileName=' + src + '&fileType=question';
			}

			aObj = $('<a class="" >').attr({'class' : 'del'});

			imgObj = $('<img class="img-preview">').attr({
				'src' : thisSrc,
				'onerror' : 'this.src="' + contextPath + '/resources/img/no-image02.gif"',
				'alt' : '이미지 추가'
			})
		}
		imgObj.css('width', 320);
		imgObj.css('height', 200);
		imgObj.appendTo(imgArea);
		if(typeof src != 'undefined' && src.length > 0 && typeof imgArea != 'undefined') {
			var _parent = imgArea.closest('div');
			_parent.append(aObj);
			_parent.find('a.del').on('click', function () {
				_parent.find('input.imgSrc').val('');
				_parent.find('input.imgAttach').val('');
				_parent.find('img.img-preview, a.del').remove();
			});
		}

		$('.imgSrc', imgArea).val(src);
	};

	return {
		insertQuestion    : insertQuestion,
		getClassCodeList  : getClassCodeList,
		updateQuestion    : updateQuestion,
		updateDaqQuestion : updateDaqQuestion,
		deleteQuestion    : deleteQuestion,
		insertAnswer      : insertAnswer,
		deleteAnswer      : deleteAnswer,
		chooseAnswer      : chooseAnswer,
		ajaxFileUpload    : ajaxFileUpload,
		previewImage      : previewImage
	}
})();