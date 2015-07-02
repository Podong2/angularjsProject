package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.BoardListEntity;
import kr.co.chunjae.entities.board.BoardReplyEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;

public interface BoardService {
	/**
	 *  게시판 리스트
	 * @param boardList
	 * @return
	 * @throws Exception
	 */
	BoardEntity selectBoardList(BoardEntity boardList) throws Exception;

    /**
     * 게시판 상세정보
     * @param boardKey
     * @return
     * @throws Exception
     */
    BoardEntity selectBoard(long boardKey) throws Exception;

    /**
     * 게시판 글쓰기(새글, 수정)
     * @param boardEntity
     * @return
     * @throws
     */
    ResponseEntity updateBoard(BoardEntity boardEntity);

	/**
	 *  이벤트 게시판 댓글 리스트
	 * @param boardReplyList
	 * @return
	 * @throws Exception
	 */
	BoardReplyEntity selectReplyList(BoardReplyEntity boardReplyList) throws Exception;

    /**
     * 게시판 글 삭제
     * @param boardEntity
     * @return
     * @throws
     */
	ResponseEntity deleteBoard(BoardEntity boardEntity);

	/**
	 * 파일 삭제
	 * @param attachKey
	 * @return
	 * @throws Exception
	 */
	ResponseEntity deleteFile(long attachKey) throws Exception;

	/**
	 * 한줄공지 정보
	 * @return
	 * @throws Exception
	 */
	LineNoticeEntity selectLineNotice() throws Exception;

	/**
	 * 메인 노출 공지 리스트 가져오기
	 * @return
	 * @throws Exception
	 */
	List<BoardEntity> selectExposeNoticeList(long userKey) throws Exception;

	/**
	 * 게시판 목록(모바일)
	 * @param boardEntity
	 * @return
	 * @throws Exception
	 */
	List<BoardEntity> selectBoardListMobile(BoardEntity boardEntity)throws Exception;

	/**
	 * 게시판 상세페이지
	 * @param boardEntity
	 * @return
	 * @throws Exception
	 */
	BoardListEntity selectBoardDetail(long boardKey) throws Exception;

	/**
	 * 게시판 상세페이지,댓글 목록
	 * @param boardEntity
	 * @return
	 * @throws Exception
	 */
	BoardListEntity selectBoardDetailReply(long boardKey, int startRow, int rowCount) throws Exception;


	/**
	 * 이벤트 게시판 댓글 전송
	 * @param boardReplyEntity
	 * @throws Exception
	 */
	BoardListEntity insertReply(BoardReplyEntity boardReplyEntity)throws Exception;

	/**
	 * 이벤트 게시판 댓글 수정
	 * @param boardReplyEntity
	 * @return
	 * @throws Exception
	 */
	BoardReplyEntity updateReply(BoardReplyEntity boardReplyEntity)throws Exception;

	/**
	 * 이벤트 게시판 댓글 삭제
	 * @param replyKey
	 * @return
	 * @throws Exception
	 */
	BoardReplyEntity deleteReply(long replyKey)throws Exception;
}