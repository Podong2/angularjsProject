package kr.co.chunjae.controller.rest;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.question.ClassCodeEntity;
import kr.co.chunjae.entities.question.ClassCodeListEntity;
import kr.co.chunjae.service.CommonCodeService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/admin/comm/rest")
public class CommonRestController {
	@Autowired
	private CommonCodeService commonCodeService;

	private static final Logger logger = LoggerFactory.getLogger(CommonRestController.class);

	/**
	 * 문제 유형 목록 불러오기
	 * @param gradeCode
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getClassCode", method = RequestMethod.GET)
	public ClassCodeListEntity getClassCode(@RequestParam(required=true, value="gradeCode") String gradeCode,
											@RequestParam(required=true, value="type") String type,
											@RequestParam(required=false, value="class8") String class8,
											@RequestParam(required=false, value="class9") String class9) {

		ClassCodeListEntity result = new ClassCodeListEntity();
		try {
			ClassCodeEntity classCodeEntity = new ClassCodeEntity();
			if (!StringUtils.isEmpty(gradeCode) && !StringUtils.isEmpty(type)) {
				classCodeEntity.setType(type);
				classCodeEntity.setGradeCode(gradeCode);
				classCodeEntity.setClass8(class8);
				if(StringUtils.equals(type, CommonCode.QUESTION_TYPE_CATEGORY)){
					classCodeEntity.setClass9(class9);
				}
				result.setResultCode(ResultCode.SUCCESS);
				result.setClassCodeList(commonCodeService.selectClassTypeList(classCodeEntity));
			}
		} catch (Exception e) {
			logger.error("문제 유형 리스트 조회 오류", e);
		}
		return result;
	}
}