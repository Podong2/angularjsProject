package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.question.ReportEntity;
import kr.co.chunjae.entities.question.ReportMemoEntity;

public interface ReportService {
	/**
	 * 신고관리 목록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	public ReportEntity selectReportList(ReportEntity reportEntity) throws Exception;
	
	/**
	 * 신고 정보
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	public ReportEntity selectReport(long reportKey) throws Exception;
	
	/**
	 * 신고 메모 리스트
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	public List<ReportMemoEntity> selectReportMemoList(long reportKey) throws Exception;
	
	/**
	 * 신고 여부 확인
	 * @param reportEntity
	 * @return
	 * @throws Exception
	 */
	public int selectIsReport(ReportEntity reportEntity) throws Exception;
	
	/**
	 * 신고 하기
	 * @param reportEntity
	 * @throws Exception
	 */
	public void insertReport(ReportEntity reportEntity) throws Exception;
	
	/**
	 * 신고 메모 등록
	 * @param reportMemoEntity
	 * @throws Exception
	 */
	public void insertReportMemo(ReportMemoEntity reportMemoEntity) throws Exception;
	
	/**
	 * 신고 메모 삭제
	 * @param reportMemoKey
	 * @throws Exception
	 */
	public void deleteReportMemo(long reportMemoKey) throws Exception;
	
	/**
	 * 신고 상태 변경
	 * @param reportEntity
	 * @throws Exception
	 */
	public void updateReportState(ReportEntity reportEntity) throws Exception;
}