package kr.co.chunjae.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.entities.question.ReportEntity;
import kr.co.chunjae.entities.question.ReportMemoEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.AnswerService;
import kr.co.chunjae.service.QuestionService;
import kr.co.chunjae.service.ReportService;

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
@RequestMapping(value = "/admin/report")
public class ReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	/**
	 * 신고관리 - 목록
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String reportList(@ModelAttribute("reportEntity") ReportEntity reportEntity) {
		
		reportEntity.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
		try {
			reportService.selectReportList(reportEntity);
		} catch (Exception e) {
			logger.error("신고관리 리스트 조회 오류", e);
		}
		return "report/list";
	}
	
	/**
	 * 신고관리 - 상세
	 * @return
	 */
	@RequestMapping(value = "/detail/{reportKey}", method = RequestMethod.GET)
	public String reportDetail(@PathVariable("reportKey") long reportKey,
								HttpSession session,
								Model model) {
		
		ReportEntity reportEntity = new ReportEntity();
		List<ReportMemoEntity> reportMemoList = new ArrayList<ReportMemoEntity>();
		UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
		try {
			reportEntity = reportService.selectReport(reportKey);			// 신고 게시물 정보
			reportMemoList = reportService.selectReportMemoList(reportKey);	// 신고 메모 리스트
		} catch (Exception e) {
			logger.error("신고정보 조회 오류", e);
		}
		model.addAttribute("adminKey", loginSession.getUserKey());
		model.addAttribute("reportEntity", reportEntity);
		model.addAttribute("reportMemoList", reportMemoList);
		return "report/detail";
	}
	
	/**
	 * 신고문제보기 팝업
	 * @param questionKey
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/reportDetail/{questionKey}", method = RequestMethod.GET)
	public String reportDetailPopup(@PathVariable("questionKey") long questionKey,
									Model model) {
		
		QuestionEntity questionInfo = new QuestionEntity();
		AnswerEntity answerEntity = new AnswerEntity();
		
		try {
			questionInfo.setQuestionKey(questionKey);
			questionInfo = questionService.selectQuestion(questionInfo);	// 문제 정보
			answerEntity.setQuestionKey(questionKey);
			answerEntity = answerService.selectAnswerList(answerEntity);	// 답글 리스트
		} catch (Exception e) {
			logger.error("문제 상세 정보 조회 오류", e);
		}
		model.addAttribute("questionEntity", questionInfo);
		model.addAttribute("answerEntity", answerEntity);
		return "popup/reportDetail";
	}
}