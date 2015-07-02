package kr.co.chunjae.entities.question;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import kr.co.chunjae.entities.PageEntity;

import org.apache.commons.lang.StringUtils;

public class ReportEntity extends PageEntity implements Serializable {
	
	private static final long serialVersionUID = -8332670791681062733L;

	public ReportEntity() {
		super(1L);
	}
	
	private long reportKey;					// 신고관리 고유키
	private long reportUserKey;				// 신고자 고유키
	private String reportUserName;			// 신고자명
	private long writeUserKey;				// 등록자 고유키(신고 당한 유저)
	private String writeUserName;			// 등록자명
	private long reportBoardKey;			// 신고 게시물 고유키
	private long reportBoardUserKey;		// 신고 게시물 유저키
	private long questionKey;				// 문제 고유키
	private String reportContent;			// 신고내용
	private String reportStateCode;			// 처리상태(01:대기, 02:완료)
	private String reportStateCodeName;		// 처리상태명
	private String reportTypeCode;			// 신고 게시물 구분 (01:문제, 02:답글)
	private String reportTypeCodeName;		// 신고 게시물 구분명
	private Date reportDate;				// 신고일
	private Date reportProcessDate;			// 신고 처리일
	private List<ReportEntity> reportList;
	
	public void setPageParams() {
		Map paramsMap = new ConcurrentHashMap<String, Object>();
		if (StringUtils.isNotEmpty(reportTypeCode))       paramsMap.put("reportTypeCode", reportTypeCode);
		if (StringUtils.isNotEmpty(getStartDate()))     paramsMap.put("startDate", getStartDate());
		if (StringUtils.isNotEmpty(getEndDate()))       paramsMap.put("endDate", getEndDate());
		if (StringUtils.isNotEmpty(getSearchKey()))     paramsMap.put("searchKey", getSearchKey());
		if (StringUtils.isNotEmpty(getSearchValue()))   paramsMap.put("searchValue", getSearchValue());
		super.setPageParams(paramsMap);
	}
	
	public long getReportKey() {
		return reportKey;
	}
	public void setReportKey(long reportKey) {
		this.reportKey = reportKey;
	}
	public long getReportUserKey() {
		return reportUserKey;
	}
	public void setReportUserKey(long reportUserKey) {
		this.reportUserKey = reportUserKey;
	}
	public String getReportUserName() {
		return reportUserName;
	}
	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}
	public long getWriteUserKey() {
		return writeUserKey;
	}
	public void setWriteUserKey(long writeUserKey) {
		this.writeUserKey = writeUserKey;
	}
	public String getWriteUserName() {
		return writeUserName;
	}
	public void setWriteUserName(String writeUserName) {
		this.writeUserName = writeUserName;
	}
	public long getReportBoardKey() {
		return reportBoardKey;
	}
	public void setReportBoardKey(long reportBoardKey) {
		this.reportBoardKey = reportBoardKey;
	}
	public long getReportBoardUserKey() {
		return reportBoardUserKey;
	}
	public void setReportBoardUserKey(long reportBoardUserKey) {
		this.reportBoardUserKey = reportBoardUserKey;
	}
	public long getQuestionKey() {
		return questionKey;
	}
	public void setQuestionKey(long questionKey) {
		this.questionKey = questionKey;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportStateCode() {
		return reportStateCode;
	}
	public void setReportStateCode(String reportStateCode) {
		this.reportStateCode = reportStateCode;
	}
	public String getReportStateCodeName() {
		return reportStateCodeName;
	}
	public void setReportStateCodeName(String reportStateCodeName) {
		this.reportStateCodeName = reportStateCodeName;
	}
	public String getReportTypeCode() {
		return reportTypeCode;
	}
	public void setReportTypeCode(String reportTypeCode) {
		this.reportTypeCode = reportTypeCode;
	}
	public String getReportTypeCodeName() {
		return reportTypeCodeName;
	}
	public void setReportTypeCodeName(String reportTypeCodeName) {
		this.reportTypeCodeName = reportTypeCodeName;
	}
	public Date getReportDate() {
		return reportDate == null ? null : new Date(reportDate.getTime());
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate == null ? null : new Date(reportDate.getTime());
	}
	public Date getReportProcessDate() {
		return reportProcessDate == null ? null : new Date(reportProcessDate.getTime());
	}
	public void setReportProcessDate(Date reportProcessDate) {
		this.reportProcessDate = reportProcessDate == null ? null : new Date(reportProcessDate.getTime());
	}
	public List<ReportEntity> getReportList() {
		return reportList;
	}
	public void setReportList(List<ReportEntity> reportList) {
		this.reportList = reportList;
	}
}