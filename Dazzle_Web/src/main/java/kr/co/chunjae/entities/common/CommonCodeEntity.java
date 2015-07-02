package kr.co.chunjae.entities.common;

import java.io.Serializable;
import java.util.Date;

public class CommonCodeEntity implements Serializable {
	
	private static final long serialVersionUID = -3378298204987212058L;
	
	private String grpCd;		//그룹코드 참조
	private String commCd;		//공통코드
	private String commCdNm;	//공통코드명
	private String commCdVal;	//공통코드값
	private int isUse;			//사용여부(0:미사용,1:사용)
	private int commCdSeq;		//공통코드 순서
	private Date insertDate;	//등록일
	
	public String getGrpCd() {
		return grpCd;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	public String getCommCd() {
		return commCd;
	}
	public void setCommCd(String commCd) {
		this.commCd = commCd;
	}
	public String getCommCdNm() {
		return commCdNm;
	}
	public void setCommCdNm(String commCdNm) {
		this.commCdNm = commCdNm;
	}
	public String getCommCdVal() {
		return commCdVal;
	}
	public void setCommCdVal(String commCdVal) {
		this.commCdVal = commCdVal;
	}
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	public int getCommCdSeq() {
		return commCdSeq;
	}
	public void setCommCdSeq(int commCdSeq) {
		this.commCdSeq = commCdSeq;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}

}