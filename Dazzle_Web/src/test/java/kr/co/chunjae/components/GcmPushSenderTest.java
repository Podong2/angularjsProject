package kr.co.chunjae.components;

import kr.co.chunjae.JunitSpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jm1218 on 15. 1. 21.
 */
public class GcmPushSenderTest extends JunitSpringRunner {
	
	@Autowired
	private GcmPushSender gcmPushSender;
	
	private static final String DEVICE_TOKEN = "APA91bHoW0kVHOACo7l8GupmPze8_x34kTc2FmTVEg49ns6sZ3v6010YZbaS_8VwX9iw9Zr9uxvere1fUqnLOpVOQVqJhTUu7BK0f20U8qM6uNIhZRLURF2ekuvlmvPx66knt00AgM-257TxoNXQoDWifhMeJVI3_Q";

	@Test
	public void canAutowired() {}
	
	@Test
	public void sendMessage() throws IOException {
		//Map<String, String> messageMap, List<String> devices
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("title", "푸시 테스트");
		messageMap.put("message", "푸시 테스트입니다.");
		List<String> devices = new ArrayList<>();
		devices.add(DEVICE_TOKEN);
		gcmPushSender.sendMessage(messageMap, devices);
	}
}
