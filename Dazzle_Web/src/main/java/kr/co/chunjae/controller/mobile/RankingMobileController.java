package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.entities.RankingEntity;
import kr.co.chunjae.service.RankingService;
import kr.co.digigroove.commons.messages.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/mobile/ranking")
public class RankingMobileController {
	@Autowired
	private RankingService rankingService;
	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(RankingMobileController.class);

	/**
	 * 개인 랭킹(누적)
	 * @return
	 */
	@RequestMapping(value="/selectUserRanking", method = RequestMethod.GET)
	public RankingEntity selectUserRanking() {

		RankingEntity result = new RankingEntity();
		try {
			result = rankingService.selectUserRanking();
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("개인 랭킹(누적) 오류", e);
		}
		return result;
	}

	/**
	 * 그룹 랭킹(누적)
	 * @return
	 */
	@RequestMapping(value="/selectGroupRanking", method = RequestMethod.GET)
	public RankingEntity selectGroupRanking() {

		RankingEntity result = new RankingEntity();
		try {
			result = rankingService.selectGroupRanking();
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("그룹 랭킹(누적) 오류", e);
		}
		return result;
	}

	/**
	 * 랭킹날짜 가져오기(이전 6개월)
	 * @return
	 */
	@RequestMapping(value="/selectMonthDateList", method = RequestMethod.GET)
	public RankingEntity selectMonthDateList() {

		RankingEntity result = new RankingEntity();
		try {
			result = rankingService.selectMonthDateList();
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("랭킹 월별 날짜 가져오기 오류", e);
		}
		return result;
	}

	/**
	 * 개인 랭킹(월별)
	 * @return
	 */
	@RequestMapping(value="/selectMonthUserRanking", method = RequestMethod.GET)
	public RankingEntity selectMonthUserRanking(
			@RequestParam(required=false ,value = "rankingDate") String rankingDate) {

		RankingEntity result = new RankingEntity();
		try {
			result = rankingService.selectMonthUserRanking(rankingDate);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("개인 랭킹(월별) 오류", e);
		}
		return result;
	}

	/**
	 * 그룹 랭킹(월별)
	 * @return
	 */
	@RequestMapping(value="/selectMonthGroupRanking", method = RequestMethod.GET)
	public RankingEntity selectMonthGroupRanking(
			@RequestParam(required=false ,value = "rankingDate") String rankingDate) {

		RankingEntity result = new RankingEntity();
		try {
			result = rankingService.selectMonthGroupRanking(rankingDate);
			result.setResultCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.error("그룹 랭킹(월별) 오류", e);
		}
		return result;
	}


}