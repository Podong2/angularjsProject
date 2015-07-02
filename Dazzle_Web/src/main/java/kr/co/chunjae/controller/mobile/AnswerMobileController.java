package kr.co.chunjae.controller.mobile;


import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.UserDAO;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.AnswerListEntity;
import kr.co.chunjae.entities.question.AnswerReplyEntity;
import kr.co.chunjae.entities.question.AnswerReplyInfoEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.AnswerService;
import kr.co.chunjae.service.PushService;
import kr.co.chunjae.service.QuestionService;
import kr.co.digigroove.commons.messages.Messages;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/answer")
public class AnswerMobileController {
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private PushService pushService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(AnswerMobileController.class);

	/**
	 * 답글 등록
	 * @param answerEntity
	 * @return
	 */
	@RequestMapping(value="/insertAnswer", method = RequestMethod.POST)
	public ResponseEntity insertAnswer(@RequestBody AnswerEntity answerEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
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
	 * @param answerEntity
	 * @return
	 */
	@RequestMapping(value="/deleteAnswer", method = RequestMethod.POST)
	public ResponseEntity deleteAnswer(@RequestBody AnswerEntity answerEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			if (answerEntity.getAnswerKey() > 0) {
				answerService.deleteAnswer(answerEntity.getAnswerKey());
				result.setResultCode(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("답글 삭제 오류", e);
		}
		return result;
	}

	/**
	 * 답글 리스트
	 * @param questionKey
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value = "/getAnswerList", method = RequestMethod.GET)
	public AnswerListEntity selectAnswerList(@RequestParam("questionKey") long questionKey,
											@RequestParam("userKey") long userKey,
											@RequestParam("startRow") int startRow,
											@RequestParam("rowCount") int rowCount) {

		AnswerListEntity result = new AnswerListEntity();
		try {
			AnswerEntity answerEntity = new AnswerEntity();
			answerEntity.setQuestionKey(questionKey);
			answerEntity.setUserKey(userKey);
			answerEntity.setCurrentPage(startRow);
			answerEntity.setPageSize(rowCount, rowCount);
			answerEntity = answerService.selectAnswerList(answerEntity);
			result.setAnswerList(answerEntity.getAnswerList());				// 답글, 답글댓글 리스트
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제정보 조회 오류", e);
		}
		return result;
	}

	/**
	 * 내 답글 리스트
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value = "/getMyAnswerList", method = RequestMethod.GET)
	public AnswerListEntity selectMyAnswerList(@RequestParam("userKey") long userKey,
												@RequestParam("startRow") int startRow,
												@RequestParam("rowCount") int rowCount) {

		AnswerListEntity result = new AnswerListEntity();
		AnswerEntity answerEntity = new AnswerEntity();

		try {
			answerEntity.setUserKey(userKey);
			answerEntity.setCurrentPage(startRow);
			answerEntity.setPageSize(rowCount, rowCount);
			answerService.selectMyAnswerList(answerEntity);
			result.setAnswerList(answerEntity.getAnswerList());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("내정보 답글 리스트 조회 오류", e);
		}
		return result;
	}

	/**
	 * 답글 채택 / 채택 취소
	 * @param answerEntity
	 * @return
	 */
	@RequestMapping(value="/chooseAnswer", method = RequestMethod.POST)
	public ResponseEntity chooseAnswer(@RequestBody AnswerEntity answerEntity) {

		ResponseEntity result = new ResponseEntity();

		try {
			// 채택하기
			if (StringUtils.equals(answerEntity.getChooseYn(), CommonCode.ANSWER_CHOOSE_Y)) {
				answerService.chooseAnswer(answerEntity);
				// 푸시 알림
				pushService.sendMyAnswerSelectedMessage(answerEntity);

				// 채택된 답변 등록자에게 활동점수 부여
				UserEntity userInfo = userDAO.selectUser(answerEntity.getChooseKey());
				String userTypeCode = userInfo.getTypeCode();
				if (!StringUtils.equals(userTypeCode, CommonCode.USER_TYPE_CODE_ADMIN)) {
					activityLogService.insertActivityLogByChooseAnswer(CommonCode.ACTIVITY_LOG_TYPE_CHOOSE_ANSWER,
							userInfo.getName(), answerEntity.getChooseKey(), answerEntity.getQuestionKey());
				}
				result.setResultMsg(messages.getMessage("answer.choose.success"));
			}
			// 채택취소
			else {
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

	/**
	 * 답글 댓글 정보
	 * @param answerReplyKey
	 * @return
	 */
	@RequestMapping(value="/getAnswerReply", method=RequestMethod.GET)
	public AnswerReplyInfoEntity selectAnswerReply(@RequestParam("answerReplyKey") long answerReplyKey) {

		AnswerReplyInfoEntity result = new AnswerReplyInfoEntity();
		try {
			if (answerReplyKey > 0) {
				result.setAnswerReplyInfo(answerService.selectAnswerReply(answerReplyKey));
				result.setResultCode(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("답글 댓글 정보 조회 오류", e);
		}
		return result;
	}

	/**
	 * 답글 댓글 등록
	 * @param answerReplyEntity
	 * @return
	 */
	@RequestMapping(value="/insertAnswerReply", method = RequestMethod.POST)
	public ResponseEntity insertAnswerReply(@RequestBody AnswerReplyEntity answerReplyEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			answerService.insertAnswerReply(answerReplyEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("답글 댓글 등록 오류", e);
		}
		return result;
	}

	/**
	 * 답글 댓글 수정
	 * @param answerReplyEntity
	 * @return
	 */
	@RequestMapping(value="/updateAnswerReply", method = RequestMethod.POST)
	public ResponseEntity updateAnswerReply(@RequestBody AnswerReplyEntity answerReplyEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			answerService.updateAnswerReply(answerReplyEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("답글 댓글 수정 오류", e);
		}
		return result;
	}

	/**
	 * 답글 댓글 삭제
	 * @param answerReplyEntity
	 * @return
	 */
	@RequestMapping(value="/deleteAnswerReply", method = RequestMethod.POST)
	public ResponseEntity deleteAnswerReply(@RequestBody AnswerReplyEntity answerReplyEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			if (answerReplyEntity.getAnswerReplyKey() > 0) {
				answerService.deleteAnswerReply(answerReplyEntity.getAnswerReplyKey());
				result.setResultCode(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("답글 댓글 삭제 오류", e);
		}
		return result;
	}
}