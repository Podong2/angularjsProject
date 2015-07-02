package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.question.ReportEntity;

public interface ReportDAO {
	/**
	 * 신고 목록 카운트
	 * @param reportEntity
	 * @return
	 * @throws Exception
	 */
	long selectReportListCount(ReportEntity reportEntity) throws Exception;
	/**
	 * 신고 목록
	 * @param reportEntity
	 * @return
	 * @throws Exception
	 */
	List<ReportEntity> selectReportList(ReportEntity reportEntity) throws Exception;
	/**
	 * 신고 정보
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	ReportEntity selectReport(long reportKey) throws Exception;
	/**
	 * 신고 여부 확인
	 * @param reportEntity
	 * @return
	 * @throws Exception
	 */
	int selectIsReport(ReportEntity reportEntity) throws Exception;
	/**
	 * 신고 하기
	 * @param reportEntity
	 * @throws Exception
	 */
	void insertReport(ReportEntity reportEntity) throws Exception;
	
	/**
	 * 신고 처리 상태 변경
	 * @param reportEntity
	 * @throws Exception
	 */
	void updateReportState(ReportEntity reportEntity) throws Exception;
}