package com.wallen.tool.upush.android;

import com.wallen.tool.upush.AbstractAndroidNotification;

/**
 * Android - 文件播
 *
 * @author qwl
 * @date 2019年9月6日
 */
public class AndroidFilecast extends AbstractAndroidNotification {
	public AndroidFilecast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "filecast");
	}

	public void setFileId(String fileId) throws Exception {
		setPredefinedKeyValue("file_id", fileId);
	}
}