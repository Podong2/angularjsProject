package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.PageEntity;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class QuestionEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = -481316535749272260L;

	public QuestionEntity() {
		super(1L);
	}

	private long questionKey;						// 문제 고유키
	private long userKey;							// 회원 고유키
	private long groupKey;							// 그룹 고유키
	private long imageKey;							// 문제 이미지 고유키
	private String questionImageName;				// 문제 이미지 파일명
	private String profileImageName;				// 프로필 이미지 파일명
	private String name;								// 작성자명
	private String questionTypeCategory;			// 문제 유형(유형)
	private String questionTypeLarge;				// 문제유형(대단원)
	private String questionTypeLargeName;			// 문제유형(대단원)명
	private String questionTypeMedium;				// 문제유형(중단원)
	private String questionTypeSmall;				// 문제유형(소단원)
	private String questionContents;				// 내용
	private String questionGrade;					// 학년코드
	private String questionGradeCodeName;			// 학년명
	private String questionType;					// 문제분류(all:일반그룹, daq:다Q)
	private String questionTypeCode;				// 문제분류타입(01:일반, 02:그룹, 03:다Q)
	private String questionTypeCodeName;			// 문제분류타입명
	private int questionCount;						// 문제수
	private int answerCount;							// 답글수
	private int userAnswerCount;					// 문제 등록자 답글수
	private int likeCount;							// 좋아요수
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exposeStartDate;					// 노출 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exposeEndDate;						// 노출 종료일
	private int exposeRemainDay;					// 노출 남은 일수
	private Date insertDate;							// 등록일
	private Date deleteDate;							// 삭제일
	private String searchQuestionType;				// 검색조건 - 문제분류(01:전체문제, 02:안풀린문제)
	private String fileName;							// 파일명
	private String imageDataString;					// base64 이미지 데이타
	private String fileOrigName;					// 원본 파일명
	private int adminChooseCount;					// 관리자 채택 답글 여부
	private int questionerChooseCount;				// 문제 등록자 채택 답글 여부
	private String insertDateTime;					// 작성 시점
	private int activityScore;						// 활동점수
	private String userRating;						// 사용자 등급
	private String classCodeType;
	private String classCode;
	private int isScrap;								// 문제 스크랩 여부
	private int isGroupScrap;						// 그룹 스크랩 여부
	private String groupColor;						// 그룹 컬러
	private String scrapColor;						// 스크랩 그룹 컬러(최초)
	private String closedYn;							// 프로필 비공개 유무
	private String resultCode = ResultCode.FAIL;
	private String resultMsg  = "";
	private List<QuestionEntity> questionList;

	public void setPageParams() {
		Map paramsMap = new ConcurrentHashMap<String, Object>();
		if (StringUtils.isNotEmpty(questionType))       paramsMap.put("questionType", questionType);
		if (StringUtils.isNotEmpty(searchQuestionType)) paramsMap.put("searchQuestionType",searchQuestionType);
		if (StringUtils.isNotEmpty(getStartDate()))     paramsMap.put("startDate", getStartDate());
		if (StringUtils.isNotEmpty(getEndDate()))       paramsMap.put("endDate", getEndDate());
		if (StringUtils.isNotEmpty(getSearchKey()))     paramsMap.put("searchKey", getSearchKey());
		if (StringUtils.isNotEmpty(getSearchValue()))   paramsMap.put("searchValue", getSearchValue());
		super.setPageParams(paramsMap);
	}

	public long getQuestionKey() {
		return questionKey;
	}
	public void setQuestionKey(long questionKey) {
		this.questionKey = questionKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public long getImageKey() {
		return imageKey;
	}
	public void setImageKey(long imageKey) {
		this.imageKey = imageKey;
	}
	public String getQuestionImageName() {
		return questionImageName;
	}
	public void setQuestionImageName(String questionImageName) {
		this.questionImageName = questionImageName;
	}
	public String getProfileImageName() {
		return profileImageName;
	}
	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestionTypeCategory() {
		return questionTypeCategory;
	}
	public void setQuestionTypeCategory(String questionTypeCategory) {
		this.questionTypeCategory = questionTypeCategory;
	}
	public String getQuestionTypeLarge() {
		return questionTypeLarge;
	}
	public void setQuestionTypeLarge(String questionTypeLarge) {
		this.questionTypeLarge = questionTypeLarge;
	}
	public String getQuestionTypeLargeName() {
		return questionTypeLargeName;
	}
	public void setQuestionTypeLargeName(String questionTypeLargeName) {
		this.questionTypeLargeName = questionTypeLargeName;
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
	public String getQuestionContents() {
		return questionContents;
	}
	public void setQuestionContents(String questionContents) {
		this.questionContents = questionContents;
	}
	public String getQuestionGrade() {
		return questionGrade;
	}
	public void setQuestionGrade(String questionGrade) {
		this.questionGrade = questionGrade;
	}
	public String getQuestionGradeCodeName() {
		return questionGradeCodeName;
	}
	public void setQuestionGradeCodeName(String questionGradeCodeName) {
		this.questionGradeCodeName = questionGradeCodeName;
	}
	public String getQuestionTypeCode() {
		return questionTypeCode;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}
	public String getQuestionTypeCodeName() {
		return questionTypeCodeName;
	}
	public void setQuestionTypeCodeName(String questionTypeCodeName) {
		this.questionTypeCodeName = questionTypeCodeName;
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
	public int getUserAnswerCount() {
		return userAnswerCount;
	}
	public void setUserAnswerCount(int userAnswerCount) {
		this.userAnswerCount = userAnswerCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public Date getExposeStartDate() {
		return exposeStartDate == null ? null : new Date(exposeStartDate.getTime());
	}
	public void setExposeStartDate(Date exposeStartDate) {
		this.exposeStartDate = exposeStartDate == null ? null : new Date(exposeStartDate.getTime());
	}
	public Date getExposeEndDate() {
		return exposeEndDate == null ? null : new Date(exposeEndDate.getTime());
	}
	public void setExposeEndDate(Date exposeEndDate) {
		this.exposeEndDate = exposeEndDate == null ? null : new Date(exposeEndDate.getTime());
	}
	public int getExposeRemainDay() {
		return exposeRemainDay;
	}
	public void setExposeRemainDay(int exposeRemainDay) {
		this.exposeRemainDay = exposeRemainDay;
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
	public String getSearchQuestionType() {
		return searchQuestionType;
	}
	public void setSearchQuestionType(String searchQuestionType) {
		this.searchQuestionType = searchQuestionType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getImageDataString() {
		return imageDataString;
	}
	public void setImageDataString(String imageDataString) {
		this.imageDataString = imageDataString;
	}
	public String getFileOrigName() {
		return fileOrigName;
	}
	public void setFileOrigName(String fileOrigName) {
		this.fileOrigName = fileOrigName;
	}
	public int getAdminChooseCount() {
		return adminChooseCount;
	}
	public void setAdminChooseCount(int adminChooseCount) {
		this.adminChooseCount = adminChooseCount;
	}
	public int getQuestionerChooseCount() {
		return questionerChooseCount;
	}
	public void setQuestionerChooseCount(int questionerChooseCount) {
		this.questionerChooseCount = questionerChooseCount;
	}
	public List<QuestionEntity> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionEntity> questionList) {
		this.questionList = questionList;
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getClassCodeType() {
		return classCodeType;
	}
	public void setClassCodeType(String classCodeType) {
		this.classCodeType = classCodeType;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public int getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(int isScrap) {
		this.isScrap = isScrap;
	}
	public int getIsGroupScrap() {
		return isGroupScrap;
	}
	public void setIsGroupScrap(int isGroupScrap) {
		this.isGroupScrap = isGroupScrap;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}

	public String getGroupColor() {
		return groupColor;
	}

	public void setGroupColor(String groupColor) {
		this.groupColor = groupColor;
	}

	public String getScrapColor() {
		return scrapColor;
	}

	public void setScrapColor(String scrapColor) {
		this.scrapColor = scrapColor;
	}

	public String getClosedYn() {
		return closedYn;
	}

	public void setClosedYn(String closedYn) {
		this.closedYn = closedYn;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}