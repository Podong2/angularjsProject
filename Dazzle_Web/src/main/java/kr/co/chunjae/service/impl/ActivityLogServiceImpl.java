package kr.co.chunjae.service.impl;

import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.dao.ActivityLogDAO;
import kr.co.chunjae.dao.ActivityScoreDAO;
import kr.co.chunjae.dao.GroupDAO;
import kr.co.chunjae.dao.ScrapDAO;
import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.entities.question.ScrapEntity;
import kr.co.chunjae.entities.user.ActivityLogEntity;
import kr.co.chunjae.entities.user.ActivityScoreEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.GroupService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ActivityLogServiceImpl implements ActivityLogService {
	@Autowired
	private ActivityScoreDAO activityScoreDAO;
	@Autowired
	private ActivityLogDAO activityLogDAO;
	@Autowired
	private GroupService groupService;
	@Autowired
	private ScrapDAO scrapDAO;
	@Autowired
	private GroupDAO groupDAO;

	/**
	 * 활동로그 등록 - 관리자 활동점수 부여
	 * @param activityTypeCode
	 * @param userKey
	 * @param activityScore
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void insertActivityLogByGiveAdmin(String activityTypeCode, long userKey, int activityScore) throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(activityTypeCode);
		activityLogInfo.setUserKey(userKey);
		// 활동 로그 문구 세팅
		String activityWords = setActivityLogMessage(activityTypeCode, null, null, activityScore);

		// 총활동점수 누적
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityScore;
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityScore;
		}
		activityLogInfo.setActivityScore(activityScore);
		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);
		activityLogDAO.insertActivityLog(activityLogInfo);
	}

	/**
	 * 활동로그 등록 - 답변 채택
	 * @param activityTypeCode
	 * @param chooseUserName
	 * @param userKey
	 * @param questionKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void insertActivityLogByChooseAnswer(String activityTypeCode, String chooseUserName, long userKey, long questionKey) throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(activityTypeCode);
		activityLogInfo.setUserKey(userKey);
		activityLogInfo.setCommonKey(questionKey);

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(activityTypeCode);
		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(activityTypeCode, chooseUserName, null, scoreInfo.getUserScore());

		// 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getUserScore());			// 부여 대상 - 개인
		// 총활동점수 누적
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityLogInfo.getActivityScore();
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityLogInfo.getActivityScore();
		}
		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);
		activityLogDAO.insertActivityLog(activityLogInfo);
	}

	/**
	 * 활동로그 등록(개인/그룹) - 문제 등록(일 최대 3점 누적) / 답글 등록(문제등록 후 1주일 이내 등록시)
	 * @param activityTypeCode
	 * @param userKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertActivityLogByInsertQnA(String activityTypeCode, long userKey, String name, long questionKey, long groupKey) throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(activityTypeCode);
		activityLogInfo.setUserKey(userKey);
		activityLogInfo.setCommonKey(questionKey);

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(activityTypeCode);
		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(activityTypeCode, name, null, 0);

		// 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getUserScore());			// 부여 대상 - 개인
		activityLogInfo.setGroupActivityScore(scoreInfo.getGroupScore());	// 부여 대상 - 개인이 속한 모든 그룹

		// 총활동점수 누적
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityLogInfo.getActivityScore();
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityLogInfo.getActivityScore();
		}
		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);
		// 1. 개인 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);

		// 2. 그룹 활동점수 부여(속한 그룹)
		if(groupKey > 0){
			ActivityLogEntity groupActivityLogInfo = new ActivityLogEntity();


			groupActivityLogInfo.setGroupKey(groupKey);
			groupActivityLogInfo.setActivityWords(activityWords);
			groupActivityLogInfo.setActivityTypeCode(activityTypeCode);
			groupActivityLogInfo.setActivityScore(activityLogInfo.getGroupActivityScore());

			//총 활동점수 누적ScrapDAO
			ActivityLogEntity recentGroupActivityLog = activityLogDAO.selectActivityLog(groupActivityLogInfo);
			int totalGroupActivityScore = activityLogInfo.getActivityScore();
			if (recentGroupActivityLog != null) {
				totalGroupActivityScore = recentGroupActivityLog.getTotalActivityScore() + activityLogInfo.getGroupActivityScore();
			}
			groupActivityLogInfo.setTotalActivityScore(totalGroupActivityScore);
			activityLogDAO.insertActivityLog(groupActivityLogInfo);
		}

		//스크랩 된 여러그룹에 활동점수 부여
		List<GroupEntity> groupList = groupDAO.selectScrapMyGroupList(activityLogInfo);

		for (int i=0; i<groupList.size(); i++) {
			ActivityLogEntity groupActivityLogInfo = new ActivityLogEntity();


			groupActivityLogInfo.setGroupKey(groupList.get(i).getGroupKey());
			groupActivityLogInfo.setActivityWords(activityWords);
			groupActivityLogInfo.setActivityTypeCode(activityTypeCode);
			groupActivityLogInfo.setActivityScore(activityLogInfo.getGroupActivityScore());

			//총 활동점수 누적ScrapDAO
			ActivityLogEntity recentGroupActivityLog = activityLogDAO.selectActivityLog(groupActivityLogInfo);
			int totalGroupActivityScore = activityLogInfo.getActivityScore();
			if (recentGroupActivityLog != null) {
				totalGroupActivityScore = recentGroupActivityLog.getTotalActivityScore() + activityLogInfo.getGroupActivityScore();
			}
			groupActivityLogInfo.setTotalActivityScore(totalGroupActivityScore);
			activityLogDAO.insertActivityLog(groupActivityLogInfo);
		}
	}


	/**
	 * 활동로그 등록(개인/그룹) - 문제/답글 좋아요(붙인자, 받은자 일 최대 3점 누적)
	 * @param activityTypeCode
	 * @param userKey
	 * @param receiveUserKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertActivityLogByLike(String activityTypeCode, long userKey
				, long groupKey, long questionKey, String name) throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(activityTypeCode);
		activityLogInfo.setUserKey(userKey);
		activityLogInfo.setCommonKey(questionKey);

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(CommonCode.ACTIVITY_LOG_TYPE_LIKE_QUESTION);
		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(activityTypeCode, name, null, 0);
		// 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getUserScore());			// 부여 대상 - 개인
		activityLogInfo.setGroupActivityScore(scoreInfo.getGroupScore());	// 부여 대상 - 개인이 속한 모든 그룹

		// 총활동점수 누적
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityLogInfo.getActivityScore();
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityLogInfo.getActivityScore();
		}
		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);

		// 좋아요 붙인자, 받은자 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);


	}

	/**
	 * 활동로그 등록(개인) - App 최초가입
	 * @param activityTypeCode
	 * @param userKey
	 * @param receiveUserKey
	 * @throws Exception
	 */
	@Override
	public void insertActivityLogByJoinApp(UserEntity userInfo)
			throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(CommonCode.STATISTICS_ACTIVITY_TYPE_JOIN_APP);
		activityLogInfo.setUserKey(userInfo.getUserKey());

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(CommonCode.STATISTICS_ACTIVITY_TYPE_JOIN_APP);
		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(CommonCode.ACTIVITY_LOG_TYPE_JOIN_APP, userInfo.getName(), null, 0);
		// 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getUserScore()); // 부여 대상 - 개인
		int totalActivityScore = activityLogInfo.getActivityScore();

		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);

		// 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);

	}

	/**
	 * 활동로그 등록(그룹) - 그룹생성
	 * @param groupEntity
	 * @throws Exception
	 */
	@Override
	public void insertActivityLogByCreateGroup(GroupEntity groupEntity)
			throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(CommonCode.STATISTICS_ACTIVITY_TYPE_CREATE_GROUP);

		activityLogInfo.setGroupKey(groupEntity.getGroupKey());

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(CommonCode.STATISTICS_ACTIVITY_TYPE_CREATE_GROUP);
		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(CommonCode.ACTIVITY_LOG_TYPE_CREATE_GROUP, groupEntity.getLeaderName(), groupEntity.getGroupName(), 0);
		// 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getGroupScore()); // 부여 대상 - 그룹
		int totalActivityScore = activityLogInfo.getActivityScore();

		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);

		// 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);

	}

	/**
	 * 활동로그 등록(그룹) - 그룹가입
	 * @param groupEntity
	 * @throws Exception
	 */
	@Override
	public void insertActivityLogByJoinGroup(GroupMemberEntity groupMemberEntity)
			throws Exception {
		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(CommonCode.STATISTICS_ACTIVITY_TYPE_JOIN_MEMBER);

		activityLogInfo.setGroupKey(groupMemberEntity.getGroupKey());

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(CommonCode.STATISTICS_ACTIVITY_TYPE_JOIN_MEMBER);

		// 총 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getGroupScore()); // 부여 대상 - 그룹
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityLogInfo.getActivityScore();
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityLogInfo.getActivityScore();
		}
		// 활동점수 세팅
		activityLogInfo.setTotalActivityScore(totalActivityScore);

		// 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);

	}

	/**
	 * 활동로그 등록(개인) - 다Q문제 최초 그룹 스크랩
	 * @param scrapEntity
	 * @throws Exception
	 */
	@Override
	public void insertActivityLogByFirstGroupScrap(ScrapEntity scrapEntity) throws Exception {

		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(CommonCode.STATISTICS_ACTIVITY_TYPE_SCRAP_DAQ);

		activityLogInfo.setUserKey(scrapEntity.getUserKey());
		activityLogInfo.setCommonKey(scrapEntity.getScrapBoardKey());

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(CommonCode.STATISTICS_ACTIVITY_TYPE_SCRAP_DAQ);

		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(CommonCode.ACTIVITY_LOG_TYPE_SCRAP_DAQ, null, scrapEntity.getGroupName(), 0);

		// 총 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getUserScore()); // 부여 대상 - 개인
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityLogInfo.getActivityScore();
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityLogInfo.getActivityScore();
		}
		// 활동점수 세팅
		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);

		// 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);

	}

	/**
	 * 활동로그 등록 (개인) - 그룹 초대
	 * @param userKey
	 * @param groupKey
	 * @throws Exception
	 */
	@Override
	public void insertActivityLogByInviteGroup(long userKey, long groupKey) throws Exception {
		ActivityLogEntity activityLogInfo = new ActivityLogEntity();
		activityLogInfo.setActivityTypeCode(CommonCode.STATISTICS_ACTIVITY_TYPE_INVITE_MEMBER);

		activityLogInfo.setUserKey(userKey);
		activityLogInfo.setCommonKey(groupKey);

		// 활동 점수 가져오기
		ActivityScoreEntity scoreInfo = activityScoreDAO.selectActivityScore(CommonCode.STATISTICS_ACTIVITY_TYPE_INVITE_MEMBER);

		// 활동 로그 세팅
		String activityWords = setActivityLogMessage(CommonCode.ACTIVITY_LOG_TYPE_INVITE_MEMBER, null, null, 0);

		// 총 활동점수 세팅
		activityLogInfo.setActivityScore(scoreInfo.getUserScore()); // 부여 대상 - 개인
		ActivityLogEntity recentActivityLog = activityLogDAO.selectActivityLog(activityLogInfo);
		int totalActivityScore = activityLogInfo.getActivityScore();
		if (recentActivityLog != null) {
			totalActivityScore = recentActivityLog.getTotalActivityScore() + activityLogInfo.getActivityScore();
		}
		// 활동점수 세팅
		activityLogInfo.setTotalActivityScore(totalActivityScore);
		activityLogInfo.setActivityWords(activityWords);

		// 활동점수 부여
		activityLogDAO.insertActivityLog(activityLogInfo);

	}


	/**
	 * 활동로그 메세지 세팅
	 * @param activityTypeCode
	 * @param name
	 * @param groupName
	 * @return
	 */
	private String setActivityLogMessage(String activityTypeCode, String name, String groupName, int activityScore) {

		String message = "";
		// 문제등록
		if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_REGIST_QUESTION)) {
			message = "문제를 등록했습니다.";
		}
		// 답글등록
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_REGIST_ANSWER)) {
			message = name + "님 문제에 답글을 등록했습니다.";
		}
		// 좋아요
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_LIKE_QUESTION)) {
			message = name + "님 문제 좋아요!";
		}
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_LIKE_ANSWER)) {
			message = name + "님 답글 좋아요!";
		}
		// 답글채택
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_CHOOSE_ANSWER)) {
			message = name + "님이 답글을 채택하였습니다.";
		}
		// 다Q 최초 그룹이동
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_SCRAP_DAQ)) {
			message = "다Q 문제를 " + groupName + "그룹으로 스크랩 하였습니다.";
		}
		// 회원가입
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_JOIN_APP)) {
			message = name + "님 회원가입을 환영합니다.";
		}
		// 그룹생성
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_CREATE_GROUP)) {
			message = groupName + "그룹을 생성하였습니다.";
		}
		// 멤버초대
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_INVITE_MEMBER)) {
			message = "그룹으로 초대하였습니다.";
		}
		// 멤버가입
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_INVITE_MEMBER)) {
			message = name + "님이 " + groupName + "그룹으로 가입하였습니다.";
		}
		// 관리자 부여
		else if (StringUtils.equals(activityTypeCode, CommonCode.ACTIVITY_LOG_TYPE_GIVE_ADMIN)) {
			message = "관리자님이 " + activityScore + "점을 부여하였습니다.";
		}

		return message;
	}

}