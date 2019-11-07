package com.wallen.tool.upush.ios;

import com.wallen.tool.upush.AbstractIOSNotification;
import org.json.JSONObject;

/**
 * IOS - 组播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class IOSGroupcast extends AbstractIOSNotification {
	public IOSGroupcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "groupcast");
	}

	public void setFilter(JSONObject filter) throws Exception {
		setPredefinedKeyValue("filter", filter);
	}
}
