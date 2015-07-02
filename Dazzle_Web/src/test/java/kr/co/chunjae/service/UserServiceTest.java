package kr.co.chunjae.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.controller.BoardController;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.entities.user.UserLoginEntity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:configs/mvc-dispatcher-servlet.xml")
public class UserServiceTest {

	private static final String USER_INFO_ID_DISCORD = "notequals";
	private static final String USER_INFO_ID_ADMIN = "test";
	private static final String USER_INFO_OLDPASSWORD_DISCORD = "12345";
	private static final String USER_INFO_PASSWORD_DISCORD = "123";
	private static final String USER_INFO_OLDPASSWORD_AGREE = "1234";
	private static final String USER_INFO_PASSWORD_AGREE = "1234";
	private static final String USER_INFO_STATE_JOIN = "01";
	private static final String USER_INFO_TYPE_ADMIN = "01";
	private static final String ACTIVITY_TYPE_ADMIN = "11";
	private static final String LINETALK_INFO_CONTENTS = "한줄토크 내용 test Controller 입니다.";
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	String password, oldPassword;

	@Autowired
	UserService userService;
	ActivityLogService activityLogService;
	UserEntity userInfo;
	ResponseEntity result;

	@Before
	public void before() throws Exception {
		userInfo = new UserEntity();
		result = new ResponseEntity();
	}
	/**
	 * 로그인
	 * case : 잘못된 정보를 입력했을때
	 * @throws Exception
	 */
	@Ignore @Test
	public void testGetDoLoginNotEquals() throws Exception{
		adminLoginNotEquals(userInfo);
		UserLoginEntity userInfoResult = userService.getDoLogin(userInfo);
		assertThat(userInfoResult, is(nullValue()));
	}

	/**
	 * 관리자 로그인
	 * case : 정상적인 정보를 입력했을때
	 * @throws Exception
	 */
	@Ignore @Test
	public void testGetDoLoginCorrect() throws Exception{
		adminLoginEquals(userInfo);
		UserLoginEntity userInfoResult = userService.getDoLogin(userInfo);
		assertThat(userInfoResult, is(notNullValue()));
	}

	/**
	 * 관리자 비밀번호 변경
	 * case : 기존비밀번호가 일치하지않아 비밀번호 변경 실패 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testGetDoPasswordNotEquals() throws Exception {
		adminPasswordNotEquals(userInfo);
		long userKey = userInfo.getUserKey();
		result = userService.getDoPassword(userKey, password, oldPassword);
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 관리자 비밀번호 변경
	 * case : 비밀번호 변경 성공 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testGetDoPasswordEquals() throws Exception {
		adminPasswordEquals(userInfo);
		long userKey = userInfo.getUserKey();
		result = userService.getDoPassword(userKey, password, oldPassword);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 사용자 정보 목록
	 * case : 이상없이 목록을 가져왔을경우
	 * @throws Exception
	 */
	@Ignore @Test
	public void testSelectUserList() throws Exception{
		userInfo = userService.selectUserList(userInfo);
		assertThat(userInfo.getUserEntity(), is(notNullValue()));
	}

