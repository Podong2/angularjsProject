package kr.co.chunjae.entities.question;

import java.io.Serializable;

import kr.co.chunjae.entities.ResponseEntity;

public class AnswerReplyInfoEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = -2874294805942180128L;

	private AnswerReplyEntity answerReplyInfo;

	public AnswerReplyEntity getAnswerReplyInfo() {
		return answerReplyInfo;
	}

	public void setAnswerReplyInfo(AnswerReplyEntity answerReplyInfo) {
		this.answerReplyInfo = answerReplyInfo;
	}
}