package com.wallen.tool.weixinpay;

import com.github.wxpay.sdk.WXPay;
import com.wallen.model.OrderDemo;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 微信支付
 *
 * @author Wallen
 * 2019/11/7 16:35
 */
public class WeixinPay {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";
	private static final String UNKNOWN = "unknown";
	/**
	 * APP唤起支付-返回支付相关参数
	 *
	 * @param httpServletRequest
	 * @param orderDemo          订单
	 * @return
	 */
	public static Map<String, String> payForAppParameter(HttpServletRequest httpServletRequest, OrderDemo orderDemo, Weixin weixin) {
		//微信支付有效期
		LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(weixin.getTimeExpire());
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timeExpire = dateTimeFormatter.format(localDateTime);

		Map<String, String> resultMap;
		//实例化初始化微信接口
		WeixinPayConfig weixinPayConfig = new WeixinPayConfig(weixin.getAppId(), weixin.getMchid(), weixin.getKey());
		WXPay wxpay = new WXPay(weixinPayConfig);
		try {
			Map<String, String> reqData = new HashMap<>(12);
			reqData.put("body", "订单号：" + orderDemo.getId());
			reqData.put("out_trade_no", orderDemo.getId());
			reqData.put("fee_type", "CNY");
			reqData.put("total_fee", orderDemo.getTotalFee().multiply(new BigDecimal(100)).intValue() + "");
			reqData.put("spbill_create_ip", getIpAddress(httpServletRequest));
			reqData.put("notify_url", weixin.getNotifyUrl());
			//APP支付固定传trade_type:APP
			reqData.put("trade_type", "APP");
			reqData.put("time_expire", timeExpire);
			reqData.put("product_id", orderDemo.getId());
			//调用微信支付接口
			resultMap = wxpay.unifiedOrder(reqData);
			TreeMap<String, String> result = new TreeMap<>();
			result.put("status" , SUCCESS);
			if (resultMap.get("result_code").equals(SUCCESS)) {
				result.put("appid", weixin.getAppId());
				result.put("partnerid", weixin.getMchid());
				result.put("prepayid", resultMap.get("prepay_id"));
				result.put("package", "Sign=WXPay");
				result.put("noncestr", UUID.randomUUID().toString().replace("-", ""));
				result.put("timestamp", System.currentTimeMillis() / 1000 + "");
				result.put("sign", WeixinPay.getWeixinSign(result, weixin.getKey()));
			} else {
				result.put("status" , FAIL);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 微信秘钥串加密字符串
	 * 参照链接：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3
	 *
	 * @param paramsMap
	 * @param appkey
	 * @return
	 */
	public static String getWeixinSign(TreeMap<String, String> paramsMap, String appkey) {
		StringBuilder signBefore = new StringBuilder();
		Iterator ir = paramsMap.keySet().iterator();
		while (ir.hasNext()) {
			String key = (String) ir.next();
			signBefore.append(key).append("=").append(paramsMap.get(key));
			if (ir.hasNext()) {
				signBefore.append("&");
			}
		}
		String stringSignTemp = signBefore.toString() + "&key=" + appkey;
		return md5(stringSignTemp);
	}

	/**
	 * ip地址获取
	 *
	 * @param httpServletRequest
	 * @return
	 */
	private static String getIpAddress(HttpServletRequest httpServletRequest) {
		String ip = httpServletRequest.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * md5加密
	 *
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		StringBuffer stringBuffer = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));
			for (int i = 0; i < array.length; i++) {
				stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
		} catch (Exception e) {
			return null;
		}
		return stringBuffer.toString();
	}
}
