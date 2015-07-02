package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.user.GradeScoreEntity;

public interface GradeScoreDAO {
	/**
	 * 회원등급 목록
	 * @return
	 * @throws Exception
	 */
	List<GradeScoreEntity> selectGradeScoreList() throws Exception;
	
	/**
	 * 회원등급 수정
	 * @param gradeScoreEntity
	 * @throws Exception
	 */
	void updateGradeScoreList(GradeScoreEntity gradeScoreEntity) throws Exception;
}