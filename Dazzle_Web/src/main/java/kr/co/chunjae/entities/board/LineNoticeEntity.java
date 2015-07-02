package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import kr.co.chunjae.entities.PageEntity;

public class LineNoticeEntity extends PageEntity implements Serializable {
	
	private static final long serialVersionUID = -148401372676330243L;

	public LineNoticeEntity() {
		super(1L);
	}
	private long lineNoticeKey;						//한줄알림 고유키
	private String lineNoticeContents;				//제목
	private Date insertDate;						//작성 날짜
	private Date eventStartDate;					//한줄알림공지 시작기간
	private Date eventEndDate;						//한줄알림공지 종료기간
	private List<LineNoticeEntity> lineNoticeList;
	
	public long getLineNoticeKey() {
		return lineNoticeKey;
	}
	public void setLineNoticeKey(long lineNoticeKey) {
		this.lineNoticeKey = lineNoticeKey;
	}
	public String getLineNoticeContents() {
		return lineNoticeContents;
	}
	public void setLineNoticeContents(String lineNoticeContents) {
		this.lineNoticeContents = lineNoticeContents;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public List<LineNoticeEntity> getLineNoticeList() {
		return lineNoticeList;
	}
	public void setLineNoticeList(List<LineNoticeEntity> lineNoticeList) {
		this.lineNoticeList = lineNoticeList;
	}
	public Date getEventStartDate() {
		return eventStartDate == null ? null : new Date(eventStartDate.getTime());
	}
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate == null ? null : new Date(eventStartDate.getTime());
	}
	public Date getEventEndDate() {
		return eventEndDate == null ? null : new Date(eventEndDate.getTime());
	}
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate == null ? null : new Date(eventEndDate.getTime());
	}
}