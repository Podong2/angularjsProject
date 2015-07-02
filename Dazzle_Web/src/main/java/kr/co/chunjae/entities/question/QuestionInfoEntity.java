package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;

public class QuestionInfoEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 5508537611509348072L;
	
	private QuestionEntity questionInfo;					// 문제 정보
	private List<AnswerEntity> answerList;					// 답글 목록
	private List<AnswerEntity> answerUserList;				// 답글 등록자 정보 리스트
	private List<List<AnswerReplyEntity>> answerReplyList;	// 답글 댓글 리스트
	
	public QuestionEntity getQuestionInfo() {
		return questionInfo;
	}
	public void setQuestionInfo(QuestionEntity questionInfo) {
		this.questionInfo = questionInfo;
	}
	public List<AnswerEntity> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerEntity> answerList) {
		this.answerList = answerList;
	}
	public List<AnswerEntity> getAnswerUserList() {
		return answerUserList;
	}
	public void setAnswerUserList(List<AnswerEntity> answerUserList) {
		this.answerUserList = answerUserList;
	}
	public List<List<AnswerReplyEntity>> getAnswerReplyList() {
		return answerReplyList;
	}
	public void setAnswerReplyList(List<List<AnswerReplyEntity>> answerReplyList) {
		this.answerReplyList = answerReplyList;
	}
}