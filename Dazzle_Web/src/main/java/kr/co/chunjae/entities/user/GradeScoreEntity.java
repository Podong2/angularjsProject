package kr.co.chunjae.entities.user;

import java.io.Serializable;
import java.util.List;

public class GradeScoreEntity implements Serializable {
	
	private static final long serialVersionUID = -1109188772682713111L;
	
	private long gradescoreKey;						// 등급점수 고유키
	private String code;							// 등급점수 코드
	private String gradeContent;					// 개인활동내용
	private int userLowScore;						// 회원 최소 점수
	private int userHighScore;						// 회원 최대 점수
	private int groupLowScore;						// 그룹 최소 점수
	private int groupHighScore;						// 그룹 최대 점수
	private List<GradeScoreEntity> gradeScoreList;	// 회원등급 목록

	public long getGradescoreKey() {
		return gradescoreKey;
	}
	
	public void setGradescoreKey(long gradescoreKey) {
		this.gradescoreKey = gradescoreKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getGradeContent() {
		return gradeContent;
	}

	public void setGradeContent(String gradeContent) {
		this.gradeContent = gradeContent;
	}

	public int getUserLowScore() {
		return userLowScore;
	}

	public void setUserLowScore(int userLowScore) {
		this.userLowScore = userLowScore;
	}

	public int getUserHighScore() {
		return userHighScore;
	}

	public void setUserHighScore(int userHighScore) {
		this.userHighScore = userHighScore;
	}

	public int getGroupLowScore() {
		return groupLowScore;
	}

	public void setGroupLowScore(int groupLowScore) {
		this.groupLowScore = groupLowScore;
	}

	public int getGroupHighScore() {
		return groupHighScore;
	}

	public void setGroupHighScore(int groupHighScore) {
		this.groupHighScore = groupHighScore;
	}

	public List<GradeScoreEntity> getGradeScoreList() {
		return gradeScoreList;
	}

	public void setGradeScoreList(List<GradeScoreEntity> gradeScoreList) {
		this.gradeScoreList = gradeScoreList;
	}
}