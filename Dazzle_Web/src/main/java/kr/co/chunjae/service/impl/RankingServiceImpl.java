package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.RankingDAO;
import kr.co.chunjae.entities.RankingEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.RankingService;
import kr.co.digigroove.commons.messages.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RankingServiceImpl implements RankingService{
	@Autowired
	private RankingDAO rankingDAO;

	@Autowired
	private GradeScoreDAO gradeScoreDAO;

	@Autowired
	private Messages messages;

	/**
	 * 개인 랭킹(누적)
	 * @return
	 * @throws Exception
	 */
	@Override
	public RankingEntity selectUserRanking() throws Exception {
		RankingEntity rankingEntity = new RankingEntity();
		List<RankingEntity> RankingList = new ArrayList<RankingEntity>();
		RankingList = rankingDAO.selectUserRanking();
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 사용자 상세 프로필 등급
		for (RankingEntity rankingData : RankingList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= rankingData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= rankingData.getActivityScore()) {
					rankingData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		rankingEntity.setRankingList(RankingList);
		return rankingEntity;
	}

	/**
	 * 그룹 랭킹(누적)
	 * @return
	 * @throws Exception
	 */
	@Override
	public RankingEntity selectGroupRanking() throws Exception {
		RankingEntity rankingEntity = new RankingEntity();
		List<RankingEntity> RankingList = new ArrayList<RankingEntity>();
		RankingList = rankingDAO.selectGroupRanking();
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 그룹 상세 프로필 등급
		for (RankingEntity rankingData : RankingList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getGroupLowScore() <= rankingData.getActivityScore()
						&& gradeScoreList.get(i).getGroupHighScore() >= rankingData.getActivityScore()) {
					rankingData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		rankingEntity.setRankingList(RankingList);
		return rankingEntity;
	}

	/**
	 * 랭킹날짜 가져오기(이전 6개월)
	 * @return
	 * @throws Exception
	 */
	@Override
	public RankingEntity selectMonthDateList() throws Exception{
		RankingEntity rankingEntity = new RankingEntity();
		List<RankingEntity> RankingMonthList = new ArrayList<RankingEntity>();
		RankingMonthList = rankingDAO.selectMonthDateList();
		rankingEntity.setRankingList(RankingMonthList);
		return rankingEntity;
	}

	/**
	 * 개인 랭킹(월별)
	 * @return
	 * @throws Exception
	 */
	@Override
	public RankingEntity selectMonthUserRanking(String rankingDate) throws Exception {
		RankingEntity rankingEntity = new RankingEntity();
		List<RankingEntity> RankingList = new ArrayList<RankingEntity>();
		RankingList = rankingDAO.selectMonthUserRanking(rankingDate);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 사용자 상세 프로필 등급
		for (RankingEntity rankingData : RankingList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= rankingData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= rankingData.getActivityScore()) {
					rankingData.setUserRating(String.format("%02d", i+1));
				}
			}
			rankingData.setChangedRankingTxt(rankingData.getChangedRanking().replace("-", ""));
		}
		rankingEntity.setRankingList(RankingList);
		return rankingEntity;
	}

	/**
	 * 그룹 랭킹(월별)
	 * @return
	 * @throws Exception
	 */
	@Override
	public RankingEntity selectMonthGroupRanking(String rankingDate) throws Exception {
		RankingEntity rankingEntity = new RankingEntity();
		List<RankingEntity> RankingList = new ArrayList<RankingEntity>();
		RankingList = rankingDAO.selectMonthGroupRanking(rankingDate);
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 그룹 상세 프로필 등급
		for (RankingEntity rankingData : RankingList){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getGroupLowScore() <= rankingData.getActivityScore()
						&& gradeScoreList.get(i).getGroupHighScore() >= rankingData.getActivityScore()) {
					rankingData.setUserRating(String.format("%02d", i+1));
				}
			}
			rankingData.setChangedRankingTxt(rankingData.getChangedRanking().replace("-", ""));
		}
		rankingEntity.setRankingList(RankingList);
		return rankingEntity;
	}


}