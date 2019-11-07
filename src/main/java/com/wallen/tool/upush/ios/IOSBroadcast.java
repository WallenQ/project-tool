package com.wallen.tool.upush.ios;

import com.wallen.tool.upush.AbstractIOSNotification;

/**
 * IOS - 广播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class IOSBroadcast extends AbstractIOSNotification {
	public IOSBroadcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "broadcast");
	}
}
