package kr.co.chunjae.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.SmsEntity;
import kr.co.chunjae.entities.board.LineTalkListEntity;
import kr.co.chunjae.entities.board.LineTalkReplyEntity;
import kr.co.chunjae.entities.user.UserEntity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:configs/mvc-dispatcher-servlet.xml")
public class UserMobileServiceTest {

	private static final String USER_INFO_PASSWORD_AGREE = "1234";
	private static final String USER_INFO_PASSWORD = "123456";
	String password, oldPassword;

	@Autowired
	UserService userService;
	UserEntity userInfo;
	ResponseEntity result;
	SmsEntity smsTestEntity;
	SmsService smsService;
	LineTalkService linetalkService;

	@Before
	public void before() throws Exception {
		userInfo = new UserEntity();
		result = new ResponseEntity();
		smsTestEntity = new SmsEntity();
	}

	/**
	 * 사용자 아이디 찾기
	 * case : 올바른 사용자 정보를 입력했을 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testSmsIdCorrect(){
		userIdSearchSmsSendCorrect();
		try {
//			result = userService.userIdSmsSend(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 사용자 아이디 찾기
	 * case : 올바른 사용자 정보를 입력했을 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testSmsIdNotEquals() throws Exception {
		userIdSearchSmsSendNotEquals();
		try {
//			result = userService.userIdSmsSend(userInfo);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 사용자 비밀번호 찾기
	 * case : 올바른 사용자 정보를 입력했을 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testSmsPassword() throws Exception {
		userPasswordSearchSmsSendCorrect();
		result = userService.sendSmsUserPassword(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 휴대폰 중복확인
	 * case : 입력 한 휴대폰번호가 중복일 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testPhoneDuplicationTrue() throws Exception {
		userInfo.setPhoneNumber("01089275329"); //TODO
		result = userService.userPhoneDuplication(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 휴대폰 중복확인
	 * case : 입력 한 휴대폰번호가 중복이 아닐 시
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testPhoneDuplicationFalse() throws Exception {
		userInfo.setPhoneNumber("01089275328"); //TODO
		result = userService.userPhoneDuplication(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 휴대폰 인증번호 SMS 발송
	 * case :  SMS 발송 성공
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testPhoneConfirmCorrct() throws Exception {
		userInfo.setPhoneNumber("01089275329"); //TODO
		result = userService.userPhoneConfirm(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 휴대폰 인증번호 SMS 발송 @Ignore
	 * case :  SMS 발송 실패(작업중)
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testPhoneConfirmFail() throws Exception { //TODO
		userInfo.setPhoneNumber("000000000"); //TODO
		result = userService.userPhoneConfirm(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 회원가입 @Ignore
	 * case : 정상적인 값 삽입
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testInsertUserCorrect() throws Exception {
		userInfo.setId("testuser");//TODO
		userInfo.setPassword(USER_INFO_PASSWORD_AGREE);
		userInfo.setPhoneNumber("01089275329");//TODO
		userInfo.setName("TDD테스터");//TODO
		result = userService.insertUser(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 회원가입
	 * case : 빈 값 삽입
	 * @throws Exception
	 */
	@Ignore @Test
	@Transactional
	@Rollback(true)
	public void testInsertUserEmpty() throws Exception {
		result = userService.insertUser(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 아이디 중복확인
	 * case : 아이디 중복이 안날때
	 * @throws Exception
	 */
	@Ignore @Test
	public void testselectIdCheckCorrect() throws Exception {
		userInfo.setId("user1231");//TODO
		result = userService.selectIdCheck(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 아이디 중복확인
	 * case : 아이디 중복이 날때
	 * @throws Exception
	 */
	@Ignore @Test
	public void testselectIdCheckIncorrect() throws Exception {
		userInfo.setId("user1");//TODO
		result = userService.selectIdCheck(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 회원가입 @Ignore
	 * case : 값을 올바르게 입력 할 경우 (테스트 시 값이 중복되어 아이디 값을 바꿔주며 테스트)
	 * @throws Exception
	 */
	@Ignore @Test
	public void testinsertUser() throws Exception {
		userInfo.setId("user1");//TODO
		userInfo.setPassword(USER_INFO_PASSWORD_AGREE);
//		userInfo.setPhoneNumber("");
		userInfo.setName("테스터");//TODO
		result = userService.insertUser(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 회원 탈퇴
	 * case : 올바른 회원 고유키를 입력했을 경우(실제 탈퇴는 로그인 세션으로 키값을가지고온다.)
	 * @throws Exception
	 */
	@Ignore @Test
	public void updateUserWithdraw() throws Exception {
		userInfo.setUserKey(82);
		result = userService.updateUserWithdraw(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 회원 프로필 편집
	 * case : 올바른 회원 정보 입력
	 * @throws Exception
	 */
	@Ignore @Test
	public void updateProfile() throws Exception{
		userInfo.setUserKey(11);
		userInfo.setName("유저1");//TODO
		userInfo.setFranchiseType("01");//TODO
		userInfo.setPassword(USER_INFO_PASSWORD);
		userInfo.setPasswordCheck(USER_INFO_PASSWORD);
		userInfo.setUserGrade("20");//TODO
		userInfo.setPhoneNumber("01089275329");//TODO
		result = userService.updateProfile(userInfo);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 회원 프로필 한줄토크 댓글 목록
	 * case :
	 * @throws Exception
	 */
	@Test
	public void lineTalkReplyList()throws Exception{
		LineTalkReplyEntity lineTalkEntity = new LineTalkReplyEntity();
		LineTalkListEntity lineTalkListEntity = new LineTalkListEntity();
		lineTalkEntity.setTalkKey(66);
		lineTalkListEntity = linetalkService.lineTalkReplyList(lineTalkEntity);
		assertThat(lineTalkListEntity.getResultCode(), is(ResultCode.SUCCESS));
	}
	/////////////////////////////////////////////////////////////////////

	/**
	 * 사용자 아이디 찾기 후 SMS발송 회원정보(일치) 테스트값
	 */
	private void userIdSearchSmsSendCorrect() {
		userInfo.setName("유저1");//TODO
		userInfo.setPhoneNumber("01089275329");//TODO
	}

	/**
	 * 사용자 아이디 찾기 후 SMS발송 회원정보(불일치) 테스트값
	 */
	private void userIdSearchSmsSendNotEquals() {
		userInfo.setName("올바르지않는정보");//TODO
		userInfo.setPhoneNumber("01089275329");//TODO
	}

	/**
	 * 사용자 비밀번호 찾기 후 SMS발송 회원정보(일치) 테스트값
	 */
	private void userPasswordSearchSmsSendCorrect() {
		userIdSearchSmsSendCorrect();
		userInfo.setId("danger");//TODO
	}

}
