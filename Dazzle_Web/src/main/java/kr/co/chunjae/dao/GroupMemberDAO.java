package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;

public interface GroupMemberDAO {
	/**
	 * 그룹멤버 목록 카운트
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	long selectGroupMemberListCount(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 그룹멤버 목록
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupMemberEntity> selectGroupMemberList(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 내그룹원 목록
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupMemberEntity> selectMyGroupMemberList(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 그룹멤버 가입상태 변경
	 * @param groupMemberEntity
	 * @throws Exception
	 */
	void updateGroupMemberJoinState(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹멤버 공동리더 수정
	 * @param groupMemberEntity
	 * @throws Exception
	 */
	void updateMyGroupMember(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹멤버 리더 변경
	 * @param groupMemberEntity
	 * @throws Exception
	 */
	int updateMemberLeader(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹멤버 리더 일반변경
	 * @param groupMemberEntity
	 * @throws Exception
	 */
	int updateMemberGeneral(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹리더 등록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	int insertGroupLeader(GroupEntity groupEntity)throws Exception;

	/**
	 * 그룹탈퇴
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	void groupWidthDraw(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 내그룹원 목록 찾기
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupMemberEntity> searchGroupMemberList(GroupMemberEntity groupMemberEntity) throws Exception;

	/**
	 * 그룹원 상세.
	 * @param groupMemberEntity (userKey, groupKey)
	 * @return groupMemberEntity
	 */
	GroupMemberEntity selectGroupMember(GroupMemberEntity groupMemberEntity);

	int changePushState(GroupMemberEntity groupMemberEntity);

	/**
	 * 그룹원 인원수
	 * @param groupMemberEntity (groupKey, memberStateCode)
	 * @return GroupMemberEntity
	 * @throws Exception
	 */
	int selectGroupMemberJoinListCount(GroupMemberEntity groupMemberEntity);
}