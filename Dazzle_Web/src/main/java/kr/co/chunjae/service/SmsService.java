package kr.co.chunjae.service;

import kr.co.chunjae.entities.ResponseEntity;
import kr.co.chunjae.entities.SmsEntity;

public interface SmsService {

	/**
	 * 리얼타임 발송 (인증문자 류) 프로시져
	 * @param smsEntity
	 * @return
	 * @throws Exception
	 */
	ResponseEntity sendSmsConfirm(SmsEntity smsEntity) throws Exception;

	/**
	 * 장문(LMS) 발송 프로시져
	 * @param smsEntity
	 * @return
	 * @throws Exception
	 */
	ResponseEntity sendSmsLMS(SmsEntity smsEntity) throws Exception;

	/**
	 * 일반 단문 발송 프로시져
	 * @param smsEntity
	 * @return
	 * @throws Exception
	 */
	ResponseEntity sendSmsGeneral(SmsEntity smsEntity) throws Exception;

}