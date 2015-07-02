package kr.co.chunjae.controller.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupListEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.service.GroupMemberService;
import kr.co.chunjae.service.GroupService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.HashUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/group")
public class GroupMobileController {
	@Value("#{file_prop['filePath']}")
	private String filePath;
	@Value("#{file_prop['groupCoverPath']}")
	private String groupCoverPath;
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupMemberService groupMemberService;
	@Autowired
	private Messages messages;
	@Autowired
	private AttachDAO attachDAO;

	private static final Logger logger = LoggerFactory.getLogger(GroupMobileController.class);

	/**
	 * 내그룹 목록 조회
	 * @param userKey
	 * @param questionKey
	 * @return
	 */
	@RequestMapping(value="/selectGroupListByUserKey", method = RequestMethod.GET)
	public GroupListEntity selectGroupListByUserKey(@RequestParam("userKey") long userKey,
													@RequestParam("scrapBoardKey") long scrapBoardKey) {

		GroupListEntity groupListEntity = new GroupListEntity();
		if (userKey > 0) {
			try {
				groupListEntity.setGroupList(groupService.selectGroupListByUserKey(userKey, scrapBoardKey));
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 목록 조회 오류", e);
			}
		}
		return groupListEntity;
	}

	/**
	 * 내그룹 목록
	 * @param userKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/selectGroupList", method = RequestMethod.GET)
	public GroupListEntity selectGroupList(@RequestParam("userKey") long userKey,
											@RequestParam("startRow") int startRow,
											@RequestParam("rowCount") int rowCount) {

		GroupListEntity groupListEntity = new GroupListEntity();
		if (userKey > 0) {
			try {
				groupListEntity.setGroupList(groupService.selectMyGroupList(userKey, startRow, rowCount));
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 목록 조회 오류", e);
			}
		}
		return groupListEntity;
	}

	/**
	 * 내그룹 가입승인대기 목록 취소하기
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateGroupJoinCencle", method = RequestMethod.POST)
	public ResponseEntity updateGroupJoinCencle(@RequestBody GroupEntity groupEntity) throws Exception {

		ResponseEntity result = new ResponseEntity();
		result = groupService.updateGroupJoinCencle(groupEntity);
		return result;
	}

	/**
	 * 내그룹 문제 목록
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/selectGroupQuestionList", method = RequestMethod.GET)
	public GroupListEntity selectMyGroupQuestionList(@RequestParam("groupKey") long groupKey,
													@RequestParam("startRow") int startRow,
													@RequestParam("rowCount") int rowCount) {

		GroupListEntity groupListEntity = new GroupListEntity();
		if (groupKey != 0) {
			try {
				groupListEntity = groupService.selectMyGroupQuestionList(groupKey, startRow, rowCount);
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹문제 목록", e);
			}
		}
		return groupListEntity;
	}

	/**
	 * 내그룹 멤버 목록
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @param searchValue
	 * @return
	 */
	@RequestMapping(value="/selectGroupMemberList", method = RequestMethod.GET)
	public GroupListEntity selectGroupMemberList(@RequestParam("groupKey") long groupKey,
													@RequestParam("startRow") int startRow,
													@RequestParam("rowCount") int rowCount,
													@RequestParam(required=false ,value = "searchValue") String searchValue) {

		GroupListEntity groupListEntity = new GroupListEntity();
		if (groupKey != 0) {
			try {
				if(StringUtils.isEmpty(searchValue)){
					groupListEntity = groupService.selectMyGroupMemberList(groupKey, startRow, rowCount);
				}else{
					groupListEntity = groupService.searchMyGroupMemberList(groupKey, startRow, rowCount, searchValue);
				}
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹문제 목록", e);
			}
		}
		return groupListEntity;
	}

	/**
	 * 그룹원 정보 가져오기
	 * @param groupMemberEntity (groupKey, userKey)
	 * @return GroupMemberEntity
	 * @throws Exception
	 */
	@RequestMapping(value="/selectGroupMember", method = RequestMethod.GET)
	public GroupMemberEntity selectGroupMember(GroupMemberEntity groupMemberEntity) throws Exception{
		return groupMemberService.selectGroupMember(groupMemberEntity);
	}

	@RequestMapping(value = "/changePushState", method = RequestMethod.POST)
	public int changePushState(@RequestBody GroupMemberEntity groupMemberEntity) {
		return groupMemberService.changePushState(groupMemberEntity);
	}

	/**
	 * 그룹원 가입상태 변경
	 * @param groupMemberEntity
	 * @return
	 */
	@RequestMapping(value="/updateGroupMemberJoinState", method = RequestMethod.POST)
	public ResponseEntity updateGroupMemberJoinState(@RequestBody GroupMemberEntity groupMemberEntity) {

		ResponseEntity result = new ResponseEntity();
		if (groupMemberEntity != null) {
			try {
				result = groupMemberService.updateGroupMemberJoinState(groupMemberEntity);
				result.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 문제 목록 조회 오류", e);
			}
		}
		return result;
	}

