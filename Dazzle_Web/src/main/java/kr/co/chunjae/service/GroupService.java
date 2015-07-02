package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupListEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;

public interface GroupService {
	/**
	 * 그룹 목록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	public GroupEntity selectGroupList(GroupEntity groupEntity) throws Exception;

	/**
	 * 내그룹 목록
	 * @param userKey
	 * @param scrapBoardKey
	 * @return
	 * @throws Exception
	 */
	public List<GroupEntity> selectGroupListByUserKey(long userKey, long scrapBoardKey) throws Exception;

	/**
	 * 그룹 정보
	 * @param groupKey
	 * @return
	 * @throws Exception
	 */
	public GroupEntity selectGroup(long groupKey) throws Exception;

	/**
	 * 그룹 삭제
	 * @param groupKey
	 * @throws Exception
	 */
	public void deleteGroup(long groupKey) throws Exception;

	/**
	 * 그룹 목록
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	public List<GroupEntity> selectMyGroupList(long userKey, int startRow, int rowCount) throws Exception;

	/**
	 * 내그룹 가입승인대기 취소하기
	 * @param groupListEntity
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity updateGroupJoinCencle(GroupEntity groupEntity)throws Exception;

	/**
	 * 그룹 문제 목록
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	public GroupListEntity selectMyGroupQuestionList(long groupKey, int startRow, int rowCount) throws Exception;

	/**
	 * 그룹 멤버 목록
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	public GroupListEntity selectMyGroupMemberList(long groupKey, int startRow, int rowCount) throws Exception;


	/**
	 * 그룹찾기
	 * @param serarchValue
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	public List<GroupEntity> searchGroupList(String searchValue, int startRow, int rowCount)throws Exception;

	/**
	 * 그룹커버 이미지 목록
	 * @return
	 * @throws Exception
	 */
	List<AttachEntity> selectTemplateImage()throws Exception;

	/**
	 * 그룹만들기
	 * @param groupEntity
	 * @throws Exception
	 */
	GroupListEntity insertGroup(GroupEntity groupEntity)throws Exception;

	/**
	 * 그룹 정보 조회
	 * @param groupKey
	 * @return
	 * @throws Exception
	 */
	GroupEntity selectGroupDetail(long groupKey)throws Exception;

	/**
	 * 그룹멤버 조회하여 가입 유무 확인
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	GroupMemberEntity groupMemberSearchResult(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹 가입하기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	ResponseEntity insertGroupJoin(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹 탈퇴하기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	void groupWidthDraw(GroupMemberEntity groupMemberEntity)throws Exception;

	/**
	 * 그룹 멤버 목록 찾기
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public GroupListEntity searchMyGroupMemberList(long groupKey, int startRow, int rowCount, String searchValue) throws Exception;

	/**
	 *  그룹정보 수정
	 * @param groupEntity
	 * @throws Exception
	 */
	void updateGroup(GroupEntity groupEntity)throws Exception;
}