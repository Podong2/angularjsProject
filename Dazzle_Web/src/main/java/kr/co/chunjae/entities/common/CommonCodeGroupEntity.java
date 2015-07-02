package kr.co.chunjae.entities.common;

import java.io.Serializable;
import java.util.Date;

public class CommonCodeGroupEntity implements Serializable {
	
	private static final long serialVersionUID = 2223372390832684574L;
	
	private String grpCd;		// 그룹코드 참조
	private String grpCdNm;		// 그룹코드명
	private int isUse;			// 사용여부(0:미사용, 1:사용)
	private Date insertDate;	// 등록일
	
	public String getGrpCd() {
		return grpCd;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	public String getGrpCdNm() {
		return grpCdNm;
	}
	public void setGrpCdNm(String grpCdNm) {
		this.grpCdNm = grpCdNm;
	}
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
}