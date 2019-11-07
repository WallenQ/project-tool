package com.wallen.tool.upush;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 推送参数设置
 *
 * @author qwl
 * @date 2019年9月6日
 */
public abstract class AbstractUmengNotification {
	/**
	 * This JSONObject is used for constructing the whole request string.
	 */
	protected final JSONObject rootJson = new JSONObject();

	/**
	 * The app master secret
	 */
	protected String appMasterSecret;

	/**
	 * Keys can be set in the root level
	 */
	protected static final HashSet<String> ROOT_KEYS = new HashSet<String>(Arrays.asList("appkey",
			"timestamp", "type", "device_tokens", "alias", "alias_type", "file_id",
			"filter", "production_mode", "feedback", "description", "thirdparty_id"));

	/**
	 * Keys can be set in the policy level
	 */
	protected static final HashSet<String> POLICY_KEYS
			= new HashSet<String>(Arrays.asList("start_time", "expire_time", "max_send_num"));

	/**
	 * Set predefined keys in the rootJson, for extra keys(Android) or customized keys(IOS) please
	 * refer to corresponding methods in the subclass.
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public abstract boolean setPredefinedKeyValue(String key, Object value) throws Exception;

	public void setAppMasterSecret(String secret) {
		appMasterSecret = secret;
	}

	public String getPostBody() {
		return rootJson.toString();
	}

	protected final String getAppMasterSecret() {
		return appMasterSecret;
	}

	protected void setProductionMode(Boolean prod) throws Exception {
		setPredefinedKeyValue("production_mode", prod.toString());
	}


	public void setProductionMode() throws Exception {
		///正式模式
		setProductionMode(true);
	}


	public void setTestMode() throws Exception {
		///测试模式
		setProductionMode(false);
	}

	/**
	 * 发送消息描述，建议填写
	 *
	 * @param description
	 * @throws Exception
	 */
	public void setDescription(String description) throws Exception {
		setPredefinedKeyValue("description", description);
	}

	/**
	 * 定时发送时间，若不填写表示立即发送
	 *
	 * @param startTime 格式: "YYYY-MM-DD hh:mm:ss"
	 * @throws Exception
	 */
	public void setStartTime(String startTime) throws Exception {
		setPredefinedKeyValue("start_time", startTime);
	}

	/**
	 * 消息过期时间
	 *
	 * @param expireTime 格式: "YYYY-MM-DD hh:mm:ss"
	 * @throws Exception
	 */
	public void setExpireTime(String expireTime) throws Exception {
		setPredefinedKeyValue("expire_time", expireTime);
	}

	/**
	 * 发送限速
	 *
	 * @param number 每秒发送的最大条数
	 * @throws Exception
	 */
	public void setMaxSendNum(Integer number) throws Exception {
		setPredefinedKeyValue("max_send_num", number);
	}

}
