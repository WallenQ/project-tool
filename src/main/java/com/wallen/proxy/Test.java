package com.wallen.proxy;

/**
 * 测试方法
 *
 * @Author: Wallen
 * @Date: 2020/1/23 12:58
 */
public class Test {
	public static void main(String[] args) {
		//静态代理
		/*MilkFactory factory = new Milk();
		Wallen wallen = new Wallen(factory);
		wallen.selectMilk("iPad");*/

		//动态代理
		//奶粉工厂生产奶粉
		MilkFactory milkFactory = new Milk();
		//饼干工厂生产钙片
		BiscuitFactory biscuitFactory = new Biscuit();
		//代理工厂
		Company company = new Company();

		//代购奶粉
		company.setFactory(milkFactory);
		MilkFactory milk = (MilkFactory)company.getProxyInstance();
		milk.selectMilk("特仑苏");

		System.out.println("**************我是分割线*************");

		//代购饼干
		company.setFactory(biscuitFactory);
		BiscuitFactory biscuit = (BiscuitFactory)company.getProxyInstance();
		biscuit.selectBiscuit("旺旺");

	}
}
