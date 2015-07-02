package kr.co.chunjae.dao;
import java.util.List;

import kr.co.chunjae.entities.common.CommonCodeEntity;

public interface CommonCodeDAO {
	/**
	 * 공통코드 정보 가져오기 (그룹코드값으로)
	 * @param commonCodeEntity
	 * @return
	 * @throws Exception
	 */
	List<CommonCodeEntity> selectCommonCodeListByCode(String grpCd);
}