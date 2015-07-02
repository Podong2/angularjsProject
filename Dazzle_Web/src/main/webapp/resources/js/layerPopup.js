var customPopup = {
	popupState: {},
	layer_option: {
		width: "500px",
		height: "300px",
		top: "50%",
		left: "50%",
		marginTop: "-150px",
		marginLeft: "-250px",
		alignX: "center",
		alignY: "middle",
		background: "#fff",
		border : "none",
		layerLevel: "200",
		overflow: "auto"
	},
	overlay_option: {
		overlay: true,
		overlayColor: '#000',
		overlayIndex: 200,
		overlayAlpha: 0.5
	},
	setup: function (selector, layerOption, overlayOption) {
		var overlayOption = this.overlayOption(overlayOption);
		if (overlayOption.overlay == true) {
			$('body').append(this.overlayStyle(overlayOption));
		}
		var layerOption = this.layerOption(layerOption);
		$('body').append(this.layerStyle(layerOption, selector));
	},
	layerOption: function (option) {
		var output_option = {};
		for (var i in customPopup.layer_option) {
			output_option[i] = customPopup.layer_option[i];
		}
		for (var i in option) {
			output_option[i] = option[i];
		}
		return output_option;
	},
	layerStyle: function (option, selector) {
		var layerStr = "<div class='customPopup-wrap' "
						+ "style='position: fixed;"
						+ "width: " + option.width + ";"
						+ "height: " + option.height + ";"
						+ "margin-top: " + option.marginTop + ";"
						+ "margin-left: " + option.marginLeft + ";"
						+ "top: " + option.top + ";"
						+ "left: " + option.left + ";"
						+ "background: " + option.background + ";"
						+ "z-index: " + option.layerLevel + ";"
					//	+ "border: " + option.border + ";"
					//	+ "overflow: " + option.overflow + ";"
						+ "'>"
						+ ""
						+ selector.html();
						+ "</div>";
		return layerStr;
	},
	overlayOption: function (option) {
		// var output_option = customPopup.overlay_option;
		var output_option = {};
		for (var i in customPopup.overlay_option) {
			output_option[i] = customPopup.overlay_option[i];
		}
		for (var i in option) {
			output_option[i] = option[i];
		}
		return output_option;
	},
	overlayStyle: function (overlayOption) {
		var overlayStr = "<div class='customPopup-overlay' style='top:0; left:0; width:100%; height:100%; position: fixed;"
							+ "background-color: " + overlayOption.overlayColor + ";"
							+ "z-index:" + overlayOption.overlayIndex + "; "
							+ "opacity:" + overlayOption.overlayAlpha + "; "
							+ "-webkit-opacity:" + overlayOption.overlayAlpha + "; "
							+ "-moz-opacity:" + overlayOption.overlayAlpha + "; "
							+ "filter:alpha(opacity=" + (overlayOption.overlayAlpha*100) + "); "
							+ "'></div>";
		return overlayStr;
	},
	layerClose: function (obj) {
		var layerObj = false;
	//	var overlayObj = false;
		if (typeof obj == "object") {
			layerObj = $(obj).closest('.customPopup-wrap');
			if (layerObj.prev().is('.customPopup-overlay')) {
				layerObj.prev().remove();
			}
			layerObj.remove();
		} else {
			$('.customPopup-wrap').remove();
			$('.customPopup-overlay').remove();
		}
	}
};

var callPopup = function (url, width, height, borderType, ajaxCallback) {
	var border = borderType == "none" ? "none" : "1px solid #333";
	var popupStyle = {
			width: width + 'px',
			height: height + 'px',
			marginTop: ((height / 2) * -1) + 'px',
			marginLeft: ((width / 2) * -1) + 'px',
			border : border,
			overflow: "hidden"
		};
	$.ajax ({
		beforeSend : function() {
		},
		url : contextPath + url,
		dataType : 'html',
		cache: false,
		success : function(r) {
			r= "<div>" + r + "</div>";
			var popupObj = $(r);
			customPopup.setup(popupObj, popupStyle);
			if(typeof ajaxCallback == "function") {
				ajaxCallback();
			}
		}
	});
};

var callStatisticsPopup = function (url, width, height, borderType, ajaxCallback) {
	var startDate = $('input[name=startDate]').val();
	var endDate = $('input[name=endDate]').val();
	var border = borderType == "none" ? "none" : "1px solid #333";
	var popupStyle = {
			width: width + 'px',
			height: height + 'px',
			marginTop: ((height / 2) * -1) + 'px',
			marginLeft: ((width / 2) * -1) + 'px',
			border : border,
			overflow: "hidden"
		};
	$.ajax ({
		beforeSend : function() {
		},
		data : {
			startDate : startDate,
			endDate : endDate
		},
		url : contextPath + url,
		dataType : 'html',
		cache: false,
		success : function(r) {
			r= "<div>" + r + "</div>";
			var popupObj = $(r);
			customPopup.setup(popupObj, popupStyle);
			if(typeof ajaxCallback == "function") {
				ajaxCallback();
			}
		}
	});
};

