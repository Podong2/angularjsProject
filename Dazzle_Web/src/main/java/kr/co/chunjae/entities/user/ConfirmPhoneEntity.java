package kr.co.chunjae.entities.user;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineTalkEntity;

public class ConfirmPhoneEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 7145923227857007583L;

	private UserEntity UserData;						//사용자 정보
	private List<LineTalkEntity> lineTalkList;				//한줄토크 목록
	private AttachEntity attachEntity;						//파일이미지
	private List<ActivityLogEntity> activityLogEntity;		//활동로그 목록

	public UserEntity getUserData() {
		return UserData;
	}

	public void setUserData(UserEntity userData) {
		UserData = userData;
	}

	public List<LineTalkEntity> getLineTalkList() {
		return lineTalkList;
	}

	public void setLineTalkList(List<LineTalkEntity> lineTalkList) {
		this.lineTalkList = lineTalkList;
	}

	public AttachEntity getAttachEntity() {
		return attachEntity;
	}

	public void setAttachEntity(AttachEntity attachEntity) {
		this.attachEntity = attachEntity;
	}

	public List<ActivityLogEntity> getActivityLogEntity() {
		return activityLogEntity;
	}

	public void setActivityLogEntity(List<ActivityLogEntity> activityLogEntity) {
		this.activityLogEntity = activityLogEntity;
	}



}