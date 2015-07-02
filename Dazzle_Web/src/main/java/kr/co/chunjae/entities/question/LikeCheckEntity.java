package kr.co.chunjae.entities.question;

import java.io.Serializable;

import kr.co.chunjae.entities.ResponseEntity;

public class LikeCheckEntity extends ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = -1698363998300964990L;
	private int likeCount;

	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
