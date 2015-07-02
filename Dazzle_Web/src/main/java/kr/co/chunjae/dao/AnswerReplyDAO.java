package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.question.AnswerReplyEntity;

public interface AnswerReplyDAO {
	/**
	 * 답글 댓글 등록
	 * @param answerReplyEntity
	 * @throws Exception
	 */
	void insertAnswerReply(AnswerReplyEntity answerReplyEntity) throws Exception;
	
	/**
	 * 답글 댓글 수정
	 * @param answerReplyEntity
	 * @throws Exception
	 */
	void updateAnswerReply(AnswerReplyEntity answerReplyEntity) throws Exception;
	
	/**
	 * 답글 댓글 삭제
	 * @param answerReplyKey
	 * @throws Exception
	 */
	void deleteAnswerReply(long answerReplyKey) throws Exception;

	/**
	 * 답글 댓글 정보
	 * @param answerReplyKey
	 * @return
	 * @throws Exception
	 */
	AnswerReplyEntity selectAnswerReply(long answerReplyKey) throws Exception;

	/**
	 * 답글 댓글 리스트
	 * @param answerKey
	 * @return
	 * @throws Exception
	 */
	List<AnswerReplyEntity> selectAnswerReplyList(long answerKey) throws Exception;
}