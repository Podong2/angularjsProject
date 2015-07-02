package kr.co.chunjae.service.impl;

import kr.co.chunjae.components.ApnPushSender;
import kr.co.chunjae.components.GcmPushSender;
import kr.co.chunjae.dao.PushSettingDao;
import kr.co.chunjae.entities.PushSettingEntity;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.question.ScrapEntity;
import kr.co.chunjae.service.GroupService;
import kr.co.chunjae.service.PushService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jm1218 on 15. 1. 22.
 */
@Service
public class PushServiceImpl implements PushService {

	@Autowired
	private PushSettingDao pushSettingDao;
	@Autowired
	private GcmPushSender gcmPushSender;
	@Autowired
	private ApnPushSender apnPushSender;
	@Autowired
	private GroupService groupService;

	private static final String PLATFORM_ANDROID = "Android";
	private static final String PLATFORM_IOS = "iOS";

	@Override
	public PushSettingEntity initPushSetting(PushSettingEntity pushSettingEntity) throws Exception{
		PushSettingEntity saved = pushSettingDao.getPushSetting(pushSettingEntity.getDeviceToken());
		if (saved == null) {
			//저장된 디바이스토큰이 없는 경우.
			pushSettingDao.insertPushSetting(pushSettingEntity);
			return pushSettingDao.getPushSetting(pushSettingEntity.getDeviceToken());
		} else {
			//저장된 디바이스 토큰이 있는 경우.
			if (saved.getUserKey() == pushSettingEntity.getUserKey()) {
				//동일한 유저가 같은 디바이스로 로그인한 경우.
				return saved;
			} else {
				//동일한 디바이스로 다른 유저가 로그인한 경우.
				pushSettingDao.deletePushSetting(saved.getPushSettingKey());
				pushSettingDao.insertPushSetting(pushSettingEntity);
				return pushSettingDao.getPushSetting(pushSettingEntity.getDeviceToken());
			}
		}
	}

	@Override
	public int updatePushSetting(PushSettingEntity pushSettingEntity) throws Exception{
		return pushSettingDao.updatePushSetting(pushSettingEntity);
	}

	@Override
	public void sendAllTargetDevices(Map<String, String> messageMap) throws Exception {
		gcmPushSender.sendMessage(messageMap, pushSettingDao.getAllTargetDevices(PLATFORM_ANDROID));
		apnPushSender.sendMessage(messageMap, pushSettingDao.getAllTargetDevices(PLATFORM_IOS));
	}
	
	// 내 문제 답글  :  ooo님이 답글을 등록했습니다. ==> 답글 등록자(user name), 문제 등록자(user key ==> device token)
	@Override
	public void sendMyQuestionAnsweredMessage(AnswerEntity answerEntity) throws Exception {
		
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("title", "[다즐]");
		messageMap.put("message", answerEntity.getName() + " 님이 답글을 등록했습니다.");
		
		List<PushSettingEntity> pushSettingList = pushSettingDao.getQuestionWriterDevices(answerEntity.getQuestionKey());
		sendMessageToEachPlatform(pushSettingList, messageMap);
	}

	//	내 답글 채택 : ooo님이 답글을 채택했습니다. ==> 답글 채택자(user name), 답글 등록자(user key ==> device token)
	@Override
	public void sendMyAnswerSelectedMessage(AnswerEntity answerEntity) throws Exception {
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("title", "[다즐]");
		messageMap.put("message", answerEntity.getName() + " 님이 답글을 채택했습니다.");

		List<PushSettingEntity> pushSettingList = pushSettingDao.getAnswerWriterDevices(answerEntity.getAnswerKey());
		sendMessageToEachPlatform(pushSettingList, messageMap);
	}

	@Override
	public void sendGroupQuestionRegistered(QuestionEntity questionEntity) throws Exception{
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("title", "[다즐]");
		messageMap.put("message", groupService.selectGroup(questionEntity.getGroupKey()).getGroupName() + " 그룹에 문제가 등록되었습니다.");
		sendMessageToEachPlatform(pushSettingDao.getGroupDevices(questionEntity.getGroupKey()), messageMap);
	}

	@Override
	public void sendGroupQuestionScrapped(ScrapEntity scrapEntity) throws Exception{
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("title", "[다즐]");
		messageMap.put("message", groupService.selectGroup(scrapEntity.getGroupKey()).getGroupName() + " 그룹에 문제가 스크랩되었습니다.");
		sendMessageToEachPlatform(pushSettingDao.getGroupDevices(scrapEntity.getGroupKey()), messageMap);
	}

	private void sendMessageToEachPlatform(List<PushSettingEntity> pushSettingList, Map<String, String> messageMap) throws Exception{
		List<String> androidDevices = new ArrayList<>();
		List<String> iosDevices = new ArrayList<>();

		for (PushSettingEntity pushSettingEntity : pushSettingList) {
			if (StringUtils.equals(pushSettingEntity.getPlatform(), PLATFORM_ANDROID)) {
				androidDevices.add(pushSettingEntity.getDeviceToken());
			} else if (StringUtils.equals(pushSettingEntity.getPlatform(), PLATFORM_IOS)) {
				iosDevices.add(pushSettingEntity.getDeviceToken());
			}
		}
		gcmPushSender.sendMessage(messageMap, androidDevices);
		apnPushSender.sendMessage(messageMap, iosDevices);
	}
}
