package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.dao.ReportDAO;
import kr.co.chunjae.dao.ReportMemoDAO;
import kr.co.chunjae.entities.question.AnswerEntity;
import kr.co.chunjae.entities.question.ReportEntity;
import kr.co.chunjae.entities.question.ReportMemoEntity;
import kr.co.chunjae.service.AnswerService;
import kr.co.chunjae.service.ReportService;
import kr.co.digigroove.commons.utils.DateUtils;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;
	@Autowired
	private ReportMemoDAO reportMemoDAO;
	@Autowired
	private AnswerService AnswerService;
	
	/**
	 * 신고관리 목록
	 * @param groupEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ReportEntity selectReportList(ReportEntity reportEntity) throws Exception {
		reportEntity.setPageParams();
		reportEntity.setDataSize(reportDAO.selectReportListCount(reportEntity));
		List<ReportEntity> reportList = reportDAO.selectReportList(reportEntity);
		reportEntity.setReportList(reportList);
		return reportEntity;
	}
	
	/**
	 * 신고 정보
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public ReportEntity selectReport(long reportKey) throws Exception {
		
		return reportDAO.selectReport(reportKey);
	}
	
	/**
	 * 신고 메모 리스트
	 * @param reportKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ReportMemoEntity> selectReportMemoList(long reportKey) throws Exception {
		
		List<ReportMemoEntity> reportMemoList = new ArrayList<ReportMemoEntity>();
		long reportMemoListCount = reportMemoDAO.selectReportMemoListCount(reportKey);
		if (reportMemoListCount > 0) {
			reportMemoList = reportMemoDAO.selectReportMemoList(reportKey);
		}
		return reportMemoList;
	}
	
	/**
	 * 신고 여부 확인
	 * @param reportEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectIsReport(ReportEntity reportEntity) throws Exception {
		
		reportEntity.setReportStateCode(CommonCode.REPORT_STATE_STAND_BY);	// 신고 완료된 게시물 제외
		if (StringUtils.equals(reportEntity.getReportTypeCode(), CommonCode.REPORT_TYPE_QUESTION)) {
			reportEntity.setReportBoardKey(reportEntity.getQuestionKey());
		} else {
			AnswerEntity answerEntity = new AnswerEntity();
			answerEntity.setQuestionKey(reportEntity.getQuestionKey());
			answerEntity.setUserKey(reportEntity.getReportBoardUserKey());
			List<AnswerEntity> reportAnswerList = AnswerService.selectReportAnswerList(answerEntity);
			reportEntity.setReportBoardKey(reportAnswerList.get(0).getAnswerKey());
		}
		return reportDAO.selectIsReport(reportEntity);
	}
	
	/**
	 * 신고 하기
	 * @param reportEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertReport(ReportEntity reportEntity) throws Exception {
		// 신고 게시물 타입이 답글일 경우, 등록된 모든 답글 신고
		if (StringUtils.equals(reportEntity.getReportTypeCode(), CommonCode.REPORT_TYPE_ANSWER)) {
			AnswerEntity answerEntity = new AnswerEntity();
			answerEntity.setQuestionKey(reportEntity.getQuestionKey());
			answerEntity.setUserKey(reportEntity.getReportBoardUserKey());
			List<AnswerEntity> reportAnswerList = AnswerService.selectReportAnswerList(answerEntity);
			for (int i=0; i<reportAnswerList.size() ; i++) {
				reportEntity.setReportBoardKey(reportAnswerList.get(i).getAnswerKey());
				reportDAO.insertReport(reportEntity);
			}
		} else {
			reportEntity.setReportBoardKey(reportEntity.getQuestionKey());
			reportDAO.insertReport(reportEntity);
		}
		
	}
	
	/**
	 * 신고 메모 등록
	 * @param reportMemoEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void insertReportMemo(ReportMemoEntity reportMemoEntity) throws Exception {
		
		reportMemoDAO.insertReportMemo(reportMemoEntity);
	}
	
	/**
	 * 신고 메모 삭제
	 * @param reportMemoKey
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteReportMemo(long reportMemoKey) throws Exception {
		
		reportMemoDAO.deleteReportMemo(reportMemoKey);
	}
	
	/**
	 * 신고 상태 변경
	 * @param reportEntity
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateReportState(ReportEntity reportEntity) throws Exception {
		
		Date reportProcessDate = DateUtils.getDate();
		if (StringUtils.isNotEmpty(reportEntity.getReportStateCode()) &&
			StringUtils.equals(reportEntity.getReportStateCode(), CommonCode.REPORT_STATE_STAND_BY)) {
			
			reportProcessDate = null;	// '대기'상태로 변경할 경우 신고처리일 초기화
		}
		reportEntity.setReportProcessDate(reportProcessDate);
		reportDAO.updateReportState(reportEntity);
	}
}