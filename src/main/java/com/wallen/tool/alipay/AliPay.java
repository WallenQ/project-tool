package com.wallen.tool.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 支付宝支付
 *
 * @author Wallen
 * 2019/11/8 9:30
 */
public class AliPay {
	/**
	 * 应用 id
	 */
	private String app_id;
	/**
	 * 仅支持JSON
	 */
	private String format;
	/**
	 * 编码格式
	 */
	private String charset;
	/**
	 * 签名类型
	 */
	private String sign_type;
	/**
	 * 回调接口
	 */
	private String notify_url;
	/**
	 * 请求网关
	 */
	private String gateway;
	private String p_id;
	/**
	 * 应用私钥
	 */
	private String private_key;
	/**
	 * 应用私钥
	 */
	private String public_key;
	/**
	 * 支付宝公钥
	 */
	private String alipay_public_key;

	public AliPay() {
	}

	public AliPay(String gateway, String app_id, String p_id, String private_key, String public_key,
				  String alipay_public_key, String notify_url, String charset, String sign_type, String format) {
		this.gateway = gateway;
		this.app_id = app_id;
		this.p_id = p_id;
		this.private_key = private_key;
		this.public_key = public_key;
		this.alipay_public_key = alipay_public_key;
		this.notify_url = notify_url;
		this.charset = charset;
		this.sign_type = sign_type;
		this.format = format;
	}

	/**
	 * app_id=2014072300007148&charset=UTF-8&version=1.0&timestamp=2016-07-01 08:08:08&method=alipay.trade.app.pay&notify_url=https://api.**.com/pay_receive_notify.html&sign_type=RSA2&sign=ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE&version=1.0&biz_content=
	 * {
	 * "body":"对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。",
	 * "subject":"大乐透",
	 * "out_trade_no":"70501111111S001111119",
	 * "timeout_express":"90m",
	 * "total_amount":"一共花费了10元",
	 * "product_code":"QUICK_MSECURITY_PAY",
	 * "tips":"测试一笔支付"
	 * }
	 *
	 * @param params
	 */
	public String getSignToken(Map<String, Object> params) throws Exception {
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("订单号" + params.get("pay_sn"));
		model.setSubject("订单号" + params.get("pay_sn"));
		model.setOutTradeNo(params.get("pay_sn").toString());
		model.setTimeoutExpress("14m");
		model.setTotalAmount(params.get("total_amount").toString());
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(notify_url);
		AlipayClient alipayClient = new DefaultAlipayClient(gateway,
				app_id, private_key, format, charset, alipay_public_key, sign_type);
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		return response.getBody();
	}

	/**
	 * 支付宝获取用户第三方登录token
	 *
	 * @return
	 */
	public String getLoginToken() {
		//treemap有序且key升序
		TreeMap<String, Object> keyValueMap = new TreeMap<>();
		keyValueMap.put("apiname", "com.alipay.account.auth");
		keyValueMap.put("method", "alipay.open.auth.sdk.code.get");
		keyValueMap.put("app_id", app_id);
		keyValueMap.put("app_name", "mc");
		keyValueMap.put("biz_type", "openservice");
		keyValueMap.put("pid", p_id);
		keyValueMap.put("product_id", "APP_FAST_LOGIN");
		keyValueMap.put("scope", "kuaijie");
		keyValueMap.put("target_id", p_id);
		keyValueMap.put("auth_type", "AUTHACCOUNT");
		keyValueMap.put("sign_type", sign_type);

		StringBuilder signBefore = new StringBuilder();

		Iterator ir = keyValueMap.keySet().iterator();
		while (ir.hasNext()) {
			String key = (String) ir.next();
			signBefore.append(key).append("=").append(keyValueMap.get(key));
			if (ir.hasNext()) {
				signBefore.append("&");
			}
		}
		String sign;
		try {
			sign = getSign(signBefore.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		signBefore.append("&sign=").append(sign);
		signBefore.append("&sign_type=" + sign_type);
		return signBefore.toString();
	}

	/**
	 * 加密
	 *
	 * @param signBefore
	 * @return
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	private String getSign(String signBefore) throws AlipayApiException, UnsupportedEncodingException {
		return URLEncoder.encode(AlipaySignature.rsaSign(signBefore, private_key, charset, sign_type), charset);
	}
}
