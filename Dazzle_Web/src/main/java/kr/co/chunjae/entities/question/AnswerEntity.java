package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import kr.co.chunjae.entities.PageEntity;

public class AnswerEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = 551930150210802821L;

	public AnswerEntity() {
		super(1L);
	}

	private long answerKey;								// 답글 게시판 고유키
	private long userKey;								// 회원 고유키
	private String name;								// 이름
	private long groupKey;								// 그룹 고유키
	private long questionKey;							// 문제 게시판 고유키
	private long imageKey;								// 이미지 고유키
	private long reportKey;								// 신고관리 고유키
	private String answerContents;						// 내용
	private String answerGrade;							// 학년정보
	private String answerTypeCode;						// 문제 분류(일반, 그룹)
	private Date insertDate;							// 작성 날짜
	private Date deleteDate;							// 삭제 날짜
	private String reportTypeCode;						// 신고 타입
	private String chooseType;							// 채택 유저 타입(admin:관리자, questioner:문제 등록자)
	private long chooseKey;								// 채택 유저 고유키
	private long chooseAdminKey;						// 채택 유저 고유키(관리자)
	private long chooseQuestionerKey;					// 채택 유저 고유키(문제 등록자)
	private String chooseYn;							// 채택 여부
	private String questionTypeLarge;					// 문제 유형(대단원)
	private String questionTypeMedium;					// 문제 유형(중단원)
	private String questionTypeSmall;					// 문제 유형(소단원)
	private String questionTypeCategory;				// 문제 유형(유형)
	private int answerLikeCount;						// 답글 좋아요 수
	private int answerLikeCountByUserKey;				// 로그인한 사용자 답글 좋아요 여부
	private int anserReplyCount;						// 답글 댓글 수
	private int answerUserQuestionCount;				// 답글 등록자 등록 문제 수
	private int answerUserAnswerCount;					// 딥글 등록자 문제 답글 수
	private int answerUserActivityScore;				// 답글 등록자 활동점수
	private String profileImageName;					// 프로필 이미지 파일명
	private String fileName;							// 파일명
	private String fileOrigName;						// 원본 파일명
	private String imageDataString;						// base64 이미지 데이타
	private String insertDateTime;						// 작성 시점
	private int activityScore;						// 활동점수
	private String userRating;							// 등급
	private String closedYn;								// 사용자 프로필 비공개 유무
	private List<AnswerEntity> answerList;				// 답글 리스트
	private List<AnswerReplyEntity> answerReplyList;	// 답글 댓글 리스트

	public long getAnswerKey() {
		return answerKey;
	}
	public void setAnswerKey(long answerKey) {
		this.answerKey = answerKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public long getQuestionKey() {
		return questionKey;
	}
	public void setQuestionKey(long questionKey) {
		this.questionKey = questionKey;
	}
	public long getImageKey() {
		return imageKey;
	}
	public void setImageKey(long imageKey) {
		this.imageKey = imageKey;
	}
	public long getReportKey() {
		return reportKey;
	}
	public void setReportKey(long reportKey) {
		this.reportKey = reportKey;
	}
	public String getAnswerContents() {
		return answerContents;
	}
	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}
	public String getAnswerGrade() {
		return answerGrade;
	}
	public void setAnswerGrade(String answerGrade) {
		this.answerGrade = answerGrade;
	}
	public String getAnswerTypeCode() {
		return answerTypeCode;
	}
	public void setAnswerTypeCode(String answerTypeCode) {
		this.answerTypeCode = answerTypeCode;
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
	public String getReportTypeCode() {
		return reportTypeCode;
	}
	public void setReportTypeCode(String reportTypeCode) {
		this.reportTypeCode = reportTypeCode;
	}
	public String getChooseType() {
		return chooseType;
	}
	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}
	public long getChooseKey() {
		return chooseKey;
	}
	public void setChooseKey(long chooseKey) {
		this.chooseKey = chooseKey;
	}
	public long getChooseAdminKey() {
		return chooseAdminKey;
	}
	public void setChooseAdminKey(long chooseAdminKey) {
		this.chooseAdminKey = chooseAdminKey;
	}
	public long getChooseQuestionerKey() {
		return chooseQuestionerKey;
	}
	public void setChooseQuestionerKey(long chooseQuestionerKey) {
		this.chooseQuestionerKey = chooseQuestionerKey;
	}
	public String getChooseYn() {
		return chooseYn;
	}
	public void setChooseYn(String chooseYn) {
		this.chooseYn = chooseYn;
	}
	public String getQuestionTypeLarge() {
		return questionTypeLarge;
	}
	public void setQuestionTypeLarge(String questionTypeLarge) {
		this.questionTypeLarge = questionTypeLarge;
	}
	public String getQuestionTypeMedium() {
		return questionTypeMedium;
	}
	public void setQuestionTypeMedium(String questionTypeMedium) {
		this.questionTypeMedium = questionTypeMedium;
	}
	public String getQuestionTypeSmall() {
		return questionTypeSmall;
	}
	public void setQuestionTypeSmall(String questionTypeSmall) {
		this.questionTypeSmall = questionTypeSmall;
	}
	public String getQuestionTypeCategory() {
		return questionTypeCategory;
	}
	public void setQuestionTypeCategory(String questionTypeCategory) {
		this.questionTypeCategory = questionTypeCategory;
	}
	public int getAnswerLikeCount() {
		return answerLikeCount;
	}
	public void setAnswerLikeCount(int answerLikeCount) {
		this.answerLikeCount = answerLikeCount;
	}
	public int getAnswerLikeCountByUserKey() {
		return answerLikeCountByUserKey;
	}
	public void setAnswerLikeCountByUserKey(int answerLikeCountByUserKey) {
		this.answerLikeCountByUserKey = answerLikeCountByUserKey;
	}
	public int getAnserReplyCount() {
		return anserReplyCount;
	}
	public void setAnserReplyCount(int anserReplyCount) {
		this.anserReplyCount = anserReplyCount;
	}
	public int getAnswerUserQuestionCount() {
		return answerUserQuestionCount;
	}
	public void setAnswerUserQuestionCount(int answerUserQuestionCount) {
		this.answerUserQuestionCount = answerUserQuestionCount;
	}
	public int getAnswerUserAnswerCount() {
		return answerUserAnswerCount;
	}
	public void setAnswerUserAnswerCount(int answerUserAnswerCount) {
		this.answerUserAnswerCount = answerUserAnswerCount;
	}
	public int getAnswerUserActivityScore() {
		return answerUserActivityScore;
	}
	public void setAnswerUserActivityScore(int answerUserActivityScore) {
		this.answerUserActivityScore = answerUserActivityScore;
	}
	public String getProfileImageName() {
		return profileImageName;
	}
	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public String getFileOrigName() {
		return fileOrigName;
	}
	public void setFileOrigName(String fileOrigName) {
		this.fileOrigName = fileOrigName;
	}
	public String getImageDataString() {
		return imageDataString;
	}
	public void setImageDataString(String imageDataString) {
		this.imageDataString = imageDataString;
	}
	public List<AnswerEntity> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerEntity> answerList) {
		this.answerList = answerList;
	}
	public List<AnswerReplyEntity> getAnswerReplyList() {
		return answerReplyList;
	}
	public void setAnswerReplyList(List<AnswerReplyEntity> answerReplyList) {
		this.answerReplyList = answerReplyList;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getClosedYn() {
		return closedYn;
	}
	public void setClosedYn(String closedYn) {
		this.closedYn = closedYn;
	}
}