package kr.co.chunjae.entities.board;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.PageEntity;

public class BoardReplyEntity extends PageEntity implements Serializable {

	private static final long serialVersionUID = -1259423498924224341L;
	public static final String BOARD_EVENT_REPLY_RESTRICTION_Y= "Y";
	public static final String BOARD_EVENT_REPLY_RESTRICTION_N= "N";

	public BoardReplyEntity() {
		super(1L);
	}

	private long replyKey;				// 댓글 고유키
	private long boardKey;				// 게시판 고유키
	private long userKey;				// 회원 고유키
	private String id;					// 회원 아이디
	private String name;					// 회원 이름
	private String userGrade;			// 회원 학년
	private String phoneNumber;			// 회원 핸드폰 번호
	private String boardReplyContents;	// 내용
	private Date insertDate;				// 작성 날짜
	private Date deleteDate;				// 삭제 날짜
	private int lownum;					// 목록번호
	private int activityScore;			// 활동점수
	private String userRating;			// 회원 등급
	private String insertDateTime;		// 작성시점
	private String eventRate;			// 이벤트 참여 작성 제한
	private String fileName;				// 프로필 이미지
	private String closedYn;				// 프로필 비공개 유무
	private String resultCode = ResultCode.FAIL;
	private String resultMsg  = "";
	private List<BoardReplyEntity> boardReplyEntity; // 게시판 댓글 목록

	public long getReplyKey() {
		return replyKey;
	}
	public void setReplyKey(long replyKey) {
		this.replyKey = replyKey;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBoardReplyContents() {
		return boardReplyContents;
	}
	public void setBoardReplyContents(String boardReplyContents) {
		this.boardReplyContents = boardReplyContents;
	}
	public Date getInsertDate() {
		return insertDate == null ? null : new Date(insertDate.getTime());
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
	}
	public Date getDeleteDate() {
		return deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
	}
	public int getLownum() {
		return lownum;
	}
	public void setLownum(int lownum) {
		this.lownum = lownum;
	}
	public List<BoardReplyEntity> getBoardReplyEntity() {
		return boardReplyEntity;
	}
	public void setBoardReplyEntity(List<BoardReplyEntity> boardReplyEntity) {
		this.boardReplyEntity = boardReplyEntity;
	}
	public int getActivityScore() {
		return activityScore;
	}
	public void setActivityScore(int activityScore) {
		this.activityScore = activityScore;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public String getEventRate() {
		return eventRate;
	}
	public void setEventRate(String eventRate) {
		this.eventRate = eventRate;
	}
	public String getClosedYn() {
		return closedYn;
	}
	public void setClosedYn(String closedYn) {
		this.closedYn = closedYn;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}