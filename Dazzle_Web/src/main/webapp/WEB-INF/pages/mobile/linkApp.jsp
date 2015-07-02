<%--
  Created by IntelliJ IDEA.
  User: jm1218
  Date: 15. 1. 12
  Time: 오후 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta charset="utf-8"/>
	<title>[다즐] 다함께 즐겁게 공부하자!</title>
	<script src="${contextPath}/resources/js/jquery/jquery-1.11.2.min.js"></script>
	<script>
		// user agent
		var isIPHONE = (navigator.userAgent.match(/iPhone/) != null || navigator.userAgent.match(/iPod/) != null);
		var isIPAD = (navigator.userAgent.match(/iPad/) != null);
		var isANDROID = (navigator.userAgent.match(/Android/) != null);
		var isCHROME = (navigator.userAgent.match(/Chrome/) != null);

		//TODO : input ios install url
		var _APP_INSTALL_URL_IOS = "http://itunes.apple.com/";
		//TODO : input android install url
		var _APP_INSTALL_URL_ANDROID = "market://details?id=kr.co.chunjae";


		//link type : DEFAULT APP LAUNCHER / 그룹초대 URL / EVENT PAGE LINK
		var TYPE = {DEFAULT : 'DEFAULT', GROUP_INVITE : 'GROUP_INVITE', EVENT_PAGE : 'EVENT_PAGE'};
		var currentType = '${linkType}';
		var inviteCode = currentType == 'GROUP_INVITE' ? '${groupInvite.inviteCode}' : '';
		var boardKey = '${boardKey}';
		var isExpired_inviteCode = inviteCode && '${groupInvite.expireDate.time}' < new Date().getTime();


		var _APP_RUN_URL = "dazzle://" + makeUrlSuffix(currentType);

		var goInstall = (function (){
			if(isANDROID)
				window.location = _APP_INSTALL_URL_ANDROID;
			else if(isIPHONE || isIPAD)
				window.location = _APP_INSTALL_URL_IOS;
			else
				alert("Android, iPhone 에서만 사용할 수 있습니다");
		});

		function checkApplicationInstall(){
			if (isANDROID){

				window.location.href = 'intent://' + makeUrlSuffix(currentType) + '#Intent;' +
					'scheme=dazzle;' +
					'action=android.intent.action.VIEW;' +
					'category=android.intent.category.BROWSABLE;' +
					'package=kr.co.chunjae;end';

			}else if (isIPHONE || isIPAD){

				setTimeout(goInstall, 350);
				window.location = _APP_RUN_URL;

			} else {
				alert("Android, iPhone 에서만 사용할 수 있습니다");
			}
		}

		function makeUrlSuffix(currentType) {
			var suffix = "type=" + currentType;
			switch (currentType) {
				case TYPE.DEFAULT :
					break;
				case TYPE.GROUP_INVITE :
					suffix += "&inviteCode=" + inviteCode;
					break;
				case TYPE.EVENT_PAGE :
					suffix += "&boardKey=" + boardKey;
					break;
				default :
					break;
			}
			return suffix;
		}

		var chk = true;
		$(document).ready(function() {

			if(chk){

				if (currentType == TYPE.GROUP_INVITE && isExpired_inviteCode) alert("만료된 초대코드입니다.");
				else checkApplicationInstall();

			}else{

				chk = false;
				
			}
		});
	</script>
</head>
<body>
<img src="${contextPath}/resources/img/icon/app-link.png" alt="썸네일" />
</body>
</html>
