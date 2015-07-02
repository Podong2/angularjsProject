package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.BoardListEntity;
import kr.co.chunjae.entities.board.BoardReplyEntity;
import kr.co.chunjae.entities.board.LineNoticeInfoEntity;
import kr.co.chunjae.service.BoardService;
import kr.co.digigroove.commons.messages.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/board")
public class BoardMobileController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(BoardMobileController.class);

	/**
	 * 한줄공지 정보 가져오기
	 * @return
	 */
	@RequestMapping(value="/selectLineNotice", method = RequestMethod.GET)
	public LineNoticeInfoEntity selectLineNotice() {

		LineNoticeInfoEntity result = new LineNoticeInfoEntity();
		try {
			result.setLineNoticeInfo(boardService.selectLineNotice());
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("한줄공지 정보 조회 오류", e);
		}
		return result;
	}

	/**
	 * 메인노출 공지 리스트 가져오기
	 * @return
	 */
	@RequestMapping(value="/selectExposeNoticeList", method = RequestMethod.GET)
	public BoardListEntity selectExposeNoticeList(@RequestParam("userKey") long userKey) {

		BoardListEntity result = new BoardListEntity();
		try {
			result.setBoardList(boardService.selectExposeNoticeList(userKey));
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("메인노출 공지 리스트 조회 오류", e);
		}
		return result;
	}

	/**
	 * 게시판 목록 가져오기(공지사항, 이벤트)
	 * @param boardTypeCode
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/selectBoardListMobile", method = RequestMethod.GET)
	public BoardListEntity selectBoardListMobile(@RequestParam("boardTypeCode") String boardTypeCode,
												@RequestParam("startRow") int startRow,
												@RequestParam("rowCount") int rowCount) {

		BoardEntity boardEntity = new BoardEntity();
		BoardListEntity result = new BoardListEntity();
		boardEntity.setBoardTypeCode(boardTypeCode);
		boardEntity.setCurrentPage(startRow);
		boardEntity.setPageSize(rowCount, rowCount);
		try {
			result.setBoardList(boardService.selectBoardListMobile(boardEntity));
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 게시판 목록 가져오기 오류", e);
		}
		return result;
	}

	/**
	 * 게시판 상세
	 * @param boardKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/selectBoardDetail", method = RequestMethod.GET)
	public BoardListEntity selectBoardDetail(@RequestParam("boardKey") long boardKey) {

		BoardListEntity result = new BoardListEntity();
		try {
			result.setBoardEntity(boardService.selectBoard(boardKey));
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글목록 가져오기 오류", e);
		}
		return result;
	}

	/**
	 * 게시판 이벤트 상세 페이지 댓글 목록
	 * @param boardKey
	 * @param startRow
	 * @param rowCount
	 * @return
	 */
	@RequestMapping(value="/selectBoardDetailReply", method = RequestMethod.GET)
	public BoardListEntity selectBoardDetailReply(@RequestParam("boardKey") long boardKey,
												@RequestParam("startRow") int startRow,
												@RequestParam("rowCount") int rowCount) {

		BoardListEntity result = new BoardListEntity();
		try {
			result = boardService.selectBoardDetailReply(boardKey, startRow, rowCount);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글목록 가져오기 오류", e);
		}
		return result;
	}

	/**
	 * 이벤트 게시판 댓글 등록
	 * @param boardReplyEntity
	 * @return
	 */
	@RequestMapping(value="/insertReply", method = RequestMethod.POST)
	public BoardListEntity insertReply(@RequestBody BoardReplyEntity boardReplyEntity) {

		BoardListEntity result = new BoardListEntity();
		try {
			result = boardService.insertReply(boardReplyEntity);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글등록 오류", e);
			result.setResultMsg(messages.getMessage("reply.fail"));
		}
		return result;
	}

	/**
	 * 이벤트 게시판 댓글 수정
	 * @param boardReplyEntity
	 * @return
	 */
	@RequestMapping(value="/updateReply", method = RequestMethod.POST)
	public BoardReplyEntity updateReply(@RequestBody BoardReplyEntity boardReplyEntity) {

		try {
			boardReplyEntity = boardService.updateReply(boardReplyEntity);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글 수정", e);
			boardReplyEntity.setResultMsg(messages.getMessage("reply.fail"));
		}
		return boardReplyEntity;
	}


	/**
	 * 이벤트 게시판 댓글 삭제
	 * @param replyKey
	 * @return
	 */
	@RequestMapping(value="/deleteReply", method = RequestMethod.GET)
	public BoardReplyEntity deleteReply(@RequestParam("replyKey") long replyKey) {
		BoardReplyEntity boardReplyEntity = new BoardReplyEntity();
		try {
			boardReplyEntity = boardService.deleteReply(replyKey);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글 삭제", e);
			boardReplyEntity.setResultMsg(messages.getMessage("reply.fail"));
		}
		return boardReplyEntity;
	}


}