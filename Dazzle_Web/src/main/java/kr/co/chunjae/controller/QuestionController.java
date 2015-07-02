package kr.co.chunjae.controller;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.entities.common.CommonCodeEntity;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.ClassCodeEntity;
import kr.co.chunjae.entities.question.QuestionEntity;
import kr.co.chunjae.service.AnswerService;
import kr.co.chunjae.service.CommonCodeService;
import kr.co.chunjae.service.QuestionService;

import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "/admin/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private CommonCodeService commonCodeService;

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	/**
	 * 문제 관리 - 리스트
	 * @param questionType (all:일반/그룹문제, daq:다Q문제)
	 * @param questionEntity
	 * @return
	 */
	@RequestMapping(value = "/list/{questionType}", method = RequestMethod.GET)
	public String questionList(@PathVariable("questionType") String questionType,
								@ModelAttribute("questionEntity") QuestionEntity questionEntity) {

		try {
			questionEntity.setPageSize(CommonCode.QUESTION_PAGE_GROUP_SIZE_PARAM, CommonCode.QUESTION_PAGE_GROUP_SIZE_PARAM);
			questionEntity.setQuestionType(questionType);
			questionService.selectQuestionList(questionEntity);
		} catch (Exception e) {
			logger.error("문제관리 리스트 조회 오류", e);
		}
		return "question/list";
	}

	/**
	 * 문제 관리 - 상세
	 * @param questionType (all:일반/그룹문제, daq:다Q문제)
	 * @param questionKey
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail/{questionType}/{questionKey}", method = RequestMethod.GET)
	public String questionDetail(@PathVariable("questionType") String questionType,
								@PathVariable("questionKey") long questionKey,
								Model model) {

		QuestionEntity questionInfo = new QuestionEntity();
		AnswerEntity answerEntity = new AnswerEntity();
		try {
			QuestionEntity questionEntity = new QuestionEntity();
			questionEntity.setQuestionKey(questionKey);
			questionInfo = questionService.selectQuestion(questionEntity);	// 문제 정보
			answerEntity.setQuestionKey(questionKey);
			answerEntity = answerService.selectAnswerList(answerEntity);	// 답글 리스트

			List<ClassCodeEntity> classTypeMediumList = new ArrayList<ClassCodeEntity>();
			List<ClassCodeEntity> classTypeSmallList = new ArrayList<ClassCodeEntity>();
			List<ClassCodeEntity> classTypeCategoryList = new ArrayList<ClassCodeEntity>();
			if (StringUtils.isNotEmpty(questionType) &&
					StringUtils.equals(questionType, CommonCode.QUESTION_TYPE_ALL)) {

				// 학년
				List<CommonCodeEntity> gradeCodeList = commonCodeService.selectCommonCodeListByCode("CLASS_TYPE");
				// 대단원(ex.1101000000)
				ClassCodeEntity classCodeLargeEntity = new ClassCodeEntity();
				classCodeLargeEntity.setType(CommonCode.QUESTION_TYPE_LARGE);
				classCodeLargeEntity.setGradeCode(questionInfo.getQuestionGrade());
				List<ClassCodeEntity> classTypeLargeList = commonCodeService.selectClassTypeList(classCodeLargeEntity);
				String class9 = null;
				// 중단원(ex.1101010000)
				if (StringUtils.isNotEmpty(questionInfo.getQuestionTypeLarge())) {
					ClassCodeEntity classCodeMediumEntity = new ClassCodeEntity();
					classCodeMediumEntity.setType(CommonCode.QUESTION_TYPE_MEDIUM);
					classCodeMediumEntity.setGradeCode(questionInfo.getQuestionGrade());
					String class8 = questionInfo.getQuestionTypeLarge().substring(2, 4);
					if(StringUtils.isNotEmpty(questionInfo.getQuestionTypeSmall())){
						class9 = questionInfo.getQuestionTypeSmall().substring(6, 8);
					}
					classCodeMediumEntity.setClass8(class8);
					classTypeMediumList = commonCodeService.selectClassTypeList(classCodeMediumEntity);
					// 소단원(ex.1101010100)
					ClassCodeEntity classCodeSmallEntity = new ClassCodeEntity();
					classCodeSmallEntity.setType(CommonCode.QUESTION_TYPE_SMALL);
					classCodeSmallEntity.setGradeCode(questionInfo.getQuestionGrade());
					classCodeSmallEntity.setClass8(class8);
					classTypeSmallList = commonCodeService.selectClassTypeList(classCodeSmallEntity);
					// 유형(ex.1101010101)
					ClassCodeEntity classCodeCategoryEntity = new ClassCodeEntity();
					classCodeCategoryEntity.setType(CommonCode.QUESTION_TYPE_CATEGORY);
					classCodeCategoryEntity.setGradeCode(questionInfo.getQuestionGrade());
					classCodeCategoryEntity.setClass8(class8);
					if(StringUtils.isNotEmpty(questionInfo.getQuestionTypeSmall())){
						classCodeCategoryEntity.setClass9(class9);
					}
					classTypeCategoryList = commonCodeService.selectClassTypeList(classCodeCategoryEntity);
				}

				model.addAttribute("classTypeLargeList", classTypeLargeList);
				model.addAttribute("classTypeMediumList", classTypeMediumList);
				model.addAttribute("classTypeSmallList", classTypeSmallList);
				model.addAttribute("classTypeCategoryList", classTypeCategoryList);
				model.addAttribute("gradeCodeList", gradeCodeList);
			}
		} catch (Exception e) {
			logger.error("문제 상세 정보 조회 오류", e);
		}
		model.addAttribute("questionEntity", questionInfo);
		model.addAttribute("answerEntity", answerEntity);
		return "question/detail";
	}

	/**
	 * 다Q문제 관리 - 등록, 수정
	 * @param questionKey
	 * @return
	 */
	@RequestMapping(value = "/daqForm/{questionKey}", method = RequestMethod.GET)
	public String daqForm(@PathVariable("questionKey") long questionKey,
							Model model) {

		QuestionEntity questionInfo = new QuestionEntity();
		if (questionKey > 0) {
			try {
				questionInfo.setQuestionKey(questionKey);
				questionInfo = questionService.selectQuestion(questionInfo);	// 문제 정보
			} catch (Exception e) {
				logger.error("문제 정보 조회 오류", e);
			}
		}

		model.addAttribute("questionEntity", questionInfo);
		return "question/daqForm";
	}
}