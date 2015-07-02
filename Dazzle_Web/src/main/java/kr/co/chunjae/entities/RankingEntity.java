package kr.co.chunjae.entities;

import java.io.Serializable;
import java.util.List;

public class RankingEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 5569962516387774268L;

	private long userKey;						// 회원 고유키
	private long groupKey;						// 그룹 고유키
	private int ranking;							// 등수
	private String name;							// 회원명, 그룹명
	private int activityScore;					// 총 활동점수
	private String userRating;					// 회원 등급
	private List<RankingEntity> rankingList;	// 랭킹 목록
	private String monthDate;					// 랭킹 날짜 ("yyyy-MM-dd HH:mm:ss)
	private int month;							// 랭킹 날짜 (MM)
	private int monthActivityScore;				// 월별 랭킹 활동점수
	private String rankingDate;					// 입력 날짜
	private String changedRanking;				// 변동 등수(양, 음수 포함)
	private String changedRankingTxt;			// 변동 등수(양수값 변경값)

	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public List<RankingEntity> getRankingList() {
		return rankingList;
	}
	public void setRankingList(List<RankingEntity> rankingList) {
		this.rankingList = rankingList;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	public int getMonthActivityScore() {
		return monthActivityScore;
	}
	public void setMonthActivityScore(int monthActivityScore) {
		this.monthActivityScore = monthActivityScore;
	}
	public String getRankingDate() {
		return rankingDate;
	}
	public void setRankingDate(String rankingDate) {
		this.rankingDate = rankingDate;
	}
	public String getChangedRanking() {
		return changedRanking;
	}
	public void setChangedRanking(String changedRanking) {
		this.changedRanking = changedRanking;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getChangedRankingTxt() {
		return changedRankingTxt;
	}
	public void setChangedRankingTxt(String changedRankingTxt) {
		this.changedRankingTxt = changedRankingTxt;
	}


}