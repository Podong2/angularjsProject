package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.common.CommonCodeEntity;
import kr.co.chunjae.entities.question.ClassCodeEntity;

public interface CommonCodeService {
	/**
	 * 공통코드 정보 (그룹코드값으로)
	 * @param grpCd
	 * @return
	 */
	List<CommonCodeEntity> selectCommonCodeListByCode(String grpCd);
	/**
	 * 문제유형 리스트
	 * @param classCodeEntity
	 * @return
	 */
	List<ClassCodeEntity> selectClassTypeList(ClassCodeEntity classCodeEntity);
	/**
	 * 메인 문제 유형 검색 리스트
	 * @param name1
	 * @return
	 */
	List<ClassCodeEntity> selectSearchClassTypeList(String name1);
}