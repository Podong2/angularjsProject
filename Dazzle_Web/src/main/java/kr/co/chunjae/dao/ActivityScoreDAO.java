package kr.co.chunjae.dao;

import kr.co.chunjae.entities.user.ActivityScoreEntity;

public interface ActivityScoreDAO {
	/**
	 * 활동점수 가져오기
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	ActivityScoreEntity selectActivityScore(String code)throws Exception;
}