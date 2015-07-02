package kr.co.chunjae.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.digigroove.commons.entities.SavedFileEntity;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.FileUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/common")
public class CommonController {
	@Value("#{file_prop['filePath']}")
	private String filePath;
	@Value("#{file_prop['fileTempPath']}")
	private String fileTempPath;
	@Value("#{file_prop['groupCoverPath']}")
	private String groupCoverPath;
	@Autowired
	private Messages messages;

	/**
	 * 이미지뷰
	 * @param model
	 * @param fileName
	 * @param fileType
	 * @return
	 */
	@RequestMapping(value="/imgView")
	public String imageView(Model model,
			@RequestParam(required=true, value="fileName") String fileName,
			@RequestParam(required=false, value="fileType") String fileType,
			@RequestParam(required=false, value="temporary", defaultValue="N") String temporary) {

		String uploadPath = "";
		String viewFileName = fileName;

		if (StringUtils.equals(temporary, "Y")) {
			uploadPath = fileTempPath;
		} else {
			if(fileType.equals(CommonCode.ATTACH_TYPE_GROUP_COVER)){
				uploadPath = groupCoverPath;
			}else{
				uploadPath = filePath;
			}
		}

		//File downloadFile;
		if(StringUtils.isEmpty(viewFileName)) {
			viewFileName = "no-img/no-image.png";
		}/* else if (StringUtils.isEmpty(fileType)) {
			uploadPath = uploadPath + "/";
		} else {
			uploadPath = uploadPath + "/" + fileType;
		}*/
		//downloadFile = new File(uploadPath  + "/" + viewFileName);

		model.addAttribute("fileName", fileName);
		model.addAttribute("filePath", uploadPath  + "/" + viewFileName);
		return "imageView";
	}

	/**
	 * 이미지 업로드
	 * @param file
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file,
															@RequestParam("type") String type,
															@RequestParam(required=false, value="temporary", defaultValue="N") String temporary) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ResultCode.RESULT_CODE, ResultCode.FAIL);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		try {
			if(!(file.getContentType().contains("image") || file.getContentType().contains("jpeg"))) {
				resultMap.put(ResultCode.RESULT_MSG, "이미지 파일만 등록 가능합니다.");

			} else {
				String fileUploadPath = filePath;	// 파일 저장경로
				if (StringUtils.equals(temporary, "Y")) {
					fileUploadPath = fileTempPath;	// 임시파일경로
				}
				SavedFileEntity savedFileEntity = FileUtils.saveFile(fileUploadPath, file);
				resultMap.put("origFileName", savedFileEntity.getOriginalFileName());	// 원본 파일명
				resultMap.put("fileName", savedFileEntity.getSavedFileName());			// 파일명
				resultMap.put("fileSize", savedFileEntity.getSavedFileSize());			// 파일 사이즈
				resultMap.put(ResultCode.RESULT_CODE, ResultCode.SUCCESS);
			}
		} catch(Exception e) {
			resultMap.put(ResultCode.RESULT_MSG, "오류가 발생하였습니다.");
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, responseHeaders, HttpStatus.OK);
	}

	/**
	 * 파일 다운로드
	 * @param model
	 * @param fileNm
	 * @param origFileNm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileDownload")
	public String fileDownload(Model model,
			@RequestParam(required=false, value="fileNm") String fileNm,
			@RequestParam(required=false, value="origFileNm") String orgFileName) throws Exception {

		String uploadPath = filePath;
		File downloadFile = new File(uploadPath  + "/" + fileNm);
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("orgFileName", orgFileName);
		return "downloadView";
	}

	/**
	 * 이미지 미리보기 팝업
	 * @param attachKey
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/image/{fileName}/{fileType}", method = RequestMethod.GET)
	public String photoViewPopup(@PathVariable("fileName") String fileName,
								@PathVariable("fileType") String fileType,
								Model model) {
		
		model.addAttribute("fileName", fileName);
		model.addAttribute("fileType", fileType);
		return "popup/photo";
		
	}
	
	/**
	 * common error
	 * @param errorCode
	 * @param errorUrl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/commonError", method = RequestMethod.GET)
	public String commonError(@RequestParam(required = true, value = "errorCode") String errorCode,
								@RequestParam(required = true, value = "errorUrl") String errorUrl,
								HttpServletRequest request, Model model) {

		String errorMessage = messages.getMessage("common.permission.accessDenied");

		model.addAttribute("errorCode", errorCode);
		model.addAttribute("errorUrl", errorUrl);
		model.addAttribute("errorMessage", errorMessage);
		return "/error/commonError";
	}

	/**
	 * common error ajax
	 * @param errorCode
	 * @param errorUrl
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/commonErrorAjax", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> commonErrorAjax(HttpServletRequest request,
												@RequestParam(required = true, value = "errorCode") String errorCode,
												@RequestParam(required = false, value = "errorUrl") String errorUrl) {

		Map<String, Object> result = new HashMap<String, Object>();
		String errorMessage = null;

		if (StringUtils.equals(errorCode, "permissionDenied")) {
			errorMessage = "message.permission.accessDenied";
			result.put("errorUrl", errorUrl);
			result.put("errorMessage", errorMessage);
		}
		else if (StringUtils.equals(errorCode, "sessionExpired")) {
			result.put("errorAction", "parent.location.reload();");
		}
		result.put("errorCode", errorCode);
		return result;
	}
}