package kr.co.chunjae.entities;

import java.io.Serializable;

import kr.co.chunjae.constants.ResultCode;

public class ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = -5401842758440913937L;
	
	private String resultCode = ResultCode.FAIL;
	private String resultMsg  = "";
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}