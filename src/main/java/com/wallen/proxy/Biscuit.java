package com.wallen.proxy;

/**
 * 工厂类
 *
 * @Author: Wallen
 * @Date: 2020/1/23 13:37
 */
public class Biscuit implements BiscuitFactory {
	public void selectBiscuit(String name) {
		System.out.println("当前选择饼干：" + name + "！");
	}
}
