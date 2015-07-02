package kr.co.chunjae.entities.common;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;

public class CommonCodeListEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 4373380424102320484L;
	
	private List<CommonCodeEntity> commonCodeList;

	public List<CommonCodeEntity> getCommonCodeList() {
		return commonCodeList;
	}

	public void setCommonCodeList(List<CommonCodeEntity> commonCodeList) {
		this.commonCodeList = commonCodeList;
	}
}
