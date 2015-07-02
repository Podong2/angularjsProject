package kr.co.chunjae.entities;

import java.io.Serializable;

public class ListCountEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = -496472521956225720L;
	
	private long listCount;

	public long getListCount() {
		return listCount;
	}

	public void setListCount(long listCount) {
		this.listCount = listCount;
	}
}