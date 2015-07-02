package kr.co.chunjae.controller.rest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.user.ActivityLogEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.entities.user.UserLoginEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.UserService;
import kr.co.digigroove.commons.messages.Messages;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/admin")
public class UserRestController {
	@Autowired
	UserService userService;
	@Autowired
	ActivityLogService activityLogService;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	/**
	 * 로그인
	 * @param userInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public UserLoginEntity loginSubmit(UserEntity userInfo, HttpSession session) {

		UserLoginEntity result = new UserLoginEntity();
		try {
			result = userService.getDoLogin(userInfo);
			if (StringUtils.equals(result.getResultCode(), ResultCode.SUCCESS)) {
				session.setAttribute(SessionCode.ADMIN_LOGIN_SESSION, result.getLoginSessionData());
			}
		} catch (Exception e) {
			logger.error(" 관리자 로그인 확인 : Error ", e);
			result.setResultMsg(messages.getMessage("login.false"));
		}
		return result;
	}

	/**
	 * 로그아웃
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doLogout", method = RequestMethod.POST)
	public ResponseEntity doLogout(HttpSession session, HttpServletResponse response){

		ResponseEntity result = new ResponseEntity();
		UserEntity loginSession = (UserEntity)session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);

		try {
			if (loginSession != null) {					//세션 존재시 로그아웃 처리
				response.setHeader("Cache-Control", "no-cache"); //케시 없애기
				response.setDateHeader("Expires", 0); //케시 없애기
				session.invalidate();	//세션 소멸
			}
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("logout.success"));


		} catch (Exception e) {
			logger.error(" 관리자 로그아웃 : Error ", e);
			result.setResultMsg(messages.getMessage("login.false"));
		}

		return result;
	}

	/**
	 * 비밀번호 변경 submit 페이지
	 * @param session
	 * @param password
	 * @param passwordCheck
	 * @param oldPassword
	 * @return
	 */
	@RequestMapping(value = "/doUpdatePassword", method = RequestMethod.POST)
	public ResponseEntity updateAdminPassword(HttpSession session,
			@RequestParam(required = false, value = "password") String password,
			@RequestParam(required = false, value = "PasswordCheck") String passwordCheck,
			@RequestParam(required = false, value = "oldPassword") String oldPassword){
		ResponseEntity result = new ResponseEntity();
        UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
        try {
        	if(!StringUtils.equals(password, passwordCheck)){
        		result.setResultMsg(messages.getMessage("password.check"));
        		return result;
        	}
        	result = userService.getDoPassword(loginSession.getUserKey(), password, oldPassword);
        } catch (Exception e) {
        	logger.error(" 관리자 비밀번호 변경 : Error ", e);
        }

        return result;
     }

    /**
     * 관리자 한줄토크 전송
     * @param linetalkEntity
     * @param session
     * @return
     */
    @RequestMapping(value = "/insertTalk", method = RequestMethod.POST)
    public ResponseEntity insertTalk(LineTalkEntity linetalkEntity, HttpSession session) {

    	ResponseEntity result = new ResponseEntity();
    	UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
        try{
        	linetalkEntity.setWriterKey(loginSession.getUserKey());
            userService.insertTalk(linetalkEntity);
            result.setResultMsg(messages.getMessage("linetalk.success"));
        }catch(Exception e){
        	logger.error(" 관리자 한줄토크 전송 : Error ", e);
            result.setResultMsg(messages.getMessage("linetalk.fail"));
        }

        return result;
     }

    /**
     * 관리자 페이지 사용자 정보 변경
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity updateUser(UserEntity userInfo){

    	ResponseEntity result = new ResponseEntity();
        try{
            userService.updateUser(userInfo);
            result.setResultMsg(messages.getMessage("update.success"));
        }catch(Exception e){
        	logger.error(" 관리자 사용자 정보 변경 : Error ", e);
            result.setResultMsg(messages.getMessage("update.fail"));
        }

        return result;
    }
    
	/**
	 * 활동 점수 등록(관리자 부여)
	 * @param activityLogEntity
	 * @return
	 */
	@RequestMapping(value = "/updateUserActivityScore", method = RequestMethod.POST)
	public ResponseEntity updateUserActivityScore(ActivityLogEntity activityLogEntity) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			activityLogService.insertActivityLogByGiveAdmin(CommonCode.ACTIVITY_LOG_TYPE_GIVE_ADMIN,
								activityLogEntity.getUserKey(), activityLogEntity.getActivityScore());
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("update.success"));
		} catch (Exception e) {
			logger.error("관리자 활동점수 부여 : Error", e);
			result.setResultMsg(messages.getMessage("update.fail"));
		}

		return result;
	}
}