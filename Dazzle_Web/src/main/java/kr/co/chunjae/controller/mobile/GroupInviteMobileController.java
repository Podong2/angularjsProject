package kr.co.chunjae.controller.mobile;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.SmsEntity;
import kr.co.chunjae.entities.group.GroupInviteEntity;
import kr.co.chunjae.entities.group.GroupMemberEntity;
import kr.co.chunjae.service.GroupInviteService;
import kr.co.chunjae.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jm1218 on 15. 1. 13.
 */
@Controller
@RequestMapping(value = "/mobile/group")
public class GroupInviteMobileController {

	@Autowired
	private GroupInviteService groupInviteService;
	@Autowired
	private SmsService smsService;

	@ResponseBody
	@RequestMapping(value = "invite/createLink", method = RequestMethod.POST)
	public String createInviteLink(@RequestBody GroupInviteEntity groupInviteEntity) throws Exception{
		return groupInviteService.makeInviteCode(groupInviteEntity);
	}
	//초대링크 클릭시 임시페이지로 이동.
	//임시페이지에서 App or Market 으로 이동.
	@RequestMapping(value = "{inviteCode}")
	public String clickInviteLink(@PathVariable(value = "inviteCode") String inviteCode, Model model) throws Exception{
		GroupInviteEntity groupInviteEntity = groupInviteService.getGroupInvite(inviteCode);
		boolean isExpired = groupInviteEntity.getExpireDate().compareTo(new Date()) < 0;
		model.addAttribute("type", CommonCode.AppLink.GROUP_INVITE);
		model.addAttribute("groupInvite", groupInviteEntity);
		model.addAttribute("isExpired", isExpired);
		return "mobile/linkApp";
	}
	//SMS 로 초대 메시지 보내기.
	@RequestMapping(value = "invite/sms", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity inviteForSMS(@RequestBody SmsEntity smsEntity) throws Exception {
		return smsService.sendSmsGeneral(smsEntity);
	}

	@RequestMapping(value = "invite/getActivatedInviteCode")
	@ResponseBody
	public GroupInviteEntity getActivatedInviteCode(GroupInviteEntity groupInviteEntity) throws Exception{
		return groupInviteService.getActivatedInviteCode(groupInviteEntity);
	}

	@RequestMapping(value = "invite/makeInviteCode")
	@ResponseBody
	public GroupInviteEntity makeInviteCode(@RequestBody GroupInviteEntity groupInviteEntity) throws Exception{
		groupInviteService.makeInviteCode(groupInviteEntity);
		return  groupInviteService.getActivatedInviteCode(groupInviteEntity);
	}

	@RequestMapping(value = "join/{inviteCode}", method = RequestMethod.POST)
	@ResponseBody
	public GroupInviteEntity groupJoinByInviteCode(@PathVariable String inviteCode, @RequestBody GroupMemberEntity groupMemberEntity) throws Exception{
		return groupInviteService.groupJoinByInviteCode(inviteCode, groupMemberEntity);
	}

	@RequestMapping(value = "invite/getGroupInvite",  method = RequestMethod.GET)
	@ResponseBody
	public GroupInviteEntity getGroupInvite(@RequestParam String inviteCode) throws Exception{
		return groupInviteService.getGroupInvite(inviteCode);
	}
}
