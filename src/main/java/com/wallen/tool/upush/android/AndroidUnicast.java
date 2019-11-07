package com.wallen.tool.upush.android;

import com.wallen.tool.upush.AbstractAndroidNotification;

/**
 * Android - 单播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class AndroidUnicast extends AbstractAndroidNotification {
	public AndroidUnicast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "unicast");
	}

	public void setDeviceToken(String token) throws Exception {
		setPredefinedKeyValue("device_tokens", token);
	}

}