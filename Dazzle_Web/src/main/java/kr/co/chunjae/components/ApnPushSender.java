package kr.co.chunjae.components;

import java.util.List;
import java.util.Map;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotifications;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApnPushSender {
	@Value("#{push_prop['apn_p12_key']}")
	private String apnCertPath;
	@Value("#{push_prop['apn_p12_key_pw']}")
	private String apnCertPassword;
	@Value("#{push_prop['apn_is_prod']}")
	private boolean ApnIsProd;
	
	private static final Logger logger = LoggerFactory.getLogger(ApnPushSender.class);
	
	public void sendMessage(Map<String, String> messageMap, List<String> deviceToken) throws Exception {
		if (deviceToken.size() == 0) {
			logger.info("registrationIds is empty!!");
			return;
		}
		try {
			PushNotificationPayload adPayload = PushNotificationPayload.complex();
			adPayload.setCharacterEncoding("UTF-8");
			for (String key : messageMap.keySet()) {
				if (StringUtils.equals(key, "message")) {
					adPayload.addAlert(messageMap.get(key));
				} else {
					adPayload.addCustomDictionary(key, messageMap.get(key));
				}
			}
			adPayload.addSound("default");
			
			PushedNotifications noti = Push.payload(adPayload, apnCertPath, apnCertPassword, ApnIsProd, deviceToken);
			logger.debug("sendMessage success : " + noti.getSuccessfulNotifications().size());
			logger.debug("sendMessage fail : " + noti.getFailedNotifications().size());
		} catch (Exception e) {
			logger.error("sendMessage faild. :" + e.getMessage());
			throw new RuntimeException();
		}
	}
}