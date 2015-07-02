package kr.co.chunjae.constants;

public class CommonCode {
	/** 페이지 그룹 수 */
	public final static int PAGE_GROUP_SIZE_PARAM = 10;
	/** 페이지 그룹 수(문제 리스트) */
	public final static int QUESTION_PAGE_GROUP_SIZE_PARAM = 15;
	/** 페이지 그룹 수(엑셀 출력) */
	public final static int EXCEL_GROUP_SIZE_PARAM = 99999999;
	/** 페이지에 출력될 게시물 수 */
	public final static int PAGE_LIST_SIZE_PARAM = 10;
	/** 페이지에 출력될 게시물 수 (엑셀 출력) */
	public final static int EXCEL_LIST_SIZE_PARAM = 1;

	/** 로그인 구분 (관리자) */
	public final static String LOGIN_TYPE_ADMIN = "admin";
	/** 로그인 구분 (사용자) */
	public final static String LOGIN_TYPE_USER = "user";

	/** 내림차순 정렬 */
	public final static String SORT_ORDER_DESC = "desc";
	/** 오름차순 정렬 */
	public final static String SORT_ORDER_ASC = "asc";

	/** 문제 타입(전체) */
	public final static String QUESTION_TYPE_ALL = "all";
	/** 문제 타입(다Q) */
	public final static String QUESTION_TYPE_DAQ = "daq";
	/** 문제 유형(대단원) */
	public final static String QUESTION_TYPE_LARGE = "large";
	/** 문제 유형(중단원) */
	public final static String QUESTION_TYPE_MEDIUM = "medium";
	/** 문제 유형(소단원) */
	public final static String QUESTION_TYPE_SMALL = "small";
	/** 문제 유형(유형) */
	public final static String QUESTION_TYPE_CATEGORY = "category";

	/** 문제 타입 코드(일반) */
	public final static String QUESTION_TYPE_CODE_GENERAL = "01";
	/** 문제 타입 코드(그룹) */
	public final static String QUESTION_TYPE_CODE_GROUP = "02";
	/** 문제 타입 코드(다Q) */
	public final static String QUESTION_TYPE_CODE_DAQ = "03";

	/** 채택 유저 타입(관리자) */
	public final static String ANSWER_CHOOSE_TYPE_ADMIN = "admin";
	/** 채택 유저 타입(문제 등록자) */
	public final static String ANSWER_CHOOSE_TYPE_QUESTIONER = "questioner";

	/** 채택 여부 타입(채택) */
	public final static String ANSWER_CHOOSE_Y = "Y";
	/** 채택 여부 타입(채택 취소) */
	public final static String ANSWER_CHOOSE_N = "N";

	/** 문제/답글 좋아요 여부(좋아요) */
	public final static String LIKE_YN_Y = "Y";
	/** 문제/답글 좋아요 여부(좋아요 취소) */
	public final static String LIKE_YN_N = "N";

	/** 문제 스크랩 여부(스크랩) */
	public final static String SCRAP_YN_Y = "Y";
	/** 문제 스크랩 여부(스크랩 취소) */
	public final static String SCRAP_YN_N = "N";

	/** 스크랩 타입(개인 스크랩) */
	public final static String SCRAP_TYPE_PERSONAL = "01";
	/** 스크랩 타입(그룹 스크랩) */
	public final static String SCRAP_TYPE_GROUP = "02";

	/** 회원 분류(사용자) */
	public final static String USER_TYPE_CODE_USER = "01";
	/** 회원 분류(전문가) */
	public final static String USER_TYPE_CODE_MASTER = "02";
	/** 회원 분류(관리자) */
	public final static String USER_TYPE_CODE_ADMIN = "03";

	/** 그룹 타입(공개) */
	public final static String GROUP_TYPE_CODE_OPEN = "01";
	/** 그룹 타입(비공개) */
	public final static String GROUP_TYPE_CODE_CLOSED = "02";

	/** 그룹 멤버 상태(가입대기) */
	public final static String GROUP_MEM_STATE_STAND_BY = "01";
	/** 그룹 멤버 상태(가입) */
	public final static String GROUP_MEM_STATE_JOIN = "02";
	/** 그룹 멤버 상태(탈퇴) */
	public final static String GROUP_MEM_STATE_DROP_OUT = "03";
	/** 그룹 멤버 상태(가입취소) */
	public final static String GROUP_MEM_STATE_DROP_CANCEL = "04";

	/** 그룹가입 시 조회 할 그룹원 수 LOW(그룹원 조회) */
	public final static int GROUP_MEMBER_LOW_SIZE_PARAM = 1;
	/** 그룹가입 시 조회 할 그룹원 수 HIGH(그룹원 조회) */
	public final static int GROUP_MEMBER_HIGH_SIZE_PARAM = 99999999;

