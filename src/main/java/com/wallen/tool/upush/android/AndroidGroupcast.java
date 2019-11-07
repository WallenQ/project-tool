package com.wallen.tool.upush.android;

import com.wallen.tool.upush.AbstractAndroidNotification;
import org.json.JSONObject;

/**
 * Android - 组播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class AndroidGroupcast extends AbstractAndroidNotification {
	public AndroidGroupcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "groupcast");
	}

	public void setFilter(JSONObject filter) throws Exception {
		setPredefinedKeyValue("filter", filter);
	}
}
