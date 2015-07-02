package kr.co.chunjae.dao;

import kr.co.chunjae.entities.question.LikeEntity;

public interface LikeDAO {
	/**
	 * 문제/답글 좋아요 여부 확인
	 * @param likeEntity
	 * @return
	 * @throws Exception
	 */
	int selectIsLike(LikeEntity likeEntity) throws Exception;
	/**
	 * 문제/답글 좋아요 정보 등록
	 * @param likeEntity
	 * @throws Exception
	 */
	void insertLike(LikeEntity likeEntity) throws Exception;
	/**
	 * 문제/답글 좋아요 정보 수정
	 * @param likeEntity
	 * @throws Exception
	 */
	void updateLike(LikeEntity likeEntity) throws Exception;
}