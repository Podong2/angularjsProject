package kr.co.chunjae.controller.rest;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.service.GroupService;
import kr.co.digigroove.commons.messages.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/admin/group/rest")
public class GroupRestController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private Messages messages;
	
	private static final Logger logger = LoggerFactory.getLogger(GroupRestController.class);
	
	/**
	 * 그룹 삭제
	 * @param groupKey
	 * @return
	 */
	@RequestMapping(value="/deleteGroup", method = RequestMethod.POST)
	public ResponseEntity deleteGroup(@RequestParam("groupKey") long groupKey) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			groupService.deleteGroup(groupKey);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("delete.success"));
		} catch (Exception e) {
			logger.error("그룹 삭제 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
}