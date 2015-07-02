package kr.co.chunjae.controller.rest;

import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.AnswerService;
import kr.co.chunjae.service.PushService;
import kr.co.chunjae.service.QuestionService;
import kr.co.digigroove.commons.messages.Messages;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/admin/question/rest")
public class QuestionRestController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private Messages messages;
	@Autowired
	private PushService pushService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionRestController.class);
	
	/**
	 * 문제 등록
	 * @param questionEntity
	 * @return
	 */
	@RequestMapping(value="/insertQuestion", method = RequestMethod.POST)
	public ResponseEntity insertQuestion(QuestionEntity questionEntity) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			questionService.insertQuestion(questionEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("question.insert.success"));
			//send push message
			Map<String, String> messageMap = new HashMap<>();
			messageMap.put("title", "[다즐]");
			messageMap.put("message", "새 다Q 문제가 등록되었습니다.");
			pushService.sendAllTargetDevices(messageMap);
			
		} catch (Exception e) {
			logger.error("문제 등록 오류", e);
			result.setResultMsg(messages.getMessage("question.insert.fail"));
		}
		return result;
	}
	
	/**
	 * 문제유형 수정
	 * @param questionEntity
	 * @return
	 */
	@RequestMapping(value="/updateQuestion", method = RequestMethod.POST)
	public ResponseEntity updateQuestion(QuestionEntity questionEntity) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			questionService.updateQuestionType(questionEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("questionType.update.success"));
		} catch (Exception e) {
			logger.error("문제유형 수정 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 다Q 문제 수정
	 * @param questionEntity
	 * @return
	 */
	@RequestMapping(value="/updateDaqQuestion", method = RequestMethod.POST)
	public ResponseEntity updateDaqQuestion(QuestionEntity questionEntity) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			questionService.updateDaqQuestion(questionEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("question.update.success"));
		} catch (Exception e) {
			logger.error("다Q 문제 수정 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 문제 삭제
	 * @param questionKey
	 * @return
	 */
	@RequestMapping(value="/deleteQuestion", method = RequestMethod.POST)
	public ResponseEntity deleteQuestion(@RequestParam("questionKey") long questionKey) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			questionService.deleteQuestion(questionKey);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("question.delete.success"));
		} catch (Exception e) {
			logger.error("문제 삭제 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 답글 등록
	 * @param answerEntity
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertAnswer", method = RequestMethod.POST)
	public ResponseEntity insertAnswer(AnswerEntity answerEntity, HttpSession session) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
			answerEntity.setUserKey(loginSession.getUserKey());
			answerService.insertAnswer(answerEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("answer.insert.success"));
		} catch (Exception e) {
			logger.error("답글 등록 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 답글 삭제
	 * @param answerKey
	 * @return
	 */
	@RequestMapping(value="/deleteAnswer", method = RequestMethod.POST)
	public ResponseEntity deleteAnswer(@RequestParam("answerKey") long answerKey) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			answerService.deleteAnswer(answerKey);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("answer.delete.success"));
		} catch (Exception e) {
			logger.error("답글 삭제 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 답글 채택(전문가 답글) / 채택 취소
	 * @param answerKey
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/chooseAnswer", method = RequestMethod.POST)
	public ResponseEntity chooseAnswer(@RequestParam("answerKey") long answerKey,
										@RequestParam("chooseYn") String chooseYn,
										HttpSession session) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
			AnswerEntity answerEntity = new AnswerEntity();
			answerEntity.setChooseKey(loginSession.getUserKey());
			answerEntity.setAnswerKey(answerKey);
			
			if (StringUtils.equals(chooseYn, CommonCode.ANSWER_CHOOSE_Y)) {
				answerService.chooseAnswer(answerEntity);
				result.setResultMsg(messages.getMessage("answer.choose.success"));
			} else {
				answerService.ChooseCancelAnswer(answerEntity);
				result.setResultMsg(messages.getMessage("answer.choose_cancel.success"));
			}
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("답글 채택/취소 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
}