package kr.co.chunjae.service;

import kr.co.chunjae.entities.PushSettingEntity;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.question.ScrapEntity;

import java.util.Map;

/**
 * Created by jm1218 on 15. 1. 22.
 */
public interface PushService {
	PushSettingEntity initPushSetting(PushSettingEntity pushSettingEntity) throws Exception;
	int updatePushSetting(PushSettingEntity pushSettingEntity) throws Exception;

	//	다Q 문제 등록 : 새 다Q 문제가 등록되었습니다.
	void sendAllTargetDevices(Map<String, String> messageMap) throws Exception;
	//	내 문제 답글  :  ooo님이 답글을 등록했습니다.
	void sendMyQuestionAnsweredMessage(AnswerEntity answerEntity) throws Exception;
	//	내 답글 채택 : ooo님이 답글을 채택했습니다.
	void sendMyAnswerSelectedMessage(AnswerEntity answerEntity) throws Exception;
	//	그룹 멤버가 그룹문제 등록 : ooo그룹에 문제가 등록되었습니다.
	void sendGroupQuestionRegistered(QuestionEntity questionEntity) throws Exception;
	//	그룹 맴버가 그룹 스크랩 : ooo그룹에 문제가 스크랩되었습니다.
	void sendGroupQuestionScrapped(ScrapEntity scrapEntity) throws Exception;
}
