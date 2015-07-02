package kr.co.chunjae.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.dao.StatisticsDAO;
import kr.co.chunjae.entities.statistics.GradeEntity;
import kr.co.chunjae.service.StatisticsService;
import kr.co.digigroove.commons.messages.Messages;
import kr.co.digigroove.commons.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService{
	@Autowired
	private StatisticsDAO statisticsDAO;

	@Autowired
	private Messages messages;

	/**
	 * 학년별 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GradeEntity selectGradeStatictics(GradeEntity gradeEntity) throws Exception {
		List<GradeEntity> GradeList = new ArrayList<GradeEntity>();
		int questionTotal = 0, answerTotal = 0, noAnswerTotal= 0;
		GradeList = statisticsDAO.selectGradeStatictics(gradeEntity);
		if(!GradeList.isEmpty()){
			for (GradeEntity gradeEntityData : GradeList) {
				questionTotal = questionTotal + gradeEntityData.getQuestionCount();
				answerTotal = answerTotal + gradeEntityData.getAnswerCount();
				noAnswerTotal = noAnswerTotal + gradeEntityData.getNoAnswerCount();
			}
			gradeEntity.setQuestionCountTotal(questionTotal);
			gradeEntity.setAnswerCountTotal(answerTotal);
			gradeEntity.setNoAnswerCountTotal(noAnswerTotal);
		}
		gradeEntity.setGradeList(GradeList);
		return gradeEntity;
	}

	/**
	 * 학년별 대단원 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GradeEntity selectGradeDetail(GradeEntity gradeEntity)
			throws Exception {
		gradeEntity.setGradeList(statisticsDAO.selectGradeDetail(gradeEntity));
		return gradeEntity;
	}

	/**
	 * 기간별 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GradeEntity selectPeriodStatictics(GradeEntity gradeEntity)
			throws Exception {
		gradeEntity.setGradeList(statisticsDAO.selectPeriodStatictics(gradeEntity));
		return gradeEntity;
	}

	/**
	 * 활동개수 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GradeEntity selectStaticticsActivityCount(GradeEntity gradeEntity)
			throws Exception {
		List<GradeEntity> gradeArray = new ArrayList<GradeEntity>();
		List<GradeEntity> gradeList = null;
		if(!StringUtils.isEmpty(gradeEntity.getStartDate()) && !StringUtils.isEmpty(gradeEntity.getEndDate())){
			GradeEntity gradeData = new GradeEntity();
			gradeData.setUserCount(0);
			gradeData.setUserRate(0);
			int gradeNumber = 0;
			boolean gradeState = false;

			if(StringUtils.isEmpty(gradeEntity.getActivityType())){
				gradeEntity.setActivityType(CommonCode.STATISTICS_ACTIVITY_TYPE_QUESTION);
				gradeList = statisticsDAO.selectStaticticsActivityBoard(gradeEntity);
			}else{
				if(!gradeEntity.getActivityType().equals(CommonCode.STATISTICS_ACTIVITY_TYPE_QNA)
						|| gradeEntity.getActivityType().isEmpty()){
					gradeList = statisticsDAO.selectStaticticsActivityBoard(gradeEntity);
				}else if(gradeEntity.getActivityType().equals(CommonCode.STATISTICS_ACTIVITY_TYPE_QNA)){
					gradeList = statisticsDAO.selectStaticticsActivityQnA(gradeEntity);
				}
			}

			// 빈 data공간 0값 넣기
			int gradeLength =  gradeList.size()-1;
			if(!StringUtils.isEmpty(gradeList)){
				for (int i = 0; i <= gradeList.get(gradeLength).getActivityCount(); i++) {
					for (int j = 0; j <= gradeLength; j++) {
						if(i == gradeList.get(j).getActivityCount()){
							gradeState = true;
							gradeNumber = j;
							break;
						}
					}
					if(gradeState){
							gradeArray.add(gradeList.get(gradeNumber));
							gradeState = false;
					}else{
						gradeData.setActivityCount(i);
						gradeArray.add(gradeData);
					}

				}
			}
			gradeEntity.setGradeList(gradeArray);
		}
		return gradeEntity;
	}

	/**
	 * 활동점수 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public GradeEntity selectStaticticsActivityScore(GradeEntity gradeEntity)
			throws Exception {
		if(!StringUtils.isEmpty(gradeEntity.getStartDate()) && !StringUtils.isEmpty(gradeEntity.getEndDate())){
			gradeEntity.setGradeList(statisticsDAO.selectStaticticsActivityScore(gradeEntity));
		}
		return gradeEntity;
	}
}