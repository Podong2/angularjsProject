package kr.co.chunjae.components;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by jm1218 on 15. 1. 21.
 */
@Component
public class GcmPushSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(GcmPushSender.class);

	private static final int RETRY = 5;

	@Autowired
	private Sender sender;

	public void sendMessage(Map<String, String> messageMap, List<String> devices) throws IOException {
		if (devices.size() == 0) {
			LOGGER.info("registrationIds is empty!!");
			return;
		}
		Message.Builder msgBuilder = new Message.Builder();
		for (String key : messageMap.keySet()) {
			//msgBuilder.addData(key, messageMap.get(key));
			msgBuilder.addData(key, URLEncoder.encode(messageMap.get(key), "UTF-8"));
		}
		
		Message msg = msgBuilder.build();

		MulticastResult multicastResult = sender.send(msg, devices, RETRY);
		
		displayResult(multicastResult);
	}

	private void displayResult(MulticastResult multicastResult) {
		if (multicastResult != null) {
			List<Result> resultList = multicastResult.getResults();
			LOGGER.info("============ gcm push result ============");
			
			for (Result result : resultList) {
				LOGGER.info(result.toString());
			}
			LOGGER.info("=========================================");
		}
	}
}
