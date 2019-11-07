package com.wallen.tool.upush.ios;

import com.wallen.tool.upush.AbstractIOSNotification;

/**
 * IOS - 组播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class IOSListcast extends AbstractIOSNotification {
	public IOSListcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "listcast");
	}

	public void setDeviceToken(String token) throws Exception {
		setPredefinedKeyValue("device_tokens", token);
	}
}
