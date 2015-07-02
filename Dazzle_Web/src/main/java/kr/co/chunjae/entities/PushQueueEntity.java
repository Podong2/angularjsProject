package kr.co.chunjae.entities;

import java.io.Serializable;
import java.util.Date;

public class PushQueueEntity implements Serializable {
	
	private static final long serialVersionUID = 5024559563294694358L;
	
	private long pushQueueKey;		//푸시 큐 고유키
	private long userKey;			//회원 고유키
	private String queueContents;	//내용
	private String sendYn;			//전송 여부
	private Date regDate;			//요청 날짜
	private Date sendDate;			//전송 날짜
	private String pushEnum;		//푸시 타입(0:일반, 1:그룹)
	
	public long getPushQueueKey() {
		return pushQueueKey;
	}
	public void setPushQueueKey(long pushQueueKey) {
		this.pushQueueKey = pushQueueKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public String getQueueContents() {
		return queueContents;
	}
	public void setQueueContents(String queueContents) {
		this.queueContents = queueContents;
	}
	public String getSendYn() {
		return sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	public Date getRegDate() {
		return regDate == null ? null : new Date(regDate.getTime());
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
		this.regDate = regDate == null ? null : new Date(regDate.getTime());
	}
	public Date getSendDate() {
		return sendDate == null ? null : new Date(sendDate.getTime());
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate == null ? null : new Date(sendDate.getTime());
	}
	public String getPushEnum() {
		return pushEnum;
	}
	public void setPushEnum(String pushEnum) {
		this.pushEnum = pushEnum;
	}

}