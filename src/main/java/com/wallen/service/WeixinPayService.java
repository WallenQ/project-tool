package com.wallen.service;

import com.wallen.model.OrderDemo;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * 微信支付
 *
 * @author Wallen
 * 2019/11/7 16:34
 */
public interface WeixinPayService {
	/**
	 * APP唤起支付-返回支付相关参数
	 *
	 * @param httpServletRequest
	 * @param orderDemo 订单信息
	 * @return
	 */
	Map<String, String> payForApp(HttpServletRequest httpServletRequest, OrderDemo orderDemo);

	/**
	 * 微信支付回调
	 *
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 */
	boolean notifyForWeixinPay(HttpServletRequest httpServletRequest) throws Exception;
}
