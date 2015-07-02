package kr.co.chunjae.service;

import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.entities.question.ScrapEntity;
import kr.co.chunjae.entities.user.UserEntity;

public interface ActivityLogService {
	/**
	 * 활동로그 등록 - 관리자 활동점수 부여
	 * @param activityTypeCode
	 * @param userKey
	 * @param activityScore
	 * @throws Exception
	 */
	public void insertActivityLogByGiveAdmin(String activityTypeCode, long userKey, int activityScore) throws Exception;

	/**
	 * 활동로그 등록 - 답변 채택
	 * @param activityTypeCode
	 * @param chooseUserName
	 * @param userKey
	 * @param questionKey
	 * @throws Exception
	 */
	public void insertActivityLogByChooseAnswer(String activityTypeCode, String chooseUserName, long userKey, long questionKey) throws Exception;

	/**
	 * 활동로그 등록(개인/그룹) - 문제 등록 / 답글 등록
	 * @param activityTypeCode
	 * @param userKey
	 * @throws Exception
	 */
	public void insertActivityLogByInsertQnA(String activityTypeCode, long userKey, String name, long questionKey, long groupKey) throws Exception;

	/**
	 * 활동로그 등록(개인/그룹) - 좋아요
	 * @param activityTypeCode
	 * @param userKey
	 * @param receiveUserKey
	 * @throws Exception
	 */
	public void insertActivityLogByLike(String activityTypeCode, long userKey, long groupKey, long questionKey, String name) throws Exception;

	/**
	 * 활동로그 등록(개인) - App최초가입
	 * @param userInfo
	 * @throws Exception
	 */
	public void insertActivityLogByJoinApp(UserEntity userInfo)throws Exception;

	/**
	 * 활동로그 등록(그룹) - 그룹생성
	 * @param groupEntity
	 * @throws Exception
	 */
	public void insertActivityLogByCreateGroup(GroupEntity groupEntity)throws Exception;

	/**
	 * 활동로그 등록(그룹) - 그룹가입
	 * @param groupEntity
	 * @throws Exception
	 */
	public void insertActivityLogByJoinGroup(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 활동로그 등록(개인) - 다Q문제 최초 그룹 스크랩
	 * @throws Exception
	 */
	public void insertActivityLogByFirstGroupScrap(ScrapEntity scrapEntity)throws Exception;

	/**
	 * 활동로그 등록(개인) - 그룹초대
	 * @param userKey
	 * @throws Exception
	 */
	public void insertActivityLogByInviteGroup(long userKey, long groupKey)throws Exception;

}