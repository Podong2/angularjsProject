package kr.co.chunjae.controller;

import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.BoardReplyEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.service.BoardService;
import kr.co.chunjae.service.SettingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@Autowired
	private SettingService settingService;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	/**
	 * 게시판관리 - 리스트
	 * @param boardType
	 * @param boardList
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/{boardType}", method = RequestMethod.GET)
	public String noticeList(@PathVariable("boardType") String boardType,
								@ModelAttribute("boardList")BoardEntity boardList,
								Model model) {

		try{
			boardList.setBoardTypeCode(boardType);
			boardService.selectBoardList(boardList);
			model.addAttribute("pageType", boardType);
		} catch (Exception e) {
			logger.error(" 관리자 게시판 목록 : Error ", e);
		}
		return "board/list";
	}

	/**
	 * 한줄공지 목록
	 * @param model
	 * @param lineNoticeList
	 * @return
	 */
	@RequestMapping(value = "/list/03", method = RequestMethod.GET)
	public String selectLineNotice(Model model,
			@ModelAttribute("lineNoticeList")LineNoticeEntity lineNoticeList) {

	        try{
	        	settingService.selectLineNoticeList(lineNoticeList);

	        } catch (Exception e) {
	        	logger.error(" 관리자 한줄공지 목록 : Error ", e);
	        }
	        model.addAttribute("lineNoticeList",lineNoticeList);

		return "setting/lineNoticeList";
	}


	/**
	 * 한줄공지 수정 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lineNoticeEdit/03/{lineNoticeKey}", method = RequestMethod.GET)
    public String lineNoticeEdit(Model model,
    		@PathVariable("lineNoticeKey") long lineNoticeKey) throws Exception {
		LineNoticeEntity lineNoticeEntity = new LineNoticeEntity();
		lineNoticeEntity = settingService.selectLineNotice(lineNoticeKey);
		model.addAttribute("lineNoticeEntity",lineNoticeEntity);
       return "setting/lineNoticeEdit";
    }

	/**
	 * 게시판관리 - 상세
	 * @param boardKey
	 * @param boardType
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/detail/{boardType}", method = RequestMethod.GET)
	public String userDetail(@RequestParam(required = false, value = "boardKey") int boardKey,
							@PathVariable("boardType") String boardType,
							Model model,
							HttpSession session) throws Exception {
        BoardEntity boardEntity =  boardService.selectBoard(boardKey);
        model.addAttribute("boardEntity", boardEntity);
        model.addAttribute("pageType", boardType);

		return "board/detail";
	}
	/**
	 * 게시판 관리 - 등록, 수정 페이지 이동
	 * @param boardType
	 * @param boardKey
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boardForm/{boardType}", method = RequestMethod.GET)
	public String boardForm(@PathVariable("boardType") String boardType,
							@RequestParam(required = false, value = "boardKey", defaultValue = "0") long boardKey,
							Model model) throws Exception {
		if(boardKey > 0){
			BoardEntity boardEntity =  boardService.selectBoard(boardKey);
			model.addAttribute("boardEntity", boardEntity);
		}
		model.addAttribute("pageType", boardType);

		return "board/write";
	}

	/**
	 * 이벤트 게시판 댓글 - 리스트
	 * @param boardKey
	 * @param boardReplyList
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/replyList/{boardKey}", method = RequestMethod.GET)
	public String eventReplyList(@PathVariable("boardKey") long boardKey,
									@ModelAttribute("boardReplyList")BoardReplyEntity boardReplyList,
									Model model) {
		try {
			boardReplyList.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
			boardReplyList.setBoardKey(boardKey);
			boardService.selectReplyList(boardReplyList);
			model.addAttribute("boardKey", boardKey);
		} catch (Exception e) {
			logger.error(" 관리자 이벤트 게시판 댓글 목록 : Error ", e);
		}
		return "board/replyList";
	}

	/**
	 * 댓글관리 - 목록 엑셀 출력
	 * @param boardKey
	 * @param boardReplyList
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/excel/excelList/{boardKey}", method = RequestMethod.GET)
	public String boardReplyExcelList(@PathVariable("boardKey") long boardKey,
			@ModelAttribute("boardReplyList")BoardReplyEntity boardReplyList,
								Model model) {

		try {
			boardReplyList.setPageSize(CommonCode.EXCEL_LIST_SIZE_PARAM, CommonCode.EXCEL_GROUP_SIZE_PARAM);
			boardReplyList.setBoardKey(boardKey);
			boardService.selectReplyList(boardReplyList);
		} catch (Exception e) {
			logger.error(" 관리자 이벤트 게시판 댓글 목록 엑셀출력 : Error ", e);
		}
		model.addAttribute("excelName", "boardReplyList");
		return "excel/boardReplyExcelList";
	}
}