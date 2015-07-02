package kr.co.chunjae.service;

import kr.co.chunjae.entities.board.LineTalkListEntity;
import kr.co.chunjae.entities.board.LineTalkReplyEntity;

public interface LineTalkService {

	/**
	 * 한줄토크 댓글 본문
	 * @param lineTalkEntity
	 * @return
	 * @throws Exception
	 */
	LineTalkListEntity lineTalkReplyMain(LineTalkReplyEntity lineTalkEntity)throws Exception;

	/**
	 * 한줄토크 댓글 목록
	 * @param lineTalkEntity
	 * @return
	 * @throws Exception
	 */
	LineTalkListEntity lineTalkReplyList(LineTalkReplyEntity lineTalkEntity)throws Exception;


	/**
	 * 한줄토크 댓글 로그 변경
	 * @param talkList
	 * @return
	 * @throws Exception
	 */
	LineTalkListEntity updateLineTalkLog(LineTalkReplyEntity talkList)throws Exception;

}