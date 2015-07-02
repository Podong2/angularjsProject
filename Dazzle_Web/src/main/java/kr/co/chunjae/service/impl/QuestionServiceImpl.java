package kr.co.chunjae.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.dao.ActivityLogDAO;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.LikeDAO;
import kr.co.chunjae.dao.QuestionDAO;
import kr.co.chunjae.dao.ScrapDAO;
import kr.co.chunjae.dao.UserDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.question.LikeEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.question.ScrapEntity;
import kr.co.chunjae.entities.user.ActivityLogEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.PushService;
import kr.co.chunjae.service.QuestionService;
import kr.co.digigroove.commons.utils.DateUtils;
import kr.co.digigroove.commons.utils.FileUtils;
import kr.co.digigroove.commons.utils.HashUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {
	@Value("#{file_prop['filePath']}")
	private String filePath;
	@Value("#{file_prop['fileTempPath']}")
	private String fileTempPath;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private LikeDAO likeDAO;
	@Autowired
	private ScrapDAO scrapDAO;
	@Autowired
	private ActivityLogDAO activityLogDAO;
	@Autowired
	private AttachDAO attachDAO;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private PushService pushService;
    @Autowired
    private GradeScoreDAO gradeScoreDAO;

	/**
	 * 문제 리스트 카운트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public long selectQuestionListCount(QuestionEntity questionEntity)
			throws Exception {

		questionEntity.setPageParams();
		return questionDAO.selectQuestionListCount(questionEntity);
	}

	/**
	 * 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public QuestionEntity selectQuestionList(QuestionEntity questionEntity) throws Exception {

		if (StringUtils.isNotEmpty(questionEntity.getClassCode())) {
			String classCodeStr = questionEntity.getClassCode().substring(4, 10);
			if (classCodeStr.contains("000000")) {
				questionEntity.setClassCodeType(CommonCode.QUESTION_TYPE_LARGE);
			} else if (classCodeStr.contains("0000")) {
				questionEntity.setClassCodeType(CommonCode.QUESTION_TYPE_MEDIUM);
			} else if (classCodeStr.contains("00")) {
				questionEntity.setClassCodeType(CommonCode.QUESTION_TYPE_SMALL);
			} else {
				String classCodeEndStr = questionEntity.getClassCode().substring(9, 10);
				if (!StringUtils.equals(classCodeEndStr, "0")) {
					questionEntity.setClassCodeType(CommonCode.QUESTION_TYPE_CATEGORY);
				}
			}
		}
		questionEntity.setPageParams();
		questionEntity.setDataSize(questionDAO.selectQuestionListCount(questionEntity));
		List<QuestionEntity> questionList = questionDAO.selectQuestionList(questionEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();

		for (int i=0; i<questionList.size(); i++) {
			questionList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(questionList.get(i).getInsertDate()));
		}
		// 내문제 프로필 등급
		for (QuestionEntity questionData : questionList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= questionData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= questionData.getActivityScore()) {
					questionData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		questionEntity.setQuestionList(questionList);
		return questionEntity;
	}

	/**
	 * 내 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public QuestionEntity selectMyQuestionList(QuestionEntity questionEntity) throws Exception {

		questionEntity.setDataSize(questionDAO.selectQuestionListByUserKeyCount(questionEntity.getUserKey()));
		List<QuestionEntity> questionList = questionDAO.selectQuestionListByUserKey(questionEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		for (int i=0; i<questionList.size(); i++) {
			questionList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(questionList.get(i).getInsertDate()));
		}
		// 내문제 프로필 등급
		for (QuestionEntity questionData : questionList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= questionData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= questionData.getActivityScore()) {
					questionData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		questionEntity.setQuestionList(questionList);
		return questionEntity;
	}

	/**
	 * 스크랩 문제 리스트
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public QuestionEntity selectMyScrapList(QuestionEntity questionEntity) throws Exception {

		questionEntity.setDataSize(questionDAO.selectMyScrapListCount(questionEntity.getUserKey()));
		List<QuestionEntity> questionList = questionDAO.selectMyScrapList(questionEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		for (int i=0; i<questionList.size(); i++) {
			questionList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(questionList.get(i).getInsertDate()));
		}
		// 스크랩 프로필 등급
		for (QuestionEntity questionData : questionList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= questionData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= questionData.getActivityScore()) {
					questionData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		questionEntity.setQuestionList(questionList);
		return questionEntity;
	}

	/**
	 * 문제 정보 가져오기
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public QuestionEntity selectQuestion(QuestionEntity questionEntity) throws Exception {
		questionEntity = questionDAO.selectQuestion(questionEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		for (int i=0; i < gradeScoreList.size(); i++){
			if(gradeScoreList.get(i).getUserLowScore() <= questionEntity.getActivityScore()
					&& gradeScoreList.get(i).getUserHighScore() >= questionEntity.getActivityScore()) {
				questionEntity.setUserRating(String.format("%02d", i+1));
			}
		}
		return questionEntity;
	}

	/**
	 * 문제/답글 좋아요 여부 확인
	 * @param likeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectIsLike(LikeEntity likeEntity) throws Exception {

		return likeDAO.selectIsLike(likeEntity);
	}

	/**
	 * 문제 등록
	 * @param questionEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertQuestion(QuestionEntity questionEntity) throws Exception {

		// 1.insert DazzleQuestion
		questionDAO.insertQuestion(questionEntity);

		AttachEntity attachEntity = new AttachEntity();
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_QUESTION);
		attachEntity.setCommonKey(questionEntity.getQuestionKey());

		// 임시폴더에 업로드된 파일 복사
		if (!StringUtils.isEmpty(questionEntity.getFileName()) &&
				!StringUtils.isEmpty(questionEntity.getFileOrigName())) {

			File in = new File(fileTempPath + "/" + questionEntity.getFileName());
			File out = new File(filePath + "/" + questionEntity.getFileName());
			FileCopyUtils.copy(in, out);

			// insert DazzleAttach
			attachEntity.setFileName(questionEntity.getFileName());
			attachEntity.setFileOrigName(questionEntity.getFileOrigName());
			attachDAO.insertAttach(attachEntity);
		}

		// Converting a Base64 String into Image byte array
		if (!StringUtils.isEmpty(questionEntity.getImageDataString())) {
			byte[] imageByteArray = HashUtils.decodeBase64(questionEntity.getImageDataString());
			long today = System.currentTimeMillis();
			String fileName = CommonCode.ATTACH_FILE_PREFIX + today + "." + CommonCode.ATTACH_FILE_EXTENSION_JPG;

			// Write a image byte array into file system
			FileOutputStream imageOutFile = new FileOutputStream(filePath + "/" + fileName);
			imageOutFile.write(imageByteArray);
			imageOutFile.close();

			// insert DazzleAttach
			attachEntity.setFileName(fileName);
			attachEntity.setFileOrigName(fileName);
			attachDAO.insertAttach(attachEntity);
		}

		// 2.그룹문제 등록일 경우 푸시 알림
		if (StringUtils.equals(questionEntity.getQuestionTypeCode(), CommonCode.QUESTION_TYPE_CODE_GROUP)) {
			pushService.sendGroupQuestionRegistered(questionEntity);
		}

		// 3.사용자가 등록한 문제일 경우 활동점수 부여
		UserEntity userInfo = userDAO.selectUser(questionEntity.getUserKey());
		String userTypeCode = userInfo.getTypeCode();
		if (!StringUtils.equals(userTypeCode, CommonCode.USER_TYPE_CODE_ADMIN)) {
			ActivityLogEntity activityLogInfo = new ActivityLogEntity();
			activityLogInfo.setActivityTypeCode(CommonCode.ACTIVITY_LOG_TYPE_REGIST_QUESTION);
			activityLogInfo.setUserKey(questionEntity.getUserKey());
			int todayActivityScore = activityLogDAO.selectTodayActivityScore(activityLogInfo);
			// 일 최대 3점 누적
			if (todayActivityScore < CommonCode.MAX_ACTIVITY_SCORE_REGIST_QUESTION) {
				activityLogInfo.setGroupKey(questionEntity.getGroupKey());
				activityLogService.insertActivityLogByInsertQnA(CommonCode.ACTIVITY_LOG_TYPE_REGIST_QUESTION,
						questionEntity.getUserKey(), null, questionEntity.getQuestionKey(), questionEntity.getGroupKey());
			}
		}
	}


	/**
	 * 문제/답글 좋아요
	 * @param likeEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void LikeYn(LikeEntity likeEntity) throws Exception {
		LikeEntity likeInfo = new LikeEntity();
		likeInfo.setUserKey(likeEntity.getUserKey());
		likeInfo.setQuestionKey(likeEntity.getQuestionKey());
		likeInfo.setAnswerKey(likeEntity.getAnswerKey());
		int likeCount = selectIsLike(likeInfo);	// 좋아요 등록 여부 확인

		// 좋아요/좋아요 취소 여부 등록
		if (likeCount > 0) {
			likeDAO.updateLike(likeEntity);
		} else {
			likeDAO.insertLike(likeEntity);
		}

		// 사용자가 좋아요 붙인 경우 활동점수 부여
		UserEntity userInfo = userDAO.selectUser(likeEntity.getUserKey());
		String userTypeCode = userInfo.getTypeCode();
		if (!StringUtils.equals(userTypeCode, CommonCode.USER_TYPE_CODE_ADMIN)
				&& StringUtils.equals(likeEntity.getLikeYn(), CommonCode.LIKE_YN_Y)) {

			String activityTypeCode = likeEntity.getAnswerKey() > 0 ?  CommonCode.ACTIVITY_LOG_TYPE_LIKE_ANSWER : CommonCode.ACTIVITY_LOG_TYPE_LIKE_QUESTION;
			ActivityLogEntity activityLogInfo = new ActivityLogEntity();
			activityLogInfo.setActivityTypeCode(activityTypeCode);

			activityLogInfo.setUserKey(likeEntity.getUserKey());

			if(likeEntity.getReceiveUserKey() != likeEntity.getUserKey()){ //다른사용자의 글에만 좋아요 활동점수 부여 가능
				//좋아요 부여자 활동점수 추가 (일 최대 누적 3점)
				int SendTodayActivityScore = activityLogDAO.selectTodayActivityScore(activityLogInfo);
				// 일 최대 3점 누적
				if (SendTodayActivityScore < CommonCode.MAX_ACTIVITY_SCORE_LIKE) {
					activityLogService.insertActivityLogByLike(activityTypeCode, likeEntity.getUserKey()
						, likeEntity.getGroupKey(), likeEntity.getQuestionKey(), likeEntity.getName());
				}

				//좋아요 받은자 활동점수 추가 (일 최대 누적 3점)
				activityLogInfo.setUserKey(likeEntity.getReceiveUserKey());
				int GiveTodayActivityScore = activityLogDAO.selectTodayActivityScore(activityLogInfo);
				// 일 최대 3점 누적
				if (GiveTodayActivityScore < CommonCode.MAX_ACTIVITY_SCORE_LIKE) {
					activityLogService.insertActivityLogByLike(activityTypeCode, likeEntity.getReceiveUserKey()
							, likeEntity.getGroupKey(), likeEntity.getQuestionKey(), likeEntity.getName());
				}
			}

		}
	}

	/**
	 * 문제 유형 수정
	 * @param questionEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateQuestionType(QuestionEntity questionEntity) throws Exception {

		questionDAO.updateQuestionType(questionEntity);
	}

	/**
	 * 다Q 문제 정보 수정
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateDaqQuestion(QuestionEntity questionEntity) throws Exception {

		QuestionEntity questionInfo = selectQuestion(questionEntity);
		AttachEntity attachInfo = new AttachEntity();
		attachInfo.setCommonKey(questionEntity.getQuestionKey());
		attachInfo.setAttachType(CommonCode.ATTACH_TYPE_QUESTION);
		attachInfo = attachDAO.selectAttachInfo(attachInfo);

		// 1.문제 정보 수정
		questionDAO.updateDaqQuestion(questionEntity);

		// 2. 첨부 이미지 수정
		if (StringUtils.isEmpty(questionEntity.getFileName())) {
			// 첨부파일 삭제
			FileUtils.deleteFile(filePath + "/" + attachInfo.getFileName()); // 파일 삭제
			attachDAO.deleteFile(attachInfo.getAttachKey());

		}
		// 기존 첨부파일 삭제 후, 첨부파일 추가
		else if (!StringUtils.equals(questionEntity.getFileName(), questionInfo.getQuestionImageName())) {

			// delete DazzleAttach
			FileUtils.deleteFile(filePath + "/" + attachInfo.getFileName()); // 파일 삭제
			attachDAO.deleteFile(attachInfo.getAttachKey());

			// insert DazzleAttach
			File in = new File(fileTempPath + "/" + questionEntity.getFileName());
			File out = new File(filePath + "/" + questionEntity.getFileName());
			FileCopyUtils.copy(in, out);

			AttachEntity attachEntity = new AttachEntity();
			attachEntity.setAttachType(CommonCode.ATTACH_TYPE_QUESTION);
			attachEntity.setCommonKey(questionEntity.getQuestionKey());
			attachEntity.setFileName(questionEntity.getFileName());
			attachEntity.setFileOrigName(questionEntity.getFileOrigName());
			attachDAO.insertAttach(attachEntity);
		}
	}

	/**
	 * 문제 삭제
	 * @param questionKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteQuestion(long questionKey) throws Exception {

		questionDAO.deleteQuestion(questionKey);

		AttachEntity attachEntity = new AttachEntity();
		attachEntity.setCommonKey(questionKey);
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_QUESTION);
		AttachEntity attachInfo = attachDAO.selectAttachInfo(attachEntity);
		if (attachInfo != null) {
			attachDAO.deleteFile(attachInfo.getAttachKey());
		}
	}

	/**
	 * 스크랩 여부 확인
	 * @param scrapEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectIsScrap(ScrapEntity scrapEntity) throws Exception {

		return scrapDAO.selectIsScrap(scrapEntity);
	}

	/**
	 * 문제 스크랩 정보 등록
	 * @param scrapEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void insertQuestionScrap(ScrapEntity scrapEntity) throws Exception {

		scrapDAO.insertScrap(scrapEntity);
	}

	/**
	 * 문제 스크랩 정보 수정
	 * @param scrapEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateQuestionScrap(ScrapEntity scrapEntity) throws Exception {

		scrapDAO.updateScrap(scrapEntity);
	}
}