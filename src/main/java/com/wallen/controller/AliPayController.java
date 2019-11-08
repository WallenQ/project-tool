package com.wallen.controller;

import com.wallen.constant.ApiCodeEnum;
import com.wallen.constant.ApiResult;
import com.wallen.model.OrderDemo;
import com.wallen.tool.alipay.AliPay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 支付宝支付
 *
 * @author Wallen
 * 2019/11/8 9:30
 */
@Controller
@RequestMapping("/api")
public class AliPayController {
	@RequestMapping(value = "loginToken")
	@ResponseBody
	public ApiResult<String> loginToken() {
		//初始化alipay
		AliPay aliPay = new AliPay();
		return new ApiResult<>(ApiCodeEnum.DEFAULT_SUCCESS, aliPay.getLoginToken());
	}

	/**
	 * APP唤起支付
	 * subject 	String 	是 	256 	商品的标题/交易标题/订单标题/订单关键字等。 	大乐透
	 * out_trade_no 	String 	是 	64 	商户网站唯一订单号 	70501111111S001111119
	 * total_amount 	String 	是 	9 	订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "token")
	@ResponseBody
	public ApiResult token(@RequestParam Map<String, Object> params) {
		//初始化alipay
		AliPay aliPay = new AliPay();
		if (!(params.containsKey("pay_sn") && !params.get("pay_sn").equals(""))) {
		}
		try {
			OrderDemo orderDemo = new OrderDemo();
			params.put("total_amount", orderDemo.getTotalFee());
			return new ApiResult<>(ApiCodeEnum.DEFAULT_SUCCESS, aliPay.getSignToken(params));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResult.fail();
	}

	/**
	 * 支付回调接口
	 * https://docs.open.alipay.com/204/105301/
	 * <p>
	 * gmt_create:2019-10-19 11:19:59
	 * charset:utf-8
	 * seller_email:jshyjkglgfyxgs@126.com
	 * subject:订单号2019101911192007824417249
	 * sign:UKefqZlsLIN1jf7feccxC1T9wNIawBy4LUG4fkaDrUMS9BFwdWPz721ZS8xZ7OYY6X+
	 * body:订单号2019101911192007824417249
	 * buyer_id:2088222240527530
	 * invoice_amount:1262.92
	 * notify_id:2019101900222112000027530506605601
	 * fund_bill_list:[{"amount":"1262.92","fundChannel":"PCREDIT"}]
	 * notify_type:trade_status_sync
	 * trade_status:TRADE_SUCCESS
	 * receipt_amount:1262.92
	 * app_id:2018060260306515
	 * buyer_pay_amount:1262.92
	 * sign_type:RSA2
	 * seller_id:2088131316308554
	 * gmt_payment:2019-10-19 11:19:59
	 * notify_time:2019-10-19 11:23:35
	 * version:1.0
	 * out_trade_no:2019101911192007824417249
	 * total_amount:1262.92
	 * trade_no:2019101922001427530595370696
	 * auth_app_id:2018060260306515
	 * buyer_logon_id:138****2292
	 * point_amount:0.00
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping("notify")
	@ResponseBody
	public String donotify(@RequestParam Map<String, Object> params) {
		//支付失败
		if (!params.get("trade_status").toString().equals("TRADE_SUCCESS")) {
		}
		return "success";
	}
}
