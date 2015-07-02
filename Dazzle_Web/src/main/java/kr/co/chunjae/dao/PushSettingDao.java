package kr.co.chunjae.dao;

import kr.co.chunjae.entities.PushSettingEntity;

import java.util.List;

/**
 * Created by jm1218 on 15. 1. 22.
 */
public interface PushSettingDao {
	int insertPushSetting(PushSettingEntity pushSettingEntity) throws Exception;
	int updatePushSetting(PushSettingEntity pushSettingEntity) throws Exception;
	PushSettingEntity getPushSetting(String deviceToken) throws Exception;
	int deletePushSetting(long pushSettingKey) throws Exception;
	
	//모든 푸시 대상 디바이스 가져오기.
	List<String> getAllTargetDevices(String platform) throws Exception;
	//문제 작성자의 디바이스 가져오기.
	List<PushSettingEntity> getQuestionWriterDevices(long questionKey);
	//답변 작성자의 디바이스 가져오기.
	List<PushSettingEntity> getAnswerWriterDevices(long answerKey);

	List<PushSettingEntity> getGroupDevices(long groupKey);
}
