package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;

public class LineTalkListEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 6606411711211239252L;
	private LineTalkEntity lineTalkEntity;
	private LineTalkReplyEntity lineTalkReplyEntity;
	private List<LineTalkEntity> lineTalkList;
	private List<LineTalkReplyEntity> lineTalkReplyList;
	private List<LineTalkLogEntity> lineTalkLogList;
	public LineTalkEntity getLineTalkEntity() {
		return lineTalkEntity;
	}
	public void setLineTalkEntity(LineTalkEntity lineTalkEntity) {
		this.lineTalkEntity = lineTalkEntity;
	}
	public LineTalkReplyEntity getLineTalkReplyEntity() {
		return lineTalkReplyEntity;
	}
	public void setLineTalkReplyEntity(LineTalkReplyEntity lineTalkReplyEntity) {
		this.lineTalkReplyEntity = lineTalkReplyEntity;
	}
	public List<LineTalkEntity> getLineTalkList() {
		return lineTalkList;
	}
	public void setLineTalkList(List<LineTalkEntity> lineTalkList) {
		this.lineTalkList = lineTalkList;
	}
	public List<LineTalkReplyEntity> getLineTalkReplyList() {
		return lineTalkReplyList;
	}
	public void setLineTalkReplyList(List<LineTalkReplyEntity> lineTalkReplyList) {
		this.lineTalkReplyList = lineTalkReplyList;
	}
	public List<LineTalkLogEntity> getLineTalkLogList() {
		return lineTalkLogList;
	}
	public void setLineTalkLogList(List<LineTalkLogEntity> lineTalkLogList) {
		this.lineTalkLogList = lineTalkLogList;
	}



}