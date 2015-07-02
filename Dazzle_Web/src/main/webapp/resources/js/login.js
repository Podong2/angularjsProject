jQuery(document).ready(function($){
	var ResultCode = {
		/** 성공 */
		SUCCESS: "success",
		/** 실패 */
		FAIL: "fail",
		/** 로그인 실패 */
		LOGIN_FAIL: "loginFail",
		/** 로그아웃 실패 */
		LOGOUT_FAIL: "logoutFail",
		/** 세션이 없음 */
		NO_SESSION: "noSession",
		/** 권한 없음 */
		NO_AUTH: "noAuth",
		/** 결과 없음 */
		NO_RESULT: "noResult",
		/** 계정과 패스워드가 일치하지 않음 */
		ACCOUNT_NOT_EQUAL: "accounTnotEqual"
	};
	$('.login').on('click', function(){
		
		$('#loginForm').ajaxSubmit ({
			
			beforeSerialize: function() {
				if ($('#id').val() == null || $('#id').val() == '') {
					alert('아이디를 입력해주세요.');
					$('#id').focus();
					return false;
				}
				if ($('#password').val() == '') {
					alert('비밀번호를 입력해주세요');
					$('#password').focus();
					return false;
				}
			},
			type: 'POST',
			dataType: 'json',
			url: contextPath +'/admin/doLogin',
			success: function (r) {
				if (r.resultCode != ResultCode.SUCCESS) {
					alert(r.resultMsg);
					return false;
				} else {
					location.href = contextPath + "/admin/user/list";
				}
			}
		});
	});
});