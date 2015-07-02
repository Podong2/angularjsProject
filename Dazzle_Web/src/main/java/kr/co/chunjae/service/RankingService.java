package kr.co.chunjae.service;

import kr.co.chunjae.entities.RankingEntity;

public interface RankingService {

	/**
	 * 개인 랭킹(누적)
	 * @return
	 * @throws Exception
	 */
	RankingEntity selectUserRanking() throws Exception;

	/**
	 * 그룹 랭킹(누적)
	 * @return
	 * @throws Exception
	 */
	RankingEntity selectGroupRanking() throws Exception;

	/**
	 * 랭킹날짜 가져오기(이전 6개월)
	 * @return
	 * @throws Exception
	 */
	RankingEntity selectMonthDateList() throws Exception;


	/**
	 * 개인 랭킹(월별)
	 * @return
	 * @throws Exception
	 */
	RankingEntity selectMonthUserRanking(String rankingDate) throws Exception;

	/**
	 * 그룹 랭킹(월별)
	 * @return
	 * @throws Exception
	 */
	RankingEntity selectMonthGroupRanking(String rankingDate) throws Exception;


}