package com.wallen.proxy;

/**
 * 代理类
 *
 * @Author: Wallen
 * @Date: 2020/1/23 12:54
 */
public class Wallen implements MilkFactory {
	private MilkFactory milkFactory;

	public Wallen(MilkFactory milkFactory) {
		this.milkFactory = milkFactory;
	}

	public void selectMilk(String name) {
		doSomethingBefore();
		milkFactory.selectMilk(name);
		doSomethingAfter();
	}

	private void doSomethingBefore() {
		System.out.println("开始选购！");
	}

	private void doSomethingAfter() {
		System.out.println("选购结束！");
	}
}
