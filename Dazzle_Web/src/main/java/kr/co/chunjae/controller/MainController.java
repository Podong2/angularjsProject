package kr.co.chunjae.controller;

import kr.co.chunjae.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MainController {
	@Autowired
	UserService userService;

	/**
	 * 로그인 페이지
	 * @param resultCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/admin/login", "/"}, method = RequestMethod.GET)
	public String doLogin(@RequestParam(required = false, value = "resultCode", defaultValue="") String resultCode,
							Model model) throws Exception {

		model.addAttribute("resultCode", resultCode);
		return "login";
	}

	/**
	 * 비밀번호 변경 페이지
	 * @return
	 */
	@RequestMapping(value = "/admin/updatePassword", method = RequestMethod.GET)
	public String upatePassword() throws Exception {

		return "admin/password";
	}
}