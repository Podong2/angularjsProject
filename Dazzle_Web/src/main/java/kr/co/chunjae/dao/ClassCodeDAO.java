package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.question.ClassCodeEntity;

public interface ClassCodeDAO {
	/**
	 * 문제 유형 목록
	 * @param classCodeEntity
	 * @return
	 * @throws Exception
	 */
	List<ClassCodeEntity> selectClassTypeList(ClassCodeEntity classCodeEntity);
	
	/**
	 * 메인 문제 유형 검색 리스트
	 * @param name1
	 * @return
	 */
	List<ClassCodeEntity> selectSearchClassTypeList(String name1);
}