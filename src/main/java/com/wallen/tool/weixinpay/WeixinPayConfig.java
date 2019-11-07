package com.wallen.tool.weixinpay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * 微信支付
 *
 * @author Wallen
 * 2019/11/7 16:35
 */
public class WeixinPayConfig implements WXPayConfig {
	/**
	 * 应用ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mchid;
	/**
	 * API密钥
	 */
	private String key;

	public WeixinPayConfig(String appid, String mchid, String key) {
		this.appid = appid;
		this.mchid = mchid;
		this.key = key;
	}

	@Override
	public String getAppID() {
		return appid;
	}

	@Override
	public String getMchID() {
		return mchid;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public InputStream getCertStream() {
		return null;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return 8000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return 10000;
	}

}
