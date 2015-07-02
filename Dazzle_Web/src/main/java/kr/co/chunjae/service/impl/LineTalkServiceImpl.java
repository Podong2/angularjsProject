package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.LineTalkDAO;
import kr.co.chunjae.entities.board.LineTalkEntity;
import kr.co.chunjae.entities.board.LineTalkListEntity;
import kr.co.chunjae.entities.board.LineTalkLogEntity;
import kr.co.chunjae.entities.board.LineTalkReplyEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.LineTalkService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LineTalkServiceImpl implements LineTalkService{
	@Autowired
	private LineTalkDAO lineTalkDAO;

    @Autowired
    private GradeScoreDAO gradeScoreDAO;

	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(LineTalkServiceImpl.class);

	/**
	 * 한줄토크 댓글 본문
	 * @param lineTalkEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public LineTalkListEntity lineTalkReplyMain(LineTalkReplyEntity lineTalkEntity) {
		LineTalkListEntity lineTalkReplyEntity = new LineTalkListEntity();
		LineTalkEntity talkEntity = new LineTalkEntity();
		talkEntity.setTalkKey(lineTalkEntity.getTalkKey());
		try {
			lineTalkReplyEntity.setLineTalkEntity(lineTalkDAO.selectLineTalkEntity(talkEntity)); // 한줄토크 부모댓글
			LineTalkEntity lineTelk = lineTalkReplyEntity.getLineTalkEntity();
			//한줄토크 댓글 목록 String, Date Utils
			List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
			// 사용자 상세 프로필 등급
			for (int i=0; i < gradeScoreList.size(); i++){
				if(gradeScoreList.get(i).getUserLowScore() <= lineTelk.getActivityScore()
						&& gradeScoreList.get(i).getUserHighScore() >= lineTelk.getActivityScore()) {
					lineTelk.setUserRating(String.format("%02d", i+1));
				}
			}
			// 한줄토크 본문 댓글 작성 시점
			lineTelk.setInsertDateTime(DateUtils.getTimeStringFromNow(lineTelk.getInsertDate()));

			lineTalkReplyEntity.setLineTalkEntity(lineTelk);
			lineTalkReplyEntity.setResultCode(ResultCode.SUCCESS);
			lineTalkReplyEntity.setResultMsg(messages.getMessage("lineTalkReply_load.success"));
		} catch (Exception e) {
			logger.error("모바일 한줄토크 댓글 목록 : Error ",e);
			lineTalkReplyEntity.setResultMsg(messages.getMessage("lineTalkReply_load.fail"));
		}
		return lineTalkReplyEntity;
	}

	/**
	 * 한줄토크 댓글 목록
	 * @param lineTalkEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public LineTalkListEntity lineTalkReplyList(LineTalkReplyEntity lineTalkEntity) {
		List<LineTalkReplyEntity> lineTalkReplyList = new ArrayList<LineTalkReplyEntity>();
		LineTalkListEntity lineTalkReplyEntity = new LineTalkListEntity();
		try {
			lineTalkReplyList = lineTalkDAO.lineTalkReplyList(lineTalkEntity); //한줄토크 하위댓글 목록

			List<GradeScoreEntity> gradeScoreList = gradeScoreDAO.selectGradeScoreList();
			// 한줄토크 작성자 프로필 등급
			for (LineTalkReplyEntity lineTalkData : lineTalkReplyList){
				for (int i=0; i < gradeScoreList.size(); i++){
					if(gradeScoreList.get(i).getUserLowScore() <= lineTalkData.getActivityScore()
							&& gradeScoreList.get(i).getUserHighScore() >= lineTalkData.getActivityScore()) {
						lineTalkData.setUserRating(String.format("%02d", i+1));
					}
				}
			}

			// 한줄토크 댓글 목록 작성 시점
			for (int i = 0; i < lineTalkReplyList.size(); i++) {
				lineTalkReplyList.get(i).setInsertDateTime(DateUtils.getTimeStringFromNow(lineTalkReplyList.get(i).getInsertDate()));
			}

			lineTalkReplyEntity.setLineTalkReplyList(lineTalkReplyList);
			lineTalkReplyEntity.setResultCode(ResultCode.SUCCESS);
			lineTalkReplyEntity.setResultMsg(messages.getMessage("lineTalkReply_load.success"));
		} catch (Exception e) {
			logger.error("모바일 한줄토크 댓글 목록 : Error ",e);
			lineTalkReplyEntity.setResultMsg(messages.getMessage("lineTalkReply_load.fail"));
		}
		return lineTalkReplyEntity;
	}

	/**
	 * 한줄토크 댓글 작성자 로그값 'Y'변
	 * @param talkList
	 * @return경
	 * @throws Exception
	 */
	@Override
	public LineTalkListEntity updateLineTalkLog(LineTalkReplyEntity talkList){
		List<LineTalkLogEntity> lineTalkLogList = new ArrayList<LineTalkLogEntity>();
		LineTalkListEntity lineTalkListEntity = new LineTalkListEntity();
		try {
			//댓글작성자 고유키 목록
			lineTalkLogList = lineTalkDAO.selectLineTalkLogList(talkList.getTalkKey());

			//댓글 읽음 여부 "Y" 변경
			for (LineTalkLogEntity lineTalkLogData : lineTalkLogList) {
				if(lineTalkLogData.getUserKey() == talkList.getSessionUserKey()){
					lineTalkLogData.setTalkKey(talkList.getTalkKey());
					lineTalkDAO.updateLineTalkLog(lineTalkLogData);
				}
			}

			lineTalkListEntity.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			lineTalkListEntity.setResultCode(ResultCode.FAIL);
			logger.error("모바일 한줄토크 댓글 로그 read_yn값 'Y' 변경 오류 : Error ");
		}

		return lineTalkListEntity;
	}
}