var callDoublePopup = function (url, width, height, ajaxCallback) {
	var popupStyle = {
		width: width + 'px',
		height: height + 'px',
		left:'10px',
		top:'10px',
		marginTop:0,
		marginLeft:0,
		background:'#ccc',
		border : '1px solid #333',
		overflow: 'hidden'
	};
	var overlayStyle = {
		overlay: false
	};
	$.ajax ({
		beforeSend : function() {
		},
		url : contextPath + url,
		dataType : 'html',
		cache: false,
		success : function(r) {
			r= "<div>" + r + "</div>";
			var popupObj = $(r);
			customPopup.setup(popupObj, popupStyle, overlayStyle);
			if(typeof ajaxCallback == "function") {
				ajaxCallback();
			}
		}
	});
};
/* 메인 팝업 오늘하루열지않기 쿠키 설정 */
var onedayClose = function(_this, popupId) {
	var today = new Date();
	var expirationDate = new Date(today.getFullYear(), today.getMonth(), today.getDate()+1, 0, 0, 0);
	$.cookie('ondayClose'+popupId, true,{expires: expirationDate});
	customPopup.layerClose(_this);
};
/* 메인 팝업 호출 */
var callMakePopup = function (url) {
	var popupStyle = {};
	var overlayStyle = {
			overlay: false
		};
	$.ajax ({
		beforeSend : function() {
		},
		url : contextPath + url,
		dataType : 'json',
		cache: false,
		success : function(result) {
			for(var i=0; i<result.popup.length; i++) {
				if($.cookie('ondayClose'+result.popup[i].popupId)) {
					continue;	// 메인 팝업 오늘하루열지않기 쿠키 체크
				}
				var contents = "";
				var img = "";
				if (result.popup[i].popupType == "T") {
					var contentsArea
						= '<div style="padding:10px; width:'+(result.popup[i].width - 20)+ 'px; height:'+(result.popup[i].height - 20)+'px;">'+result.popup[i].contents+'</div>';
					if(result.popup[i].linkOption == 0) {
						contents = contentsArea;
					} else if(result.popup[i].linkOption == 1) {
						contents = "<a href='"+result.popup[i].linkUrl+"' target='_blank'>" + contentsArea + "</a>";
					} else {
						contents = "<a href='"+result.popup[i].linkUrl+"'>" + contentsArea + "</a>";
					}
				} else {
					var imgTag = "<img src='"+ contextPath + "/commons/imgView?fileName=" + result.popup[i].imageNm +"&type=setting' style='width:"+ result.popup[i].width+ "px; height:"+result.popup[i].height+"px;'>";
					if(result.popup[i].linkOption == 0) {
						img = imgTag;
					} else if(result.popup[i].linkOption == 1) {
						img = "<a href='" + result.popup[i].linkUrl + "' target='_blank'>" + imgTag + "</a>";
					} else {
						img = "<a href='" + result.popup[i].linkUrl + "'>" + imgTag + "</a>";
					}
				}
				var width = result.popup[i].width;
				var height = result.popup[i].height;
				var xCoordinate = result.popup[i].xCoordinate;
				var yCoordinate = result.popup[i].yCoordinate;
				popupStyle = {
					width: width + 'px',
					height: height + 30 + 25 + 'px',
					top: yCoordinate + 'px',
					left: xCoordinate + 'px',
					marginTop: 0,
					marginLeft: 0,
					border : "1px solid #333",
					overflow: "hidden"
				};
				r = "<div>" +
						"<div id='pop-wrap' class='admin-popup'>" +
							"<div class='pop-title'>" +
								"<span class='tit'>" + result.popup[i].subject + "</span>" +
								"<a href='#' class='close' onclick='customPopup.layerClose($(this)); return false;'></a>" +
							"</div>" + (result.popup[i].popupType == "T" ? contents:img) +
							"<div class='pop-close'>" +
								"<p>오늘하루열지않기<input type='checkbox' class='ml10' onclick='onedayClose($(this),"+result.popup[i].popupId+");'/></p>" +
							"</div>" +
						"</div>" +
					"</div>";
				var popupObj = $(r);
				customPopup.setup(popupObj, popupStyle, overlayStyle);
			}
		}
	});
};