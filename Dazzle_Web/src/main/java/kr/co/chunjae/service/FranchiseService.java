package kr.co.chunjae.service;

import kr.co.chunjae.entities.FranchiseEntity;
import kr.co.chunjae.entities.ResponseEntity;

public interface FranchiseService {

	/**
	 * 프랜차이즈 인증
	 * @param franchiseEntity
	 * @return
	 * @throws Exception
	 */
	ResponseEntity confirmFranchiseInfo(FranchiseEntity franchiseEntity) throws Exception;
}