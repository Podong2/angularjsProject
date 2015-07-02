package kr.co.chunjae.controller.rest;

import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.question.ReportEntity;
import kr.co.chunjae.entities.question.ReportMemoEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.ReportService;
import kr.co.digigroove.commons.messages.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/admin/report/rest")
public class ReportRestController {
	@Autowired
	ReportService reportService;
	@Autowired
	private Messages messages;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	/**
	 * 신고 처리 상태 변경
	 * @param reportEntity
	 * @return
	 */
	@RequestMapping(value="/updateReportState", method = RequestMethod.POST)
	public ResponseEntity updateReportState(ReportEntity reportEntity) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			reportService.updateReportState(reportEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("report.state_update.success"));
		} catch (Exception e) {
			logger.error("신고 상태 변경 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 신고 메모 등록
	 * @param reportEntity
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertReportMemo", method = RequestMethod.POST)
	public ResponseEntity insertReportMemo(ReportMemoEntity reportMemoEntity,
												HttpSession session) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
			reportMemoEntity.setUserKey(loginSession.getUserKey());
			reportService.insertReportMemo(reportMemoEntity);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("report_memo.insert.success"));
		} catch (Exception e) {
			logger.error("신고 메모 등록 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
	
	/**
	 * 신고 메모 삭제
	 * @param reportMemoKey
	 * @return
	 */
	@RequestMapping(value="/deleteReportMemo", method = RequestMethod.POST)
	public ResponseEntity deleteReportMemo(@RequestParam("reportMemoKey") long reportMemoKey) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			reportService.deleteReportMemo(reportMemoKey);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("report_memo.delete.success"));
		} catch (Exception e) {
			logger.error("신고 메모 삭제 오류", e);
			result.setResultMsg(messages.getMessage("common.exception"));
		}
		return result;
	}
}