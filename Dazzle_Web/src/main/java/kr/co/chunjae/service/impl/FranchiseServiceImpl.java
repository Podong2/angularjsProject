package kr.co.chunjae.service.impl;

import kr.co.chunjae.constants.CommonCode;
import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.FranchiseDAO;
import kr.co.chunjae.entities.FranchiseEntity;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.service.FranchiseService;
import kr.co.digigroove.commons.messages.Messages;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FranchiseServiceImpl implements FranchiseService{
	@Autowired
	private FranchiseDAO franchiseDAO;

	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);

	/**
	 * 프랜차이즈 인증
	 * @param franchiseEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity confirmFranchiseInfo(FranchiseEntity franchiseEntity) throws Exception {
		ResponseEntity result = new ResponseEntity();
		int userCount = 0;
		try {
			if (StringUtils.equals(franchiseEntity.getFranchiseType(), CommonCode.FRANCHISE_CONFIRM_STUDYROOM)) {
				userCount = franchiseDAO.confirmFranchiseEHB(franchiseEntity);
			} else if (StringUtils.equals(franchiseEntity.getFranchiseType(), CommonCode.FRANCHISE_CONFIRM_SELPA)) {
				userCount = franchiseDAO.confirmFranchiseSP(franchiseEntity);
			}
			if(userCount > 0) {
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMsg(messages.getMessage("confirmFranchise.success"));
			}else{
				result.setResultMsg(messages.getMessage("confirmFranchise.fail"));
			}
		} catch(Exception e) {
			logger.error("프랜차이즈 인증 : Error ", e);
			result.setResultCode(ResultCode.FAIL);
			result.setResultMsg(messages.getMessage("confirmFranchise.fail"));
		}
		return result;
	}
}