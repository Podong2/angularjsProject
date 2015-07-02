package kr.co.chunjae.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.dao.ClassCodeDAO;
import kr.co.chunjae.dao.CommonCodeDAO;
import kr.co.chunjae.entities.common.CommonCodeEntity;
import kr.co.chunjae.entities.question.ClassCodeEntity;
import kr.co.chunjae.service.CommonCodeService;

@Service
@Transactional(readOnly = true)
public class CommonCodeServiceImpl implements CommonCodeService {
	@Autowired
	private CommonCodeDAO commonCodeDAO;
	@Autowired
	private ClassCodeDAO classCodeDAO;

	private static final Logger logger = LoggerFactory.getLogger(CommonCodeServiceImpl.class);

	/**
	 * 공통코드 정보 (그룹코드값으로)
	 * @param grpCd
	 * @return
	 */
	@Override
	public List<CommonCodeEntity> selectCommonCodeListByCode(String grpCd) {

		return commonCodeDAO.selectCommonCodeListByCode(grpCd);
	}

	/**
	 * 문제유형 리스트
	 * @param classCodeEntity
	 * @return
	 */
	@Override
	public List<ClassCodeEntity> selectClassTypeList(ClassCodeEntity classCodeEntity) {

		logger.debug("classCode type :: "+ classCodeEntity.getType());
		//List<ClassCodeEntity> codeList = new ArrayList<ClassCodeEntity>();

		if (!StringUtils.isEmpty(classCodeEntity.getGradeCode())
				&& !StringUtils.isEmpty(classCodeEntity.getType())) {

			String class6 = classCodeEntity.getGradeCode().replace("0", "");
			StringBuffer classCode = new StringBuffer(class6);
			// 대단원
			if (StringUtils.equals(classCodeEntity.getType(), CommonCode.QUESTION_TYPE_LARGE)) {
				classCode.append("_0_000000");
				// TODO
				/*if (StringUtils.equals(classCodeEntity.getGradeCode(), "00")) {
					ClassCodeEntity classCodeInfo = new ClassCodeEntity();
					classCodeInfo.setName1("전체");
					classCodeInfo.setClassCode("0000000000");
					codeList.add(classCodeInfo);
				}*/
			}
			// 중단원
			else if (StringUtils.equals(classCodeEntity.getType(), CommonCode.QUESTION_TYPE_MEDIUM)) {
				classCode.append("_");
				classCode.append(classCodeEntity.getClass8());
				classCode.append(classCodeEntity.getClass8());
				classCode.append("0000");
			}
			// 소단원
			else if (StringUtils.equals(classCodeEntity.getType(), CommonCode.QUESTION_TYPE_SMALL)) {
				classCode.append("_");
				classCode.append(classCodeEntity.getClass8());
				classCode.append(classCodeEntity.getClass8());
				//classCode.append(classCodeEntity.getClass8());
				classCode.append("__");
				classCode.append("00");
			}
			// 유형
			else if (StringUtils.equals(classCodeEntity.getType(), CommonCode.QUESTION_TYPE_CATEGORY)) {
				classCode.append("_");
				classCode.append(classCodeEntity.getClass8());
				classCode.append(classCodeEntity.getClass8());
				classCode.append(classCodeEntity.getClass9());
				classCode.append("__");
				//classCode.append("____");
			}
			classCodeEntity.setClassCode(classCode.toString());
		}
		return classCodeDAO.selectClassTypeList(classCodeEntity);
	}

	/**
	 * 메인 문제 유형 검색 리스트
	 * @param name1
	 * @return
	 */
	@Override
	public List<ClassCodeEntity> selectSearchClassTypeList(String name1) {

		return classCodeDAO.selectSearchClassTypeList(name1);
	}
}