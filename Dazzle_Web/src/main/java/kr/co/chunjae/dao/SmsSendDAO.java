package kr.co.chunjae.dao;

import kr.co.chunjae.entities.SmsEntity;

public interface SmsSendDAO {
	/**
	 * 일반 단문 발송 프로시져
	 * @param selectSMS
	 * @throws Exception
	 */
    void sendSmsGeneral(SmsEntity smsEntity)throws Exception;

	/**
	 * 리얼타임 발송 (인증문자 류) 프로시져
	 * @param selectSMS
	 * @throws Exception
	 */
    void sendSmsConfirm(SmsEntity smsEntity)throws Exception;

	/**
	 * 장문(LMS) 발송 프로시져
	 * @param selectSMS
	 * @throws Exception
	 */
    void sendSmsLMS(SmsEntity smsEntity)throws Exception;
}