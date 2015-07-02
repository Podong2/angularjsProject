package kr.co.chunjae.controller;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.entities.user.ActivityScoreEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.SettingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;

    private static final Logger logger = LoggerFactory.getLogger(SettingController.class);

    /**
     * 다즐설정 - 활동점수 목록
     * @param model
     * @return
     */
    @RequestMapping(value = "/activityScoreForm", method = RequestMethod.GET)
    public String selectActivityScoreForm(Model model) {
        List<ActivityScoreEntity> activityList = new ArrayList<ActivityScoreEntity>();

        try{
            activityList = settingService.selectActivityscore();
        } catch (Exception e) {
        	logger.error(" 관리자 활동점수 목록 : Error ", e);
        }
        model.addAttribute("activityList",activityList);
        return "setting/activityScoreForm";
    }

	/**
	 * 다즐설정 - 회원등급 설정
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userGradeForm", method = RequestMethod.GET)
    public String selectGradeScoreForm(Model model) {
        List<GradeScoreEntity> gradeList = new ArrayList<GradeScoreEntity>();

        try{
        	gradeList = settingService.selectGradescore();
        } catch (Exception e) {
        	logger.error(" 관리자 회원등급 목록 : Error ", e);
        }
        model.addAttribute("GradeList",gradeList);
        return "setting/userGradeForm";
    }
}