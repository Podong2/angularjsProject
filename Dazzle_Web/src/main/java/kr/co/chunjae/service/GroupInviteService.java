package kr.co.chunjae.service;

import kr.co.chunjae.entities.group.GroupInviteEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;

/**
 * Created by jm1218 on 15. 1. 14.
 */
public interface GroupInviteService {
	GroupInviteEntity getGroupInvite(String inviteCode) throws Exception;
	//groupKey(long), inviteFrom(long), inviteType(string)
	GroupInviteEntity getActivatedInviteCode(GroupInviteEntity groupInviteEntity) throws Exception;
	//groupKey(long), inviteFrom(long), inviteType(string)
	String makeInviteCode(GroupInviteEntity groupInviteEntity) throws Exception;
	//inviteCode(string), userKey(long), memberStateCode(string)
	GroupInviteEntity groupJoinByInviteCode(String inviteCode, GroupMemberEntity groupMemberEntity) throws Exception;
}
