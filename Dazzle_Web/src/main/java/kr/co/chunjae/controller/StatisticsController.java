package kr.co.chunjae.controller;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.entities.statistics.GradeEntity;
import kr.co.chunjae.service.StatisticsService;
import kr.co.digigroove.commons.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/statistics")
public class StatisticsController {
	@Autowired
	StatisticsService statisticsService;

	private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

	/**
	 * 학년별 통계
	 * @param gradeEntity
	 * @return
	 */
	@RequestMapping(value = "/list/grade", method = RequestMethod.GET)
	public String selectStatisticsGrade(@ModelAttribute("gradeEntity")GradeEntity gradeEntity) {
		try {
			statisticsService.selectGradeStatictics(gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 학년별 통계 : Error ", e);
		}
		return "statistics/grade";
	}

	/**
	 * 학년별 대단원 통계
	 * @param gradeCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gradeDetail/{questionGrade}/{commCdNm}", method = RequestMethod.GET)
	public String selectGradeDetail(@PathVariable("questionGrade") String questionGrade,
			@PathVariable("commCdNm") String commCdNm,
			@RequestParam(required=false, value="startDate") String startDate,
			@RequestParam(required=false, value="endDate") String endDate,
			Model model) {
		GradeEntity gradeEntity = new GradeEntity();
		gradeEntity.setCommCdNm(commCdNm);
		try {
			questionGrade = questionGrade.substring(0,1);
			gradeEntity.setClassCodeForm(questionGrade.concat(CommonCode.STATISTICS_GRADE_CODE_FORM));
			gradeEntity.setStartDate(startDate);
			gradeEntity.setEndDate(endDate);

			gradeEntity = statisticsService.selectGradeDetail(gradeEntity);
			model.addAttribute("gradeEntity", gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 학년별 대단원 통계 : Error ", e);
		}
		return "popup/statisticsDetail";
	}

	/**
	 * 학년별 통계 엑셀
	 * @param gradeEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/gradeList", method = RequestMethod.GET)
	public String statisticsGradeExcelList(@ModelAttribute("gradeEntity")GradeEntity gradeEntity, Model model) {

		try {
			statisticsService.selectGradeStatictics(gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 학년별 통계 엑셀출력 : Error ", e);
		}
		model.addAttribute("excelName", "gradeEntity");
		return "excel/statisticsGradeExcelList";
	}


	/**
	 * 기간별 통계
	 * @param gradeEntity
	 * @return
	 */
	@RequestMapping(value = "/list/period", method = RequestMethod.GET)
	public String selectStatisticsPeriod(@ModelAttribute("gradeEntity")GradeEntity gradeEntity) {
		try {
			if(!StringUtils.isEmpty(gradeEntity.getStartMonth())){
				gradeEntity.setStartDate(gradeEntity.getStartMonth()+ CommonCode.STATISTICS_PERIOD_MONTH_FORM);
				gradeEntity.setEndDate(gradeEntity.getEndMonth()+CommonCode.STATISTICS_PERIOD_MONTH_FORM);
			}else if(!StringUtils.isEmpty(gradeEntity.getStartYear())){
				gradeEntity.setStartDate(gradeEntity.getStartYear()+CommonCode.STATISTICS_PERIOD_START_YEAR_FORM);
				gradeEntity.setEndDate(gradeEntity.getEndYear()+CommonCode.STATISTICS_PERIOD_END_YEAR_FORM);
			}else if(!StringUtils.isEmpty(gradeEntity.getStartDay())){
				gradeEntity.setStartDate(gradeEntity.getStartDay());
				gradeEntity.setEndDate(gradeEntity.getEndDay());
			}
			if(!StringUtils.isEmpty(gradeEntity.getStartDate())){
				statisticsService.selectPeriodStatictics(gradeEntity);
			}
		} catch (Exception e) {
			logger.error(" 관리자 기간별 통계 : Error ", e);
		}
		return "statistics/period";
	}

	/**
	 * 기간별 통계 엑셀
	 * @param gradeEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/periodList", method = RequestMethod.GET)
	public String statisticsPeriodExcelList(@ModelAttribute("gradeEntity")GradeEntity gradeEntity, Model model) {

		try {
			if(!StringUtils.isEmpty(gradeEntity.getStartMonth())){
				gradeEntity.setStartDate(gradeEntity.getStartMonth()+ CommonCode.STATISTICS_PERIOD_MONTH_FORM);
				gradeEntity.setEndDate(gradeEntity.getEndMonth()+CommonCode.STATISTICS_PERIOD_MONTH_FORM);
			}else if(!StringUtils.isEmpty(gradeEntity.getStartYear())){
				gradeEntity.setStartDate(gradeEntity.getStartYear()+CommonCode.STATISTICS_PERIOD_START_YEAR_FORM);
				gradeEntity.setEndDate(gradeEntity.getEndYear()+CommonCode.STATISTICS_PERIOD_END_YEAR_FORM);
			}else if(!StringUtils.isEmpty(gradeEntity.getStartDay())){
				gradeEntity.setStartDate(gradeEntity.getStartDay());
				gradeEntity.setEndDate(gradeEntity.getEndDay());
			}
			if(!StringUtils.isEmpty(gradeEntity.getStartDate())){
				statisticsService.selectPeriodStatictics(gradeEntity);
			}
		} catch (Exception e) {
			logger.error(" 관리자 기간별 통계 엑셀출력 : Error ", e);
		}
		model.addAttribute("excelName", "gradeEntity");
		return "excel/statisticsPeriodExcelList";
	}

	/**
	 * 활동개수 통계
	 * @param gradeEntity
	 * @return
	 */
	@RequestMapping(value = "/list/activityCount", method = RequestMethod.GET)
	public String selectStatisticsActivityCount(@ModelAttribute("gradeEntity")GradeEntity gradeEntity) {
		try {
			statisticsService.selectStaticticsActivityCount(gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 활동개수 통계 : Error ", e);
		}
		return "statistics/activityCount";
	}

	/**
	 * 활동개수 통계 엑셀
	 * @param gradeEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/countList", method = RequestMethod.GET)
	public String statisticsCountExcelList(@ModelAttribute("gradeEntity")GradeEntity gradeEntity, Model model) {

		try {
			statisticsService.selectStaticticsActivityCount(gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 활동개수 통계 엑셀출력 : Error ", e);
		}
		model.addAttribute("excelName", "gradeEntity");
		return "excel/statisticsCountExcelList";
	}

	/**
	 * 활동점수 통계
	 * @param gradeEntity
	 * @return
	 */
	@RequestMapping(value = "/list/activityScore", method = RequestMethod.GET)
	public String selectStatisticsActivityScore(@ModelAttribute("gradeEntity")GradeEntity gradeEntity) {
		try {
			statisticsService.selectStaticticsActivityScore(gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 활동점수 통계 : Error ", e);
		}
		return "statistics/activityScore";
	}

	/**
	 * 활동점수 통계 엑셀
	 * @param gradeEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/scoreList", method = RequestMethod.GET)
	public String statisticsScoreExcelList(@ModelAttribute("gradeEntity")GradeEntity gradeEntity, Model model) {

		try {
			statisticsService.selectStaticticsActivityScore(gradeEntity);
		} catch (Exception e) {
			logger.error(" 관리자 활동점수 통계 엑셀출력 : Error ", e);
		}
		model.addAttribute("excelName", "gradeEntity");
		return "excel/statisticsScoreExcelList";
	}


}