	/**
	 * 그룹멤버 목록(그룹설정 - 리더,공동리더 지정)
	 * @param groupKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/selectMyGroupMemberList", method = RequestMethod.GET)
	public GroupListEntity selectMyGroupMemberList(@RequestParam("groupKey") long groupKey,
													@RequestParam("startRow") int startRow,
													@RequestParam("rowCount") int rowCount) {

		GroupListEntity groupListEntity = new GroupListEntity();
		if (groupKey != 0) {
			try {

				groupListEntity.setGroupMemberEntity(groupMemberService.selectMyGroupMemberList(groupKey, startRow, rowCount));
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 문제 목록 조회 오류", e);
			}
		}
		return groupListEntity;
	}

	/**
	 * 그룹멤버 공동리더 수정
	 * @param groupKey
	 * @return
	 */
	@RequestMapping(value="/updateMyGroupMember", method = RequestMethod.POST)
	public ResponseEntity updateMyGroupMember(@RequestBody GroupMemberEntity groupMemberEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			groupMemberService.updateMyGroupMember(groupMemberEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("그룹멤버 공동리더 수정", e);
		}
		return result;
	}

	/**
	 * 그룹멤버 리더 변경
	 * @param groupKey
	 * @return
	 */
	@RequestMapping(value="/updateMemberLeader", method = RequestMethod.POST)
	public ResponseEntity updateMemberLeader(@RequestBody GroupMemberEntity groupMemberEntity) {

		ResponseEntity result = new ResponseEntity();
		try {
			groupMemberService.updateMemberLeader(groupMemberEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("그룹 리더 변경 오류", e);
		}
		return result;
	}

	/**
	 * 그룹찾기
	 * @param searchValue
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/searchGroupList", method = RequestMethod.GET)
	public GroupListEntity searchGroupList(@RequestParam("searchValue") String searchValue,
											@RequestParam("startRow") int startRow,
											@RequestParam("rowCount") int rowCount) {

		GroupListEntity groupListEntity = new GroupListEntity();
		if (!searchValue.isEmpty()) {
			try {
				groupListEntity.setGroupList(groupService.searchGroupList(searchValue, startRow, rowCount));
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 목록 조회 오류", e);
			}
		}
		return groupListEntity;
	}

	/**
	 * 그룹커버 목록
	 * @param
	 * @return
	 */
	@RequestMapping(value="/selectTemplateImage", method = RequestMethod.GET)
	public GroupListEntity selectTemplateImage() {

		GroupListEntity groupListEntity = new GroupListEntity();
		List<AttachEntity> templateList = new ArrayList<AttachEntity>();
			try {
				templateList = groupService.selectTemplateImage();
				groupListEntity.setTemplateList(templateList);
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 만들기 오류", e);
			}
		return groupListEntity;
	}


	/**
	 * 그룹만들기
	 * @param groupEntity
	 * @return
	 */
	@RequestMapping(value="/insertGroup", method = RequestMethod.POST)
	public GroupListEntity insertGroup(@RequestBody GroupEntity groupEntity) {

		GroupListEntity groupListEntity = new GroupListEntity();

		AttachEntity attachEntity = new AttachEntity();
		attachEntity.setAttachType(CommonCode.ATTACH_TYPE_GROUP);
			try {
				if(groupEntity.getGroupKey() > 0){
					// 그룹 수정
					groupService.updateGroup(groupEntity);
					attachEntity.setCommonKey(groupEntity.getGroupKey());
				}else{
					// 그룹 등록
					groupListEntity = groupService.insertGroup(groupEntity);

					attachEntity.setCommonKey(groupEntity.getGroupKey());

					// 그룹 리더 등록
					if (StringUtils.equals(groupListEntity.getResultCode(), ResultCode.SUCCESS)) {
						groupListEntity = groupMemberService.insertGroupLeader(groupEntity);
					}
				}

				// 그룹 커버이미지 등록
				if(!StringUtils.isEmpty(groupEntity.getGroupCoverCameraImageName())){
					String image = groupEntity.getGroupCoverCameraImageName();

					groupFileOutputStream(attachEntity, image);

				}else if(!StringUtils.isEmpty(groupEntity.getGroupCoverImageName())){

					String groupCoverImageName = groupFileInputStream(groupEntity.getGroupCoverImageName());

					groupFileOutputStream(attachEntity, groupCoverImageName);
				}

				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 만들기 오류", e);
			}
		return groupListEntity;
	}

	/**
	 * 그룹 정보 조회
	 * @param groupKey
	 * @return
	 */
	@RequestMapping(value="/selectGroupDetail", method = RequestMethod.GET)
	public GroupListEntity selectGroupDetail(@RequestParam("groupKey") long groupKey) {

		GroupListEntity groupListEntity = new GroupListEntity();
			try {
				groupListEntity.setGroupEntity(groupService.selectGroupDetail(groupKey));
				groupListEntity.setResultCode(ResultCode.SUCCESS);
			} catch (Exception e) {
				logger.error("그룹 정보 불러오기 실패", e);
			}
		return groupListEntity;
	}

	/**
	 * 그룹 가입하기
	 * @param groupEntity
	 * @return
	 */
	@RequestMapping(value="/insertGroupJoin", method = RequestMethod.POST)
	public ResponseEntity insertGroupJoin(@RequestBody GroupMemberEntity groupMemberEntity) {

		ResponseEntity result = new ResponseEntity();
			try {
				groupMemberEntity = groupService.groupMemberSearchResult(groupMemberEntity);

				if (StringUtils.equals(groupMemberEntity.getResponseEntity().getResultCode(), ResultCode.SUCCESS)) {
					result = groupService.insertGroupJoin(groupMemberEntity);
				}else{
					result = groupMemberEntity.getResponseEntity();
				}

			} catch (Exception e) {
				logger.error("그룹 가입 실패", e);
			}
		return result;
	}

	/**
	 * 그룹 탈퇴하기
	 * @param groupEntity
	 * @return
	 */
	@RequestMapping(value="/groupWidthDraw", method = RequestMethod.GET)
	public ResponseEntity groupWidthDraw(@RequestParam("userKey") long userKey,
			@RequestParam("groupKey") long groupKey) {
		GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
		groupMemberEntity.setUserKey(userKey);
		groupMemberEntity.setGroupKey(groupKey);
		ResponseEntity result = new ResponseEntity();
			try {
				groupService.groupWidthDraw(groupMemberEntity);
				result.setResultCode(ResultCode.SUCCESS);

			} catch (Exception e) {
				logger.error("그룹 탈퇴 실패", e);
				result.setResultMsg(messages.getMessage("groupWidthDraw.fail"));
			}
		return result;
	}

	/**
	 * 그룹 삭제하기
	 * @param groupEntity
	 * @return
	 */
	@RequestMapping(value="/deleteGroup", method = RequestMethod.GET)
	public ResponseEntity deleteGroup(@RequestParam("groupKey") long groupKey) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setGroupKey(groupKey);
		ResponseEntity result = new ResponseEntity();
			try {
				groupService.deleteGroup(groupKey);
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMsg(messages.getMessage("deleteGroup.success"));

			} catch (Exception e) {
				logger.error("그룹 삭제 실패", e);
				result.setResultMsg(messages.getMessage("deleteGroup.fail"));
			}
		return result;
	}

