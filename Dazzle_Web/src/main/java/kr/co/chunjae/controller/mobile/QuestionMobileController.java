package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.ActivityLogDAO;
import kr.co.chunjae.dao.ScrapDAO;
import kr.co.chunjae.dao.UserDAO;
import kr.co.chunjae.entities.ListCountEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.question.LikeCheckEntity;
import kr.co.chunjae.entities.question.LikeEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.question.QuestionInfoEntity;
import kr.co.chunjae.entities.question.QuestionListEntity;
import kr.co.chunjae.entities.question.ScrapEntity;
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
@RequestMapping ("/mobile/question")
public class QuestionMobileController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ActivityLogDAO activityLogDAO;
	@Autowired
	private ScrapDAO scrapDAO;
	@Autowired
	private Messages messages;
	@Autowired
	private PushService pushService;

	private static final Logger logger = LoggerFactory.getLogger(QuestionMobileController.class);

	/**
	 * 문제 등록
	 * @param questionEntity
	 * @return
	 */
	@RequestMapping(value="/insertQuestion", method = RequestMethod.POST)
	public QuestionEntity insertQuestion(@RequestBody QuestionEntity questionEntity) {

		try {
			questionService.insertQuestion(questionEntity);
			questionEntity.setResultCode(ResultCode.SUCCESS);
			questionEntity.setResultMsg(messages.getMessage("question.insert.success"));
		} catch (Exception e) {
			logger.error("문제 등록 오류", e);
			questionEntity.setResultMsg(messages.getMessage("question.insert.fail"));
		}

		return questionEntity;
	}

	/**
	 * 문제 목록 카운트
	 * @param questionType
	 * @return
	 */
	@RequestMapping(value = "/getQuestionListCount", method = RequestMethod.GET)
	public ListCountEntity selectQuestionListCount(@RequestParam("questionType") String questionType) {

		ListCountEntity countEntity = new ListCountEntity();
		QuestionEntity questionEntity = new QuestionEntity();
		try {
			questionEntity.setQuestionType(questionType);
			countEntity.setListCount(questionService.selectQuestionListCount(questionEntity));
			countEntity.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제 리스트 카운트 조회 오류", e);
		}
		return countEntity;
	}

	/**
	 * 문제 목록
	 * @param questionType
	 * @param searchQuestionType
	 * @param userKey
	 * @param questionGrade
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value = "/getQuestionList", method = RequestMethod.GET)
	public QuestionListEntity selectQuestionList(@RequestParam("questionType") String questionType,
												@RequestParam("searchQuestionType") String searchQuestionType,
												@RequestParam("userKey") long userKey,
												@RequestParam("questionGrade") String questionGrade,
												@RequestParam("classCode") String classCode,
												@RequestParam("startRow") int startRow,
												@RequestParam("rowCount") int rowCount) {

		QuestionListEntity result = new QuestionListEntity();
		QuestionEntity questionEntity = new QuestionEntity();
		try {
			questionEntity.setQuestionType(questionType);				// 전체문제, 그룹문제 구분
			questionEntity.setSearchQuestionType(searchQuestionType);	// 전체/안풀린문제 구분
			questionEntity.setUserKey(userKey);
			questionEntity.setQuestionGrade(questionGrade);				// 학년코드
			questionEntity.setClassCode(classCode);						// 문제유형코드
			questionEntity.setCurrentPage(startRow);
			questionEntity.setPageSize(rowCount, rowCount);
			questionService.selectQuestionList(questionEntity);
			result.setQuestionList(questionEntity.getQuestionList());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제 리스트 조회 오류", e);
		}
		return result;
	}

	/**
	 * 내정보 - 문제 목록
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value = "/getMyQuestionList", method = RequestMethod.GET)
	public QuestionListEntity selectMyQuestionList(@RequestParam("userKey") long userKey,
												@RequestParam("startRow") int startRow,
												@RequestParam("rowCount") int rowCount) {

		QuestionListEntity result = new QuestionListEntity();
		QuestionEntity questionEntity = new QuestionEntity();

		try {
			questionEntity.setUserKey(userKey);
			questionEntity.setCurrentPage(startRow);
			questionEntity.setPageSize(rowCount, rowCount);
			questionService.selectMyQuestionList(questionEntity);
			result.setQuestionList(questionEntity.getQuestionList());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("내정보 문제 리스트 조회 오류", e);
		}
		return result;
	}

	/**
	 * 내정보 - 스크랩 문제 목록
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value = "/getMyScrapList", method = RequestMethod.GET)
	public QuestionListEntity selectMyScrapList(@RequestParam("userKey") long userKey,
												@RequestParam("startRow") int startRow,
												@RequestParam("rowCount") int rowCount) {

		QuestionListEntity result = new QuestionListEntity();
		QuestionEntity questionEntity = new QuestionEntity();
		try {
			questionEntity.setUserKey(userKey);
			questionEntity.setCurrentPage(startRow);
			questionEntity.setPageSize(rowCount, rowCount);
			questionService.selectMyScrapList(questionEntity);
			result.setQuestionList(questionEntity.getQuestionList());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("스크랩 문제 리스트 조회 오류", e);
		}
		return result;
	}

	/**
	 * 문제 정보
	 * @param questionKey
	 * @param userKey
	 * @return
	 */
	@RequestMapping(value = "/getQuestion", method = RequestMethod.GET)
	public QuestionInfoEntity selectQuestion(@RequestParam("questionKey") long questionKey,
											@RequestParam("userKey") long userKey) {

		QuestionInfoEntity result = new QuestionInfoEntity();
		QuestionEntity questionInfo = new QuestionEntity();
		try {
			QuestionEntity questionEntity = new QuestionEntity();
			questionEntity.setQuestionKey(questionKey);
			questionEntity.setUserKey(userKey);
			questionInfo = questionService.selectQuestion(questionEntity);
			result.setQuestionInfo(questionInfo);	// 문제 정보
			result.setAnswerUserList(answerService.selectAnswerUserListByQuestionKey(questionKey));	// 답글 등록자 정보
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제정보 조회 오류", e);
		}
		return result;
	}

	/**
	 * 문제 삭제
	 * @param questionEntity
	 * @return
	 */
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
	public ResponseEntity deleteQuestion(@RequestBody QuestionEntity questionEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			if (questionEntity.getQuestionKey() > 0) {
				questionService.deleteQuestion(questionEntity.getQuestionKey());
				result.setResultCode(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("문제 삭제 오류", e);
		}
		return result;
	}

	/**
	 * 문제/답글 좋아요 여부 체크
	 * @param userKey
	 * @param questionKey
	 * @param answerKey
	 * @return
	 */
	@RequestMapping(value="/selectLikeYn", method= RequestMethod.GET)
	public LikeCheckEntity selectLikeYn(@RequestParam("userKey") long userKey,
										@RequestParam(required=false, value="questionKey", defaultValue="0") long questionKey,
										@RequestParam(required=false, value="answerKey", defaultValue="0") long answerKey) {

		LikeCheckEntity result = new LikeCheckEntity();

		try {
			LikeEntity likeEntity = new LikeEntity();
			likeEntity.setUserKey(userKey);
			likeEntity.setQuestionKey(questionKey);
			likeEntity.setAnswerKey(answerKey);
			likeEntity.setLikeYn(CommonCode.LIKE_YN_Y);			// 좋아요 여부
			result.setLikeCount(questionService.selectIsLike(likeEntity));
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제/답글 좋아요 여부 조회 오류", e);
		}
		return result;
	}

	/**
	 * 좋아요/좋아요 취소(문제,답글)
	 * @param likeEntity
	 * @return
	 */
	@RequestMapping(value = "/updateLikeYn", method = RequestMethod.POST)
	public ResponseEntity updateLikeYn(@RequestBody LikeEntity likeEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			questionService.LikeYn(likeEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제/답글 좋아요 오류", e);
		}
		return result;
	}

	/**
	 * 문제 스크랩
	 * @param scrapEntity
	 * @return
	 */
	@RequestMapping(value = "/insertScrap", method = RequestMethod.POST)
	public ResponseEntity insertScrap(@RequestBody ScrapEntity scrapEntity) {
		int scrapCount = 0;
		ScrapEntity scrapInfo = new ScrapEntity();
		ResponseEntity result = new ResponseEntity();
		try {

			scrapInfo.setUserKey(scrapEntity.getUserKey());
			scrapInfo.setGroupKey(scrapEntity.getGroupKey());

			// 다Q 최초 그룹 스크랩일 경우 활동점수 부여
			scrapCount = questionService.selectIsScrap(scrapInfo);
			if (scrapCount == 0 && scrapEntity.getGroupKey() > 0
					&& StringUtils.equals(scrapEntity.getQuestionTypeCode(), CommonCode.QUESTION_TYPE_CODE_DAQ)) {
				activityLogService.insertActivityLogByFirstGroupScrap(scrapEntity);
			}

			// 스크랩 등록 여부 확인
			scrapInfo.setScrapBoardKey(scrapEntity.getScrapBoardKey());
			scrapCount = questionService.selectIsScrap(scrapInfo);
			if (scrapCount > 0) {
				questionService.updateQuestionScrap(scrapEntity);
			} else {
				questionService.insertQuestionScrap(scrapEntity);
			}


			if (StringUtils.equals(scrapEntity.getScrapYn(), CommonCode.SCRAP_YN_Y)) {
				// 그룹 스크랩일 경우 푸시 알림
				if (StringUtils.equals(scrapEntity.getScrapTypeCode(), CommonCode.SCRAP_TYPE_GROUP)) {
					pushService.sendGroupQuestionScrapped(scrapEntity);
				}

				result.setResultMsg(messages.getMessage("question.scrap.success"));
			} else {
				result.setResultMsg(messages.getMessage("question.scrap_cancel.success"));
			}
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("문제 스크랩 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
}