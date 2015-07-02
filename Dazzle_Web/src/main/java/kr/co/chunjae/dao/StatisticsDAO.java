package kr.co.chunjae.dao;

import java.util.List;

import kr.co.chunjae.entities.statistics.GradeEntity;

public interface StatisticsDAO {
	/**
	 * 학년별 통계
	 * @param gradeEntity
	 * @throws Exception
	 */
    List<GradeEntity> selectGradeStatictics(GradeEntity gradeEntity)throws Exception;

    /**
     * 학년별 대단원 통계
     * @param gradeEntity
     * @return
     * @throws Exception
     */
    List<GradeEntity> selectGradeDetail(GradeEntity gradeEntity)throws Exception;
    /**
     * 기간별 통계
     * @param gradeEntity
     * @return
     * @throws Exception
     */
    List<GradeEntity> selectPeriodStatictics(GradeEntity gradeEntity)throws Exception;

    /**
     * 활동개수 통계 (문제 + 답글)
     * @param gradeEntity
     * @return
     * @throws Exception
     */
    List<GradeEntity> selectStaticticsActivityQnA(GradeEntity gradeEntity)throws Exception;

    /**
     * 활동개수 통계 (문제 or 답글)
     * @param gradeEntity
     * @return
     * @throws Exception
     */
    List<GradeEntity> selectStaticticsActivityBoard(GradeEntity gradeEntity)throws Exception;

    /**
     * 활동점수 통계
     * @param gradeEntity
     * @return
     * @throws Exception
     */
    List<GradeEntity> selectStaticticsActivityScore(GradeEntity gradeEntity)throws Exception;
}