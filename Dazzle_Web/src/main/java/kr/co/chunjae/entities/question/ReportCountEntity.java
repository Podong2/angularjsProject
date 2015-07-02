package kr.co.chunjae.entities.question;

import java.io.Serializable;

import kr.co.chunjae.entities.ResponseEntity;

public class ReportCountEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = -1705645371168202131L;
	
	private int reportCount;	// 신고여부 카운트

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}
}