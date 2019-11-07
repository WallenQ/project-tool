package com.wallen.tool.weixinpay;

import lombok.Data;

/**
 * 微信支付相关参数
 *
 * @author Wallen
 * 2019/11/7 17:25
 */
@Data
public class Weixin {

	private String appId;
	/**
	 * 商户号
	 */
	private String mchid;
	/**
	 * API密钥
	 */
	private String key;
	/**
	 * 回调地址
	 */
	private String notifyUrl;
	/**
	 * 支付有效期:分钟
	 */
	private Long timeExpire;
}
