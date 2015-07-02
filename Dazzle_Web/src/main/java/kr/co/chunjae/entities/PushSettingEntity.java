package kr.co.chunjae.entities;


import java.io.Serializable;
import java.util.Date;

public class PushSettingEntity implements Serializable {

	private static final long serialVersionUID = -6085114142080420922L;
	private long pushSettingKey;
	private long userKey;
	private String deviceToken;
	private String platform;
	private boolean pushActive;
	private Date regDate;
	private String inActiveTimeStart;
	private String inActiveTimeEnd;
	private boolean inActiveTime;

	public long getPushSettingKey() {
		return pushSettingKey;
	}

	public void setPushSettingKey(long pushSettingKey) {
		this.pushSettingKey = pushSettingKey;
	}

	public long getUserKey() {
		return userKey;
	}

	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public boolean isPushActive() {
		return pushActive;
	}

	public void setPushActive(boolean pushActive) {
		this.pushActive = pushActive;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getInActiveTimeStart() {
		return inActiveTimeStart;
	}

	public void setInActiveTimeStart(String inActiveTimeStart) {
		this.inActiveTimeStart = inActiveTimeStart;
	}

	public String getInActiveTimeEnd() {
		return inActiveTimeEnd;
	}

	public void setInActiveTimeEnd(String inActiveTimeEnd) {
		this.inActiveTimeEnd = inActiveTimeEnd;
	}

	public boolean isInActiveTime() {
		return inActiveTime;
	}

	public void setInActiveTime(boolean inActiveTime) {
		this.inActiveTime = inActiveTime;
	}
}