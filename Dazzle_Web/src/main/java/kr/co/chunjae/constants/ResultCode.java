package kr.co.chunjae.constants;

public class ResultCode {
	/** 결과 코드*/
	public final static String RESULT_CODE = "resultCode";
	/** 결과 메세지*/
	public final static String RESULT_MSG = "resultMsg";
	/** 결과 URL*/
	public final static String RESULT_URL = "resultURL";
	/** 결과 ACTION*/
	public final static String RESULT_ACT = "resultACT";
	/** 결과 DATA*/
	public final static String RESULT_DATA = "resultDATA";
	/** 성공 */
	public final static String SUCCESS = "success";
	/** 실패 */
	public final static String FAIL = "fail";
	/** 로그인 실패 */
	public final static String LOGIN_FAIL = "loginFail";
	/** 로그아웃 실패 */
	public final static String LOGOUT_FAIL = "logoutFail";
	/** 세션이 없음 */
	public final static String NO_SESSION = "noSession";
	/** 권한 없음 */
	public final static String NO_AUTH = "noAuth";
	/** 결과 없음 */
	public final static String NO_RESULT = "noResult";
	/** 계정과 패스워드가 일치하지 않음 */
	public final static String ACCOUNT_NOT_EQUAL = "accounTnotEqual";
	/** 삭제 질의 */
	public final static String DELETE_QUESTION = "deleteQuestion";
	
	// 엑셀 전용
	/** 갯수가 일치하지 않음 */
	public final static String NOT_COUNT_EQUAL = "notCountEqual";
	/** 컬럼 갯수가 일치하지 않음 */
	public final static String NOT_COL_COUNT_EQUAL = "notColCountEqual";
	/** 파일이 없음 */
	public final static String NO_FILE = "noFile";
	/** 잘못된 확장자임 */
	public final static String WRONG_EXT = "wrongExt";
	/** 필수입력항목임 */
	public final static String IS_REQUIRED = "isRequired";
	/** 잘못된 값임 */
	public final static String IS_INVALID = "isInvalid";

	public static final class GroupJoinResult {
		public static final String INVALID_INVITE_CODE = "invalid invite code";
		public static final String EXPIRED_INVITE_CODE = "expired invite code";
		public static final String VALID_INVITE_CODE = "valid invite code";
		public static final String ALREADY_MEMBER = "already member";
		public static final String DROPPED_MEMBER = "dropped member";
		public static final String ENTRY_SUCCESS = "entry success";
		public static final String ENTRY_FAIL = "entry fail";
	}
}
