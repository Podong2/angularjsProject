package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.board.LineTalkLogEntity;
import kr.co.chunjae.entities.board.LineTalkReplyEntity;

public interface LineTalkDAO {
    /**
     * 한줄토크전송
     * @param lineTalkEntity
     * @throws Exception
     */
    void inserTalk(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 한줄토크 댓글 전송
     * @param lineTalkEntity
     * @throws Exception
     */
    void inserTalkReply(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 한줄토크 댓글목록
     * @param linetalkEntity
     * @return
     * @throws Exception
     */
    List<LineTalkReplyEntity> lineTalkReplyList(LineTalkReplyEntity linetalkEntity)throws Exception;

    /**
     * 한줄토크 댓글 목록 부모글
     * @param linetalkEntity
     * @return
     * @throws Exception
     */
    LineTalkEntity selectLineTalkEntity(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 한줄토크 댓글 유저 고유키 로그
     * @param talkKey
     * @return
     * @throws Exception
     */
    List<LineTalkLogEntity> selectLineTalkLogList(long talkKey)throws Exception;

    /**
     * 한줄토크 댓글 로그 read_yn 읽음 변경
     * @param lineTalkLogData
     * @throws Exception
     */
    void updateLineTalkLog(LineTalkLogEntity lineTalkLogData)throws Exception;

    /**
     * 한줄토크 댓글 로그 read_yn 안읽음 변경 (작성자는 읽음 처리)
     * @param lineTalkEntity
     * @throws Exception
     */
    void updateLineTalkLogNotRead(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 한줄토크 로그 작성
     * @param lineTalkEntity
     * @throws Exception
     */
    void insertLineTalkLog(LineTalkEntity lineTalkEntity)throws Exception;

    /**
     * 한줄토크 삭제
     * @param lineTalkEntity
     * @throws Exception
     */
    void deleteLineTalk(LineTalkEntity lineTalkEntity)throws Exception;
}