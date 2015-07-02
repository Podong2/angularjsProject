package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.dao.BoardDAO;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.LineNoticeDAO;
import kr.co.chunjae.dao.UserDAO;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.BoardListEntity;
import kr.co.chunjae.entities.board.BoardReplyEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.entities.user.UserEntity;
import kr.co.chunjae.service.BoardService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{

	private static final String BOARD_EVENT_RATING_CHECK_Y = "Y";
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AttachDAO attachDAO;
	@Autowired
	private LineNoticeDAO LineNoticeDAO;
    @Autowired
    private GradeScoreDAO gradeScoreDAO;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	/**
	 *  게시판 리스트
	 * @param boardList
	 * @throws Exception
	 */
	@Override
	public BoardEntity selectBoardList(BoardEntity boardList) throws Exception {
		boardList.setPageParams();
		boardList.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
		boardList.setDataSize(boardDAO.selectBoardListCount(boardList));
		List<BoardEntity> boardListResult = boardDAO.selectBoardList(boardList);
		List<BoardEntity> noticeList = boardDAO.selectExposeNoticeList(boardList.getUserKey());
		boardList.setBoardEntity(boardListResult);
		boardList.setNoticeList(noticeList);
		return boardList;
	}

    /**
      * 게시판 상세정보
     * @param boardKey
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false)
	public BoardEntity selectBoard(long boardKey) throws Exception {

    	boardDAO.updateHitsCount(boardKey);
        BoardEntity boardInfo = boardDAO.selectBoard(boardKey);
        if(StringUtils.isNotEmpty(boardInfo.getEventRate())){
        	String eventRate[] = boardInfo.getEventRate().split(",");
        	for (int i = 0; i < eventRate.length; i++) {
        		String value = eventRate[i];
        		if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_LEAF_NUMBER)) {
        			boardInfo.setRatingLeaf(BOARD_EVENT_RATING_CHECK_Y);
        		} else if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_WOOD_NUMBER)) {
        			boardInfo.setRatingWood(BOARD_EVENT_RATING_CHECK_Y);
        		} else if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_STONE_NUMBER)) {
        			boardInfo.setRatingStone(BOARD_EVENT_RATING_CHECK_Y);
        		} else if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_COPPER_NUMBER)) {
        			boardInfo.setRatingCopper(BOARD_EVENT_RATING_CHECK_Y);
        		} else if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_SILVER_NUMBER)) {
        			boardInfo.setRatingSilver(BOARD_EVENT_RATING_CHECK_Y);
        		} else if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_GOLD_NUMBER)) {
        			boardInfo.setRatingGold(BOARD_EVENT_RATING_CHECK_Y);
        		} else if (value.equals(BoardEntity.BOARD_EVENT_RATIRNG_CRYSTAL_NUMBER)) {
        			boardInfo.setRatingCrystal(BOARD_EVENT_RATING_CHECK_Y);
        		}
        	}
		}
        return boardInfo;
	}

    /**
      * 게시판 글쓰기(새글, 수정)
     * @param boardEntity
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity updateBoard(BoardEntity boardEntity) {
		ResponseEntity result = new ResponseEntity();
		if (StringUtils.isBlank(boardEntity.getTopExposeYn())) boardEntity.setTopExposeYn(BoardEntity.EXPOSE_YN);
		if (StringUtils.isBlank(boardEntity.getSevendaysExposeYn())) boardEntity.setSevendaysExposeYn(BoardEntity.EXPOSE_YN);
		try {
			if (boardEntity.getBoardKey() != 0) {
				boardDAO.updateBoard(boardEntity);
			} else {
				boardDAO.insertBoard(boardEntity);
			}
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("update.success"));
		}catch(Exception e){
			logger.error(" 관리자 게시판 등록 / 수정 페이지 : Error ", e);
			result.setResultMsg(messages.getMessage("update.fail"));
		}
		return result;
	}

	/**
	 * 이벤트 게시판 댓글 리스트
	 * @param boardReplyList
	 * @throws Exception
	 */
	@Override
	public BoardReplyEntity selectReplyList(BoardReplyEntity boardReplyList) throws Exception {

		boardReplyList.setDataSize(boardDAO.selectBoardReplyListCount(boardReplyList));
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
		List<BoardReplyEntity> boardReplyListResult = boardDAO.selectBoardReplyList(boardReplyList);
		for (BoardReplyEntity boardReplyEntity : boardReplyListResult) {
			if(!StringUtils.isEmpty(boardReplyEntity.getPhoneNumber())){
				boardReplyEntity.setPhoneNumber(boardReplyEntity.getPhoneNumber().replaceAll(regEx, "$1-$2-$3"));
			}
		}
		boardReplyList.setBoardReplyEntity(boardReplyListResult);

		return boardReplyList;
	}

    /**
     * 게시판 글 삭제
     * @param boardEntity
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity deleteBoard(BoardEntity boardEntity) {
		ResponseEntity result = new ResponseEntity();
		try {
			boardDAO.deleteBoard(boardEntity);
//			result.setResultCode(ResultCode.SUCCESS);
//			result.setResultMsg(messages.getMessage("delete.success"));
		} catch(Exception e) {
			logger.error(" 관리자 이벤트 게시판 댓글 목록 : Error ", e);
			result.setResultMsg(messages.getMessage("delete.fail"));
		}
		return result;
	}

    /**
      * 파일 삭제
     * @param attachKey
     * @return
     * @throws Exception
     */
	@Override
	public ResponseEntity deleteFile(long attachKey) {
		ResponseEntity result = new ResponseEntity();
		try {
			attachDAO.deleteFile(attachKey);
		} catch (Exception e) {
			logger.error(" 관리자 기존 파일 삭제 오류 : Error ", e);
		}
		return result;
	}

	/**
	 * 한줄 알림 정보
	 * @return
     * @throws Exception
	 */
	@Override
	public LineNoticeEntity selectLineNotice() throws Exception {

		return LineNoticeDAO.selectLineNotice();
	}

	/**
	 * 메인 노출 공지 리스트 가져오기
	 * @return
     * @throws Exception
	 */
	@Override
	public List<BoardEntity> selectExposeNoticeList(long userKey) throws Exception {
		List<BoardEntity> noticeList = boardDAO.selectExposeNoticeList(userKey);
		for (int i = 0; i < noticeList.size(); i++) {
			noticeList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(noticeList.get(i).getInsertDate()));
		}
		return noticeList;
	}

	/**
	 * 게시판 목록(모바일)
	 * @param boardEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BoardEntity> selectBoardListMobile(BoardEntity boardEntity)
			throws Exception {
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		boardList = boardDAO.selectBoardList(boardEntity);
		return boardList;
	}

	/**
	 * 게시판 상세페이지
	 * @param boardEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoardListEntity selectBoardDetail(long boardKey)
			throws Exception {
		BoardListEntity boardListEntity = new BoardListEntity();
		BoardReplyEntity boardReplyEntity = new BoardReplyEntity();
		boardReplyEntity.setBoardKey(boardKey);

		//게시판 상세정보
		BoardEntity boardInfo = boardDAO.selectBoard(boardKey);

		boardListEntity.setBoardEntity(boardInfo);
		return boardListEntity;
	}

	/**
	 * 게시판 이벤트 상세 댓글 목록
	 * @param boardEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoardListEntity selectBoardDetailReply(long boardKey, int startRow, int rowCount)
			throws Exception {
		BoardListEntity boardListEntity = new BoardListEntity();
		BoardReplyEntity boardReplyEntity = new BoardReplyEntity();
		boardReplyEntity.setBoardKey(boardKey);
		boardReplyEntity.setCurrentPage(startRow);
		boardReplyEntity.setPageSize(rowCount, rowCount);

		//이벤트게시판 댓글목록
		List<BoardReplyEntity> boardReplyListResult = boardDAO.selectBoardReplyList(boardReplyEntity);

		//활동점수 목록
		List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();

		// 댓글 작성자 프로필 등급
		for (BoardReplyEntity boardReplyData : boardReplyListResult){
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= boardReplyData.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= boardReplyData.getActivityScore()) {
					boardReplyData.setUserRating(String.format("%02d", i+1));
				}
			}
		}
		// 이벤트 댓글 목록 작성 시점
		for (int i = 0; i < boardReplyListResult.size(); i++) {
			boardReplyListResult.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(boardReplyListResult.get(i).getInsertDate()));
		}
		boardListEntity.setBoardReplyList(boardReplyListResult);

		return boardListEntity;
	}


	/**
	 * 이벤트 게시판 댓글 전송
	 * @param boardReplyEntity
	 * @throws Exception
	 */
	@Override
	public BoardListEntity insertReply(BoardReplyEntity boardReplyEntity) throws Exception {
		String restriction = BoardReplyEntity.BOARD_EVENT_REPLY_RESTRICTION_N;
		BoardListEntity result = new BoardListEntity();
    		//프로필 사용자 정보
    	UserEntity userInformation = userDAO.selectUser(boardReplyEntity.getUserKey());
    	List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
		// 사용자 상세 프로필 등급
		for (int i=0; i < gradeScoreList.size(); i++){
			if(gradeScoreList.get(i).getUserLowScore() <= userInformation.getActivityScore()
					&& gradeScoreList.get(i).getUserHighScore() >= userInformation.getActivityScore()) {
				userInformation.setUserRating(String.format("%02d", i+1));
			}
		}
		//게시판 정보
//		BoardEntity boardInfo = boardDAO.selectBoard(boardReplyEntity.getBoardKey());
        String eventRate[] = boardReplyEntity.getEventRate().split(",");
        for (int i = 0; i < eventRate.length; i++) {
        	String value = eventRate[i];
        	if(value.equals(userInformation.getUserRating())){
        		restriction = BoardReplyEntity.BOARD_EVENT_REPLY_RESTRICTION_Y;
        	}
		}
       if(restriction.equals(BoardReplyEntity.BOARD_EVENT_REPLY_RESTRICTION_Y)){
    	   //이벤트 대상 참여
    	   boardDAO.insertReply(boardReplyEntity);
    	   result.setResultCode(ResultCode.SUCCESS);
       }else{
    	   //이벤트 제외대상 미참여
    	   result.setResultMsg(messages.getMessage("reply_write.fail"));
       }
       return result;
	}

	/**
	 * 이벤트 게시판 댓글 수정
	 * @param boardReplyEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoardReplyEntity updateReply(BoardReplyEntity boardReplyEntity) {
		try {
			boardDAO.updateReply(boardReplyEntity);
			boardReplyEntity.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글목록 가져오기 오류", e);
		}
		return boardReplyEntity;
	}

	/**
	 * 이벤트 게시판 댓글 삭제
	 * @param replyKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoardReplyEntity deleteReply(long replyKey) {
		BoardReplyEntity boardReplyEntity = new BoardReplyEntity();
		try {
			boardDAO.deleteReply(replyKey);
			boardReplyEntity.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error(" 게시판 상세, 댓글목록 가져오기 오류", e);
		}
		return boardReplyEntity;
	}
}