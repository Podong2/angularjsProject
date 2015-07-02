package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import kr.co.chunjae.entities.ResponseEntity;

public class LineTalkEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 6606411711211239252L;

	private long talkKey;			// 한줄토크 고유키
	private long userKey;			// 회원 고유키
	private String typeCode;		// 사용자 타입 (01: 회원, 02: 전문가 회원, 03:관리자)
	private String name;			// 작성자 이름
	private int activityScore;		// 활동점수
	private String userRating;		// 활동점수 등급
	private long writerKey;			// 작성자 고유키
	private String talkContents;	// 내용
	private String readYn;			// 댓글에 댓글 읽음 여부
	private int replyCount;			// 하위 댓글 개수
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;		// 작성 날짜
	private Date deleteDate;		// 탈퇴 날짜
	private String insertDateTime;	// 작성 경과 시간 (년/달/일/시/분)
	private String fileName;			// 프로필 이미지
	private String closedYn;			// 프로필 비공개 여부

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

	public long getWriterKey() {
		return writerKey;
	}

	public void setWriterKey(long writerKey) {
		this.writerKey = writerKey;
	}

	public String getTalkContents() {
		return talkContents;
	}

	public void setTalkContents(String talkContents) {
		this.talkContents = talkContents;
	}

	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}

	public Date getDeleteDate() {
		return deleteDate == null ? null : new Date(deleteDate.getTime());
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
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

	public String getUserRating() {
		return userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

	public String getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public String getReadYn() {
		return readYn;
	}

	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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