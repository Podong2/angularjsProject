package kr.co.chunjae.service;

import kr.co.chunjae.entities.statistics.GradeEntity;

public interface StatisticsService {

	/**
	 * 학년별 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	GradeEntity selectGradeStatictics(GradeEntity gradeEntity) throws Exception;

	/**
	 * 학년별 대단원 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	GradeEntity selectGradeDetail(GradeEntity gradeEntity)throws Exception;

	/**
	 * 기간별 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	GradeEntity selectPeriodStatictics(GradeEntity gradeEntity) throws Exception;

	/**
	 * 활동개수 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	GradeEntity selectStaticticsActivityCount(GradeEntity gradeEntity) throws Exception;

	/**
	 * 활동점수 통계
	 * @param gradeEntity
	 * @return
	 * @throws Exception
	 */
	GradeEntity selectStaticticsActivityScore(GradeEntity gradeEntity) throws Exception;
}