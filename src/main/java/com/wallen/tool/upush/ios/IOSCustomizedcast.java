package com.wallen.tool.upush.ios;

import com.wallen.tool.upush.AbstractIOSNotification;

/**
 * IOS - 自定义播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class IOSCustomizedcast extends AbstractIOSNotification {
	public IOSCustomizedcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "customizedcast");
	}

	public void setAlias(String alias, String aliasType) throws Exception {
		setPredefinedKeyValue("alias", alias);
		setPredefinedKeyValue("alias_type", aliasType);
	}

	public void setFileId(String fileId, String aliasType) throws Exception {
		setPredefinedKeyValue("file_id", fileId);
		setPredefinedKeyValue("alias_type", aliasType);
	}

}
