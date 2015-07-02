package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.board.LineNoticeEntity;

public interface LineNoticeDAO {
	/**
	 * 한줄공지 쓰기
	 * @param lineNoticeEntity
	 * @throws Exception
	 */
	void inserNotice(LineNoticeEntity lineNoticeEntity) throws Exception;

	/**
	 * 한줄공지 목록 개수
	 * @return
	 * @throws Exception
	 */
	int selectLineNoticeListCount() throws Exception;

	/**
	 * 한줄공지 목록
	 * @param lineNoticeEntity
	 * @return
	 * @throws Exception
	 */
	List<LineNoticeEntity> selectLineNoticeList(LineNoticeEntity lineNoticeEntity) throws Exception;

	/**
	 * 한줄공지 정보
	 * @return
	 * @throws Exception
	 */
	LineNoticeEntity selectLineNotice() throws Exception;

	/**
	 * 한줄공지 정보(관리자)
	 * @param lineNoticeKey
	 * @return
	 * @throws Exception
	 */
	LineNoticeEntity selectLineNoticeInfo(long lineNoticeKey) throws Exception;

	/**
	 * 한줄알림 수정
	 * @param lineNoticeEntity
	 * @throws Exception
	 */
	void updateLineNotice(LineNoticeEntity lineNoticeEntity)throws Exception;

	/**
	 * 한줄알림 삭제
	 * @param lineNoticeKey
	 * @throws Exception
	 */
	 void deleteLineNotice(long lineNoticeKey)throws Exception;
}