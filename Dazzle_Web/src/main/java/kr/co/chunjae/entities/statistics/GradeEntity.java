package kr.co.chunjae.entities.statistics;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.PageEntity;

public class GradeEntity extends PageEntity implements Serializable {

	public GradeEntity() {
		super(1L);
	}

	private static final long serialVersionUID = 5569962516387774268L;

	private String questionGrade;	// 학년
	private int questionCount;		// 문제 수
	private int answerCount;		// 답글 수
	private int noAnswerCount;		// 미답글 수
	private String classCode;		// 단원 코드
	private String commCdNm;		// 공통코드명
	private String commCdVal;		// 공통코드
	private String classCodeForm;	// 단원코드 형식
	private String className;		// 단원명
	private int questionCountTotal;	// 문제 수
	private int answerCountTotal;	// 답글 수
	private int noAnswerCountTotal;	// 미답글 수
	private String resultDate;		// 기간별 날짜
	private String dateParam;		// 기간 타입 코드
	private int activityCount;		// 총 활동개수
	private String activityType;	// 활동개수 통계 검색타입
	private int userCount;			// 사용자 수
	private float userRate;			// 사용자 비율
	private int activityScore;		// 활동점수 단위값
	private String sectionScore;	// 활동점수 구간값
	private String startDay;			// 일별 시작일
	private String endDay;			// 일별 종료일
	private String startMonth;		// 월별 시작일
	private String endMonth;			// 월별 종료일
	private String startYear;		// 년별 시작
	private String endYear;			// 년별 종료일
	private List<GradeEntity> gradeList;

	public String getQuestionGrade() {
		return questionGrade;
	}
	public void setQuestionGrade(String questionGrade) {
		this.questionGrade = questionGrade;
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
	public int getNoAnswerCount() {
		return noAnswerCount;
	}
	public void setNoAnswerCount(int noAnswerCount) {
		this.noAnswerCount = noAnswerCount;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getCommCdNm() {
		return commCdNm;
	}
	public void setCommCdNm(String commCdNm) {
		this.commCdNm = commCdNm;
	}
	public List<GradeEntity> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<GradeEntity> gradeList) {
		this.gradeList = gradeList;
	}
	public String getClassCodeForm() {
		return classCodeForm;
	}
	public void setClassCodeForm(String classCodeForm) {
		this.classCodeForm = classCodeForm;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getQuestionCountTotal() {
		return questionCountTotal;
	}
	public void setQuestionCountTotal(int questionCountTotal) {
		this.questionCountTotal = questionCountTotal;
	}
	public int getAnswerCountTotal() {
		return answerCountTotal;
	}
	public void setAnswerCountTotal(int answerCountTotal) {
		this.answerCountTotal = answerCountTotal;
	}
	public int getNoAnswerCountTotal() {
		return noAnswerCountTotal;
	}
	public void setNoAnswerCountTotal(int noAnswerCountTotal) {
		this.noAnswerCountTotal = noAnswerCountTotal;
	}
	public String getCommCdVal() {
		return commCdVal;
	}
	public void setCommCdVal(String commCdVal) {
		this.commCdVal = commCdVal;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getDateParam() {
		return dateParam;
	}
	public void setDateParam(String dateParam) {
		this.dateParam = dateParam;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public float getUserRate() {
		return userRate;
	}
	public void setUserRate(float userRate) {
		this.userRate = userRate;
	}
	public int getActivityCount() {
		return activityCount;
	}
	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public String getSectionScore() {
		return sectionScore;
	}
	public void setSectionScore(String sectionScore) {
		this.sectionScore = sectionScore;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}


}