	/**
	 * 그룹찾기 - default 리스트
	 * @param groupEntity
	 * @return
	 */
	@RequestMapping(value = "/selectPublicGroupList", method = RequestMethod.GET)
	public GroupEntity groupList(@RequestParam("groupTypeCode") String groupTypeCode,
			@RequestParam("startRow") int startRow,
			@RequestParam("rowCount") int rowCount) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setCurrentPage(startRow);
		groupEntity.setPageSize(rowCount, rowCount);
		groupEntity.setGroupTypeCode(groupTypeCode);
		groupEntity.setMemberStateCode(CommonCode.GROUP_MEM_STATE_STAND_BY);
		try {
			if(StringUtils.isEmpty(groupEntity.getSortBy())) {
				groupEntity.setSortOrder(CommonCode.SORT_ORDER_DESC);
			}
			groupService.selectGroupList(groupEntity);
		} catch (Exception e) {
			logger.error("그룹관리 리스트 조회 오류", e);
		}
		return groupEntity;
	}

	/**
	 * 그룹원 인원수
	 * @param groupMemberEntity (groupKey, memberStateCode)
	 * @return GroupMemberEntity
	 * @throws Exception
	 */
	@RequestMapping(value="/selectGroupMemberJoinListCount", method = RequestMethod.GET)
	public GroupMemberEntity selectGroupMemberJoinListCount(GroupMemberEntity groupMemberEntity) throws Exception{
		groupMemberEntity = groupMemberService.selectGroupMemberJoinListCount(groupMemberEntity);
		return groupMemberEntity;
	}


	//템플릿 파일 불러오기
	private String groupFileInputStream(String groupCoverImageName) {
		String filePath = groupCoverPath + "/" + groupCoverImageName;
		File file = new File(filePath);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			return Base64.encodeBase64String(IOUtils.toByteArray(fileInputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) fileInputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//파일 업로드
	private void groupFileOutputStream(AttachEntity attachEntity, String image)
			throws FileNotFoundException, IOException, Exception {
		byte[] imageByteArray = HashUtils.decodeBase64(image);
		long today = System.currentTimeMillis();
		String fileName = CommonCode.ATTACH_FILE_PREFIX + today + "." + CommonCode.ATTACH_FILE_EXTENSION_JPG;

		// Write a image byte array into file system
		FileOutputStream imageOutFile = new FileOutputStream(filePath + "/" + fileName);
		imageOutFile.write(imageByteArray);
		imageOutFile.close();

		// insert DazzleAttach
		attachEntity.setFileName(fileName);
		attachEntity.setFileOrigName(fileName);
		attachDAO.insertAttach(attachEntity);
	}

}
