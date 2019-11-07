package com.wallen.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.wallen.model.OrderDemo;
import com.wallen.service.WeixinPayService;
import com.wallen.tool.weixinpay.Weixin;
import com.wallen.tool.weixinpay.WeixinPay;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * 微信支付
 *
 * @author Wallen
 * 2019/11/7 16:35
 */
@Service
public class WeixinPayServiceImpl implements WeixinPayService {
	private static final String SUCCESS = "SUCCESS";
	private Weixin weixin = new Weixin();

	@Override
	public Map<String, String> payForApp(HttpServletRequest httpServletRequest, OrderDemo orderDemo) {
		return WeixinPay.payForAppParameter(httpServletRequest, orderDemo, weixin);
	}

	@Override
	public boolean notifyForWeixinPay(HttpServletRequest httpServletRequest) throws Exception {
		InputStream inputStream = httpServletRequest.getInputStream();
		//微信支付结果回调开始
		byte[] buffer = new byte[1024];
		int readBytes;
		StringBuilder stringBuilder = new StringBuilder();
		while ((readBytes = inputStream.read(buffer)) > 0) {
			stringBuilder.append(new String(buffer, 0, readBytes));
		}
		inputStream.close();

		//从inputstream流中读出来的string转成xmlMap；
		String xmlString = stringBuilder.toString();

		//微信验证签名
		if (!WXPayUtil.isSignatureValid(xmlString, weixin.getKey())) {
			return false;
		}

		//微信检查是否支付成功
		Map<String, String> xmlMap = WXPayUtil.xmlToMap(xmlString);
		//TODO 业务操作（更新支付状态等）
		if (!SUCCESS.equals(xmlMap.get("result_code"))) {
			//支付失败的情况

		}

		return true;
	}
}
