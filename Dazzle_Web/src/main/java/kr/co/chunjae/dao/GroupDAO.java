package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.entities.user.ActivityLogEntity;

public interface GroupDAO {
	/**
	 * 그룹 목록 카운트
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	long selectGroupListCount(GroupEntity groupEntity) throws Exception;

	/**
	 * 그룹 목록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupEntity> selectGroupList(GroupEntity groupEntity) throws Exception;

	/**
	 * 내그룹 목록
	 * @param groupMemeberEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupEntity> selectGroupListByUserKey(GroupMemberEntity groupMemeberEntity) throws Exception;

	/**
	 * 그룹 정보
	 * @param groupKey
	 * @return
	 * @throws Exception
	 */
	GroupEntity selectGroup(long groupKey) throws Exception;

	/**
	 * 그룹 삭제
	 * @param groupKey
	 * @throws Exception
	 */
	void deleteGroup(long groupKey) throws Exception;

	/**
	 * 내그룹 목록
	 * @param groupMemeberEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupEntity> selectMyGroupList(GroupMemberEntity groupMemeberEntity) throws Exception;

	/**
	 * 내그룹 승인대기 목록 가입 취소하기
	 * @param groupEntity
	 * @throws Exception
	 */
	void updateGroupJoinCencle(GroupEntity groupEntity)throws Exception;

	/**
	 * 그룹 찾기
	 * @param groupMemeberEntity
	 * @return
	 * @throws Exception
	 */
	List<GroupEntity> searchGroupList(GroupMemberEntity groupMemeberEntity)throws Exception;

	/**
	 * 그룹만들기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	int insertGroup(GroupEntity groupEntity)throws Exception;

	/**
	 * 그룹만들기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	int insertGroupJoin(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 탈퇴그룹 재가입
	 * @param groupEntity
	 * @throws Exception
	 */
	void updateJoinGroupMember(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹정보 수정
	 * @param groupEntity
	 * @throws Exception
	 */
	void updateGroup(GroupEntity groupEntity)throws Exception;

	/**
	 * 스크랩 되어있는 내 그룹 목록 조회
	 * @param userKey
	 * @param scrapBoardKey
	 * @return
	 * @throws Exception
	 */
	List<GroupEntity> selectScrapMyGroupList(ActivityLogEntity activityLogInfo)throws Exception;
}