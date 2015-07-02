package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;

public class AnswerListEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 6917469848041023456L;
	
	private List<AnswerEntity> answerList;

	public List<AnswerEntity> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerEntity> answerList) {
		this.answerList = answerList;
	}
}