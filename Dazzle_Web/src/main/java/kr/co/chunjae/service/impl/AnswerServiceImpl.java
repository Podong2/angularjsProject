package kr.co.chunjae.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.dao.AnswerDAO;
import kr.co.chunjae.dao.AnswerReplyDAO;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.QuestionDAO;
import kr.co.chunjae.dao.UserDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.AnswerReplyEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.AnswerService;
import kr.co.chunjae.service.PushService;
import kr.co.digigroove.commons.utils.DateUtils;
import kr.co.digigroove.commons.utils.HashUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

@Service
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {
	@Value("#{file_prop['filePath']}")
	private String filePath;
	@Value("#{file_prop['fileTempPath']}")
	private String fileTempPath;
	@Autowired
	private AnswerDAO answerDAO;
	@Autowired
	private AnswerReplyDAO answerReplyDAO;
	@Autowired
	private AttachDAO attachDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private PushService pushService;
	@Autowired
	private GradeScoreDAO gradeScoreDAO;
	@Autowired
	private QuestionDAO questionDAO;

	/**
	 * 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public AnswerEntity selectAnswerList(AnswerEntity answerEntity) throws Exception {
		// 답글 리스트
		answerEntity.setDataSize(answerDAO.selectAnswerListCount(answerEntity));
		List<AnswerEntity> answerList = answerDAO.selectAnswerList(answerEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();

		for (int i=0; i<answerList.size(); i++) {
			answerList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(answerList.get(i).getInsertDate()));
			// 답글 댓글 리스트
			List<AnswerReplyEntity> answerReplyList = answerReplyDAO.selectAnswerReplyList(answerList.get(i).getAnswerKey());
			for (int j=0; j<answerReplyList.size(); j++) {
				answerReplyList.get(j).setInsertDateTime(DateUtils.getTimeStringFromNow(answerReplyList.get(j).getInsertDate()));
			}

			for (AnswerReplyEntity answerReplyData : answerReplyList){
				for (int k=0; k < gradeScoreList.size(); k++){
					if(gradeScoreList.get(k).getUserLowScore() <= answerReplyData.getActivityScore()
							&& gradeScoreList.get(k).getUserHighScore() >= answerReplyData.getActivityScore()) {
						answerReplyData.setUserRating(String.format("%02d", k+1));
					}
				}
			}
			answerList.get(i).setAnswerReplyList(answerReplyList);
		}
		for (AnswerEntity answerData : answerList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= answerData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= answerData.getActivityScore()) {
					answerData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		answerEntity.setAnswerList(answerList);
		return answerEntity;
	}

	/**
	 * 내답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public AnswerEntity selectMyAnswerList(AnswerEntity answerEntity) throws Exception {

		answerEntity.setDataSize(answerDAO.selectAnswerListByUserKeyCount(answerEntity.getUserKey()));
		List<AnswerEntity> answerList = answerDAO.selectAnswerListByUserKey(answerEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();

		for (AnswerEntity anAnswerList : answerList) {
			anAnswerList.setInsertDateTime(DateUtils.getTimeStringFromNow(anAnswerList.getInsertDate()));
		}
		// 내답글 프로필 등급
		for (AnswerEntity answerData : answerList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= answerData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= answerData.getActivityScore()) {
					answerData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		answerEntity.setAnswerList(answerList);
		return answerEntity;
	}

	/**
	 * 답글 등록자 리스트
	 * @param answerKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AnswerEntity> selectAnswerUserListByQuestionKey(long questionKey) throws Exception {

		return answerDAO.selectAnswerUserListByQuestionKey(questionKey);
	}

	/**
	 * 신고 대상 답글 리스트
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AnswerEntity> selectReportAnswerList(AnswerEntity answerEntity)
			throws Exception {

		return answerDAO.selectReportAnswerList(answerEntity);
	}

	/**
	 * 답글 등록
	 * @param answerEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertAnswer(AnswerEntity answerEntity) throws Exception {

		// 1.insert DazzleAnswer
		answerDAO.insertAnswer(answerEntity);

		AttachEntity attachEntity = new AttachEntity();
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_ANSWER);
		attachEntity.setCommonKey(answerEntity.getAnswerKey());

		if (!StringUtils.isEmpty(answerEntity.getFileName()) &&
				!StringUtils.isEmpty(answerEntity.getFileOrigName())) {

			// 임시 폴더에 업로드된 파일 복사
			File in = new File(fileTempPath + "/" + answerEntity.getFileName());
			File out = new File(filePath + "/" + answerEntity.getFileName());
			FileCopyUtils.copy(in, out);

			// insert DazzleAttach
			attachEntity.setFileName(answerEntity.getFileName());
			attachEntity.setFileOrigName(answerEntity.getFileOrigName());
			attachDAO.insertAttach(attachEntity);
		}

		// Converting a Base64 String into Image byte array
		if (!StringUtils.isEmpty(answerEntity.getImageDataString())) {
			byte[] imageByteArray = HashUtils.decodeBase64(answerEntity.getImageDataString());
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

		// 2.푸시 알림
		pushService.sendMyQuestionAnsweredMessage(answerEntity);

		// 3.사용자가 등록한 답변일 경우 활동점수 부여
		UserEntity userInfo = userDAO.selectUser(answerEntity.getUserKey());
		String userTypeCode = userInfo.getTypeCode();
		if (!StringUtils.equals(userTypeCode, CommonCode.USER_TYPE_CODE_ADMIN)) {
			// 문제 등록후 1주일 이내 등록시
			int WithinWeek = questionDAO.selectWithinWeekQuestion(answerEntity.getQuestionKey());
			if(WithinWeek < 8){
				activityLogService.insertActivityLogByInsertQnA(CommonCode.ACTIVITY_LOG_TYPE_REGIST_ANSWER,
						answerEntity.getUserKey(), answerEntity.getName(), answerEntity.getQuestionKey(), answerEntity.getGroupKey());
			}
		}
	}

	/**
	 * 답글 삭제
	 * @param answerKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteAnswer(long answerKey) throws Exception {

		answerDAO.deleteAnswer(answerKey);

		AttachEntity attachEntity = new AttachEntity();
		attachEntity.setCommonKey(answerKey);
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_ANSWER);
		AttachEntity attachInfo = attachDAO.selectAttachInfo(attachEntity);
		if (attachInfo != null) {
			attachDAO.deleteFile(attachInfo.getAttachKey());
		}
	}

	/**
	 * 답글 채택
	 * @param answerEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void chooseAnswer(AnswerEntity answerEntity) throws Exception {

		// 채택유저 타입 체크
		UserEntity userInfo = userDAO.selectUser(answerEntity.getChooseKey());
		String userTypeCode = userInfo.getTypeCode();

		if (StringUtils.isNotEmpty(userTypeCode)) {
			// 전문가 답글
			if (StringUtils.equals(userTypeCode, CommonCode.USER_TYPE_CODE_ADMIN)) {
				answerEntity.setChooseAdminKey(answerEntity.getChooseKey());
				answerEntity.setChooseType(CommonCode.ANSWER_CHOOSE_TYPE_ADMIN);
			}
			// 채택 답글
			else {
				answerEntity.setChooseQuestionerKey(answerEntity.getChooseKey());
				answerEntity.setChooseType(CommonCode.ANSWER_CHOOSE_TYPE_QUESTIONER);
			}
		}
		// 답글 채택
		answerDAO.updateChooseAnswer(answerEntity);
	}

	/**
	 * 답글 채택 취소
	 * @param answerEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void ChooseCancelAnswer(AnswerEntity answerEntity) throws Exception {

		// 채택유저 타입 체크
		UserEntity userInfo = userDAO.selectUser(answerEntity.getChooseKey());
		String userTypeCode = userInfo.getTypeCode();

		if (StringUtils.isNotEmpty(userTypeCode)) {
			// 전문가 답글 채택 취소
			if (StringUtils.equals(userTypeCode, CommonCode.USER_TYPE_CODE_ADMIN)) {
				answerEntity.setChooseType(CommonCode.ANSWER_CHOOSE_TYPE_ADMIN);
			}
			// 채택 답글 채택 취소
			else {
				answerEntity.setChooseType(CommonCode.ANSWER_CHOOSE_TYPE_QUESTIONER);
			}
		}
		// 답글 채택 취소
		answerDAO.updateChooseCancelAnswer(answerEntity);
	}

	/**
	 * 답글 댓글 등록
	 * @param answerReplyEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void insertAnswerReply(AnswerReplyEntity answerReplyEntity) throws Exception {

		answerReplyDAO.insertAnswerReply(answerReplyEntity);
	}

	/**
	 * 답글 댓글 정보
	 * @param answerReplyKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public AnswerReplyEntity selectAnswerReply(long answerReplyKey) throws Exception {

		return answerReplyDAO.selectAnswerReply(answerReplyKey);
	}

	/**
	 * 답글 댓글 수정
	 * @param answerReplyEntity
	 * @throws Exception
	 */
	@Override
	public void updateAnswerReply(AnswerReplyEntity answerReplyEntity)
			throws Exception {
		
		answerReplyDAO.updateAnswerReply(answerReplyEntity);
	}

	/**
	 * 답글 댓글 삭제
	 * @param answerReplyKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteAnswerReply(long answerReplyKey) throws Exception {

		answerReplyDAO.deleteAnswerReply(answerReplyKey);
	}
}