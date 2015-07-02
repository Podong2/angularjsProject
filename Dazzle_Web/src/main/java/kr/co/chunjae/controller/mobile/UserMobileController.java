package kr.co.chunjae.controller.mobile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.FranchiseEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.board.LineTalkListEntity;
import kr.co.chunjae.entities.board.LineTalkReplyEntity;
import kr.co.chunjae.entities.user.ConfirmPhoneEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.entities.user.UserLoginEntity;
import kr.co.chunjae.service.FranchiseService;
import kr.co.chunjae.service.LineTalkService;
import kr.co.chunjae.service.SmsService;
import kr.co.chunjae.service.UserService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.HashUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/user")
public class UserMobileController {
	@Value("#{file_prop['filePath']}")
	private String filePath;

	@Autowired
	private UserService userService;

	@Autowired
	private LineTalkService lineTalkService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private Messages messages;

	@Autowired
	private AttachDAO attachDAO;

	@Autowired
	private FranchiseService franchiseService;

	private static final Logger logger = LoggerFactory.getLogger(UserMobileController.class);

	/**
	 * 로그인
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ResponseEntity loginSubmit(@RequestBody UserEntity userInfo) {

		UserLoginEntity result = new UserLoginEntity();
		try {
			result = userService.getDoLogin(userInfo);
		} catch (Exception e) {
			logger.error("로그인 오류", e);
			result.setResultMsg(messages.getMessage("login.false"));
		}
		return result;
	}

	/**
	 * 아이디 & 비밀번호 찾기
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/idSearch", method = RequestMethod.POST)
	public ResponseEntity userIdSearchSmsSend(@RequestBody UserEntity userInfo){
		ResponseEntity result = new ResponseEntity();
		try {
			if(userInfo.getId() != null) {
				userInfo = userService.userIdFind(userInfo);
				if(userInfo != null){
					result = userService.sendSmsUserPassword(userInfo);
					result.setResultMsg(messages.getMessage("password_search.success"));
				}else{
					result.setResultMsg(messages.getMessage("password_search.fail"));
				}
			} else {
				userInfo = userService.userIdFind(userInfo);
				if(userInfo != null) {
					result = smsService.sendSmsGeneral(userInfo.getSmsEntity());
					result.setResultMsg(messages.getMessage("id_search.success"));
				}else{
					result.setResultMsg(messages.getMessage("id_search.fail"));
				}
			}
		} catch (Exception e) {
			logger.error(" 아이디 찾기 오류", e);
			if (userInfo.getId() != null) {
				result.setResultMsg(messages.getMessage("id_search.fail"));
			} else {
				result.setResultMsg(messages.getMessage("password_search.fail"));
			}
		}
		return result;
	}

	/**
	 * 휴대폰 번호 중복확인 후 인증번호 전송
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/phoneConfirm", method = RequestMethod.POST)
	public ConfirmPhoneEntity userPhoneDuplication(@RequestBody UserEntity userInfo) throws Exception {

		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		result = userService.userPhoneDuplication(userInfo);
		if (StringUtils.equals(result.getResultCode(), ResultCode.SUCCESS)) {
			result = userService.userPhoneConfirm(userInfo);
		}
		return result;
	}

	/**
	 * 휴대폰 인증번호 확인
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/phoneConfirmCheck", method = RequestMethod.POST)
	public ResponseEntity userPhoneConfirmCheck(@RequestBody UserEntity userInfo) throws Exception {
		ResponseEntity result = new ResponseEntity();
		if (userInfo.getConfirmNumber().equals(userInfo.getInputConfirmNumber())) {
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("phone_confirm_check.success"));
		} else {
			result.setResultMsg(messages.getMessage("phone_confirm_check.fail"));
		}

		return result;
	}

	/**
	 * 아이디 중복체크
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public ResponseEntity idCheck(@RequestBody UserEntity userInfo) throws Exception {
		ResponseEntity result = new ResponseEntity();
		if(StringUtils.isEmpty(userInfo.getId())){
			return result;
		}else{
			result = userService.selectIdCheck(userInfo);
		}
		return result;
	}

	/**
	 * 회원가입
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public ResponseEntity insertUser(@RequestBody UserEntity userInfo) throws Exception {
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
    	if(!StringUtils.equals(userInfo.getPassword(), userInfo.getPasswordCheck())){
    		result.setResultMsg(messages.getMessage("password.check"));
    		return result;
    	}
		result = userService.insertUser(userInfo);
		if (StringUtils.equals(result.getResultCode(), ResultCode.SUCCESS)) {
			result.setUserData(userInfo);
		}

		return result;
	}

	/**
	 * 회원탈퇴
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/widthDraw", method = RequestMethod.POST)
	public ResponseEntity updateUserWithdraw(@RequestBody UserEntity userInfo) throws Exception {
		ResponseEntity result = new ResponseEntity();
		result = userService.updateUserWithdraw(userInfo);

		return result;
	}

	/**
	 * 사용자 프로필 상세
	 * @param userKey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ConfirmPhoneEntity userDetail(@RequestParam("userKey") long userKey){

		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		UserEntity userInfo = new UserEntity();
		if(userKey == 0){
			return result;
		}
		userInfo.setUserKey(userKey);
		if(userInfo.getStartRow() != 0 && userInfo.getRowCount() != 0){
			userInfo.setCurrentPage(userInfo.getStartRow());
			userInfo.setPageSize(userInfo.getRowCount(), userInfo.getRowCount());
		}

		try {
			result = userService.selectUserProfile(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("profile_load.success"));
		} catch (Exception e) {
			logger.error(" 사용자 프로필 오류", e);
			result.setResultMsg(messages.getMessage("profile_load.fail"));
		}
		return result;
	}


	/**
	 * 사용자 프로필 상세 (한줄토크 목록)
	 * @param userKey
	 * @param sessionUserKey
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailLineTalk", method = RequestMethod.GET)
	public ConfirmPhoneEntity userDetailLineTalk(@RequestParam("userKey") long userKey,
			@RequestParam("sessionUserKey") long sessionUserKey,
			@RequestParam("startRow") int startRow,
			@RequestParam("rowCount") int rowCount){
		UserEntity userInfo = new UserEntity();
		userInfo.setUserKey(userKey);
		userInfo.setStartRow(startRow);
		userInfo.setRowCount(rowCount);
		userInfo.setSessionUserKey(sessionUserKey);
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		if(userInfo.getStartRow() != 0 && userInfo.getRowCount() != 0){
			userInfo.setCurrentPage(userInfo.getStartRow());
			userInfo.setPageSize(userInfo.getRowCount(), userInfo.getRowCount());
		}

		try {
			result = userService.selectUserProfileLineTalk(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("profile_load.success"));
		} catch (Exception e) {
			logger.error(" 사용자 프로필 오류", e);
			result.setResultMsg(messages.getMessage("profile_load.fail"));
		}
		return result;
	}


	/**
	 * 사용자 프로필 상세 (활동로그 목록)
	 * @param userKey
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailActivityLog", method = RequestMethod.GET)
	public ConfirmPhoneEntity userDetailActivityLog(@RequestParam("userKey") long userKey,
			@RequestParam("startRow") int startRow,
			@RequestParam("rowCount") int rowCount){
		UserEntity userInfo = new UserEntity();
		userInfo.setUserKey(userKey);
		userInfo.setStartRow(startRow);
		userInfo.setRowCount(rowCount);
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		if(userInfo.getStartRow() != 0 && userInfo.getRowCount() != 0){
			userInfo.setCurrentPage(userInfo.getStartRow());
			userInfo.setPageSize(userInfo.getRowCount(), userInfo.getRowCount());
		}

		try {
			result = userService.selectUserProfileActivityLog(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("profile_load.success"));
		} catch (Exception e) {
			logger.error(" 사용자 프로필 오류", e);
			result.setResultMsg(messages.getMessage("profile_load.fail"));
		}
		return result;
	}

	/**
	 * 유저 프로필 편집
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity updateProfile(@RequestBody UserEntity userInfo) throws Exception {
		ConfirmPhoneEntity result = new ConfirmPhoneEntity();
		AttachEntity attachEntity = new AttachEntity();
		attachEntity.setCommonKey(userInfo.getUserKey());
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_PROFILE);

    	if(!StringUtils.equals(userInfo.getPassword(), userInfo.getPasswordCheck())){
    		result.setResultMsg(messages.getMessage("password.check"));
    		return result;
    	}
		result = userService.updateProfile(userInfo);

		// 유저 프로필 이미지 등록
		if (StringUtils.equals(result.getResultCode(), ResultCode.SUCCESS)) {
			if(!kr.co.digigroove.commons.utils.StringUtils.isEmpty(userInfo.getFileName())){
				String image = userInfo.getFileName();

				groupFileOutputStream(attachEntity, image);

			}

		}

		return result;
	}

    /**
     * 사용자 한줄토크 전송
     * @param linetalkEntity
     * @return
     */
    @RequestMapping(value = "/insertTalk", method = RequestMethod.POST)
    public ResponseEntity insertTalk(@RequestBody LineTalkEntity linetalkEntity) {

    	ResponseEntity result = new ResponseEntity();
        try{
            userService.insertTalk(linetalkEntity);
            result.setResultMsg(messages.getMessage("linetalk.success"));
        }catch(Exception e){
        	logger.error(" 사용자 한줄토크 전송 : Error ", e);
            result.setResultMsg(messages.getMessage("linetalk.fail"));
        }

        return result;
     }

