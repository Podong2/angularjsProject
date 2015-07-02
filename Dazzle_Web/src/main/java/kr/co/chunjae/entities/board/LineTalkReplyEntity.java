package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import kr.co.chunjae.entities.PageEntity;

public class LineTalkReplyEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = 3275638502655164840L;
	public LineTalkReplyEntity() {
		super(1L);
	}

	private long talkReplyKey;			// 한줄토크 댓글 고유키
	private long talkKey;				// 한줄토크 고유키
	private long userKey;				// 회원 고유키
	private String talkReplyContents;	// 내용
	private String name;				// 이름
	private Date insertDate;			// 작성 날짜
	private int activityScore; 			// 활동 점수
	private String userRating;			// 활동점수 등급
	private String insertDateTime;		// 작성 경과 시간 (년/달/일/시/분)
	private long sessionUserKey;		// 세션 회원 고유키
	private int startRow;				//시작 개수
	private int rowCount;				//목록 개수
	private String closedYn;				// 프로필 비공개 여부
	private String fileName;
	private List<LineTalkReplyEntity> lineTalkReplyList;
	private LineTalkEntity lineTalkEntity;

	public long getTalkReplyKey() {
		return talkReplyKey;
	}
	public void setTalkReplyKey(long talkReplyKey) {
		this.talkReplyKey = talkReplyKey;
	}
	public long getTalkKey() {
		return talkKey;
	}
	public void setTalkKey(long talkKey) {
		this.talkKey = talkKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public String getTalkReplyContents() {
		return talkReplyContents;
	}
	public void setTalkReplyContents(String talkReplyContents) {
		this.talkReplyContents = talkReplyContents;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public List<LineTalkReplyEntity> getLineTalkReplyList() {
		return lineTalkReplyList;
	}
	public void setLineTalkReplyList(List<LineTalkReplyEntity> lineTalkReplyList) {
		this.lineTalkReplyList = lineTalkReplyList;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public LineTalkEntity getLineTalkEntity() {
		return lineTalkEntity;
	}
	public void setLineTalkEntity(LineTalkEntity lineTalkEntity) {
		this.lineTalkEntity = lineTalkEntity;
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSessionUserKey() {
		return sessionUserKey;
	}
	public void setSessionUserKey(long sessionUserKey) {
		this.sessionUserKey = sessionUserKey;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getClosedYn() {
		return closedYn;
	}
	public void setClosedYn(String closedYn) {
		this.closedYn = closedYn;
	}

}