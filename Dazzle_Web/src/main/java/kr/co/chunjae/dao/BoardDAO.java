package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.BoardReplyEntity;

public interface BoardDAO {
	/**
	 * 게시판 목록
	 * @param boardList
	 * @return
	 * @throws Exception
	 */
	List<BoardEntity> selectBoardList(BoardEntity boardList) throws Exception;

	/**
	 * 게시판 목록 개수
	 * @param boardList
	 * @return
	 * @throws Exception
	 */
	int selectBoardListCount(BoardEntity boardList) throws Exception;

	/**
	 * 게시판 상세
	 * @param boardKey
	 * @return
	 * @throws Exception
	 */
	BoardEntity selectBoard(long boardKey) throws Exception;

	/**
	 * 메인 노출 공지 리스트 가져오기
	 * @return
	 * @throws Exception
	 */
	List<BoardEntity> selectExposeNoticeList(long userKey) throws Exception;

	/**
	 * 게시판 수정
	 * @param boardEntity
	 * @throws Exception
	 */
	void updateBoard(BoardEntity boardEntity) throws Exception;

	/**
	 * 게시판 새글 쓰기
	 * @param boardEntity
	 * @throws Exception
	 */
	void insertBoard(BoardEntity boardEntity) throws Exception;

	/**
	 * 이벤트 게시판 댓글 목록 개수
	 * @param boardReplyList
	 * @return
	 * @throws Exception
	 */
	int selectBoardReplyListCount(BoardReplyEntity boardReplyList) throws Exception;

	/**
	 * 이벤트 게시판 댓글 목록
	 * @param boardReplyList
	 * @return
	 * @throws Exception
	 */
	List<BoardReplyEntity> selectBoardReplyList(BoardReplyEntity boardReplyList) throws Exception;

	/**
	 * 게시판 삭제
	 * @param boardEntity
	 * @throws Exception
	 */
	void deleteBoard(BoardEntity boardEntity) throws Exception;

	/**
	 * 조회수 업데이트
	 * @param boardKey
	 * @return
	 * @throws Exception
	 */
	void updateHitsCount(long boardKey) throws Exception;

	/**
	 * 이벤트 게시판 댓글 작성
	 * @param boardReplyEntity
	 * @throws Exception
	 */
	void insertReply(BoardReplyEntity boardReplyEntity)throws Exception;

	/**
	 * 이벤트 게시판 댓글 수정
	 * @param boardReplyEntity
	 * @throws Exception
	 */
	void updateReply(BoardReplyEntity boardReplyEntity)throws Exception;

	/**
	 * 이벤트 게시판 댓글 삭제
	 * @param replyKey
	 * @throws Exception
	 */
	void deleteReply(long replyKey)throws Exception;
}