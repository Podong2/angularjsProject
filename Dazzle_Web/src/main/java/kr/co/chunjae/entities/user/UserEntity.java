package kr.co.chunjae.entities.user;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.entities.PageEntity;
import kr.co.chunjae.entities.SmsEntity;

/**
 * @author hsy
 *
 */
public class UserEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = 4723808707238708456L;

	public UserEntity() {
		super(1L);
	}

	private long userKey;									// 회원 고유키
	private String id;										// 회원 아이디
	private String typeCode;								// 회원 분류(01:회원, 02:전문가, 03:관리자)
	private String password;								// 회원 비밀번호
	private String passwordCheck;							// 회원 비밀번호 확인
	private String name;									// 회원 이름
	private String phoneNumber;								// 휴대폰 번호
	private String userGrade;								// 학년
	private String userGradeCodeName;						// 학년 코드명
	private String franchiseType;							// 프렌차이즈 인증타입
	private String franchiseCodeName;						// 프렌차이즈 코드명
	private String stateCode;								// 회원 상태(01:정상, 02:탈퇴, 03:강퇴)
	private long imageKey;									// 프로필 이미지 고유키
	private Date insertDate;								// 가입 날짜
	private Date deleteDate;								// 탈퇴 날짜
	private int questionCount;								// 문제수
	private int answerCount;								// 답글수
	private int activityScore;								// 활동점수
	private int activityLogScore;								// 활동점수
	private String arrayType;								// 활동점수 정렬
	private String userRating;								// 개인 레벨
	private String tempPassword;							// 임시비밀번호
	private String confirmNumber;							// 휴대폰 인증번호
	private String inputConfirmNumber;						// 입력한 휴대폰 인증번호
	private String loginType = CommonCode.LOGIN_TYPE_USER;	// 로그인 구분(관리자, 사용자)
	private long sessionUserKey;							// 세션 회원 고유키
	private int startRow;									// 시작 개수
	private int rowCount;									// 목록 개수
	private Date coercionDeleteDate;						// 강제퇴장 날짜
	private String fileName;								// 프로필 이미지
	private int groupCount;									// 내 그룹 갯수
	private String closedYn;									// 비공개 설정 여부
	private List<UserEntity> userEntity;					// 유저목록
	private SmsEntity smsEntity;							// sms 전송값

	public void setPageParams() {
		Map paramsMap = new ConcurrentHashMap<String, Object>();
		if (StringUtils.isNotEmpty(typeCode))   paramsMap.put("typeCode", typeCode);
		if (StringUtils.isNotEmpty(stateCode))   paramsMap.put("stateCode", stateCode);
		if (StringUtils.isNotEmpty(userGrade))  paramsMap.put("userGrade", userGrade);
		if (StringUtils.isNotEmpty(franchiseType))  paramsMap.put("franchiseType", franchiseType);
		if (StringUtils.isNotEmpty(getSearchKey())) paramsMap.put("searchKey", getSearchKey());
		if (StringUtils.isNotEmpty(getSearchValue())) paramsMap.put("searchValue", getSearchValue());
		if (StringUtils.isNotEmpty(arrayType))   paramsMap.put("arrayType", arrayType);
		if (StringUtils.isNotEmpty(getStartDate()))   paramsMap.put("startDate", getStartDate());
		if (StringUtils.isNotEmpty(getEndDate()))     paramsMap.put("endDate", getEndDate());
		super.setPageParams(paramsMap);
	}

	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserGrade() {
    	return userGrade;
    }
    public void setUserGrade(String userGrade) {
    	this.userGrade = userGrade;
    }
    public String getUserGradeCodeName() {
		return userGradeCodeName;
	}
	public void setUserGradeCodeName(String userGradeCodeName) {
		this.userGradeCodeName = userGradeCodeName;
	}
	public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    public String getFranchiseType() {
		return franchiseType;
	}
	public void setFranchiseType(String franchiseType) {
		this.franchiseType = franchiseType;
	}
	public long getImageKey() {
		return imageKey;
	}
	public void setImageKey(long imageKey) {
		this.imageKey = imageKey;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public Date getDeleteDate() {
		return deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public List<UserEntity> getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(List<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public String getArrayType() {
		return arrayType;
	}
	public void setArrayType(String arrayType) {
		this.arrayType = arrayType;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	public String getConfirmNumber() {
		return confirmNumber;
	}
	public void setConfirmNumber(String confirmNumber) {
		this.confirmNumber = confirmNumber;
	}
	public String getInputConfirmNumber() {
		return inputConfirmNumber;
	}
	public void setInputConfirmNumber(String inputConfirmNumber) {
		this.inputConfirmNumber = inputConfirmNumber;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public long getSessionUserKey() {
		return sessionUserKey;
	}

	public void setSessionUserKey(long sessionUserKey) {
		this.sessionUserKey = sessionUserKey;
	}

	public String getFranchiseCodeName() {
		return franchiseCodeName;
	}

	public void setFranchiseCodeName(String franchiseCodeName) {
		this.franchiseCodeName = franchiseCodeName;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public Date getCoercionDeleteDate() {
		return coercionDeleteDate;
	}

	public void setCoercionDeleteDate(Date coercionDeleteDate) {
		this.coercionDeleteDate = coercionDeleteDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public SmsEntity getSmsEntity() {
		return smsEntity;
	}

	public void setSmsEntity(SmsEntity smsEntity) {
		this.smsEntity = smsEntity;
	}

	public int getActivityLogScore() {
		return activityLogScore;
	}

	public void setActivityLogScore(int activityLogScore) {
		this.activityLogScore = activityLogScore;
	}

	public String getClosedYn() {
		return closedYn;
	}

	public void setClosedYn(String closedYn) {
		this.closedYn = closedYn;
	}
}