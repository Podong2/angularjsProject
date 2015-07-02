package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupListEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;

public interface GroupMemberService {
	/**
	 * 그룹 멤버 리스트
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	public GroupMemberEntity selectGroupMemberList(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 그룹멤버 가입상태 변경
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	public GroupListEntity updateGroupMemberJoinState(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹 멤버 목록(모바일 그룹 세팅)
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	public List<GroupMemberEntity> selectMyGroupMemberList(long groupKey, int startRow, int rowCount) throws Exception;

	/**
	 * 그룹 멤버 공동리더 수정
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	public void updateMyGroupMember(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 그룹 멤버 리더 변경
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	public void updateMemberLeader(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 그룹장 등록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	GroupListEntity insertGroupLeader(GroupEntity groupEntity)throws Exception;

	/**
	 * 그룹원 상세.
	 * @param groupMemberEntity (userKey, groupKey)
	 * @return groupMemberEntity
	 */
	GroupMemberEntity selectGroupMember(GroupMemberEntity groupMemberEntity);

	/**
	 * 그룹 푸시 상태 변경.
	 * @param groupMemberEntity
	 * @return
	 */
	int changePushState(GroupMemberEntity groupMemberEntity);

	/**
	 * 그룹원 인원수
	 * @param groupMemberEntity (groupKey, memberStateCode)
	 * @return GroupMemberEntity
	 * @throws Exception
	 */
	GroupMemberEntity selectGroupMemberJoinListCount(GroupMemberEntity groupMemberEntity);
}
