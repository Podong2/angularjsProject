package kr.co.chunjae.controller.rest;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.entities.user.ActivityScoreEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.SettingService;
import kr.co.digigroove.commons.messages.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/setting")
public class SettingRestController {
	@Autowired
	private SettingService settingService;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(SettingRestController.class);

    /**
     * 다즐설정 - 활동점수 설정
     * @param activityScoreEntity
     * @return
     */
    @RequestMapping(value = "/UpdateactivityScoreForm", method = RequestMethod.POST)
    public ResponseEntity updateActivityScoreForm(@RequestBody ActivityScoreEntity activityScoreEntity){
    	ResponseEntity result = new ResponseEntity();
        try{
        	settingService.updateActivityscore(activityScoreEntity.getActivityScoreList());
        	result.setResultCode(ResultCode.SUCCESS);
        }catch(Exception e){
        	logger.error(" 관리자 활동점수 설정 : Error ", e);
        }

        return result;
    }

    /**
     * 다즐설정 - 회원등급 설정
     * @param gradeScoreEntity
     * @return
     */
    @RequestMapping(value = "/UpdateGradeScoreForm", method = RequestMethod.POST)
    public ResponseEntity updateGradeScoreForm(@RequestBody GradeScoreEntity gradeScoreEntity){
    	ResponseEntity result = new ResponseEntity();

    	try{
    		settingService.updateGradescore(gradeScoreEntity.getGradeScoreList());
    		result.setResultCode(ResultCode.SUCCESS);
	    }catch(Exception e){
	    	logger.error(" 관리자 회원등급 설정 : Error ", e);
	    }
        return result;

    }

    /**
     * 관리자 한줄공지 쓰기
     * @param lineNoticeEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/insertNotice", method = RequestMethod.POST)
    public ResponseEntity insertNotice(LineNoticeEntity lineNoticeEntity, Model model){

    	ResponseEntity result = new ResponseEntity();
        try{
        	result = settingService.insertLineNotice(lineNoticeEntity);
        	result.setResultCode(ResultCode.SUCCESS);
        	result.setResultMsg(messages.getMessage("lineNotice.success"));
        }catch(Exception e){
        	logger.error(" 관리자 한줄공지 쓰기 : Error ", e);
        	result.setResultMsg(messages.getMessage("lineNotice.fail"));
        }

        return result;
     }
}