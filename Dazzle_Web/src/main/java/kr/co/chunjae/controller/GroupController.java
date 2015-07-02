package kr.co.chunjae.controller;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.entities.group.GroupEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.service.GroupMemberService;
import kr.co.chunjae.service.GroupService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupMemberService groupMemberService;

	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	/**
	 * 그룹관리 - 리스트
	 * @param groupEntity
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String groupList(@ModelAttribute("groupEntity") GroupEntity groupEntity) {

		try {
			if(StringUtils.isEmpty(groupEntity.getSortBy())) {
				groupEntity.setSortOrder(CommonCode.SORT_ORDER_DESC);
			}
			groupEntity.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
			groupService.selectGroupList(groupEntity);
		} catch (Exception e) {
			logger.error("그룹관리 리스트 조회 오류 ", e);
		}
		return "group/list";
	}

	/**
	 * 그룹관리 - 리스트 엑셀 출력
	 * @param groupEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/excelList", method = RequestMethod.GET)
	public String groupExcelList(@ModelAttribute("groupEntity") GroupEntity groupEntity,
								Model model) {

		try {
			groupEntity.setPageSize(CommonCode.EXCEL_LIST_SIZE_PARAM, CommonCode.EXCEL_GROUP_SIZE_PARAM);
			groupService.selectGroupList(groupEntity);
		} catch (Exception e) {
			logger.error("그룹관리 엑셀 출력 오류", e);
		}
		model.addAttribute("excelName", "groupList");
		return "excel/groupExcelList";
	}

	/**
	 * 그룹관리 - 상세
	 * @param groupKey
	 * @param groupMemberEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail/{groupKey}", method = RequestMethod.GET)
	public String groupDetail(@PathVariable("groupKey") long groupKey,
							@ModelAttribute("groupMemberEntity") GroupMemberEntity groupMemberEntity,
							Model model) {

		GroupEntity groupEntity = new GroupEntity();
		try {
			if(StringUtils.isEmpty(groupMemberEntity.getSortBy())) {
				groupMemberEntity.setSortOrder(CommonCode.SORT_ORDER_DESC);
			}
			groupMemberEntity.setGroupKey(groupKey);
			groupMemberEntity.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
			groupMemberService.selectGroupMemberList(groupMemberEntity);	// 멤버 리스트

			groupEntity = groupService.selectGroup(groupKey);				// 그룹 정보
		} catch (Exception e) {
			logger.error("그룹 상세정보 조회 오류", e);
		}
		model.addAttribute("groupEntity", groupEntity);
		return "group/detail";
	}
}