	/** 신고 처리 상태(대기) */
	public final static String REPORT_STATE_STAND_BY = "01";
	/** 신고 처리 상태(완료) */
	public final static String REPORT_STATE_COMPLETE = "02";
	/** 신고 게시물 구분(문제) */
	public final static String REPORT_TYPE_QUESTION = "01";
	/** 신고 게시물 구분(답글) */
	public final static String REPORT_TYPE_ANSWER = "02";

	/** 첨부파일 종류(사용자 프로필) */
	public final static String ATTACH_TYPE_PROFILE = "01";
	/** 첨부파일 구분(그룹 커버) */
	public final static String ATTACH_TYPE_GROUP_COVER = "02";
	/** 첨부파일 구분(문제) */
	public final static String ATTACH_TYPE_QUESTION = "03";
	/** 첨부파일 구분(답글) */
	public final static String ATTACH_TYPE_ANSWER = "04";
	/** 첨부파일 구분(공지사항 게시판) */
	public final static String ATTACH_TYPE_BOARD_NOTICE = "05";
	/** 첨부파일 구분(이벤트 게시판) */
	public final static String ATTACH_TYPE_BOARD_EVENT = "06";
	/** 첨부파일 구분(그룹) */
	public final static String ATTACH_TYPE_GROUP = "07";

	/** 첨부파일 구분(원본 파일) */
	public final static String ATTACH_TYPE_ORIGINAL = "orig";
	/** 첨부파일 구분(임시 파일) */
	public final static String ATTACH_TYPE_TEMPORARILY = "temp";
	/** 첨부파일명 접두사 */
	public final static String ATTACH_FILE_PREFIX = "m";
	/** 첨부파일명 확장자 */
	public final static String ATTACH_FILE_EXTENSION_JPG = "jpg";

	/** 레벨 아이콘 클래스명 접두사(개인) */
	public final static String LEVEL_ICON_PREFIX_PERSONAL = "p-";
	/** 레벨 아이콘 클래스명 접두사(그룹) */
	public final static String LEVEL_ICON_PREFIX_GROUP = "g-";

	/** SMS발송 타입 (휴대폰인증) */
	public final static int SMS_SEND_CONFIGRATION = 75;
	/** SMS발송 타입 (이벤트) */
	public final static int SMS_SEND_EVENT = 76;
	/** SMS발송 타입 (친구초대) */
	public final static int SMS_SEND_INVITE = 77;
	/** SMS발송 즉시 발송 */
	public final static String SMS_MESSAGETYPE_INSTANTLY = "1";
	/** SMS발송 예약 발송 */
	public final static String SMS_MESSAGETYPE_BOOKING = "2";
	/** SMS발송 아이디 멘트 */
	public static final String SMS_SEND_EVENT_MESSAGE_ID = "[Dazzle] 회원님의 아이디를 발송하였습니다. ";
	/** SMS발송 비밀번호 멘트 */
	public static final String SMS_SEND_EVENT_MESSAGE_PASSWORD = "[Dazzle] 회원님의 임시 비밀번호를 발송하였습니다. ";
	/** SMS발송 휴대폰인증 멘트 */
	public static final String SMS_SEND_EVENT_MESSAGE_CONFIRM = "[Dazzle] 인증번호를 입력해주세요. 인증번호:[";

	/** 사용자 나뭇잎 등급 (숫자)*/
	public static final String USER_RATING_LEAF_NUMBER = "01";
	/** 사용자 나무 등급 (숫자)*/
	public static final String USER_RATING_WOOD_NUMBER = "02";
	/** 사용자 바위 등급 (숫자)*/
	public static final String USER_RATING_STONE_NUMBER = "03";
	/** 사용자 구리 등급 (숫자)*/
	public static final String USER_RATING_COPPER_NUMBER = "04";
	/** 사용자 은 등급 (숫자)*/
	public static final String USER_RATING_SILVER_NUMBER = "05";
	/** 사용자 금 등급 (숫자)*/
	public static final String USER_RATING_GOLD_NUMBER = "06";
	/** 사용자 수정 등급 (숫자)*/
	public static final String USER_RATING_CRYSTAL_NUMBER = "07";

	/** 사용자 나뭇잎 등급 (class명)*/
	public static final String USER_RATING_LEAF_CLASS = "p-leaf";
	/** 사용자 나무 등급 (class명)*/
	public static final String USER_RATING_WOOD_CLASS = "p-wood";
	/** 사용자 바위 등급 (class명)*/
	public static final String USER_RATING_STONE_CLASS = "p-stone";
	/** 사용자 구리 등급 (class명)*/
	public static final String USER_RATING_COPPER_CLASS = "p-copper";
	/** 사용자 은 등급 (class명)*/
	public static final String USER_RATING_SILVER_CLASS = "p-silver";
	/** 사용자 금 등급 (class명)*/
	public static final String USER_RATING_GOLD_CLASS = "p-gold";
	/** 사용자 수정 등급 (class명)*/
	public static final String USER_RATING_CRYSTAL_CLASS = "p-crystal";

