package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.user.ActivityScoreEntity;

public interface SettingDAO {
	/**
	 * 활동점수 목록
	 * @return
	 * @throws Exception
	 */
	List<ActivityScoreEntity> selectActivityScoreList() throws Exception;
	
	/**
	 * 활동점수 수정
	 * @param activityScoreEntity
	 * @throws Exception
	 */
	void updateActivityScoreList(ActivityScoreEntity activityScoreEntity) throws Exception;
}