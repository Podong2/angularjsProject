package kr.co.chunjae.components;

import kr.co.chunjae.JunitSpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApnPushSenderTest extends JunitSpringRunner {
	
	@Autowired
	private ApnPushSender apnPushSender;
	
	private static final String DEVICE_TOKEN_IOS = "da61c478599fe41c34cb7931e9056461b5c2e2c60f359a522d80e5dc6ca7098d";

	@Test
	public void canAutowired() {}
	
	@Test
	public void sendMessage() throws Exception {
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("title", "푸시 테스트");
		messageMap.put("message", "푸시 테스트입니다.");
		List<String> devices = new ArrayList<>();
		devices.add(DEVICE_TOKEN_IOS);
		apnPushSender.sendMessage(messageMap, devices);
	}
}