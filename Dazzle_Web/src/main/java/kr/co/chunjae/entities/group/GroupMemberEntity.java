package kr.co.chunjae.entities.group;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import kr.co.chunjae.entities.PageEntity;
import kr.co.chunjae.entities.ResponseEntity;

public class GroupMemberEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = -8210560737449079282L;

	public GroupMemberEntity() {
		super(1L);
	}

	private long memberKey;					// 그룹 멤버 고유키
	private long groupKey;					// 그룹 고유키
	private long userKey;					// 회원 고유키
	private long scrapBoardKey;				// 스크랩 게시물 고유키
	private String userTypeCodeName;		// 회원 분류 코드명
	private String userStateCode;			// 회원 분류 코드
	private String userStateCodeName;		// 회원 상태 코드명
	private String id;						// 아이디
	private String name;					// 이름
	private String phoneNumber;				// 휴대폰번호
	private String userGrade;				// 학년코드
	private String userGradeName;			// 학년이름
	private String memberName;				// 그룹 멤버 이름
	private String memberTypeCode;			// 그룹 멤버 분류(01:멤버, 02:리더, 03:공동리더)
	private String memberStateCode;			// 그룹 멤버 상태(01:가입대기, 02:가입, 03:가입취소, 04:탈퇴)
	private boolean groupPushActive;		// 그룹알람 설정 on/off
	private Date deleteDate;				// 그룹 탈퇴 날짜
	private Date insertDate;				// 그룹 가입 날짜
	private Date acceptDate;				// 승인 날짜
	private Date refuseDate;				// 승인 거절 날짜
	private int questionCount;				// 문제수
	private int answerCount;				// 답글수
	private int activityScore;				// 활동점수
	private String userRating;				// 회원 등급
	private String userRatingClassName;		// 그룹 멤버 레벨 클래스명
	private String franchiseType;			// 프렌차이즈 인증타입
	private String franchiseCodeName;		// 프렌차이즈 코드명
	private String groupTypeCode;			// 그룹타입코드
	private String fileName;					// 프로필 이미지명
	private String closedYn;					// 프로필 비공개 유무
	private ResponseEntity responseEntity;
	private List<GroupMemberEntity> groupMemberList;

	public void setPageParams() {
		Map paramsMap = new ConcurrentHashMap<String, Object>();
		if (StringUtils.isNotEmpty(getSortBy()))      paramsMap.put("sortBy", getSortBy());
		if (StringUtils.isNotEmpty(getSortOrder()))   paramsMap.put("sortOrder", getSortOrder());
		super.setPageParams(paramsMap);
	}

	public long getMemberKey() {
		return memberKey;
	}
	public void setMemberKey(long memberKey) {
		this.memberKey = memberKey;
	}
	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public long getScrapBoardKey() {
		return scrapBoardKey;
	}
	public void setScrapBoardKey(long scrapBoardKey) {
		this.scrapBoardKey = scrapBoardKey;
	}
	public String getUserTypeCodeName() {
		return userTypeCodeName;
	}
	public void setUserTypeCodeName(String userTypeCodeName) {
		this.userTypeCodeName = userTypeCodeName;
	}
	public String getUserStateCode() {
		return userStateCode;
	}
	public void setUserStateCode(String userStateCode) {
		this.userStateCode = userStateCode;
	}
	public String getUserStateCodeName() {
		return userStateCodeName;
	}
	public void setUserStateCodeName(String userStateCodeName) {
		this.userStateCodeName = userStateCodeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUserGradeName() {
		return userGradeName;
	}
	public void setUserGradeName(String userGradeName) {
		this.userGradeName = userGradeName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTypeCode() {
		return memberTypeCode;
	}
	public void setMemberTypeCode(String memberTypeCode) {
		this.memberTypeCode = memberTypeCode;
	}
	public String getMemberStateCode() {
		return memberStateCode;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public boolean isGroupPushActive() {
		return groupPushActive;
	}
	public void setGroupPushActive(boolean groupPushActive) {
		this.groupPushActive = groupPushActive;
	}
	public void setMemberStateCode(String memberStateCode) {
		this.memberStateCode = memberStateCode;
	}
	public Date getDeleteDate() {
		return deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public Date getAcceptDate() {
		return acceptDate == null ? null : new Date(acceptDate.getTime());
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate == null ? null : new Date(acceptDate.getTime());
	}
	public Date getRefuseDate() {
		return refuseDate == null ? null : new Date(refuseDate.getTime());
	}
	public void setRefuseDate(Date refuseDate) {
		this.refuseDate = refuseDate;
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
	public List<GroupMemberEntity> getGroupMemberList() {
		return groupMemberList;
	}
	public void setGroupMemberList(List<GroupMemberEntity> groupMemberList) {
		this.groupMemberList = groupMemberList;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getUserRatingClassName() {
		return userRatingClassName;
	}
	public void setUserRatingClassName(String userRatingClassName) {
		this.userRatingClassName = userRatingClassName;
	}
	public String getFranchiseType() {
		return franchiseType;
	}
	public void setFranchiseType(String franchiseType) {
		this.franchiseType = franchiseType;
	}
	public String getFranchiseCodeName() {
		return franchiseCodeName;
	}
	public void setFranchiseCodeName(String franchiseCodeName) {
		this.franchiseCodeName = franchiseCodeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupTypeCode() {
		return groupTypeCode;
	}
	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}
	public ResponseEntity getResponseEntity() {
		return responseEntity;
	}
	public void setResponseEntity(ResponseEntity responseEntity) {
		this.responseEntity = responseEntity;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getClosedYn() {
		return closedYn;
	}

	public void setClosedYn(String closedYn) {
		this.closedYn = closedYn;
	}
}