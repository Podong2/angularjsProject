package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.GroupDAO;
import kr.co.chunjae.dao.GroupMemberDAO;
import kr.co.chunjae.dao.QuestionDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupListEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.ActivityLogService;
import kr.co.chunjae.service.GroupService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.DateUtils;
import kr.co.digigroove.commons.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDAO groupDAO;
	@Autowired
	private GroupMemberDAO groupMemberDAO;
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private AttachDAO attachDAO;
	@Autowired
	private Messages messages;
	@Autowired
	private GradeScoreDAO gradeScoreDAO;
    @Autowired
    private ActivityLogService activityLogService;

	private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	/**
	 * 그룹 목록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupEntity selectGroupList(GroupEntity groupEntity) throws Exception {
		groupEntity.setPageParams();
		if(StringUtils.isEmpty(groupEntity.getMemberStateCode())){
			groupEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_JOIN);	// 가입중 멤버
		}
		groupEntity.setDataSize(groupDAO.selectGroupListCount(groupEntity));
		List<GroupEntity> groupList = groupDAO.selectGroupList(groupEntity);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		String groupLevelClassPrefix = CommonCode.LEVEL_ICON_PREFIX_GROUP;
		// 그룹 레벨 설정
		for (int j = 0; j < groupList.size(); j++) {
			for (int i=0; i < gradeScoreList.size(); i++) {
				if (gradeScoreList.get(i).getGroupLowScore() <= groupList.get(j).getActivityScore()
						&& gradeScoreList.get(i).getGroupHighScore() >= groupList.get(j).getActivityScore()) {

					groupList.get(j).setGroupRating(String.format("%02d", i+1));
					groupList.get(j).setGroupRatingClassName(groupLevelClassPrefix.concat(groupList.get(j).getGroupRating()));
				}
			}
		}

		groupEntity.setGroupList(groupList);
		return groupEntity;
	}

	/**
	 * 내그룹 목록
	 * @param userKey
	 * @param scrapBoardKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GroupEntity> selectGroupListByUserKey(long userKey, long scrapBoardKey) throws Exception {
		GroupMemberEntity memberEntity = new GroupMemberEntity();
		memberEntity.setUserKey(userKey);
		memberEntity.setScrapBoardKey(scrapBoardKey);						// 그룹 스크랩 여부 확인(문제/답변)
		memberEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_JOIN);	// 가입중 멤버
		return groupDAO.selectGroupListByUserKey(memberEntity);
	}

	/**
	 * 그룹 정보
	 * @param groupKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupEntity selectGroup(long groupKey) throws Exception {

		return groupDAO.selectGroup(groupKey);
	}

	/**
	 * 그룹 삭제
	 * @param groupKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteGroup(long groupKey) throws Exception {

		groupDAO.deleteGroup(groupKey);
	}

	/**
	 * 내그룹 목록(내그룹게시판)
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GroupEntity> selectMyGroupList(long userKey, int startRow, int rowCount) throws Exception {
		GroupMemberEntity memberEntity = new GroupMemberEntity();
		List<GroupEntity> myGroupList = new ArrayList<GroupEntity>();
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		memberEntity.setUserKey(userKey);
		memberEntity.setCurrentPage(startRow);
		memberEntity.setPageSize(rowCount, rowCount);
		myGroupList = groupDAO.selectMyGroupList(memberEntity);
		for (GroupEntity myGroupData : myGroupList) {
			for (int i=0; i < gradeScoreList.size(); i++) {
				if (gradeScoreList.get(i).getGroupLowScore() <= myGroupData.getActivityScore()
						&& gradeScoreList.get(i).getGroupHighScore() >= myGroupData.getActivityScore()) {
					myGroupData.setGroupRating(String.format("%02d", i+1));
				}
			}
		}
		return myGroupList;
	}

	/**
	 * 내그룹 가입승인대기 취소하기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity updateGroupJoinCencle(GroupEntity groupEntity){
		ResponseEntity result = new ResponseEntity();
		try {
			groupDAO.updateGroupJoinCencle(groupEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("group_join_cancle.success"));
		} catch (Exception e) {
			logger.error("그룹 승인대기 목록 가입취소 실패", e);
			result.setResultMsg(messages.getMessage("group_join_cancle.fail"));
		}

		return result;
	}


	/**
	 * 내그룹문제 목록
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupListEntity selectMyGroupQuestionList(long groupKey, int startRow, int rowCount) throws Exception {
		GroupListEntity myGroupInfo = new GroupListEntity();
		List<QuestionEntity> myGroupQustionList = new ArrayList<QuestionEntity>();
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setGroupKey(groupKey);
		groupEntity.setCurrentPage(startRow);
		groupEntity.setPageSize(rowCount, rowCount);

		//그룹문제 목록
		myGroupQustionList = questionDAO.selectMyGroupQustionList(groupEntity);

		//그룹문제, 그룹원 DataUtils
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		if(!StringUtils.isEmpty(myGroupQustionList)){
			// 그룹문제 작성자 프로필 등급
			for (QuestionEntity groupQuestionData : myGroupQustionList) {
				for (int i=0; i < gradeScoreList.size(); i++){
					if(gradeScoreList.get(i).getUserLowScore() <= groupQuestionData.getActivityScore()
							&& gradeScoreList.get(i).getUserHighScore() >= groupQuestionData.getActivityScore()) {
						groupQuestionData.setUserRating(String.format("%02d", i+1));
					}
				}
			}
			// 문제 작성 시점
			for (int i = 0; i < myGroupQustionList.size(); i++) {
				myGroupQustionList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(myGroupQustionList.get(i).getInsertDate()));
			}
		}

		myGroupInfo.setQuestionEntity(myGroupQustionList);
		return myGroupInfo;
	}

	/**
	 * 내그룹멤버 목록
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupListEntity selectMyGroupMemberList(long groupKey, int startRow, int rowCount) throws Exception {
		GroupListEntity myGroupInfo = new GroupListEntity();
		List<GroupMemberEntity> groupMemberList = new ArrayList<GroupMemberEntity>();
		GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
		groupMemberEntity.setGroupKey(groupKey);

		groupMemberEntity.setCurrentPage(startRow);
		groupMemberEntity.setPageSize(rowCount, rowCount);
		groupMemberEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_STAND_BY);

		//그룹원 목록
		groupMemberList = groupMemberDAO.selectGroupMemberList(groupMemberEntity);

		// 그룹원 프로필 등급
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		for (GroupMemberEntity groupMemberData : groupMemberList) {
			for (int i=0; i < gradeScoreList.size(); i++) {
				if(gradeScoreList.get(i).getUserLowScore() <= groupMemberData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= groupMemberData.getActivityScore()) {
					groupMemberData.setUserRating(String.format("%02d", i+1));
				}
			}
		}

		myGroupInfo.setGroupMemberEntity(groupMemberList);
		return myGroupInfo;
	}

	/**
	 * 내그룹 페이지 그룹문제, 그룹원 DataUtil
	 * @param myGroupQustionList
	 * @param groupMemberList
	 * @throws Exception
	 */
	protected void myGroupDetailUtils(List<QuestionEntity> myGroupQustionList,
			List<GroupMemberEntity> groupMemberList) throws Exception {
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		if(!StringUtils.isEmpty(myGroupQustionList)){
			// 그룹문제 작성자 프로필 등급
			for (QuestionEntity groupQuestionData : myGroupQustionList) {
				for (int i=0; i < gradeScoreList.size(); i++){
					if(gradeScoreList.get(i).getUserLowScore() <= groupQuestionData.getActivityScore()
							&& gradeScoreList.get(i).getUserHighScore() >= groupQuestionData.getActivityScore()) {
						groupQuestionData.setUserRating(String.format("%02d", i+1));
					}
				}
			}
			// 문제 작성 시점
			for (int i = 0; i < myGroupQustionList.size(); i++) {
				myGroupQustionList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(myGroupQustionList.get(i).getInsertDate()));
			}
		}

		// 그룹원 프로필 등급
		for (GroupMemberEntity groupMemberData : groupMemberList) {
			for (int i=0; i < gradeScoreList.size(); i++) {
				if(gradeScoreList.get(i).getUserLowScore() <= groupMemberData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= groupMemberData.getActivityScore()) {
					groupMemberData.setUserRating(String.format("%02d", i+1));
				}
			}
		}

	}

	/**
	 * 그룹찾기
	 * @param searchValue
	 * @param startRow
	 * @param rowCount
	 * @throws Exception
	 */
	@Override
	public List<GroupEntity> searchGroupList(String searchValue, int startRow, int rowCount) throws Exception {
		GroupMemberEntity memberEntity = new GroupMemberEntity();
		List<GroupEntity> myGroupList = new ArrayList<GroupEntity>();
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		memberEntity.setSearchValue(searchValue);
		memberEntity.setCurrentPage(startRow);
		memberEntity.setPageSize(rowCount, rowCount);
		myGroupList = groupDAO.searchGroupList(memberEntity);
		for (GroupEntity myGourpData : myGroupList) {
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getGroupLowScore() <= myGourpData.getActivityScore()
						&& gradeScoreList.get(i).getGroupHighScore() >= myGourpData.getActivityScore()) {
					myGourpData.setGroupRating(String.format("%02d", i+1));
				}
			}
		}
		return myGroupList;
	}

	/**
	 * 그룹만들기
	 * @param groupEntity
	 * @throws Exception
	 */
	@Override
	public GroupListEntity insertGroup(GroupEntity groupEntity) throws Exception {
		GroupListEntity groupListEntity = new GroupListEntity();
		int result = groupDAO.insertGroup(groupEntity);
		groupListEntity.setGroupEntity(groupEntity);
		if(result != 0) {
			groupListEntity.setResultCode(ResultCode.SUCCESS);
		}
		return groupListEntity;
	}

	/**
	 * 그룹커버 이미지 목록
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AttachEntity> selectTemplateImage() throws Exception {
		List<AttachEntity> templateList = new ArrayList<AttachEntity>();
		templateList = attachDAO.selectTemplateImage();
		return templateList;
	}

	/**
	 * 그룹 정보 조회
	 * @param groupKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupEntity selectGroupDetail(long groupKey) throws Exception {
		GroupEntity groupEntity  = new GroupEntity();

		groupEntity = groupDAO.selectGroup(groupKey);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();

		// 사용자 상세 프로필 등급
		for (int i=0; i < gradeScoreList.size(); i++){
			if (gradeScoreList.get(i).getGroupLowScore() <= groupEntity.getActivityScore()
					&& gradeScoreList.get(i).getGroupHighScore() >= groupEntity.getActivityScore()) {
				groupEntity.setGroupRating(String.format("%02d", i+1));
			}
		}
		return groupEntity;
	}

	/**
	 * 그룹 가입하기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@Override //groupKey, userKey, memberStatus
	public ResponseEntity insertGroupJoin(GroupMemberEntity groupMemberEntity)throws Exception{
		ResponseEntity resultMessage = new GroupListEntity();
		//그룹 공개상태값 체크하여 멤버 상태값 적용
		if (groupMemberEntity.getGroupTypeCode().equals(CommonCode.GROUP_TYPE_CODE_OPEN)) {
			//가입
			groupMemberEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_JOIN);
		} else {
			//가입대기
			groupMemberEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_STAND_BY);
		}

		if(StringUtils.isEmpty(groupMemberEntity.getDeleteDate())){
			int result = groupDAO.insertGroupJoin(groupMemberEntity); // 그룹 가입
			if (result != 0) {
				resultMessage.setResultCode(ResultCode.SUCCESS);
				resultMessage.setResultMsg(messages.getMessage("groupJoin.success"));
			} else {
				resultMessage.setResultCode(ResultCode.FAIL);
				resultMessage.setResultMsg(messages.getMessage("groupJoin.fail"));
			}

		}else{
			groupDAO.updateJoinGroupMember(groupMemberEntity);	// 그룹 재가입
			resultMessage.setResultMsg(messages.getMessage("groupJoin.success"));
		}
		if (groupMemberEntity.getGroupTypeCode().equals(CommonCode.GROUP_TYPE_CODE_OPEN)) {
			//그룹가입 활동점수 등록
			activityLogService.insertActivityLogByJoinGroup(groupMemberEntity);
		}

		return resultMessage;
	}

	/**
	 * 그룹멤버 조회하여 가입 유무 확인
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GroupMemberEntity groupMemberSearchResult(GroupMemberEntity groupMemberEntity)throws Exception{
		ResponseEntity result = new ResponseEntity();
		String groupTypeCode = groupMemberEntity.getGroupTypeCode();
		GroupMemberEntity groupMemberData = new GroupMemberEntity();
		groupMemberData = groupMemberDAO.selectGroupMember(groupMemberEntity);

		// 그룹 멤버 유무 확인
		if(StringUtils.isEmpty(groupMemberData)){
			result.setResultCode(ResultCode.SUCCESS); // 그룹 가입리턴
		}else{
			if(StringUtils.isEmpty(groupMemberData.getDeleteDate())){
				result.setResultCode(ResultCode.FAIL); // 이미 가입 한 그룹
				result.setResultMsg(messages.getMessage("groupMember.exist"));
			}else{
				result.setResultCode(ResultCode.SUCCESS);
				groupMemberEntity = groupMemberData;
			}
		}
		groupMemberEntity.setGroupTypeCode(groupTypeCode);
		groupMemberEntity.setResponseEntity(result);
		return groupMemberEntity;
	}

	/**
	 * 그룹 탈퇴하기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	public void groupWidthDraw(GroupMemberEntity groupMemberEntity) throws Exception{
		groupMemberDAO.groupWidthDraw(groupMemberEntity);
	}

	/**
	 * 내그룹멤버 목록 찾기
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
//	@Override
	public GroupListEntity searchMyGroupMemberList(long groupKey, int startRow, int rowCount, String searchValue) throws Exception {
		GroupListEntity myGroupInfo = new GroupListEntity();
		List<GroupMemberEntity> groupMemberList = new ArrayList<GroupMemberEntity>();
		GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
		groupMemberEntity.setGroupKey(groupKey);
		groupMemberEntity.setSearchValue(searchValue);
		groupMemberEntity.setCurrentPage(startRow);
		groupMemberEntity.setPageSize(rowCount, rowCount);
		groupMemberEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_STAND_BY);

		//그룹원 목록
		groupMemberList = groupMemberDAO.searchGroupMemberList(groupMemberEntity);

		// 그룹원 프로필 등급
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		for (GroupMemberEntity groupMemberData : groupMemberList) {
			for (int i=0; i < gradeScoreList.size(); i++) {
				if(gradeScoreList.get(i).getUserLowScore() <= groupMemberData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= groupMemberData.getActivityScore()) {
					groupMemberData.setUserRating(String.format("%02d", i+1));
				}
			}
		}

		myGroupInfo.setGroupMemberEntity(groupMemberList);
		return myGroupInfo;
	}

	/**
	 *  그룹정보 수정
	 * @param groupEntity
	 * @throws Exception
	 */
	@Override
	public void updateGroup(GroupEntity groupEntity) throws Exception {
		groupDAO.updateGroup(groupEntity);

	}
}
