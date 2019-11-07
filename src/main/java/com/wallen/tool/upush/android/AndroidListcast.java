package com.wallen.tool.upush.android;

import com.wallen.tool.upush.AbstractAndroidNotification;

/**
 * Android - 列播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class AndroidListcast extends AbstractAndroidNotification {
	public AndroidListcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "listcast");
	}

	public void setDeviceToken(String token) throws Exception {
		setPredefinedKeyValue("device_tokens", token);
	}

}