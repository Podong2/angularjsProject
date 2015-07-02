package kr.co.chunjae.service;

import kr.co.chunjae.entities.question.LikeEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.question.ScrapEntity;

public interface QuestionService {
	/**
	 * 문제 리스트 카운트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	public long selectQuestionListCount(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	public QuestionEntity selectQuestionList(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 내 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	public QuestionEntity selectMyQuestionList(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 스크랩 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	public QuestionEntity selectMyScrapList(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 문제 정보 가져오기
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	public QuestionEntity selectQuestion(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 문제/답글 좋아요 여부 확인
	 * @param likeEntity
	 * @return
	 * @throws Exception
	 */
	public int selectIsLike(LikeEntity likeEntity) throws Exception;
	
	/**
	 * 문제 등록
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	public void insertQuestion(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 문제/답글 좋아요
	 * @param likeEntity
	 * @throws Exception
	 */
	public void LikeYn(LikeEntity likeEntity) throws Exception;
	
	/**
	 * 문제 유형 수정
	 * @param questionEntity
	 * @throws Exception
	 */
	public void updateQuestionType(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 다Q 문제 정보 수정
	 * @throws Exception
	 */
	public void updateDaqQuestion(QuestionEntity questionEntity) throws Exception;
	
	/**
	 * 문제 삭제
	 * @param questionKey
	 * @throws Exception
	 */
	public void deleteQuestion(long questionKey) throws Exception;
	
	/**
	 * 스크랩 여부 확인
	 * @param scrapEntity
	 * @return
	 * @throws Exception
	 */
	public int selectIsScrap(ScrapEntity scrapEntity) throws Exception;
	
	/**
	 * 문제 스크랩 정보 등록
	 * @param scrapEntity
	 * @return
	 * @throws Exception
	 */
	public void insertQuestionScrap(ScrapEntity scrapEntity) throws Exception;
	
	/**
	 * 문제 스크랩 정보 수정
	 * @param scrapEntity
	 * @throws Exception
	 */
	public void updateQuestionScrap(ScrapEntity scrapEntity) throws Exception;
}