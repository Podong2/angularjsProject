package kr.co.chunjae.entities.question;

import java.io.Serializable;

public class LikeEntity implements Serializable {

	private static final long serialVersionUID = 7133083592104275893L;

	private long likeKey;				// 좋아요 고유키
	private long userKey;				// 회원 고유키(좋아요 붙인자)
	private long receiveUserKey;		// 좋아요 받은자 고유키
	private long questionKey;			// 문제 고유키
	private long answerKey;				// 답글 고유키
	private long groupKey;				// 그룹 고유키
	private String name;					// 이름
	private String questionTypeCode;	// 문제 타입(0:일반, 1:그룹)
	private String answerTypeCode;		// 답글 타입(0:일반, 1:그룹)
	private String likeYn;				// 좋아요 여부

	public long getLikeKey() {
		return likeKey;
	}
	public void setLikeKey(long likeKey) {
		this.likeKey = likeKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public long getReceiveUserKey() {
		return receiveUserKey;
	}
	public void setReceiveUserKey(long receiveUserKey) {
		this.receiveUserKey = receiveUserKey;
	}
	public long getQuestionKey() {
		return questionKey;
	}
	public void setQuestionKey(long questionKey) {
		this.questionKey = questionKey;
	}
	public long getAnswerKey() {
		return answerKey;
	}
	public void setAnswerKey(long answerKey) {
		this.answerKey = answerKey;
	}
	public String getQuestionTypeCode() {
		return questionTypeCode;
	}
	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}
	public String getAnswerTypeCode() {
		return answerTypeCode;
	}
	public void setAnswerTypeCode(String answerTypeCode) {
		this.answerTypeCode = answerTypeCode;
	}
	public String getLikeYn() {
		return likeYn;
	}
	public void setLikeYn(String likeYn) {
		this.likeYn = likeYn;
	}
	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}