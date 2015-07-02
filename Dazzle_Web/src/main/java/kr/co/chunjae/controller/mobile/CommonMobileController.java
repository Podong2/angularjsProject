package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.common.CommonCodeListEntity;
import kr.co.chunjae.entities.question.ClassCodeEntity;
import kr.co.chunjae.entities.question.ClassCodeListEntity;
import kr.co.chunjae.service.CommonCodeService;
import kr.co.digigroove.commons.messages.Messages;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/common")
public class CommonMobileController {
	@Autowired
	private AttachDAO attachDAO;
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private Messages messages;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonMobileController.class);
	
	/**
	 * 공통코드 목록 조회
	 * @param grpCd
	 * @return
	 */
	@RequestMapping(value="/selectCommonCodeList", method = RequestMethod.GET)
	public CommonCodeListEntity selectCommonCodeList(@RequestParam("grpCd") String grpCd) {
		
		CommonCodeListEntity codeListEntity = new CommonCodeListEntity();
		if (StringUtils.isNotEmpty(grpCd)) {
			codeListEntity.setResultCode(ResultCode.SUCCESS);
			codeListEntity.setCommonCodeList(commonCodeService.selectCommonCodeListByCode(grpCd));
		}
		return codeListEntity;
	}
	
	/**
	 * 문제유형 목록 조회
	 * @param questionType
	 * @param gradeCode
	 * @return
	 */
	@RequestMapping(value="/selectClassTypeList", method = RequestMethod.GET)
	public ClassCodeListEntity selectClassTypeList(@RequestParam("questionType") String questionType,
													@RequestParam("gradeCode") String gradeCode) {
		
		ClassCodeListEntity codeListEntity = new ClassCodeListEntity();
		if (StringUtils.isNotEmpty(questionType)) {
			ClassCodeEntity classCodeEntity = new ClassCodeEntity();
			classCodeEntity.setType(questionType);
			classCodeEntity.setGradeCode(gradeCode);
			codeListEntity.setResultCode(ResultCode.SUCCESS);
			codeListEntity.setClassCodeList(commonCodeService.selectClassTypeList(classCodeEntity));
		}
		
		return codeListEntity;
	}
	
	/**
	 * 메인 문제 유형 검색 리스트
	 * @param name1
	 * @return
	 */
	@RequestMapping(value="/selectSearchClassTypeList", method = RequestMethod.GET)
	public ClassCodeListEntity selectSearchClassTypeList(@RequestParam("name1") String name1) {
		
		ClassCodeListEntity codeListEntity = new ClassCodeListEntity();
		if (StringUtils.isNotEmpty(name1)) {
			codeListEntity.setResultCode(ResultCode.SUCCESS);
			codeListEntity.setClassCodeList(commonCodeService.selectSearchClassTypeList(name1));
		}
		return codeListEntity;
	}

	@RequestMapping(value="/selectAttachInfo", method = RequestMethod.GET)
	public AttachEntity selectAttachInfo(@RequestParam("commonKey") long commonKey,
									@RequestParam("attachType") String attachType) {
		
		AttachEntity attachInfo = new AttachEntity();
		try {
			AttachEntity attachEntity = new AttachEntity();
			attachEntity.setCommonKey(commonKey);
			attachEntity.setAttachType(attachType);
			attachInfo = attachDAO.selectAttachInfo(attachEntity);
		} catch (Exception e) {
			logger.error("첨부파일 정보 조회 오류", e);
		}
		return attachInfo;
	}
}