package kr.co.chunjae.dao;

import kr.co.chunjae.entities.question.ScrapEntity;

public interface ScrapDAO {
	/**
	 * 스크랩 여부 확인
	 * @param scrapEntity
	 * @return
	 * @throws Exception
	 */
	int selectIsScrap(ScrapEntity scrapEntity) throws Exception;
	/**
	 * 스크랩 정보 등록
	 * @param scrapEntity
	 * @throws Exception
	 */
	void insertScrap(ScrapEntity scrapEntity) throws Exception;
	/**
	 * 스크랩 정보 수정
	 * @param scrapEntity
	 * @throws Exception
	 */
	void updateScrap(ScrapEntity scrapEntity) throws Exception;
}