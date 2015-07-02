package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.ActivityLogDAO;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.LineTalkDAO;
import kr.co.chunjae.dao.SmsSendDAO;
import kr.co.chunjae.dao.UserDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.SmsEntity;
import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.board.LineTalkLogEntity;
import kr.co.chunjae.entities.user.ActivityLogEntity;
import kr.co.chunjae.entities.user.ConfirmPhoneEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.entities.user.UserLoginEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.UserService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.DateUtils;
import kr.co.digigroove.commons.utils.HashUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	@Value("#{file_prop['filePath']}")
	private String filePath;
	@Value("#{sms_prop['callBackNumber']}")
	private String callBackNumber;
	@Autowired
	private UserDAO userDAO;
    @Autowired
    private LineTalkDAO lineTalkDAO;
    @Autowired
    private ActivityLogDAO activityLogDAO;
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private GradeScoreDAO gradeScoreDAO;
    @Autowired
    private SmsSendDAO smsSendDAO;
    @Autowired
    private AttachDAO attachDAO;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * 로그인
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserLoginEntity getDoLogin(UserEntity userEntity) throws Exception {

		UserLoginEntity result = new UserLoginEntity();
		UserEntity userInfo = new UserEntity();

		// 비밀번호 암호화(자동로그인시 암호화 하지 않은 비밀번호로 로그인)
		if (StringUtils.isNotEmpty(userEntity.getPassword())
				&& userEntity.getPassword().length() < 20) {
			String encryptPassword = HashUtils.encryptSHA256(userEntity.getPassword());
			userEntity.setPassword(encryptPassword);
		}

		userInfo = userDAO.getDoLogin(userEntity);
		if (userInfo == null) {															//로그인 정보가 없을 시
			logger.error("로그인 (회원정보 없음) : Error ");
			result.setResultCode(ResultCode.NO_RESULT);
			result.setResultMsg(messages.getMessage("login.not_equal"));
		} /*else if (StringUtils.equals(userEntity.getPassword(), userInfo.getPassword())) {	//로그인정보 일치
			result.setLoginSessionData(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("login.success"));
		} else if (!StringUtils.equals(userEntity.getPassword(), userInfo.getPassword())) {	//로그인 비밀번호 오류
			logger.error("로그인 (기존 비밀번호 불일치) : Error ");
			result.setResultCode(ResultCode.ACCOUNT_NOT_EQUAL);
			result.setResultMsg(messages.getMessage("login.not_equal"));
		} else {																			//로그인 오류 실패
			logger.error("로그인 실패 : Error ");
			result.setResultMsg(messages.getMessage("login.fail"));
		}*/
		else {
			result.setLoginSessionData(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("login.success"));
		}
		return result;
	}

	/**
	 * 비밀번호 확인
	 * @param userKey
	 * @param password
	 * @param oldPassword
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity getDoPassword(long userKey, String password, String oldPassword) throws Exception {
		ResponseEntity result = new ResponseEntity();
		UserEntity userInfo = new UserEntity();
		userInfo.setUserKey(userKey);
		String oldPwd = HashUtils.encryptSHA256(oldPassword);	//현재 비밀번호 암호화
		userInfo.setPassword(oldPwd);
		int results = userDAO.selectPassword(userInfo);			//현재 비밀번호가 일치하는 계정 조회
		if(results == 0) {
			logger.error(" 관리자 비밀번호 변경 (기존 비밀번호 불일치) : Error ");
			result.setResultMsg(messages.getMessage("password.fail"));
			return result;
		} else {
			String pwd = HashUtils.encryptSHA256(password);
			userInfo.setPassword(pwd);
			userDAO.updateAdminPassword(userInfo);				//새로운 비밀번호 update
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("password.success"));
		}
		return result;
	}

	/**
	 * 회원 목록
	 * @param
	 * @param userList
	 * @return entity
	 * @throws Exception
	 */
	@Override
	public UserEntity selectUserList(UserEntity userList) throws Exception {
		userList.setPageParams();
		userList.setDataSize(userDAO.selectUserListCount(userList));
		List<UserEntity> userListResult = userDAO.selectUserList(userList);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		String userLevelClassPrefix = CommonCode.LEVEL_ICON_PREFIX_PERSONAL;
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";

		for (UserEntity userResultList : userListResult){
			if(!StringUtils.isEmpty(userResultList.getPhoneNumber())){
				userResultList.setPhoneNumber(userResultList.getPhoneNumber().replaceAll(regEx, "$1-$2-$3"));
			}
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= userResultList.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= userResultList.getActivityScore()) {
					userResultList.setUserRating(String.format("%02d", i+1));
					userResultList.setUserRating(userLevelClassPrefix.concat(userResultList.getUserRating()));
				}
			}
		}
		userList.setUserEntity(userListResult);
		return userList;
	}

    /**
     * 유저 상세정보
     * @param userKey
     * @return
     * @throws Exception
     */
    @Override
    public UserEntity selectUser(long userKey) throws Exception {
    	UserEntity userInfo = userDAO.selectUser(userKey);
    	String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
    	if(!StringUtils.isEmpty(userInfo.getPhoneNumber())){
    		userInfo.setPhoneNumber(userInfo.getPhoneNumber().replaceAll(regEx, "$1-$2-$3"));
    	}
        return userInfo;
	}

    /**
     * 한줄토크 전송
     * @param
     * @param linetalkentity
     * @return
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false)
	public void insertTalk(LineTalkEntity lineTalkEntity) throws Exception {
		List<LineTalkLogEntity> lineTalkLogList = new ArrayList<LineTalkLogEntity>();
		int userKeyCount = 0;
		if(StringUtils.isEmpty(lineTalkEntity.getTalkContents()) || lineTalkEntity.getUserKey() == 0){
			logger.error(" 한줄토크 전송 실패 : Error ");
			throw new Exception();
		}
		if(lineTalkEntity.getTalkKey() != 0){
			lineTalkEntity.setUserKey(lineTalkEntity.getWriterKey());
			lineTalkDAO.inserTalkReply(lineTalkEntity);
		}else{
			lineTalkDAO.inserTalk(lineTalkEntity);

		}
		lineTalkLogList = lineTalkDAO.selectLineTalkLogList(lineTalkEntity.getTalkKey());
		if(lineTalkLogList.isEmpty()){

			lineTalkEntity.setReadYn("Y");
			lineTalkDAO.insertLineTalkLog(lineTalkEntity);
			lineTalkEntity.setUserKey(lineTalkEntity.getWriterKey());
			lineTalkDAO.insertLineTalkLog(lineTalkEntity);
		}else{
			for (LineTalkLogEntity lineTalkLogEntity : lineTalkLogList) {
				if(lineTalkLogEntity.getUserKey() != lineTalkEntity.getWriterKey()){
					userKeyCount++;
				}
			}
			if(lineTalkLogList.size() > userKeyCount){ // N 으로 업뎃 작성자 제외
				lineTalkDAO.updateLineTalkLogNotRead(lineTalkEntity);
			}else{ //
				lineTalkEntity.setReadYn("Y");
				lineTalkDAO.insertLineTalkLog(lineTalkEntity);
				lineTalkDAO.updateLineTalkLogNotRead(lineTalkEntity);
			}
		}

	}

    /**
     * 회원정보 수정
     * @param
     * @param userInfo
     * @return
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false)
	public void updateUser(UserEntity userInfo) throws Exception {
		if(StringUtils.isEmpty(userInfo.getTypeCode()) || StringUtils.isEmpty(userInfo.getStateCode())){
			logger.error(" 회원정보 수정 실패 : Error ");
			throw new Exception();
		}
		userDAO.updateUser(userInfo);
	}

	/**
 	 * 사용자 비밀번호 찾기 임시비밀번호 SMS 발송
 	 * @param userInfo
 	 * @return
 	 * @throws Exception
 	 */
	@Override
	public ResponseEntity sendSmsUserPassword(UserEntity userInfo)throws Exception {
		ResponseEntity result = new ResponseEntity();
		SmsEntity smsSendEntity = new SmsEntity();
		if(userInfo.getUserKey() > 0){
			//임시 비밀번호 생성
			Random random = new Random();
			String tempPwd = "";
			for(int i = 0; i < 6; i++) {
				tempPwd = tempPwd.concat(String.valueOf(random.nextInt(10)));
			}
			String tempPasword = HashUtils.encryptSHA256(tempPwd);	//현재 비밀번호 암호화
			userInfo.setPassword(tempPasword);
			userInfo.setTempPassword(tempPwd);

			//임시 비밀번호 등록
			int resultPassword = userDAO.updateTempPassword(userInfo);
			if(resultPassword > 0) {
				String sendPhoneNumber = userInfo.getPhoneNumber();
				smsSendEntity.setCId(CommonCode.SMS_SEND_EVENT);
				smsSendEntity.setDestAddr(sendPhoneNumber); // 연락처
				smsSendEntity.setCallBack("123123123"); // 발송처
				smsSendEntity.setMsg(CommonCode.SMS_SEND_EVENT_MESSAGE_PASSWORD+userInfo.getTempPassword()+"\"");
				smsSendEntity.setMsgType(CommonCode.SMS_MESSAGETYPE_INSTANTLY);
				smsSendDAO.sendSmsGeneral(smsSendEntity);
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMsg(messages.getMessage("password_search.success"));
			}
		}
		return result;
	}

	/**
 	 * 사용자 휴대폰 인증 SMS 발송
 	 * @param userInfo
 	 * @return
 	 * @throws Exception
 	 */
	@Override
	public ConfirmPhoneEntity userPhoneConfirm(UserEntity userInfo){
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		SmsEntity smsSendEntity = new SmsEntity();
		try {
			//인증번호 생성 (6자리 숫자)
			Random random = new Random();
			String confirmNumber = "";
			for(int i = 0; i < 6; i++) {
				confirmNumber = confirmNumber.concat(String.valueOf(random.nextInt(10)));
			}
			//휴대폰 인증번호 발송
			smsSendEntity.setCId(CommonCode.SMS_SEND_CONFIGRATION);
			smsSendEntity.setDestAddr(userInfo.getPhoneNumber()); //연락처
			smsSendEntity.setCallBack(callBackNumber); //발송처
			smsSendEntity.setMsg(CommonCode.SMS_SEND_EVENT_MESSAGE_CONFIRM+confirmNumber+"]");
			smsSendEntity.setMsgType(CommonCode.SMS_MESSAGETYPE_INSTANTLY);
			smsSendDAO.sendSmsConfirm(smsSendEntity);
			userInfo.setConfirmNumber(confirmNumber);
			result.setUserData(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("phone_confirm_send.success"));
		} catch (Exception e) {
			logger.error(" 휴대폰 인증번호 발송 오류 : Error ", e);
			result.setResultMsg(messages.getMessage("phone_confirm_send.fail"));
		}

		return result;
	}

	/**
 	 * 휴대폰 중복확인
 	 * @param userInfo, result
 	 * @return
 	 * @throws Exception
 	 */
	@Override
	public ConfirmPhoneEntity userPhoneDuplication(UserEntity userInfo){
		int phoneResult = 0;
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		try {
			phoneResult = userDAO.phoneDuplication(userInfo);
			if(phoneResult > 0){
				result.setResultMsg(messages.getMessage("phone_duplication.fail"));
			}else{
				result.setResultCode(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(" 휴대폰 중복확인 오류 : Error ", e);
		}
		return result;
	}

	/**
 	 * 아이디 중복확인
 	 * @param userInfo
 	 * @return
 	 * @throws Exception
 	 */
	@Override
	public ResponseEntity selectIdCheck(UserEntity userInfo) throws Exception {
		ResponseEntity result = new ResponseEntity();
		int resultId = userDAO.selectIdCheck(userInfo);
		if(resultId == 0){ //중복아이디가 없을 시
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("id_check.success"));
		}else{ //중복된 아이디가 존재 할 시
			result.setResultMsg(messages.getMessage("id_check.fail"));
		}
		return result;
	}

	/**
 	 * 회원가입
 	 * @param userInfo
 	 * @return
 	 * @throws Exception
 	 */
	@Override
	public ConfirmPhoneEntity insertUser(UserEntity userInfo){
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		try {
			String encryptPassword = HashUtils.encryptSHA256(userInfo.getPassword());
			userInfo.setPassword(encryptPassword);
			userDAO.insertUser(userInfo);

			// 앱 최초가입 활동점수 부여
			activityLogService.insertActivityLogByJoinApp(userInfo);

			result.setUserData(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("join.success"));
		} catch (Exception e) {
			logger.error(" 회원가입 오류 : Error ", e);
			result.setResultMsg(messages.getMessage("join.fail"));
		}
		return result;
	}

	/**
	 * 회원 탈퇴
 	 * @param userInfo
 	 * @return
 	 * @throws Exception
	 */
	@Override
	public ResponseEntity updateUserWithdraw(UserEntity userInfo){
		ResponseEntity result = new ResponseEntity();
		try {
			userDAO.updateUserWithdraw(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("withdraw.success"));
		} catch (Exception e) {
			logger.error(" 회원탈퇴 오류 : Error ", e);
			result.setResultMsg(messages.getMessage("withdraw.fail"));
		}

		return result;
	}

	/**
	 * 아이디 찾기
 	 * @param userInfo
 	 * @return UserEntity
 	 * @throws Exception
	 */
	@Override
	public UserEntity userIdFind(UserEntity userInfo)throws Exception {
		userInfo = userDAO.userIdFind(userInfo); // 일치하는 유저정보를 가지고온다. (전화번호, 이름)

		if(userInfo != null){
			SmsEntity smsSendEntity = new SmsEntity();
			smsSendEntity.setCId(CommonCode.SMS_SEND_EVENT); //sendType
			smsSendEntity.setDestAddr(userInfo.getPhoneNumber()); //연락처
			smsSendEntity.setCallBack(callBackNumber); // 발송처
			smsSendEntity.setMsg(CommonCode.SMS_SEND_EVENT_MESSAGE_ID+userInfo.getId()+"\"");
			smsSendEntity.setMsgType(CommonCode.SMS_MESSAGETYPE_INSTANTLY);
			userInfo.setSmsEntity(smsSendEntity);
		}
		return userInfo;
	}

	/**
	 * 비밀번호 찾기
 	 * @param userInfo
 	 * @return UserEntity
 	 * @throws Exception
	 */
	@Override
	public UserEntity userPasswordFind(UserEntity userInfo)throws Exception {
		userInfo = userDAO.userPasswordFind(userInfo); // 일치하는 유저정보를 가지고온다. (아이디, 전화번호, 이름)
		return userInfo;
	}

    /**
     * 유저 상세정보
     * @param userInfo
     * @return
     * @throws Exception
     */
    @Override
    public ConfirmPhoneEntity selectUserProfile(UserEntity userInfo) throws Exception {
    	ConfirmPhoneEntity result = new ConfirmPhoneEntity();
    	AttachEntity attachEntity = new AttachEntity();

    		//프로필 사용자 정보
    	UserEntity userInformation = userDAO.selectUser(userInfo.getUserKey());

		//회원 프로필 사진
		attachEntity.setCommonKey(userInfo.getUserKey());
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_PROFILE);
		attachEntity = attachDAO.selectAttachInfo(attachEntity);

		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 사용자 상세 프로필 등급
		for (int i=0; i < gradeScoreList.size(); i++){
			if(gradeScoreList.get(i).getUserLowScore() <= userInformation.getActivityScore()
					&& gradeScoreList.get(i).getUserHighScore() >= userInformation.getActivityScore()) {
				userInformation.setUserRating(String.format("%02d", i+1));
			}
		}

		result.setAttachEntity(attachEntity);
    	result.setUserData(userInformation);
        return result;
	}

    /**
     * 유저 상세정보(한줄토크)
     * @param userInfo
     * @return
     * @throws Exception
     */
    @Override
    public ConfirmPhoneEntity selectUserProfileLineTalk(UserEntity userInfo) throws Exception {
    	ConfirmPhoneEntity result = new ConfirmPhoneEntity();
    		//한줄토크 목록
    	List<LineTalkEntity> lineTalkList = userDAO.selectLineTalk(userInfo);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();

		// 한줄토크 작성자 프로필 등급
		for (LineTalkEntity lineTalkData : lineTalkList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= lineTalkData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= lineTalkData.getActivityScore()) {
					lineTalkData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		// 한줄토크 작성 시점
		for (int i = 0; i < lineTalkList.size(); i++) {
			lineTalkList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(lineTalkList.get(i).getInsertDate()));
		}

		result.setLineTalkList(lineTalkList);
        return result;
	}

    /**
     * 유저 상세정보(활동로그)
     * @param userInfo
     * @return
     * @throws Exception
     */
    @Override
    public ConfirmPhoneEntity selectUserProfileActivityLog(UserEntity userInfo) throws Exception {
    	ConfirmPhoneEntity result = new ConfirmPhoneEntity();
    		//활동로그 목록

    	List<ActivityLogEntity> activityLogList = activityLogDAO.selectActivityLogList(userInfo);
    	List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 활동로그 작성 시점
		for (int i = 0; i < activityLogList.size(); i++) {
			activityLogList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(activityLogList.get(i).getInsertDate()));
		}
		// 활동로그 프로필 등급
		for (ActivityLogEntity lineTalkData : activityLogList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= lineTalkData.getActivityLogScore()
						&& gradeScoreList.get(i).getUserHighScore() >= lineTalkData.getActivityLogScore()) {
					lineTalkData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		result.setActivityLogEntity(activityLogList);
        return result;
	}

    /**
     * 유저 프로필 편집
     * @param userInfo
     * @return
     * @throws Exception
     */
	@Override
	public ConfirmPhoneEntity updateProfile(UserEntity userInfo)throws Exception {
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		try {
			if(!StringUtils.isEmpty(userInfo.getPassword()) || !StringUtils.isEmpty(userInfo.getPasswordCheck())){
	        	if(!StringUtils.equals(userInfo.getPassword(), userInfo.getPasswordCheck())){ // 비밀번호 일치확인
	        		result.setResultMsg(messages.getMessage("password.check"));
	        		return result;
	        	}
	        	String encryptPassword = HashUtils.encryptSHA256(userInfo.getPassword()); // 비밀번호 암호화
	        	userInfo.setPassword(encryptPassword);
			}

			userDAO.updateProfile(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("profile_update.success"));
		} catch (Exception e) {
			logger.error(" 회원 프로필 편집 오류 : Error ", e);
			result.setResultMsg(messages.getMessage("profile_update.fail"));
		}
		return result;
	}

	/**
	 * 한줄토크 삭제
	 * @param lineTalkEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public LineTalkEntity deleteLineTalk(LineTalkEntity lineTalkEntity){

		try {
			lineTalkDAO.deleteLineTalk(lineTalkEntity);
			lineTalkEntity.setResultMsg(messages.getMessage("lineTalk_delete.success"));
		} catch (Exception e) {
			logger.error(" 회원 프로필 편집 오류 : Error ", e);
			lineTalkEntity.setResultMsg(messages.getMessage("lineTalk_delete.fail"));
		}

		return lineTalkEntity;
	}
}