package kr.co.chunjae.service.impl;

import java.util.Calendar;
import java.util.Date;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.ActivityLogDAO;
import kr.co.chunjae.dao.GroupDAO;
import kr.co.chunjae.dao.GroupInviteDao;
import kr.co.chunjae.dao.GroupMemberDAO;
import kr.co.chunjae.entities.group.GroupInviteEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.entities.user.ActivityLogEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.GroupInviteService;
import kr.co.chunjae.service.GroupService;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jm1218 on 15. 1. 14.
 */
@Service
public class GroupInviteServiceImpl implements GroupInviteService{

	@Autowired
	private GroupInviteDao groupInviteDao;
	@Autowired
	private GroupMemberDAO groupMemberDAO;
	@Autowired
	private GroupDAO groupDAO;
	@Autowired
	private GroupService groupService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private ActivityLogDAO activityLogDAO;

	private boolean checkDuplicateInviteCode(String inviteCode) throws Exception {
		return groupInviteDao.checkDuplicateInviteCode(inviteCode) > 0;
	}
	private void setExpiredDate(GroupInviteEntity groupInviteEntity) throws Exception{
		Calendar cal = Calendar.getInstance();
		if (StringUtils.equals(groupInviteEntity.getInviteType(), CommonCode.GroupInviteType.INVITE_TO_LINK)) {
			cal.add(Calendar.DATE, 30);
		} else if (StringUtils.equals(groupInviteEntity.getInviteType(), CommonCode.GroupInviteType.INVITE_TO_CODE)) {
			cal.add(Calendar.HOUR, 24);
		}
		groupInviteEntity.setExpireDate(new Date(cal.getTimeInMillis()));
	}

	@Override
	public GroupInviteEntity getGroupInvite(String inviteCode) throws Exception {
		return groupInviteDao.getGroupInvite(inviteCode);
	}

	@Override
	public GroupInviteEntity getActivatedInviteCode(GroupInviteEntity groupInviteEntity) throws Exception {
		return groupInviteDao.getActivatedInviteCode(groupInviteEntity);
	}

	@Override
	public String makeInviteCode(GroupInviteEntity groupInviteEntity) throws Exception {

		String inviteCode = "";
		if (StringUtils.equals(groupInviteEntity.getInviteType(), CommonCode.GroupInviteType.INVITE_TO_LINK)) {
			inviteCode = RandomStringUtils.randomAlphanumeric(8);
			while (checkDuplicateInviteCode(inviteCode)) {
				inviteCode = RandomStringUtils.randomAlphanumeric(8);
			}

			// 활동점수 검색값 세팅
			ActivityLogEntity activityLogInfo = new ActivityLogEntity();
			activityLogInfo.setActivityTypeCode(CommonCode.STATISTICS_ACTIVITY_TYPE_INVITE_MEMBER);

			activityLogInfo.setUserKey(groupInviteEntity.getInviteFrom());

			//좋아요 부여자 활동점수 추가 (일 최대 누적 3점)
			int SendTodayActivityScore = activityLogDAO.selectTodayActivityScore(activityLogInfo);
			// 일 최대 3점 누적
			if (SendTodayActivityScore < CommonCode.MAX_ACTIVITY_SCORE_LIKE) {
				// 그룹초대 활동점수 부여
				activityLogService.insertActivityLogByInviteGroup(groupInviteEntity.getInviteFrom(), groupInviteEntity.getGroupKey());
			}

		} else if (StringUtils.equals(groupInviteEntity.getInviteType(), CommonCode.GroupInviteType.INVITE_TO_CODE)) {
			inviteCode = RandomStringUtils.randomNumeric(4) + RandomStringUtils.randomAlphabetic(6);
			while (checkDuplicateInviteCode(inviteCode)) {
				inviteCode = RandomStringUtils.randomNumeric(4) + RandomStringUtils.randomAlphabetic(6);
			}
		}


		groupInviteEntity.setInviteCode(inviteCode);
		setExpiredDate(groupInviteEntity);
		groupInviteDao.insertGroupInvite(groupInviteEntity);

		return inviteCode;
	}

	@Override
	public GroupInviteEntity groupJoinByInviteCode(String inviteCode, GroupMemberEntity groupMemberEntity) throws Exception{
		GroupInviteEntity groupInviteEntity = getGroupInvite(inviteCode);
		String result = checkInviteCode(groupInviteEntity);

		if (StringUtils.equals(result, ResultCode.GroupJoinResult.VALID_INVITE_CODE)) {
			groupMemberEntity.setGroupKey(groupInviteEntity.getGroupKey());
			GroupMemberEntity memberState = groupMemberDAO.selectGroupMember(groupMemberEntity);

			if (memberState == null) {
				groupDAO.insertGroupJoin(groupMemberEntity);
				activityLogService.insertActivityLogByJoinGroup(groupMemberEntity);
				groupInviteEntity.setResultCode(ResultCode.GroupJoinResult.ENTRY_SUCCESS);
				return groupInviteEntity;
			} else {

				if (memberState.getDeleteDate() == null) {
					groupInviteEntity.setResultCode(ResultCode.GroupJoinResult.ALREADY_MEMBER);
					return groupInviteEntity;
				} else {
					groupDAO.updateJoinGroupMember(groupMemberEntity);
					activityLogService.insertActivityLogByJoinGroup(groupMemberEntity);
					groupInviteEntity.setResultCode(ResultCode.GroupJoinResult.ENTRY_SUCCESS);
					return groupInviteEntity;
				}
			}

		} else {
			return groupInviteEntity;
		}
	}

	private String checkInviteCode(GroupInviteEntity groupInviteEntity) throws Exception{

		if (groupInviteEntity == null) {
			return ResultCode.GroupJoinResult.INVALID_INVITE_CODE;
		} else if (groupInviteEntity.getExpireDate().compareTo(new Date()) < 0) {
			return ResultCode.GroupJoinResult.EXPIRED_INVITE_CODE;
		} else {
			return ResultCode.GroupJoinResult.VALID_INVITE_CODE;
		}
	}
}
