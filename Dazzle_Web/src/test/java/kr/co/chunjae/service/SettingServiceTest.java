package kr.co.chunjae.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.entities.user.ActivityScoreEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;

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
public class SettingServiceTest {

	private static final String LINE_NOTICE_INFO_ENDDATE = "2014-12-12 19:02:19.600";
	private static final String LINE_NOTICE_INFO_STATEDATE = "2014-12-09 19:02:19.600";
	private static final String LINE_NOTICE_INFO_CONTENT = "한줄공지 TDD 입니다.";

	@Autowired
	private SettingService settingService;
	List<ActivityScoreEntity> activityList;
	ActivityScoreEntity activityScoreEntity;
	List<GradeScoreEntity> gradeList;
	GradeScoreEntity gradeScoreEntity;
	LineNoticeEntity lineNoticeList;
	ResponseEntity result;

	@Before
	public void before() throws Exception {
		activityList = new ArrayList<ActivityScoreEntity>();
		result = new ResponseEntity();
		activityScoreEntity = new ActivityScoreEntity();
		gradeScoreEntity = new GradeScoreEntity();
		gradeList = new ArrayList<GradeScoreEntity>();
		lineNoticeList = new LineNoticeEntity();
	}

	/**
	 * 다즐설정 - 활동점수 목록
	 * case : 정상적인 정보를 불러왔을때
	 * @throws Exception
	 */
	@Test
	public void testSelectActivityscore()throws Exception{
		activityList = settingService.selectActivityscore();
		assertThat(activityList, is(notNullValue()));
	}

	/**
	 * 다즐설정 - 활동점수 설정
	 * case : 정상적인 정보를 저장 했을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateActivityscoreCorrect() throws Exception{
		activityScoreList();
		result = settingService.updateActivityscore(activityList);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 다즐설정 - 활동점수 설정
	 * case : 값을 넘기지 않았을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateActivityscore() {
		try {
			result = settingService.updateActivityscore(activityScoreEntity.getActivityScoreList());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {e.printStackTrace();}
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 다즐설정 - 회원등급 목록
	 * case : 값을 가지고 오지 않을때
	 * @throws Exception
	 */
	@Ignore @Test
	public void testSelectGradescoreEmpty() throws Exception {
		gradeList = settingService.selectGradescore();
		assertEquals(gradeList.size(), 0);
	}

	/**
	 * 다즐설정 - 회원등급 목록
	 * case : 정상적으로 값을 가지고 올때
	 * @throws Exception
	 */
	@Test
	public void testSelectGradescoreCorrect() throws Exception {
		gradeList = settingService.selectGradescore();
		assertThat(gradeList, is(notNullValue()));
	}

	/**
	 * 다즐설정 - 회원등급 수정
	 * case : 정상적으로 값을 넘겨줄때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateGradescoreCorrect() throws Exception {
		gradeScoreList();
		result = settingService.updateGradescore(gradeList);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	/**
	 * 다즐설정 - 회원등급 수정
	 * case : 값을 넘겨주지않을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateGradescoreEmpty(){
		try {
			result = settingService.updateGradescore(gradeScoreEntity.getGradeScoreList());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {e.printStackTrace();}
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 한줄공지 목록
	 * case : 정상적인 정보를 불러왔을때
	 * @throws Exception
	 */
	@Test
	public void testSelectLineNoticeCorrect() throws Exception {
		lineNoticeList = settingService.selectLineNoticeList(lineNoticeList);
		assertThat(lineNoticeList, is(notNullValue()));
	}

	/**
	 * 한줄공지 목록
	 * case : 값을 가지고오지 않았을때
	 * @throws Exception
	 */
	@Ignore @Test
	public void testSelectLineNoticeEmpty() throws Exception {
		lineNoticeList = settingService.selectLineNoticeList(lineNoticeList);
		assertThat(lineNoticeList, is(nullValue()));
	}


	/**
	 * 한줄공지 저장
	 * case : 정상적인 값을 넘겨줄때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testInsertLineNoticeCorrect() throws Exception {
		lineNoticeInfo();
		result = settingService.insertLineNotice(lineNoticeList);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));

	}

	/**
	 * 한줄공지 저장
	 * case : 값을 넘겨주지않을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testInsertLineNoticeEmpty() throws Exception {
		result = settingService.insertLineNotice(lineNoticeList);
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 활동점수 테스트값 세팅
	 * @throws Exception
	 */
	private void activityScoreList() throws Exception {
		activityList = settingService.selectActivityscore();
		int userScore[] = {1,3,1,3,3,10,0,1,2};
		int groupScore[] = {1,2,1,0,0,0,100,0,1};
		for (int i = 0; i < activityList.size(); i++) {
			activityList.get(i).setUserScore(userScore[i]);
			activityList.get(i).setGroupScore(groupScore[i]);
		}
	}

	/**
	 * 한줄공지 테스트값 세팅
	 */
	private void lineNoticeInfo() {
		lineNoticeList.setLineNoticeContents(LINE_NOTICE_INFO_CONTENT);
		lineNoticeList.setStartDate(LINE_NOTICE_INFO_STATEDATE);
		lineNoticeList.setEndDate(LINE_NOTICE_INFO_ENDDATE);
	}

	/**
	 * 회원등급 테스트값 세팅
	 * @throws Exception
	 */
	private void gradeScoreList() throws Exception {
		gradeList = settingService.selectGradescore();
		int userLowScore[] = {0,50,200,500,1000,3000,5000};
		int userHighScore[] = {49,199,499,999,2999,4999,999999};
		int groupLowScore[] = {100,200,500,1000,2000,5000,10000};
		int groupHighScore[] = {199,499,999,1999,4999,9999,999999};
		for (int i = 0; i < gradeList.size(); i++) {
			gradeList.get(i).setUserLowScore(userLowScore[i]);
			gradeList.get(i).setUserHighScore(userHighScore[i]);
			gradeList.get(i).setGroupLowScore(groupLowScore[i]);
			gradeList.get(i).setGroupHighScore(groupHighScore[i]);
		}
	}

}
