package com.wallen.tool.upush.ios;

import com.wallen.tool.upush.AbstractIOSNotification;

/**
 * IOS - 单播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class IOSUnicast extends AbstractIOSNotification {
	public IOSUnicast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "unicast");
	}

	public void setDeviceToken(String token) throws Exception {
		setPredefinedKeyValue("device_tokens", token);
	}
}
