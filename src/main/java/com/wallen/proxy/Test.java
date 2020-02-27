package com.wallen.proxy;

/**
 * 测试方法
 *
 * @Author: Wallen
 * @Date: 2020/1/23 12:58
 */
public class Test {
	public static void main(String[] args) throws Exception {
		//静态代理
		/*MilkFactory factory = new Milk();
		Wallen wallen = new Wallen(factory);
		wallen.selectMilk("iPad");*/

		//动态代理
		//代理工厂
		Company company = new Company();


		//奶粉工厂生产奶粉
		MilkFactory milkFactory = new Milk();
		//代购奶粉
		company.setFactory(milkFactory);
		MilkFactory milk = (MilkFactory)company.getProxyInstance();
		milk.selectMilk("特仑苏");

		System.out.println("**************我是分割线*************");

		//饼干工厂生产钙片
		BiscuitFactory biscuitFactory = new Biscuit();
		//代购饼干
		company.setFactory(biscuitFactory);
		BiscuitFactory biscuit = (BiscuitFactory)company.getProxyInstance();
		biscuit.selectBiscuit("旺旺");

		BiscuitFactory biscuitFactory1 = new Bread();
		//代购面包
		company.setFactory(biscuitFactory1);
		BiscuitFactory biscuit1 = (BiscuitFactory)company.getProxyInstance();
		biscuit1.selectBiscuit("面包");

		BiscuitFactory biscuitFactory2 = (BiscuitFactory)Class.forName("com.wallen.proxy.Bread").newInstance();
		//代购面包2
		company.setFactory(biscuitFactory2);
		BiscuitFactory biscuit2 = (BiscuitFactory)company.getProxyInstance();
		biscuitFactory2.selectBiscuit("面包2222");

	}
}
