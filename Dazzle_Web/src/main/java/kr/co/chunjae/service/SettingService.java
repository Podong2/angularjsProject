package kr.co.chunjae.service;

import java.util.List;

import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.entities.user.ActivityScoreEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;

public interface SettingService {
	/**
	 * 활동점수 목록 출력
	 * @return
	 * @throws Exception
	 */
    List<ActivityScoreEntity> selectActivityscore() throws Exception;

    /**
     * 활동점수 수정
     * @param activityScoreEntity
     * @return
     * @throws Exception
     */
    ResponseEntity updateActivityscore(List<ActivityScoreEntity> activityScoreEntity)throws Exception;

	/**
	 * 회원등급점수 목록 출력
	 * @return
	 * @throws Exception
	 */
    List<GradeScoreEntity> selectGradescore() throws Exception;

    /**
     * 회원등급 수정
     * @param gradeScoreEntity
     * @return
     * @throws Exception
     */
    ResponseEntity updateGradescore(List<GradeScoreEntity> gradeScoreEntity) throws Exception;

    /**
     * 한줄공지 쓰기
     * @param lineNoticeEntity
     * @return
     * @throws Exception
     */
    ResponseEntity insertLineNotice(LineNoticeEntity lineNoticeEntity) throws Exception;

	/**
	 * 한줄공지 목록
	 * @param lineNoticeEntity
	 * @return
	 * @throws Exception
	 */
     LineNoticeEntity selectLineNoticeList(LineNoticeEntity lineNoticeEntity) throws Exception;

  	/**
  	 * 한줄공지 정보
  	 * @param lineNoticeEntity
  	 * @return
  	 * @throws Exception
  	 */
     LineNoticeEntity selectLineNotice(long lineNoticeKey) throws Exception;

 	/**
 	 * 한줄알림 수정
 	 * @param LineNoticeEntity
 	 * @return
 	 * @throws Exception
 	 */
 	void updateLineNotice(LineNoticeEntity lineNoticeEntity)throws Exception;

 	/**
 	 * 한줄알림 삭제
 	 * @param lineNoticeKey
 	 * @return
 	 * @throws Exception
 	 */
 	void deleteLineNotice(long lineNoticeKey)throws Exception;
}