	/**
	 * 사용자 정보 상세
	 * case : 빈 키값을 넣었을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	public void testSelectUserEmpty() throws Exception{
		int userKey = 0 ;
		userInfo = userService.selectUser(userKey);
		assertThat(userInfo, is(nullValue()));
	}

	/**
	 * 사용자 정보 상세
	 * case : 빈 키값을 넣었을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	public void testSelectUserCorrect() throws Exception{
		int userKey = 1 ;
		userInfo = userService.selectUser(userKey);
		assertThat(userInfo.getUserEntity(), is(nullValue()));
	}

	/**
	 * 관리자 한줄토크 전송
	 * case : 빈 값을 넣었을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testInsertTalkEmpty(){
		LineTalkEntity linetalkEntity = new LineTalkEntity();
		try {
			userService.insertTalk(linetalkEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 관리자 한줄토크 전송 실패 : Error ", e);
		}
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 관리자 한줄토크 전송
	 * case : 정상적인 값을 넣었을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testInsertTalkCorrect(){
		LineTalkEntity linetalkEntity = new LineTalkEntity();
		lineTalkInfo(linetalkEntity);
		try {
			userService.insertTalk(linetalkEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 관리자 한줄토크 전송 실패 : Error ", e);
		}
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 관리자페이지 사용자 정보변경
	 * case : 사용자 정보를 빈 값을 넣었을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testUpdateUserEmpty() {
        try{
            userService.updateUser(userInfo);
            result.setResultCode(ResultCode.SUCCESS);
        }catch(Exception e){
        	logger.error(" 관리자 사용자 정보 변경 : Error ", e);
        }
        assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 관리자페이지 사용자 정보변경
	 * case : 사용자 정보를 정상정인 값을 넣었을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testUpdateUserCorrect() {
		userUpdateInfo(userInfo);
        try{
            userService.updateUser(userInfo);
            result.setResultCode(ResultCode.SUCCESS);
        }catch(Exception e){
        	logger.error(" 관리자 사용자 정보 변경 : Error ", e);
        }
        assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 사용자 활동점수 부여
	 * case : 값을 보내지 않았을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testUpdateUserActivityScoreEmpty() throws Exception {
		activityLogService.insertActivityLogByGiveAdmin(CommonCode.ACTIVITY_LOG_TYPE_GIVE_ADMIN, 11, 30);
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 사용자 활동점수 부여
	 * case : 정상적인 값을 보냈을 경우
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testUpdateUserActivityScore() throws Exception {
		activityLogService.insertActivityLogByGiveAdmin(CommonCode.ACTIVITY_LOG_TYPE_GIVE_ADMIN, 11, 0);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 회원정보 변경 테스트값
	 * @param userInfo
	 */
	private void userUpdateInfo(UserEntity userInfo) {
		userInfo.setTypeCode(USER_INFO_TYPE_ADMIN);
		userInfo.setStateCode(USER_INFO_STATE_JOIN);
		userInfo.setUserKey(5);
	}

	/**
	 * 한줄토크 전송 테스트값
	 * @param linetalkEntity
	 */
	private void lineTalkInfo(LineTalkEntity linetalkEntity) {
		linetalkEntity.setTalkContents(LINETALK_INFO_CONTENTS);
		linetalkEntity.setUserKey(1);
		linetalkEntity.setWriterKey(1);
	}

	/**
	 * 관리자 비밀번호 변경 일치 테스트값
	 * @param userInfo
	 */
	private void adminPasswordEquals(UserEntity userInfo) {
		userInfo.setUserKey(1);
		password = USER_INFO_PASSWORD_AGREE;// new password
		oldPassword = USER_INFO_OLDPASSWORD_AGREE;// old passowrd
	}

	/**
	 * 관리자 비밀번호 변경 불일치 테스트값
	 * @param userInfo
	 */
	private void adminPasswordNotEquals(UserEntity userInfo) {
		userInfo.setUserKey(1);
		password = USER_INFO_PASSWORD_DISCORD;// new password
		oldPassword = USER_INFO_OLDPASSWORD_DISCORD;// old passowrd
	}

	/**
	 * 관리자 로그인 정보 일치 테스트값
	 * @param userInfo
	 */
	private void adminLoginEquals(UserEntity userInfo) {
		userInfo.setId(USER_INFO_ID_ADMIN);//ID
		userInfo.setPassword(USER_INFO_PASSWORD_AGREE);//password
	}

	/**
	 * 관리자 로그인 정보 불일치 테스트값
	 * @param userInfo
	 */
	private void adminLoginNotEquals(UserEntity userInfo) {
		userInfo.setId(USER_INFO_ID_DISCORD);//ID
		userInfo.setPassword(USER_INFO_PASSWORD_DISCORD);//Password
	}

}
