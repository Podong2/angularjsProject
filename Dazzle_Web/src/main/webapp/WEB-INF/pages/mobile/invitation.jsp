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
	<title></title>
	<script src="${contextPath}/resources/js/jquery/jquery-1.11.2.min.js"></script>
	<script>
		var isIPHONE = (navigator.userAgent.match('iPhone') != null || navigator.userAgent.match('iPod') != null);
		var isIPAD = (navigator.userAgent.match('iPad') != null);
		var isANDROID = (navigator.userAgent.match('Android') != null);
		var isCHROME = (navigator.userAgent.match('Chrome') != null);
		var inviteCode = '${groupInvite.inviteCode}';

		//TODO : ISO 프로그램 설치 URL을 넣어주세요.
		var _APP_INSTALL_URL_IOS = "http://itunes.apple.com/";
		//TODO : ANDROID 프로그램 설치 URL을 넣어주세요.
		var _APP_INSTALL_URL_ANDROID = "market://details?id=";

		var _APP_RUN_URL = "dazzle://" + inviteCode;

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
				window.location.href = 'intent://'+inviteCode+'#Intent;' +
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
		
		var chk = true;
		$(document).ready(function(e) {
			if(chk){
				if ('${isExpired}' == 'true') alert("만료된 초대코드입니다.");
				else checkApplicationInstall();
			}else{
				chk = false;
			}
		});
	</script>
</head>
<body>
</body>
</html>
