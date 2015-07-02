package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.sql.Date;

public class ScrapEntity implements Serializable {

	private static final long serialVersionUID = -8636366468037157927L;

	private long scrapKey;			// 스크랩 테이블 고유키
	private long scrapBoardKey;		// 스크랩할 게시물 고유키
	private long userKey;			// 회원 고유키
	private long groupKey;			// 그룹 고유키
	private String scrapTypeCode;	// 스크랩 타입(01:개인 스크랩, 02:그룹 스크랩)
	private String scrapYn;			// 스크랩 여부
	private Date insertDate;			// 스크랩 날짜
	private String groupName;		// 그룹명
	private String questionTypeCode; // 게시판 타입(01: 개인, 02: 그룹, 03: 다큐)

	public long getScrapKey() {
		return scrapKey;
	}
	public void setScrapKey(long scrapKey) {
		this.scrapKey = scrapKey;
	}
	public long getScrapBoardKey() {
		return scrapBoardKey;
	}
	public void setScrapBoardKey(long scrapBoardKey) {
		this.scrapBoardKey = scrapBoardKey;
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
	public String getScrapTypeCode() {
		return scrapTypeCode;
	}
	public void setScrapTypeCode(String scrapTypeCode) {
		this.scrapTypeCode = scrapTypeCode;
	}
	public String getScrapYn() {
		return scrapYn;
	}
	public void setScrapYn(String scrapYn) {
		this.scrapYn = scrapYn;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getQuestionTypeCode() {
		return questionTypeCode;
	}
	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}
}