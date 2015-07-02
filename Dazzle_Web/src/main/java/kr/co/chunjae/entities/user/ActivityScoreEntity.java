package kr.co.chunjae.entities.user;

import java.io.Serializable;
import java.util.List;

public class ActivityScoreEntity implements Serializable {
	
	private static final long serialVersionUID = 1212228223869313517L;
	
	private long activityscoreKey;		//활동점수 고유키
	private String code;				//활동점수 코드
	private String activityContent;		//개인활동내용
	private int userScore;				//회원 점수
	private int groupScore;				//그룹 점수
	private String etc;					//기타
    private List<ActivityScoreEntity> activityScoreList; //활동점수 목록

    public List<ActivityScoreEntity> getActivityScoreList() {
        return activityScoreList;
    }

    public void setActivityScoreList(List<ActivityScoreEntity> activityScoreList) {
        this.activityScoreList = activityScoreList;
    }

    public long getActivityscoreKey() {
        return activityscoreKey;
    }
    public void setActivityscoreKey(long activityscoreKey) {
        this.activityscoreKey = activityscoreKey;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getActivityContent() {
        return activityContent;
    }
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }
    public int getUserScore() {
        return userScore;
    }
    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
    public int getGroupScore() {
        return groupScore;
    }
    public void setGroupScore(int groupScore) {
        this.groupScore = groupScore;
    }
    public String getEtc() {
        return etc;
    }
    public void setEtc(String etc) {
        this.etc = etc;
    }
}