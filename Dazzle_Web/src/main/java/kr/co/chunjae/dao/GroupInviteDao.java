package kr.co.chunjae.dao;

import kr.co.chunjae.entities.group.GroupInviteEntity;

/**
 * Created by jm1218 on 15. 1. 14.
 */
public interface GroupInviteDao {
	int insertGroupInvite(GroupInviteEntity groupInviteEntity) throws Exception;
	int checkDuplicateInviteCode(String inviteCode) throws Exception;
	GroupInviteEntity getGroupInvite(String inviteCode) throws Exception;
	GroupInviteEntity getActivatedInviteCode(GroupInviteEntity groupInviteEntity) throws Exception;
//	GroupInviteEntity selectNotExpiredCode(GroupInviteEntity groupInviteEntity);
}
