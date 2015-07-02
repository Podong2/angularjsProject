package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;

public class ClassCodeListEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 4139523364241691589L;
	
	private List<ClassCodeEntity> classCodeList;

	public List<ClassCodeEntity> getClassCodeList() {
		return classCodeList;
	}

	public void setClassCodeList(List<ClassCodeEntity> classCodeList) {
		this.classCodeList = classCodeList;
	}
}