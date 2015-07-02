package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;

public class QuestionListEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = -7287645736454482545L;
	
	private List<QuestionEntity> questionList;

	public List<QuestionEntity> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionEntity> questionList) {
		this.questionList = questionList;
	}
}
