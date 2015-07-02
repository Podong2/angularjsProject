package kr.co.chunjae.dao;

import kr.co.chunjae.entities.FranchiseEntity;

public interface FranchiseDAO {
	/**
	 * 프랜차이즈 인증 (공부방)
	 * @param franchiseEntity
	 * @throws Exception
	 */
	int confirmFranchiseEHB(FranchiseEntity franchiseEntity)throws Exception;

	/**
	 * 프랜차이즈 인증 (셀파)
	 * @param franchiseEntity
	 * @throws Exception
	 */
	int confirmFranchiseSP(FranchiseEntity franchiseEntity)throws Exception;
}