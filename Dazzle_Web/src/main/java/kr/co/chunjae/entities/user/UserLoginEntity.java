package kr.co.chunjae.entities.user;

import java.io.Serializable;

import kr.co.chunjae.entities.ResponseEntity;

public class UserLoginEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 8783492251930713466L;
	
	private UserEntity loginSessionData;

	public UserEntity getLoginSessionData() {
		return loginSessionData;
	}

	public void setLoginSessionData(UserEntity loginSessionData) {
		this.loginSessionData = loginSessionData;
	}
}