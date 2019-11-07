package com.wallen.tool.upush.android;

import com.wallen.tool.upush.AbstractAndroidNotification;

/**
 * Android - 广播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class AndroidBroadcast extends AbstractAndroidNotification {
	public AndroidBroadcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "broadcast");
	}
}
