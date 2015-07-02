package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.GroupMemberDAO;
import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupListEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.GroupMemberService;
import kr.co.digigroove.commons.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GroupMemberServiceImpl implements GroupMemberService {
	@Autowired
	private GroupMemberDAO groupMemberDAO;

	@Autowired
	private GradeScoreDAO gradeScoreDAO;

    @Autowired
    private ActivityLogService activityLogService;
	/**
	 * 그룹 멤버 리스트
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupMemberEntity selectGroupMemberList(GroupMemberEntity groupMemberEntity) throws Exception {

		long groupMemberListCount = groupMemberDAO.selectGroupMemberListCount(groupMemberEntity);
		groupMemberEntity.setDataSize(groupMemberListCount);

		if (groupMemberListCount > 0) {
			List<GroupMemberEntity> groupMemberList = groupMemberDAO.selectGroupMemberList(groupMemberEntity);
			for (int i=0; i<groupMemberList.size(); i++) {
				String regex = "(\\d{3})(\\d{3})(\\d+)";
				if(!StringUtils.isEmpty(groupMemberList.get(i).getPhoneNumber())
						&& groupMemberList.get(i).getPhoneNumber().length() > 10) {
					regex = "(\\d{3})(\\d{4})(\\d+)";
					groupMemberList.get(i).setPhoneNumber(groupMemberList.get(i).getPhoneNumber().replaceFirst(regex, "$1-$2-$3"));
				}

				// 그룹 멤버 레벨 설정
				String groupLevelClassPrefix = CommonCode.LEVEL_ICON_PREFIX_PERSONAL;
				List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
				for (int g=0; g < gradeScoreList.size(); g++) {
					if (gradeScoreList.get(g).getGroupLowScore() <= groupMemberList.get(i).getActivityScore()
							&& gradeScoreList.get(g).getGroupHighScore() >= groupMemberList.get(i).getActivityScore()) {

						groupMemberList.get(i).setUserRating(String.format("%02d", g+1));
						groupMemberList.get(i).setUserRatingClassName(groupLevelClassPrefix.concat(groupMemberList.get(i).getUserRating()));
					}
				}
			}
			groupMemberEntity.setGroupMemberList(groupMemberList);
		}
		return groupMemberEntity;
	}

	/**
	 * 그룹멤버 가입상태 변경
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupListEntity updateGroupMemberJoinState(
			GroupMemberEntity groupMemberEntity) throws Exception {
		GroupListEntity groupListEntity = new GroupListEntity();

		groupMemberDAO.updateGroupMemberJoinState(groupMemberEntity);
		System.out.println(groupMemberEntity.getMemberStateCode());
		if(groupMemberEntity.getMemberStateCode().equals(CommonCode.GROUP_MEM_STATE_JOIN)){
			activityLogService.insertActivityLogByJoinGroup(groupMemberEntity);
		}

		return groupListEntity;
	}

	/**
	 * 그룹 멤버 목록(모바일 그룹 세팅)
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GroupMemberEntity> selectMyGroupMemberList(long groupKey, int startRow, int rowCount)throws Exception {
		List<GroupMemberEntity> groupMemberList = new ArrayList<GroupMemberEntity>();
		GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
		groupMemberEntity.setGroupKey(groupKey);
		groupMemberEntity.setCurrentPage(startRow);
		groupMemberEntity.setPageSize(rowCount, rowCount);
		groupMemberList = groupMemberDAO.selectGroupMemberList(groupMemberEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 그룹원 프로필 등급
		for (GroupMemberEntity groupMemberData : groupMemberList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= groupMemberData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= groupMemberData.getActivityScore()) {
					groupMemberData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		return groupMemberList;
	}

	/**
	 * 그룹 멤버 공동리더 수정
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateMyGroupMember(GroupMemberEntity groupMemberEntity)throws Exception {
		groupMemberDAO.updateMyGroupMember(groupMemberEntity);
	}

	/**
	 * 그룹 멤버 리더 변경
	 * @param groupMemberEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateMemberLeader(GroupMemberEntity groupMemberEntity)
			throws Exception {
		int result = groupMemberDAO.updateMemberGeneral(groupMemberEntity);
		if(result > 0){
			groupMemberDAO.updateMemberLeader(groupMemberEntity);
		}
	}

	/**
	 * 그룹리더 등록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupListEntity insertGroupLeader(GroupEntity groupEntity)
			throws Exception {
		GroupListEntity groupListEntity = new GroupListEntity();
		int result = groupMemberDAO.insertGroupLeader(groupEntity);
		if(result != 0){

			// 그룹생성 시 그룹 활동점수 부여
			activityLogService.insertActivityLogByCreateGroup(groupEntity);

			groupListEntity.setResultCode(ResultCode.SUCCESS);
		}else{
			groupListEntity.setResultCode(ResultCode.FAIL);
		}
		return groupListEntity;
	}

	/**
	 * 그룹원 상세.
	 * @param groupMemberEntity (userKey, groupKey)
	 * @return GroupMemberEntity
	 */
	@Override
	public GroupMemberEntity selectGroupMember(GroupMemberEntity groupMemberEntity) {
		return groupMemberDAO.selectGroupMember(groupMemberEntity);
	}

	@Override
	public int changePushState(GroupMemberEntity groupMemberEntity) {
		return groupMemberDAO.changePushState(groupMemberEntity);
	}

	/**
	 * 그룹원 인원수
	 * @param groupMemberEntity (groupKey, memberStateCode)
	 * @return GroupMemberEntity
	 * @throws Exception
	 */
	@Override
	public GroupMemberEntity selectGroupMemberJoinListCount(GroupMemberEntity groupMemberEntity) {
		groupMemberEntity.setDataSize(groupMemberDAO.selectGroupMemberJoinListCount(groupMemberEntity));
		return groupMemberEntity;
	}
}