package com.wallen.proxy;

/**
 * 工厂类
 *
 * @Author: Wallen
 * @Date: 2020/1/23 12:52
 */
public class Milk implements MilkFactory {
	public void selectMilk(String name) {
		System.out.println("当前选择牛奶：" + name + "！");
	}
}
