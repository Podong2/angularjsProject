package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.question.ReportCountEntity;
import kr.co.chunjae.entities.question.ReportEntity;
import kr.co.chunjae.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/report")
public class ReportMobileController {
	@Autowired
	private ReportService reportService;

	private static final Logger logger = LoggerFactory.getLogger(ReportMobileController.class);
	
	/**
	 * 신고 여부 조회
	 * @param reportUserKey
	 * @param reportTypeCode
	 * @param reportBoardUserKey
	 * @param questionKey
	 * @return
	 */
	@RequestMapping(value = "getIsReport", method = RequestMethod.GET)
	public ReportCountEntity getIsReport(@RequestParam("reportUserKey") long reportUserKey,
										@RequestParam("reportTypeCode") String reportTypeCode,
										@RequestParam("reportBoardUserKey") long reportBoardUserKey,
										@RequestParam("questionKey") long questionKey) {
		
		ReportCountEntity result = new ReportCountEntity();
		try {
			ReportEntity reportEntity = new ReportEntity();
			reportEntity.setReportUserKey(reportUserKey);				// 신고자 고유키
			reportEntity.setReportTypeCode(reportTypeCode);				// 신고 게시물 구분
			reportEntity.setReportBoardUserKey(reportBoardUserKey);		// 신고 게시물 작성자 고유키
			reportEntity.setQuestionKey(questionKey);
			result.setReportCount(reportService.selectIsReport(reportEntity));
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("신고 여부 조회 오류", e);
		}
		return result;
	}

	/**
	 * 신고하기
	 * @param reportEntity
	 * @return
	 */
	@RequestMapping(value = "insertReport", method = RequestMethod.POST)
	public ResponseEntity insertReport(@RequestBody ReportEntity reportEntity) {
		
		ResponseEntity result = new ResponseEntity();
		try {
			reportEntity.setReportStateCode(CommonCode.REPORT_STATE_STAND_BY);
			reportService.insertReport(reportEntity);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("신고하기 등록 오류", e);
		}
		return result;
	}
}