	/** 프랜차이즈 인증(공부방) */
	public final static String FRANCHISE_CONFIRM_STUDYROOM = "01";
	/** 프랜차이즈 인증(셀파) */
	public final static String FRANCHISE_CONFIRM_SELPA = "02";

	/** 그룹초대 타입 */
	public static final class GroupInviteType {
		public final static String INVITE_TO_LINK = "01";
		public final static String INVITE_TO_CODE = "02";
	}

	public enum AppLink {
		DEFAULT, GROUP_INVITE, EVENT_PAGE
	}

	/** 학년별 대단원 통계 코드명 접미사 */
	public final static String STATISTICS_GRADE_CODE_FORM  = "_0_000000";

	/** 활동개수 통계 타입(문제) */
	public final static String STATISTICS_ACTIVITY_TYPE_QUESTION  = "01";
	/** 활동개수 통계 타입(답글) */
	public final static String STATISTICS_ACTIVITY_TYPE_ANSWER  = "02";
	/** 활동개수 통계 타입(문제+답글) */
	public final static String STATISTICS_ACTIVITY_TYPE_QNA  = "03";
	/** 활동개수 통계 타입(채택 답글) */
	public final static String STATISTICS_ACTIVITY_TYPE_CHOOSE_ANSWER = "04";
	/** 활동개수 통계 타입(다Q최초 그룹 이동) */
	public final static String STATISTICS_ACTIVITY_TYPE_SCRAP_DAQ = "05";
	/** 활동개수 통계 타입(App 최초가입) */
	public final static String STATISTICS_ACTIVITY_TYPE_JOIN_APP = "06";
	/** 활동개수 통계 타입(그룹 생성) */
	public final static String STATISTICS_ACTIVITY_TYPE_CREATE_GROUP = "07";
	/** 활동개수 통계 타입(맴버 초대) */
	public final static String STATISTICS_ACTIVITY_TYPE_INVITE_MEMBER = "08";
	/** 활동개수 통계 타입(맴버 가입) */
	public final static String STATISTICS_ACTIVITY_TYPE_JOIN_MEMBER = "09";

	/** 활동 구분 타입(문제 등록) */
	public final static String ACTIVITY_LOG_TYPE_REGIST_QUESTION = "01";
	/** 활동 구분 타입(답글 등록) */
	public final static String ACTIVITY_LOG_TYPE_REGIST_ANSWER = "02";
	/** 활동 구분 타입(문제 좋아요) */
	public final static String ACTIVITY_LOG_TYPE_LIKE_QUESTION = "03";
	/** 활동 구분 타입(답글 좋아요) */
	public final static String ACTIVITY_LOG_TYPE_LIKE_ANSWER = "04";
	/** 활동 구분 타입(채택 답글) */
	public final static String ACTIVITY_LOG_TYPE_CHOOSE_ANSWER = "05";
	/** 활동 구분 타입(다Q최초 그룹 이동) */
	public final static String ACTIVITY_LOG_TYPE_SCRAP_DAQ = "06";
	/** 활동 구분 타입(App 최초가입) */
	public final static String ACTIVITY_LOG_TYPE_JOIN_APP = "07";
	/** 활동 구분 타입(그룹 생성) */
	public final static String ACTIVITY_LOG_TYPE_CREATE_GROUP = "08";
	/** 활동 구분 타입(맴버 초대) */
	public final static String ACTIVITY_LOG_TYPE_INVITE_MEMBER = "09";
	/** 활동 구분 타입(맴버 가입) */
	public final static String ACTIVITY_LOG_TYPE_JOIN_MEMBER = "10";
	/** 활동 구분 타입(관리자 부여) */
	public final static String ACTIVITY_LOG_TYPE_GIVE_ADMIN = "11";

	/** 활동 점수 부여 - 일 최대 점수(문제 등록) */
	public final static int MAX_ACTIVITY_SCORE_REGIST_QUESTION = 3;
	/** 활동 점수 부여 - 일 최대 점수(스티커 좋아요) */
	public final static int MAX_ACTIVITY_SCORE_LIKE = 3;

	/** 통계관리 - 기간별 통계 (월별 날짜 접미사) */
	public final static String STATISTICS_PERIOD_MONTH_FORM = "-01";
	/** 통계관리 - 기간별 통계 (년별 시작 날짜 접미사) */
	public final static String STATISTICS_PERIOD_START_YEAR_FORM = "-01-01";
	/** 통계관리 - 기간별 통계 (년별 종료 날짜 접미사) */
	public final static String STATISTICS_PERIOD_END_YEAR_FORM = "-12-31";


}