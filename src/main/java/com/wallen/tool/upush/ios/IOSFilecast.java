package com.wallen.tool.upush.ios;

import com.wallen.tool.upush.AbstractIOSNotification;

/**
 * IOS - 文件播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class IOSFilecast extends AbstractIOSNotification {
	public IOSFilecast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "filecast");
	}

	public void setFileId(String fileId) throws Exception {
		setPredefinedKeyValue("file_id", fileId);
	}
}
