package kr.co.chunjae.service.impl;

import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.GradeScoreDAO;
import kr.co.chunjae.dao.LineNoticeDAO;
import kr.co.chunjae.dao.SettingDAO;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.board.LineNoticeEntity;
import kr.co.chunjae.entities.user.ActivityScoreEntity;
import kr.co.chunjae.entities.user.GradeScoreEntity;
import kr.co.chunjae.service.SettingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SettingServiceImpl implements SettingService{
    @Autowired
    private SettingDAO settingDAO;
    @Autowired
    private GradeScoreDAO gradeScoreDAO;
    @Autowired
    private  LineNoticeDAO lineNoticeDAO;

    private static final Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);

	/**
	 * 활동점수 목록 출력
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ActivityScoreEntity> selectActivityscore() throws Exception {

		return settingDAO.selectActivityScoreList();
	}

    /**
     * 활동점수 수정
     * @param activityScoreEntity
     * @return
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseEntity updateActivityscore(List<ActivityScoreEntity> activityScoreEntity) throws Exception{
		ResponseEntity result = new ResponseEntity();
        for (ActivityScoreEntity list : activityScoreEntity) {
            settingDAO.updateActivityScoreList(list);
        }
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

	/**
	 * 회원등급점수 목록 출력
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GradeScoreEntity> selectGradescore() throws Exception {

		return gradeScoreDAO.selectGradeScoreList();
	}

    /**
     * 회원등급 수정
     * @param gradeScoreEntity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseEntity updateGradescore(List<GradeScoreEntity> gradeScoreEntity)  throws Exception{
    	ResponseEntity result = new ResponseEntity();
        for (GradeScoreEntity list : gradeScoreEntity){
        	gradeScoreDAO.updateGradeScoreList(list);
        }
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

	/**
	 * 한줄공지 목록
	 * @param
	 * @param lineNoticeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public LineNoticeEntity selectLineNoticeList(LineNoticeEntity lineNoticeEntity) throws Exception {

		lineNoticeEntity.setPageSize(CommonCode.PAGE_LIST_SIZE_PARAM, CommonCode.PAGE_GROUP_SIZE_PARAM);
		lineNoticeEntity.setDataSize(lineNoticeDAO.selectLineNoticeListCount());
		List<LineNoticeEntity> lineNoticeListResult = lineNoticeDAO.selectLineNoticeList(lineNoticeEntity);
		lineNoticeEntity.setLineNoticeList(lineNoticeListResult);

		return lineNoticeEntity;
	}

    /**
     * 한줄공지 쓰기
     * @param lineNoticeEntity
     * @return
     * @throws Exception
     */
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity insertLineNotice(LineNoticeEntity lineNoticeEntity){
		ResponseEntity result = new ResponseEntity();

		try{
			lineNoticeDAO.inserNotice(lineNoticeEntity);
			result.setResultCode(ResultCode.SUCCESS);
        }catch(Exception e){
        	logger.error(" 관리자 한줄공지 등록 / 수정 페이지 : Error ", e);
		}
		return result;
	}

    /**
     * 한줄공지 정보
     * @param lineNoticeKey
     * @return
     * @throws Exception
     */
	@Override
	public LineNoticeEntity selectLineNotice(long lineNoticeKey)
			throws Exception {
		return lineNoticeDAO.selectLineNoticeInfo(lineNoticeKey);
	}


	/**
	 * 한줄알림 수정
	 * @param LineNoticeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateLineNotice(LineNoticeEntity lineNoticeEntity)throws Exception {
		lineNoticeDAO.updateLineNotice(lineNoticeEntity);
	}

	/**
	 * 한줄알림 삭제
	 * @param lineNoticeKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deleteLineNotice(long lineNoticeKey) throws Exception {
		lineNoticeDAO.deleteLineNotice(lineNoticeKey);
	}
}