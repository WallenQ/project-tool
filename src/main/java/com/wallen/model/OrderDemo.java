package com.wallen.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 订单示例
 *
 * @author Wallen
 * 2019/11/7 16:50
 */
@Data
public class OrderDemo {
	/**
	 * 订单id
	 */
	@NotBlank(message = "订单id为空！")
	private String id;
	/**
	 * 总费用（单位：元RMB）
	 */
	private BigDecimal totalFee;
}
