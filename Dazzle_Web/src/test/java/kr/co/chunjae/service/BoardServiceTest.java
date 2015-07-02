package kr.co.chunjae.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.BoardReplyEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:configs/mvc-dispatcher-servlet.xml")
public class BoardServiceTest {
	private static final String BOARD_INFO_EVENT_ENDDATE = "2014-12-12 19:02:19.600";
	private static final String BOARD_INFO_EVENT_STARTDATE = "2014-12-09 19:02:19.600";
	private static final String BOARD_INFO_EXPOSE_YN = "N";
	private static final String BOARD_INFO_CONTENT = "TDD 입니다.";
	private static final String BOARD_INFO_SUBJECT = "게시판 글쓰기 TDD";
	private static final String BOARD_INFO_TYPE_NOTICE = "01";
	String boardTypeNotice = BoardEntity.BOARD_TYPE_CODE_NOTICE;
	String boardTypeEvent = BoardEntity.BOARD_TYPE_CODE_EVENT;
	long boardKey = 0;
	@Autowired
	BoardService boardService;
	BoardEntity boardEntity;
	ResponseEntity result;
	BoardReplyEntity boardReplyList;
	BoardEntity boardKeyEntity;

	@Before
	public void before() throws Exception {
		boardEntity = new BoardEntity();
		result = new ResponseEntity();
		boardReplyList = new BoardReplyEntity();
		boardKeyEntity = new BoardEntity();
	}
	/**
	 * 게시판 목록
	 * case : 정상적인 정보를 불러왔을때
	 * @throws Exception
	 */
	@Test
	public void testSelectBoardList() throws Exception {
		boardEntity.setBoardTypeCode(BOARD_INFO_TYPE_NOTICE);
		boardEntity = boardService.selectBoardList(boardEntity);
		assertThat(boardEntity, is(notNullValue()));
	}

	/**
	 * 게시판 상세
	 * case : 정상적인 정보를 불러왔을때
	 * @throws Exception
	 */
	@Test
	public void testSelectBoardNotNull()throws Exception {
		int boardKey = 10;
		boardEntity =  boardService.selectBoard(boardKey);
		assertThat(boardEntity, is(notNullValue()));
	}

	/**
	 * 게시판 상세
	 * case : 정보를 불러오지 못 했을때
	 * @throws Exception
	 */
	@Test
	public void testSelectBoard()throws Exception {
		int boardKey = 0;
		boardEntity =  boardService.selectBoard(boardKey);
		assertThat(boardEntity, is(nullValue()));
	}

	/**
	 * 게시판 글쓰기
	 * case : 정보를 저장하지 못 했을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateBoardEmpty() {

		if(boardTypeNotice.equals(BoardEntity.BOARD_TYPE_CODE_NOTICE)) {
			boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_NOTICE);
		} else {
			boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_EVENT);
		}
		result = boardService.updateBoard(boardEntity);
		assertThat(result.getResultCode(), is(ResultCode.FAIL));
	}

	/**
	 * 게시판 글쓰기
	 * case : 정보저장 성공 했을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateBoardCorrect() {
		boardUpdateInfo(); // 게시판 글쓰기 값
		if(boardTypeNotice.equals(BoardEntity.BOARD_TYPE_CODE_NOTICE)) {
			boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_NOTICE);
		} else {
			boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_EVENT);
		}
		result = boardService.updateBoard(boardEntity);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}


	/**
	 * 이벤트 게시판 댓글 목록
	 * case : 게시판이없거나 게시판에 댓글이 없을때
	 * @throws Exception
	 */
	@Test
	public void testSelectReplyListEmpty() throws Exception {
		boardReplyList.setBoardKey(0);
		boardReplyList = boardService.selectReplyList(boardReplyList);
		assertEquals(boardReplyList.getBoardReplyEntity().size(), 0);
	}

	/**
	 * 이벤트 게시판 댓글 목록
	 * case : 댓글 이상없이 목록 불러왔을때
	 * @throws Exception
	 */
	@Test
	public void testSelectReplyListCorrect() throws Exception {
		boardReplyList.setBoardKey(54);
		boardReplyList = boardService.selectReplyList(boardReplyList);
		assertThat(boardReplyList.getBoardReplyEntity(), is(notNullValue()));
	}

	/**
	 * 이벤트 게시판 삭제
	 * case : 이상없이 삭제되었을때
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteBoard() {
    	if (boardTypeNotice.equals(BoardEntity.BOARD_TYPE_CODE_NOTICE)) {
    		boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_NOTICE);
    	} else {
    		boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_EVENT);
    	}
    	boardEntity.setBoardKey(boardKey);// boardKey는 DB 확인 후 값에맞게 삽입
    	result = boardService.deleteBoard(boardEntity);
		assertThat(result.getResultCode(), is(ResultCode.SUCCESS));
	}

	private void boardUpdateInfo() {
		boardEntity.setUserKey(1);
		boardEntity.setSubject(BOARD_INFO_SUBJECT);
		boardEntity.setBoardContents(BOARD_INFO_CONTENT);
		boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_NOTICE);
		boardEntity.setTopExposeYn(BOARD_INFO_EXPOSE_YN);
		boardEntity.setSevendaysExposeYn(BOARD_INFO_EXPOSE_YN);
		boardEntity.setStartDate(BOARD_INFO_EVENT_STARTDATE);
		boardEntity.setEndDate(BOARD_INFO_EVENT_ENDDATE);
		boardEntity.setEventRate(null);
	}

}
