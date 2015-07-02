package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.AnswerReplyEntity;

public interface AnswerService {
	/**
	 * 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	public AnswerEntity selectAnswerList(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 내답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	public AnswerEntity selectMyAnswerList(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 등록자 리스트
	 * @param questionKey
	 * @return
	 * @throws Exception
	 */
	public List<AnswerEntity> selectAnswerUserListByQuestionKey(long questionKey) throws Exception;
	
	/**
	 * 신고 대상 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	public List<AnswerEntity> selectReportAnswerList(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 등록
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	public void insertAnswer(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 삭제
	 * @param answerKey
	 * @throws Exception
	 */
	public void deleteAnswer(long answerKey) throws Exception;
	
	/**
	 * 답글 채택
	 * @param answerEntity
	 * @throws Exception
	 */
	public void chooseAnswer(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 채택 취소
	 * @param answerEntity
	 * @throws Exception
	 */
	public void ChooseCancelAnswer(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 댓글 등록
	 * @param answerReplyEntity
	 * @throws Exception
	 */
	public void insertAnswerReply(AnswerReplyEntity answerReplyEntity) throws Exception;

	/**
	 * 답글 댓글 정보
	 * @param answerReplyKey
	 * @throws Exception
	 */
	public AnswerReplyEntity selectAnswerReply(long answerReplyKey) throws Exception;

	/**
	 * 답글 댓글 수정
	 * @param answerReplyEntity
	 * @throws Exception
	 */
	public void updateAnswerReply(AnswerReplyEntity answerReplyEntity) throws Exception;
	
	/**
	 * 답글 댓글 삭제
	 * @param answerReplyKey
	 * @throws Exception
	 */
	public void deleteAnswerReply(long answerReplyKey) throws Exception;
}