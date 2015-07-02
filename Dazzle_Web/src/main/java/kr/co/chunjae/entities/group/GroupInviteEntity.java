package kr.co.chunjae.entities.group;

import java.io.Serializable;
import java.util.Date;

import kr.co.chunjae.entities.ResponseEntity;

public class GroupInviteEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = -8210560737449079282L;

	private long inviteKey;		// 초대 코드 고유키
	private long groupKey;		// 그룹 고유키
	private long inviteFrom;		// 초대 한 회원 고유키
	private String inviteCode;	// 초대 코드
	private String inviteType;	// 초대 타입
	private Date regDate;		// 초대일
	private Date expireDate;		// 만료일
	private String groupName;	// 그룹명

	public long getInviteKey() {
		return inviteKey;
	}
	public void setInviteKey(long inviteKey) {
		this.inviteKey = inviteKey;
	}
	public long getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(long groupKey) {
		this.groupKey = groupKey;
	}
	public long getInviteFrom() {
		return inviteFrom;
	}
	public void setInviteFrom(long inviteFrom) {
		this.inviteFrom = inviteFrom;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getInviteType() {
		return inviteType;
	}
	public void setInviteType(String inviteType) {
		this.inviteType = inviteType;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}