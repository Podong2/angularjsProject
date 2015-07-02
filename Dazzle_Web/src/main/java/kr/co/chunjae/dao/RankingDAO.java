package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.RankingEntity;

public interface RankingDAO {
	/**
	 * 개인 랭킹(누적)
	 * @throws Exception
	 */
    List<RankingEntity> selectUserRanking()throws Exception;

	/**
	 * 그룹 랭킹(누적)
	 * @throws Exception
	 */
    List<RankingEntity> selectGroupRanking()throws Exception;

    /**
     * 랭킹날짜 가져오기(이전 6개월)
     * @return
     * @throws Exception
     */
    List<RankingEntity> selectMonthDateList()throws Exception;

	/**
	 * 개인 랭킹(월별)
	 * @throws Exception
	 */
    List<RankingEntity> selectMonthUserRanking(String rankingDate)throws Exception;

	/**
	 * 그룹 랭킹(월별)
	 * @throws Exception
	 */
    List<RankingEntity> selectMonthGroupRanking(String rankingDate)throws Exception;

}