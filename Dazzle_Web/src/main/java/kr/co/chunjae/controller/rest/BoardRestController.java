package kr.co.chunjae.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.dao.AttachDAO;
import kr.co.chunjae.service.BoardService;
import kr.co.chunjae.service.SettingService;
import kr.co.digigroove.commons.entities.SavedFileEntity;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.FileUtils;
import kr.co.digigroove.commons.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import kr.co.chunjae.entities.AttachEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.BoardEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.entities.user.UserEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping ("/admin/board")
public class BoardRestController {
	@Value("#{file_prop['filePath']}")
	private String filePath;
	@Autowired
	BoardService boardService;
	@Autowired
	SettingService settingService;
	@Autowired
	private Messages messages;
	@Autowired
	private AttachDAO attachDAO;

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

    /**
     * 관리자 게시판 수정
     * @param session
     * @param file
     * @param boardType
     * @param boardEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateBoard/{boardType}", method = RequestMethod.POST, headers = "content-type=multipart/*")
    public org.springframework.http.ResponseEntity<ResponseEntity> updateBoard(HttpSession session,
    		@RequestParam(required=false, value="file") MultipartFile file,
			@PathVariable("boardType") String boardType, BoardEntity boardEntity, Model model) {

    	ResponseEntity result = new ResponseEntity();
        Map<String, Object> resultMap = new HashMap<String, Object>();

        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

        UserEntity loginSession = (UserEntity) session.getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
        boardEntity.setUserKey(loginSession.getUserKey());
        try {
			/* 게시판 타입 */
			if(boardType.equals(BoardEntity.BOARD_TYPE_CODE_NOTICE)) {
				boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_NOTICE);
			} else {
				boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_EVENT);
			}

			/* 게시판 업로드 */
			result = boardService.updateBoard(boardEntity);
			if(!StringUtils.isEmpty(file)){
				if(boardEntity.getAttachKey() > 0){
					FileUtils.deleteFile(filePath+"/"+boardEntity.getFileName()); // 파일 삭제
					result = boardService.deleteFile(boardEntity.getAttachKey()); // 기존 DB 데이터의 delete_data update
				}
				/* 파일 업로드 */
				fileUploadMethod(file, boardType, boardEntity, result, resultMap, responseHeaders);
			}

			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("update.success"));
        } catch(Exception e) {
        	logger.error(" 관리자 게시판 수정 : Error ", e);
        }

        return new org.springframework.http.ResponseEntity<ResponseEntity>(result, responseHeaders, HttpStatus.OK);
     }

    /**
     * 관리자 게시판 삭제
     * @param session
     * @param boardType
     * @param boardEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteBoard/{boardType}", method = RequestMethod.POST)
    public ResponseEntity deleteBoard(HttpSession session,
    	@PathVariable("boardType") String boardType,
        BoardEntity boardEntity, Model model){

    	ResponseEntity result = new ResponseEntity();
        boardEntity.setBoardKey(boardEntity.getBoardKey());
        try{
        	if (boardType.equals(BoardEntity.BOARD_TYPE_CODE_NOTICE)) {
        		boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_NOTICE);
        	} else {
        		boardEntity.setBoardTypeCode(BoardEntity.BOARD_TYPE_CODE_EVENT);
        	}
        	result = boardService.deleteBoard(boardEntity);
        	if(boardEntity.getAttachKey() > 0){
        		FileUtils.deleteFile(filePath+"/"+boardEntity.getFileName()); // 파일 삭제
        		result = boardService.deleteFile(boardEntity.getAttachKey()); // 기존 DB 데이터의 delete_data update
        	}
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("delete.success"));
        }catch(Exception e){
        	logger.error(" 관리자 게시판 삭제 : Error ", e);
        	result.setResultMsg(messages.getMessage("delete.fail"));
        }

        return result;
     }
    /**
     * 한줄알림 수정
     * @param lineNoticeEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateLineNotice", method = RequestMethod.POST)
    public ResponseEntity updateLineNotice(LineNoticeEntity lineNoticeEntity, Model model) {

    	ResponseEntity result = new ResponseEntity();


        try {
        	settingService.updateLineNotice(lineNoticeEntity);

			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("update.success"));
        } catch(Exception e) {
        	logger.error(" 관리자 한줄공지 수정 : Error ", e);
        }

        return result;
     }

    /**
     * 한줄알림 삭제
     * @param lineNoticeKey
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteLineNotice", method = RequestMethod.POST)
    public ResponseEntity deleteLineNotice(
    		@RequestParam(required=false, value="lineNoticeKey") long lineNoticeKey,
    		Model model) {

    	ResponseEntity result = new ResponseEntity();


        try {

        	settingService.deleteLineNotice(lineNoticeKey);

			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMsg(messages.getMessage("delete.success"));
        } catch(Exception e) {
        	logger.error(" 관리자 한줄공지 삭제 : Error ", e);
        }

        return result;
     }

    /**
     * 관리자 게시판 이미지 삭제
     * @param attachKey
     * @param fileName
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteBoardImage/{fileName}/{attachKey}", method = RequestMethod.GET)
    public ResponseEntity deleteBoardImage(@PathVariable("attachKey") long attachKey,
        @PathVariable("fileName") String fileName, Model model) {

        ResponseEntity result = new ResponseEntity();
        try {
            if(!StringUtils.isEmpty(fileName) && attachKey > 0){
                FileUtils.deleteFile(filePath+"/"+fileName); // 파일 삭제
                result = boardService.deleteFile(attachKey); // 기존 DB 데이터의 delete_data update
            }

            result.setResultCode(ResultCode.SUCCESS);
            result.setResultMsg(messages.getMessage("delete.success"));
        } catch(Exception e) {
            logger.error(" 관리자 게시판 파일 삭제 : Error ", e);
        }

        return result;
    }



   /**
    * 파일업로드
    * @param file
    * @param boardType
    * @param boardEntity
    * @param result
    * @param resultMap
    * @param responseHeaders
    * @throws Exception
    */
	private void fileUploadMethod(MultipartFile file, String boardType,
			BoardEntity boardEntity, ResponseEntity result,
			Map<String, Object> resultMap, HttpHeaders responseHeaders)
			throws Exception {

		/* 파일 업로드 */
		if(!(file.getContentType().contains("image") || file.getContentType().contains("jpeg"))) {
			result.setResultMsg(messages.getMessage("image.extension"));
		} else {
			String fileUploadPath = filePath;	// 파일 저장경로
			SavedFileEntity savedFileEntity = FileUtils.saveFile(fileUploadPath, file);

			resultMap.put("origFileName", savedFileEntity.getOriginalFileName());	// 원본 파일명
			resultMap.put("fileName", savedFileEntity.getSavedFileName());			// 파일명
			resultMap.put("fileSize", savedFileEntity.getSavedFileSize());			// 파일 사이즈
			resultMap.put(ResultCode.RESULT_CODE, ResultCode.SUCCESS);
			new org.springframework.http.ResponseEntity<Map<String,Object>>(resultMap, responseHeaders, HttpStatus.OK);

			// insert DazzleAttach
			AttachEntity attachEntity = new AttachEntity();
			if(boardType.equals(BoardEntity.BOARD_TYPE_CODE_NOTICE)) {
				attachEntity.setAttachType(CommonCode.ATTACH_TYPE_BOARD_NOTICE);
			}else{
				attachEntity.setAttachType(CommonCode.ATTACH_TYPE_BOARD_EVENT);
			}
			attachEntity.setCommonKey(boardEntity.getBoardKey());
			attachEntity.setFileName(savedFileEntity.getSavedFileName());
			attachEntity.setFileOrigName(savedFileEntity.getOriginalFileName());
			attachDAO.insertAttach(attachEntity);
		}
	}

}