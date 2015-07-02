package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.question.AnswerEntity;

public interface AnswerDAO {
	/**
	 * 답글 리스트 카운트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	long selectAnswerListCount(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	List<AnswerEntity> selectAnswerList(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 내 답글 리스트 카운트
	 * @param userKey
	 * @return
	 * @throws Exception
	 */
	long selectAnswerListByUserKeyCount(long userKey) throws Exception;
	
	/**
	 * 내 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	List<AnswerEntity> selectAnswerListByUserKey(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 등록자 리스트
	 * @param questionKey
	 * @return
	 * @throws Exception
	 */
	List<AnswerEntity> selectAnswerUserListByQuestionKey(long questionKey) throws Exception;
	
	/**
	 * 신고 대상 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	List<AnswerEntity> selectReportAnswerList(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 등록
	 * @param answerEntity
	 * @throws Exception
	 */
	void insertAnswer(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 삭제
	 * @param answerKey
	 * @throws Exception
	 */
	void deleteAnswer(long answerKey) throws Exception;
	
	/**
	 * 답글 채택
	 * @param answerEntity
	 * @throws Exception
	 */
	void updateChooseAnswer(AnswerEntity answerEntity) throws Exception;
	
	/**
	 * 답글 채택 취소
	 * @param answerEntity
	 * @throws Exception
	 */
	void updateChooseCancelAnswer(AnswerEntity answerEntity) throws Exception;
}