package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.Date;

public class LineTalkLogEntity implements Serializable {

	private static final long serialVersionUID = 6606411711211239252L;

	private long talkKey;			// 한줄토크 고유키
	private long userKey;			// 회원 고유키
	private String readYn;			// 글 읽음 여부 ( Y: 읽음, N:안읽음 )
	private Date insertDate;		// 작성 날짜
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
	public String getReadYn() {
		return readYn;
	}
	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}




}