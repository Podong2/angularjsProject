package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.Date;

public class ReportMemoEntity implements Serializable {
	
	private static final long serialVersionUID = 821456143801063877L;
	
	private long reportMemoKey;		// 신고 메모 고유키
	private long reportKey;			// 신고관리 고유키
	private long userKey;			// 신고 메모 작성자 고유키
	private String memoContents;	// 내용
	private Date insertDate;		// 작성 날짜
	private Date deleteDate;		// 삭제 날짜
	
	public long getReportMemoKey() {
		return reportMemoKey;
	}
	public void setReportMemoKey(long reportMemoKey) {
		this.reportMemoKey = reportMemoKey;
	}
	public long getReportKey() {
		return reportKey;
	}
	public void setReportKey(long reportKey) {
		this.reportKey = reportKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public String getMemoContents() {
		return memoContents;
	}
	public void setMemoContents(String memoContents) {
		this.memoContents = memoContents;
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