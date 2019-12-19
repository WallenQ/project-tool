package com.wallen;

import com.wallen.model.OrderDemo;
import com.wallen.tool.util.BeanValidator;

/**
 *
 * @author Wallen
 * 2019/12/19 15:16
 */
public class Test {
	public static void main(String[] args) {
		OrderDemo orderDemo = new OrderDemo();
		//校验数据
		BeanValidator.validate(orderDemo);
	}

}
