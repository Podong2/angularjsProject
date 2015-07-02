package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.user.ActivityLogEntity;
import kr.co.chunjae.entities.user.UserEntity;

public interface ActivityLogDAO {
	/**
	 * 활동로그 등록
	 * @param activityLogEntity
	 * @throws Exception
	 */
	void insertActivityLog(ActivityLogEntity activityLogEntity) throws Exception;

	/**
	 * 활동로그 정보
	 * @param activityLogEntity
	 * @return
	 * @throws Exception
	 */
	ActivityLogEntity selectActivityLog(ActivityLogEntity activityLogEntity) throws Exception;

	/**
	 * 활동로그 목록
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	List<ActivityLogEntity> selectActivityLogList(UserEntity userInfo) throws Exception;

	/**
	 * 오늘 등록된 활동점수 합계 가져오기(활동로그 타입별)
	 * @param activityLogEntity
	 * @return
	 * @throws Exception
	 */
	int selectTodayActivityScore(ActivityLogEntity activityLogEntity) throws Exception;

}