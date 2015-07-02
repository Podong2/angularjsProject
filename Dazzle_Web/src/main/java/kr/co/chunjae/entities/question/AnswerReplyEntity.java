package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.Date;

public class AnswerReplyEntity implements Serializable {

	private static final long serialVersionUID = 5358823855821314500L;

	private long answerReplyKey;		// 댓글 고유키
	private long answerKey;				// 답글 고유키
	private long userKey;				// 회원 고유키
	private String name;					// 댓글 작성자 이름
	private String profileImageName;	// 프로필 이미지 파일명
	private String answerReplyContents;// 내용
	private Date insertDate;				// 작성 날짜
	private String insertDateTime;		// 작성 경과 시간 (년/달/일/시/분)
	private Date deleteDate;				// 삭제 날짜
	private int activityScore;			// 활동점수
	private String userRating;			// 등급
	private String closedYn;				// 프로필 비공개 여부

	public long getAnswerReplyKey() {
		return answerReplyKey;
	}
	public void setAnswerReplyKey(long answerReplyKey) {
		this.answerReplyKey = answerReplyKey;
	}
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
	public String getProfileImageName() {
		return profileImageName;
	}
	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	public String getAnswerReplyContents() {
		return answerReplyContents;
	}
	public void setAnswerReplyContents(String answerReplyContents) {
		this.answerReplyContents = answerReplyContents;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public Date getDeleteDate() {
		return deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
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