	/**
	 * 한줄토크 댓글 본문
	 * @param talkList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/talkReply", method = RequestMethod.GET)
	public LineTalkListEntity lineTalkReplyMain(@RequestParam("talkKey") long talkKey,
			@RequestParam("sessionUserKey") long sessionUserKey) throws Exception{
		LineTalkReplyEntity talkList = new LineTalkReplyEntity();
		talkList.setTalkKey(talkKey);
		talkList.setSessionUserKey(sessionUserKey);

		LineTalkListEntity lineTalkListEntity = new LineTalkListEntity();

		lineTalkListEntity = lineTalkService.lineTalkReplyMain(talkList);

		if (StringUtils.equals(lineTalkListEntity.getResultCode(), ResultCode.SUCCESS)) {
			lineTalkService.updateLineTalkLog(talkList);
		}

		return lineTalkListEntity;
	}

	/**
	 * 한줄토크 댓글 목록
	 * @param talkList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/talkReplyList", method = RequestMethod.GET)
	public LineTalkListEntity lineTalkReplyList(@RequestParam("talkKey") long talkKey,
			@RequestParam("sessionUserKey") long sessionUserKey,
			@RequestParam("startRow") int startRow,
			@RequestParam("rowCount") int rowCount) throws Exception{
		LineTalkReplyEntity talkList = new LineTalkReplyEntity();
		talkList.setTalkKey(talkKey);
		talkList.setStartRow(startRow);
		talkList.setRowCount(rowCount);

		LineTalkListEntity lineTalkListEntity = new LineTalkListEntity();
		talkList.setCurrentPage(talkList.getStartRow());
		talkList.setPageSize(talkList.getRowCount(), talkList.getRowCount());

		lineTalkListEntity = lineTalkService.lineTalkReplyList(talkList);

		return lineTalkListEntity;
	}

	/**
	 * 한줄토크 삭제
	 * @param talkList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteLineTalk", method = RequestMethod.POST)
	public LineTalkEntity deleteLineTalk(@RequestBody LineTalkEntity talkEntity) throws Exception{
		talkEntity = userService.deleteLineTalk(talkEntity);
		return talkEntity;
	}

	/**
	 * 프렌차이즈 인증하기
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/confirmCheckFranchise", method = RequestMethod.GET)
	public ResponseEntity confirmCheckFranchise(@RequestParam("HakbunID") String HakbunID,
			@RequestParam("Name") String Name,
			@RequestParam("franchiseType") String franchiseType) throws Exception {
		FranchiseEntity franchiseEntity = new FranchiseEntity();
		franchiseEntity.setFranchiseType(franchiseType);
		franchiseEntity.setHakbunID(HakbunID);
		franchiseEntity.setName(Name);

		ResponseEntity result = new ResponseEntity();
		result = franchiseService.confirmFranchiseInfo(franchiseEntity);
		return result;
	}

	//파일 업로드
	private void groupFileOutputStream(AttachEntity attachEntity, String image)
			throws FileNotFoundException, IOException, Exception {
		byte[] imageByteArray = HashUtils.decodeBase64(image);
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

}
