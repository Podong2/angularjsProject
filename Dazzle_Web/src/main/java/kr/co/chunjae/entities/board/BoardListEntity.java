package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.List;
import kr.co.chunjae.entities.ResponseEntity;

public class BoardListEntity extends ResponseEntity implements Serializable {

	private static final long serialVersionUID = 7629883071270986506L;

	private List<BoardEntity> boardList;
	private List<BoardReplyEntity> boardReplyList;
	private BoardReplyEntity boardReplyEntity;
	private BoardEntity boardEntity;

	public List<BoardEntity> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardEntity> boardList) {
		this.boardList = boardList;
	}

	public List<BoardReplyEntity> getBoardReplyList() {
		return boardReplyList;
	}

	public void setBoardReplyList(List<BoardReplyEntity> boardReplyList) {
		this.boardReplyList = boardReplyList;
	}

	public BoardReplyEntity getBoardReplyEntity() {
		return boardReplyEntity;
	}

	public void setBoardReplyEntity(BoardReplyEntity boardReplyEntity) {
		this.boardReplyEntity = boardReplyEntity;
	}
	public BoardEntity getBoardEntity() {
		return boardEntity;
	}
	public void setBoardEntity(BoardEntity boardEntity) {
		this.boardEntity = boardEntity;
	}

}