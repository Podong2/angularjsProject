package kr.co.chunjae.entities.board;

import java.io.Serializable;

import kr.co.chunjae.entities.ResponseEntity;

public class LineNoticeInfoEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = -148401372676330243L;
	
	private LineNoticeEntity lineNoticeInfo;

	public LineNoticeEntity getLineNoticeInfo() {
		return lineNoticeInfo;
	}

	public void setLineNoticeInfo(LineNoticeEntity lineNoticeInfo) {
		this.lineNoticeInfo = lineNoticeInfo;
	}
}