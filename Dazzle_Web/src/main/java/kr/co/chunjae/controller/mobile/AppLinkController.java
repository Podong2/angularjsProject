package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.service.GroupInviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jm1218 on 15. 2. 23.
 */
@RequestMapping("/mobile/appLink")
@Controller
public class AppLinkController {

	private static final Logger logger = LoggerFactory.getLogger(AppLinkController.class);
	
	@Autowired
	private GroupInviteService groupInviteService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String handleAppLink(@RequestParam("type") String type,
	                            @RequestParam(value = "ic", required = false) String inviteCode,
	                            @RequestParam(value = "bk", defaultValue = "0") int boardKey,
	                            Model model) throws Exception{
		CommonCode.AppLink appLinkType = CommonCode.AppLink.valueOf(type);
		switch (appLinkType) {
			case DEFAULT :
				//TODO : ADD DEFAULT ATTRIBUTE
				break;
			case GROUP_INVITE :
				model.addAttribute("groupInvite", groupInviteService.getGroupInvite(inviteCode));
				break;
			case EVENT_PAGE :
				model.addAttribute("boardKey", boardKey);
				break;
			default :
				break;
		}

		model.addAttribute("linkType", appLinkType);
		return "mobile/linkApp";
	}
}
