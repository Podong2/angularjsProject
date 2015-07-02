package kr.co.chunjae.controller.mobile;

import kr.co.chunjae.entities.PushSettingEntity;
import kr.co.chunjae.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jm1218 on 15. 1. 22.
 */

@RestController
@RequestMapping("/mobile/push")
public class PushSettingMobileController {

	@Autowired
	private PushService pushService;

	@RequestMapping(value = "init", method = RequestMethod.POST)
	public PushSettingEntity initPushSetting(@RequestBody PushSettingEntity pushSettingEntity) throws Exception{
		return pushService.initPushSetting(pushSettingEntity);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public int updatePushSettings(@RequestBody PushSettingEntity pushSettingEntity) throws Exception{
		return pushService.updatePushSetting(pushSettingEntity);
	}

}
