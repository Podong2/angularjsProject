$(function() {
	// left menu
	var contHeight = $('#right-wrap .contents').height() + 80;
	$('#left-wrap').css('height', contHeight);
	
	var pathArr = window.location.pathname.replace(contextPath + '/', '').split('/');
	$('.left-bar li').each(function () {
		if (pathArr[1] == $(this).data('menu')) {
			$(this).addClass('on');
			var depth = $(this).find('.depth');
			depth.show();
			depth.find('li').each(function() {
				var _this = $(this);
				if (pathArr[3] == $(this).data('depth')) {
					_this.addClass('on');
				} else {
					_this.removeClass('on');
				}
			});
		}
	});
	
	// ajax interceptor 처리
	commonUtils.interceptorAjax();
	
	// block UI
	commonUtils.blockUI();
});

var commonUtils = (function () {
	"use strict";
	
	/* Ajax blockUI 실행 */
	var blockUI = function() {
		$(document).bind("ajaxSend", function() {
			$.blockUI();
		})
		.bind("ajaxStop", function() {
			$.unblockUI();
		});
	};
	/* Ajax blockUI 중지 */
	var unblockUI = function() {
		$(document).unbind("ajaxSend").unbind("ajaxStop");
	};
	/* 리스트 정렬 */
	var sortInit = function () {
		var searchForm	= $('#searchForm');
		var sortBy		= $('[name="sortBy"]', searchForm);
		var sortOrder	= $('[name="sortOrder"]', searchForm);

		$('a.btnSort').each(function () {
			var btnSort = $(this);
			var btnSortBy = btnSort.data('sort-by');
			if(btnSortBy == sortBy.val()) {
				btnSort.data('sort-order', sortOrder.val());
			} else {
				btnSort.data('sort-order', 'desc');
			}
			btnSort.text(btnSort.data('sort-order') == 'asc' ? '▲' : '▼');
		});

		$('a.btnSort').click(function () {
			var btnSort = $(this);
			if(btnSort.data('sort-order') == 'asc') {
				btnSort.text('▼');
				btnSort.data('sort-order', 'desc');
			} else {
				btnSort.text('▲');
				btnSort.data('sort-order', 'asc');
			}

			var btnSortBy = btnSort.data('sort-by');
			var btnSortOrder = btnSort.data('sort-order');

			if(sortBy.val().length == 0) {
				sortBy.prop('value', btnSortBy);
				sortOrder.prop('value', btnSortOrder);
			} else {
				if(btnSortBy == sortBy.val()) {
					sortOrder.prop('value', btnSortOrder);
				} else {
					sortBy.prop('value', sortBy.val());
					sortOrder.prop('value', sortOrder.val());
					sortBy.prop('value', btnSortBy);
					sortOrder.prop('value', btnSortOrder);
				}
			}
			searchForm.submit();
		});
	};
	/* ajax interceptor 처리 */
	var interceptorAjax = function () {
		$(document).ajaxSuccess(function (event, request) {
			var resultData = request.responseJSON;
			if (resultData != undefined) {
				if (resultData.errorCode) {
					if (resultData.hasOwnProperty("errorMessage")) {
						alert(resultData.errorMessage);
					}
					if (resultData.hasOwnProperty("errorUrl")) {
						location.href = contextPath + resultData.errorUrl;
					}
					if (resultData.hasOwnProperty("errorAction")) {
						eval(resultData.errorAction);
					}
				}
			}
		});
	};
	/* HTML tag 제거 */
	var stripHTMLtag = function (string) {
		var objStrip = new RegExp();
		objStrip = /[<][^>]*[>]/gi;
		return string.replace(objStrip, "");
	};
	/* 숫자 타입 체크 */
	var checkNumberType = function () {
		$('.numChk').on('keyup', function() {
			if(/[^0123456789]/g.test($(this).val())) {
				alert("숫자만 입력가능합니다.");
				$(this).val('');
				return false;
			}
		});
	};

	return {
		blockUI         : blockUI,
		unblockUI       : unblockUI,
		sortInit        : sortInit,
		interceptorAjax : interceptorAjax,
		stripHTMLtag    : stripHTMLtag,
		checkNumberType : checkNumberType
	}
})();