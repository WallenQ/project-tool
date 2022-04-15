package com.wallen.controller;

import com.wallen.model.OrderDemo;
import com.wallen.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信支付
 *
 * @author Wallen
 * 2019/11/7 16:28
 */
@Controller
@RequestMapping("/api/weixin")
public class WeixinPayController {
	@Autowired
	private WeixinPayService weixinPayService;

	/**
	 * APP唤起支付-返回支付相关参数
	 *
	 * @param httpServletRequest
	 * @param orderDemo 订单信息
	 * @return
	 */
	@RequestMapping(value = "/payForApp")
	public Map<String, String> payForApp(HttpServletRequest httpServletRequest, OrderDemo orderDemo) {
		return weixinPayService.payForApp(httpServletRequest, orderDemo);
	}

	/**
	 * 微信支付回调接口
	 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
	 *
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/notify")
	@ResponseBody
	public void notify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

		boolean result = weixinPayService.notifyForWeixinPay(httpServletRequest);
		if (result){
			httpServletResponse.getWriter().write(
					"<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
		}else {
			httpServletResponse.getWriter().write(
					"<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[exception]]></return_msg></xml>");
		}
	}
}
