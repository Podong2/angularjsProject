package kr.co.chunjae.service.impl;

import kr.co.chunjae.constants.ResultCode;
import kr.co.chunjae.dao.SmsSendDAO;
import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.SmsEntity;
import kr.co.chunjae.service.SmsService;
import kr.co.digigroove.commons.messages.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SmsServiceImpl implements SmsService{
	@Autowired
	private SmsSendDAO smsSendDAO;

	@Autowired
	private Messages messages;

	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	/**
	 * 일반 단문 발송 프로시져
	 * @param smsEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity sendSmsGeneral(SmsEntity smsEntity){
		ResponseEntity result = new ResponseEntity();
		try{
			if(!smsEntity.getDestAddr().isEmpty()){
				smsSendDAO.sendSmsGeneral(smsEntity);
			}else{
				for (String destAddr : smsEntity.getDestAddrArray()) {
					smsEntity.setDestAddr(destAddr);
					smsSendDAO.sendSmsGeneral(smsEntity);
				}
			}

			result.setResultCode(ResultCode.SUCCESS);
		}catch(Exception e){
			logger.error(" 일반 단문 발송 프로시져 : Error ", e);
			result.setResultCode(ResultCode.FAIL);
		}
		return result;
	}
	/**
	 * 리얼타임 발송 (인증문자 류) 프로시져
	 * @param smsEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity sendSmsConfirm(SmsEntity smsEntity){
		ResponseEntity result = new ResponseEntity();
		try{
			smsSendDAO.sendSmsConfirm(smsEntity);
			result.setResultCode(ResultCode.SUCCESS);
		}catch(Exception e){
			logger.error(" 리얼타임 발송 (인증문자 류) 프로시져 : Error ", e);
		}
		return result;
	}

	/**
	 * 장문(LMS) 발송 프로시져
	 * @param smsEntity
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity sendSmsLMS(SmsEntity smsEntity){
		ResponseEntity result = new ResponseEntity();
		try{
			smsSendDAO.sendSmsLMS(smsEntity);
			result.setResultCode(ResultCode.SUCCESS);
		}catch(Exception e){
			logger.error(" 장문(LMS) 발송 프로시져 : Error ", e);
		}
		return result;
	}

}