package kr.co.chunjae.entities.user;

import java.io.Serializable;
import java.util.Date;

import kr.co.chunjae.entities.ResponseEntity;

public class ActivityLogEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = -3101002525195484938L;
	public static final String LOG_TYPE_USER = "01";	// 활동점수 부여받은 사용자코드
	public static final String LOG_TYPE_ADMIN = "11";	// 활동점수 부여한 관리자코드

	private long activityLogKey;		// 활동로그 고유키
	private long userKey;				// 회원 고유키
	private long groupKey;				// 그룹 고유키
	private Date insertDate;			// 작성 날짜
	private String activityWords;		// 활동내역 문구
	private int activityScore;			// 활동점수
	private int userActivityScore;		// 개인 부여 활동점수
	private int groupActivityScore;		// 그룹 부여 활동점수
	private String activityTypeCode;	// 활동 구분 타입(01:문제 등록, 02:답글 등록, 03:문제 좋아요, 04:답글 좋아요, 05:채택 답글,
										// 06:다Q최초 그룹이동, 07:App 최초가입, 08:그룹 생성, 09:멤버 초대, 10:멤버 가입, 11:관리자 점수 부여)
	private int totalActivityScore;		// 총 활동점수
	private String insertDateTime;		// 작성 경과 시간 (년/달/일/시/분)
	private String userRating; 			// 사용자 등급
	private String fileName;				// 이미지 명
	private String name;					// 사용자 명
	private String groupName;			// 그룹명
	private long commonKey;				// 공통 고유키
	private int activityLogScore;		// 활동점수 로그

	public long getActivityLogKey() {
		return activityLogKey;
	}
	public void setActivityLogKey(long activityLogKey) {
		this.activityLogKey = activityLogKey;
	}
	public long getUserKey() {
        return userKey;
    }
    public void setUserKey(long userKey) {
        this.userKey = userKey;
    }
    public long getGroupKey() {
        return groupKey;
    }
    public void setGroupKey(long groupKey) {
        this.groupKey = groupKey;
    }
    public Date getInsertDate() {
        return insertDate == null ? null : new Date(insertDate.getTime());
    }
    public void setInsertDate(Date insertDate) {
    	this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
    }
    public String getActivityWords() {
        return activityWords;
    }
    public void setActivityWords(String activityWords) {
        this.activityWords = activityWords;
    }
    public int getActivityScore() {
        return activityScore;
    }
    public void setActivityScore(int activityScore) {
        this.activityScore = activityScore;
    }
    public int getUserActivityScore() {
		return userActivityScore;
	}
	public void setUserActivityScore(int userActivityScore) {
		this.userActivityScore = userActivityScore;
	}
	public int getGroupActivityScore() {
		return groupActivityScore;
	}
	public void setGroupActivityScore(int groupActivityScore) {
		this.groupActivityScore = groupActivityScore;
	}
	public String getActivityTypeCode() {
        return activityTypeCode;
    }
    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }
	public int getTotalActivityScore() {
		return totalActivityScore;
	}
	public void setTotalActivityScore(int totalActivityScore) {
		this.totalActivityScore = totalActivityScore;
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getActivityLogScore() {
		return activityLogScore;
	}
	public void setActivityLogScore(int activityLogScore) {
		this.activityLogScore = activityLogScore;
	}
	public long getCommonKey() {
		return commonKey;
	}
	public void setCommonKey(long commonKey) {
		this.commonKey = commonKey;
	}
}