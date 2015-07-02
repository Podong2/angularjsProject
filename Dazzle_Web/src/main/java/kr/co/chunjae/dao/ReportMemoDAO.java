package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.question.ReportMemoEntity;

public interface ReportMemoDAO {
	/**
	 * 신고 메모 목록 카운트
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	long selectReportMemoListCount(long reportKey) throws Exception;
	
	/**
	 * 신고 메모 목록
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	List<ReportMemoEntity> selectReportMemoList(long reportKey) throws Exception;
	
	/**
	 * 신고 메모 등록
	 * @param reportMemoEntity
	 * @throws Exception
	 */
	void insertReportMemo(ReportMemoEntity reportMemoEntity) throws Exception;
	
	/**
	 * 신고 메모 삭제
	 * @param reportMemoKey
	 * @throws Exception
	 */
	void deleteReportMemo(long reportMemoKey) throws Exception;
}