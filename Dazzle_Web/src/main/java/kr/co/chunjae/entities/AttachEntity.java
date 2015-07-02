package kr.co.chunjae.entities;

import java.io.Serializable;
import java.util.Date;

public class AttachEntity implements Serializable {
	
	private static final long serialVersionUID = 5569962516387774268L;
	
	private long attachKey;			// 첨부파일 고유키
	private long commonKey;			// 첨부파일 타입에 해당하는 고유키
	private String fileName;		// 파일명
	private String fileOrigName;	// 원본 파일명
	private String attachType;		// 첨부파일 구분(01:사용자 프로필, 02:그룹커버, 03:문제, 04:답글, 05:공지사항, 06:이벤트)
	private Date insertDate;		// 등록일
	private Date deleteDate;		// 삭제일
	
	public long getAttachKey() {
		return attachKey;
	}
	public void setAttachKey(long attachKey) {
		this.attachKey = attachKey;
	}
	public long getCommonKey() {
		return commonKey;
	}
	public void setCommonKey(long commonKey) {
		this.commonKey = commonKey;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOrigName() {
		return fileOrigName;
	}
	public void setFileOrigName(String fileOrigName) {
		this.fileOrigName = fileOrigName;
	}
	public String getAttachType() {
		return attachType;
	}
	public void setAttachType(String attachType) {
		this.attachType = attachType;
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
}