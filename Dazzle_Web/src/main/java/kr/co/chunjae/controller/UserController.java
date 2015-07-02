package kr.co.chunjae.controller;

import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 회원관리 - 리스트
	 * @param userEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userList(@ModelAttribute("userList")UserEntity userEntity,
							Model model) {
		try {
			userEntity.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
			userService.selectUserList(userEntity);
			model.addAttribute("arrayType", userEntity.getArrayType());
		} catch (Exception e) {
			logger.error(" 관리자 회원관리 목록 : Error ", e);
		}
		return "user/list";
	}

	/**
	 * 회원관리 - 상세
	 * @param userKey
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String userDetail(@RequestParam(required = false, value = "userKey") long userKey,
								Model model,
								HttpSession session) throws Exception{

        UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
        UserEntity userEntity =  userService.selectUser(userKey);
        model.addAttribute("userEntity", userEntity);
        model.addAttribute("adminKey", loginSession.getUserKey());
		return "user/detail";
	}

	/**
	 * 회원관리 - 목록 엑셀 출력
	 * @param userEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/excelList", method = RequestMethod.GET)
	public String userExcelList(@ModelAttribute("userList")UserEntity userEntity,
								Model model) {

		try {
			userEntity.setPageSize(CommonCode.EXCEL_LIST_SIZE_PARAM, CommonCode.EXCEL_GROUP_SIZE_PARAM);
			userService.selectUserList(userEntity);
		} catch (Exception e) {
			logger.error(" 관리자 회원관리 목록 엑셀출력 : Error ", e);
		}
		model.addAttribute("excelName", "userEntity");
		return "excel/userExcelList";
	}
}