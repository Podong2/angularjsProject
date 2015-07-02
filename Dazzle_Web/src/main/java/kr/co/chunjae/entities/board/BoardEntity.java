package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import kr.co.chunjae.entities.PageEntity;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class BoardEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = -4497321988008034210L;
	public static final String BOARD_TYPE_CODE_NOTICE = "01";			// 공지사항 게시판 타입코드
	public static final String BOARD_TYPE_CODE_EVENT = "02";			// 이벤트 게시판 타입코드
	public static final String BOARD_EVENT_RATIRNG_LEAF_NUMBER = "01";
	public static final String BOARD_EVENT_RATIRNG_WOOD_NUMBER = "02";
	public static final String BOARD_EVENT_RATIRNG_STONE_NUMBER = "03";
	public static final String BOARD_EVENT_RATIRNG_COPPER_NUMBER = "04";
	public static final String BOARD_EVENT_RATIRNG_SILVER_NUMBER = "05";
	public static final String BOARD_EVENT_RATIRNG_GOLD_NUMBER = "06";
	public static final String BOARD_EVENT_RATIRNG_CRYSTAL_NUMBER = "07";
	public static final String EXPOSE_YN = "N";							// 메인상단 게시상태 empty값 설정

	public BoardEntity() {
		super(1L);
	}

	private long boardKey;					// 게시판 고유키
	private long userKey;					// 회원 고유키
	private long imageKey;					// 이미지 고유키
	private long boardImageName;			// 게시판 이미지 이름
	private String subject;					// 제목
	private String boardContents;			// 내용
	private String boardTypeCode;			// 게시판 타입(공지사항, 이벤트)
	private int hitsCount;					// 조회수
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventStartDate;			// 이벤트 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventEndDate;				// 이벤트 종료일
	private String topExposeYn;				// 상단 노출 여부
	private String sevendaysExposeYn;		// 7일간 상단 노출여부
	private String eventRate;				// 이벤트 조건 등급
	private int eventActivityScore;			// 이벤트 조건 활동점수
	private Date insertDate;					// 작성 날짜
	private String insertDateTime;			// 작성 경과 시간 (년/달/일/시/분)
	private Date deleteDate;					// 삭제 날짜
	private Date updateDate;					// 수정 날짜
	private Date insertState;				// 이벤트 진행여부
	private String fileName;					// 파일명
	private String fileOrigName;			// 원본 파일명
	private long attachKey; 					// 파일 고유키
	private int replyCount;					// 댓글 개수
	private int startRow;					//시작 개수
	private int rowCount;					//목록 개수
	private String ratingLeaf  = "N";		// 이벤트 게시판 등급 조건 (나뭇잎)
	private String ratingWood  = "N";		// 이벤트 게시판 등급 조건 (나무)
	private String ratingStone  = "N";		// 이벤트 게시판 등급 조건 (바위)
	private String ratingCopper  = "N";	// 이벤트 게시판 등급 조건 (구리)
	private String ratingSilver  = "N";	// 이벤트 게시판 등급 조건 (은)
	private String ratingGold  = "N";		// 이벤트 게시판 등급 조건 (금)
	private String ratingCrystal  = "N";	// 이벤트 게시판 등급 조건 (수정)
	private int dateCount;					// 이벤트 기간 카운트
	private String replyYn;					// 이벤트 기간에 따른 댓글 작성 유무
	private List<BoardEntity> boardEntity; // 게시판 목록
	private List<BoardEntity> noticeList; // 게시판 목록

	public void setPageParams(){
		Map paramsMap = new ConcurrentHashMap<String, Object>();
		if (StringUtils.isNotEmpty(getSearchKey())) paramsMap.put("searchKey", getSearchKey());
		if (StringUtils.isNotEmpty(getSearchValue())) paramsMap.put("searchValue", getSearchValue());
		super.setPageParams(paramsMap);
	}

	public long getBoardKey() {
		return boardKey;
	}
	public void setBoardKey(long boardKey) {
		this.boardKey = boardKey;
	}
	public long getUserKey() {
		return userKey;
	}
	public void setUserKey(long userKey) {
		this.userKey = userKey;
	}
	public long getImageKey() {
		return imageKey;
	}
	public void setImageKey(long imageKey) {
		this.imageKey = imageKey;
	}
	public long getBoardImageName() {
		return boardImageName;
	}
	public void setBoardImageName(long boardImageName) {
		this.boardImageName = boardImageName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public String getBoardTypeCode() {
		return boardTypeCode;
	}
	public void setBoardTypeCode(String boardTypeCode) {
		this.boardTypeCode = boardTypeCode;
	}
	public int getHitsCount() {
		return hitsCount;
	}
	public void setHitsCount(int hitsCount) {
		this.hitsCount = hitsCount;
	}
	public Date getEventStartDate() {
		return eventStartDate == null ? null : new Date(eventStartDate.getTime());
	}
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate == null ? null : new Date(eventStartDate.getTime());
	}
	public Date getEventEndDate() {
		return eventEndDate == null ? null : new Date(eventEndDate.getTime());
	}
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate == null ? null : new Date(eventEndDate.getTime());
	}
	public String getTopExposeYn() {
		return topExposeYn;
	}
	public void setTopExposeYn(String topExposeYn) {
		this.topExposeYn = topExposeYn;
	}
	public String getSevendaysExposeYn() {
		return sevendaysExposeYn;
	}
	public void setSevendaysExposeYn(String sevendaysExposeYn) {
		this.sevendaysExposeYn = sevendaysExposeYn;
	}
	public String getEventRate() {
		return eventRate;
	}
	public void setEventRate(String eventRate) {
		this.eventRate = eventRate;
	}
	public int getEventActivityScore() {
		return eventActivityScore;
	}
	public void setEventActivityScore(int eventActivityScore) {
		this.eventActivityScore = eventActivityScore;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public Date getDeleteDate() {
		return deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public Date getUpdateDate() {
		return updateDate == null ? null : new Date(updateDate.getTime());
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate == null ? null : new Date(updateDate.getTime());
	}
	public List<BoardEntity> getBoardEntity() {
		return boardEntity;
	}
	public void setBoardEntity(List<BoardEntity> boardEntity) {
		this.boardEntity = boardEntity;
	}
	public Date getInsertState() {
		return insertState == null ? null : new Date(insertState.getTime());
	}
	public void setInsertState(Date insertState) {
		this.insertState = insertState == null ? null : new Date(insertState.getTime());
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOrigName() {
		return fileOrigName;
	}
	public void setFileOrigName(String fileOrigName) {
		this.fileOrigName = fileOrigName;
	}
	public long getAttachKey() {
		return attachKey;
	}
	public void setAttachKey(long attachKey) {
		this.attachKey = attachKey;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getRatingLeaf() {
		return ratingLeaf;
	}

	public void setRatingLeaf(String ratingLeaf) {
		this.ratingLeaf = ratingLeaf;
	}

	public String getRatingWood() {
		return ratingWood;
	}

	public void setRatingWood(String ratingWood) {
		this.ratingWood = ratingWood;
	}

	public String getRatingStone() {
		return ratingStone;
	}

	public void setRatingStone(String ratingStone) {
		this.ratingStone = ratingStone;
	}

	public String getRatingCopper() {
		return ratingCopper;
	}

	public void setRatingCopper(String ratingCopper) {
		this.ratingCopper = ratingCopper;
	}

	public String getRatingSilver() {
		return ratingSilver;
	}

	public void setRatingSilver(String ratingSilver) {
		this.ratingSilver = ratingSilver;
	}

	public String getRatingGold() {
		return ratingGold;
	}

	public void setRatingGold(String ratingGold) {
		this.ratingGold = ratingGold;
	}

	public String getRatingCrystal() {
		return ratingCrystal;
	}

	public void setRatingCrystal(String ratingCrystal) {
		this.ratingCrystal = ratingCrystal;
	}

	public List<BoardEntity> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<BoardEntity> noticeList) {
		this.noticeList = noticeList;
	}

	public int getDateCount() {
		return dateCount;
	}

	public void setDateCount(int dateCount) {
		this.dateCount = dateCount;
	}

	public String getReplyYn() {
		return replyYn;
	}

	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}
}