package com.wallen.tool.upush;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Android - 推送参数设置
 *
 * @author qwl
 * @date 2019年9月6日
 */
public abstract class AbstractAndroidNotification extends AbstractUmengNotification {
	/**
	 * Keys can be set in the payload level
	 */
	protected static final HashSet<String> PAYLOAD_KEYS = new HashSet<String>(Arrays.asList("display_type"));

	/**
	 * Keys can be set in the body level
	 */
	protected static final HashSet<String> BODY_KEYS = new HashSet<String>(Arrays.asList("ticker", "title",
			"text", "builder_id", "icon", "largeIcon", "img", "play_vibrate", "play_lights",
			"play_sound", "sound", "after_open", "url", "activity", "custom"));

	public enum DisplayType {
		//通知:消息送达到用户设备后，由友盟SDK接管处理并在通知栏上显示通知内容。
		NOTIFICATION {
			@Override
			public String getValue() {
				return "notification";
			}
		},
		//消息:消息送达到用户设备后，消息内容透传给应用自身进行解析处理。
		MESSAGE {
			@Override
			public String getValue() {
				return "message";
			}
		};

		public abstract String getValue();
	}

	public enum AfterOpenAction {
		//打开应用
		go_app,
		//跳转到URL
		go_url,
		//打开特定的activity
		go_activity,
		//用户自定义内容。
		go_custom
	}

	@Override
	public boolean setPredefinedKeyValue(String key, Object value) throws Exception {
		// Set key/value in the rootJson, for the keys can be set please see ROOT_KEYS, PAYLOAD_KEYS,
		// BODY_KEYS and POLICY_KEYS.
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);
		} else if (PAYLOAD_KEYS.contains(key)) {
			// This key should be in the payload level
			JSONObject payloadJson = null;
			if (rootJson.has("payload")) {
				payloadJson = rootJson.getJSONObject("payload");
			} else {
				payloadJson = new JSONObject();
				rootJson.put("payload", payloadJson);
			}
			payloadJson.put(key, value);
		} else if (BODY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject bodyJson = null;
			JSONObject payloadJson = null;
			// 'body' is under 'payload', so build a payload if it doesn't exist
			if (rootJson.has("payload")) {
				payloadJson = rootJson.getJSONObject("payload");
			} else {
				payloadJson = new JSONObject();
				rootJson.put("payload", payloadJson);
			}
			// Get body JSONObject, generate one if not existed
			if (payloadJson.has("body")) {
				bodyJson = payloadJson.getJSONObject("body");
			} else {
				bodyJson = new JSONObject();
				payloadJson.put("body", bodyJson);
			}
			bodyJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson = null;
			if (rootJson.has("policy")) {
				policyJson = rootJson.getJSONObject("policy");
			} else {
				policyJson = new JSONObject();
				rootJson.put("policy", policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (key == "payload" || key == "body" || key == "policy" || key == "extra") {
				throw new Exception("You don't need to set value for " + key + " , just set values for the sub keys in it.");
			} else {
				throw new Exception("Unknown key: " + key);
			}
		}
		return true;
	}

	public boolean setExtraField(String key, String value) throws Exception {
		// Set extra key/value for Android notification
		JSONObject payloadJson = null;
		JSONObject extraJson = null;
		if (rootJson.has("payload")) {
			payloadJson = rootJson.getJSONObject("payload");
		} else {
			payloadJson = new JSONObject();
			rootJson.put("payload", payloadJson);
		}

		if (payloadJson.has("extra")) {
			extraJson = payloadJson.getJSONObject("extra");
		} else {
			extraJson = new JSONObject();
			payloadJson.put("extra", extraJson);
		}
		extraJson.put(key, value);
		return true;
	}

	public void setDisplayType(DisplayType d) throws Exception {
		setPredefinedKeyValue("display_type", d.getValue());
	}


	public void setTicker(String ticker) throws Exception {
		///通知栏提示文字
		setPredefinedKeyValue("ticker", ticker);
	}

	public void setTitle(String title) throws Exception {
		///通知标题
		setPredefinedKeyValue("title", title);
	}

	public void setText(String text) throws Exception {
		///通知文字描述
		setPredefinedKeyValue("text", text);
	}

	public void setBuilderId(Integer builderId) throws Exception {
		///用于标识该通知采用的样式。使用该参数时, 必须在SDK里面实现自定义通知栏样式。
		setPredefinedKeyValue("builder_id", builderId);
	}

	public void setIcon(String icon) throws Exception {
		///状态栏图标ID, R.drawable.[smallIcon],如果没有, 默认使用应用图标。
		setPredefinedKeyValue("icon", icon);
	}

	public void setLargeIcon(String largeIcon) throws Exception {
		///通知栏拉开后左侧图标ID
		setPredefinedKeyValue("largeIcon", largeIcon);
	}

	public void setImg(String img) throws Exception {
		///通知栏大图标的URL链接。该字段的优先级大于largeIcon。该字段要求以http或者https开头。
		setPredefinedKeyValue("img", img);
	}

	public void setPlayVibrate(Boolean playVibrate) throws Exception {
		///收到通知是否震动,默认为"true"
		setPredefinedKeyValue("play_vibrate", playVibrate.toString());
	}

	public void setPlayLights(Boolean playLights) throws Exception {
		///收到通知是否闪灯,默认为"true"
		setPredefinedKeyValue("play_lights", playLights.toString());
	}

	public void setPlaySound(Boolean playSound) throws Exception {
		///收到通知是否发出声音,默认为"true"
		setPredefinedKeyValue("play_sound", playSound.toString());
	}

	public void setSound(String sound) throws Exception {
		///通知声音，R.raw.[sound]. 如果该字段为空，采用SDK默认的声音
		setPredefinedKeyValue("sound", sound);
	}

	public void setPlaySound(String sound) throws Exception {
		///收到通知后播放指定的声音文件
		setPlaySound(true);
		setSound(sound);
	}

	public void goAppAfterOpen() throws Exception {
		///点击"通知"的后续行为，默认为打开app。
		setAfterOpenAction(AfterOpenAction.go_app);
	}

	public void goUrlAfterOpen(String url) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_url);
		setUrl(url);
	}

	public void goActivityAfterOpen(String activity) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_activity);
		setActivity(activity);
	}

	public void goCustomAfterOpen(String custom) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_custom);
		setCustomField(custom);
	}

	public void goCustomAfterOpen(JSONObject custom) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_custom);
		setCustomField(custom);
	}

	public void setAfterOpenAction(AfterOpenAction action) throws Exception {
		///点击"通知"的后续行为，默认为打开app。原始接口
		setPredefinedKeyValue("after_open", action.toString());
	}

	public void setUrl(String url) throws Exception {
		setPredefinedKeyValue("url", url);
	}

	public void setActivity(String activity) throws Exception {
		setPredefinedKeyValue("activity", activity);
	}

	public void setCustomField(String custom) throws Exception {
		///can be a string of json
		setPredefinedKeyValue("custom", custom);
	}

	public void setCustomField(JSONObject custom) throws Exception {
		setPredefinedKeyValue("custom", custom);
	}

}
