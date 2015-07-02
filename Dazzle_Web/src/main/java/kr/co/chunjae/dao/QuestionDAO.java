package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.question.QuestionEntity;

public interface QuestionDAO {
	/**
	 * 문제 리스트 카운트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	long selectQuestionListCount(QuestionEntity questionEntity) throws Exception;

	/**
	 * 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	List<QuestionEntity> selectQuestionList(QuestionEntity questionEntity) throws Exception;

	/**
	 * 내 문제 리스트 카운트
	 * @param userKey
	 * @return
	 * @throws Exception
	 */
	long selectQuestionListByUserKeyCount(long userKey) throws Exception;

	/**
	 * 내 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	List<QuestionEntity> selectQuestionListByUserKey(QuestionEntity questionEntity) throws Exception;

	/**
	 * 스크랩 리스트 카운트
	 * @param userKey
	 * @return
	 * @throws Exception
	 */
	long selectMyScrapListCount(long userKey) throws Exception;

	/**
	 * 스크랩 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	List<QuestionEntity> selectMyScrapList(QuestionEntity questionEntity) throws Exception;

	/**
	 * 문제 정보 가져오기
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	QuestionEntity selectQuestion(QuestionEntity questionEntity) throws Exception;

	/**
	 * 문제 등록
	 * @param questionEntity
	 * @throws Exception
	 */
	void insertQuestion(QuestionEntity questionEntity) throws Exception;

	/**
	 * 문제 유형 수정
	 * @param questionEntity
	 * @throws Exception
	 */
	void updateQuestionType(QuestionEntity questionEntity) throws Exception;

	/**
	 * 다Q 문제 수정
	 * @param questionEntity
	 * @throws Exception
	 */
	void updateDaqQuestion(QuestionEntity questionEntity) throws Exception;

	/**
	 * 문제 삭제
	 * @param questionKey
	 * @throws Exception
	 */
	void deleteQuestion(long questionKey) throws Exception;

	/**
	 * 내그룹 목록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	List<QuestionEntity> selectMyGroupQustionList(GroupEntity groupEntity) throws Exception;

	/**
	 * 문제 작성 일주일 이내 기간 체크
	 * @param questionKey
	 * @return
	 * @throws Exception
	 */
	int selectWithinWeekQuestion(long questionKey)throws Exception;
}