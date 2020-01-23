package com.wallen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @Author: Wallen
 * @Date: 2020/1/23 13:25
 */
public class Company implements InvocationHandler {

	private Object factory;

	public Object getFactory() {
		return factory;
	}

	public void setFactory(Object factory) {
		this.factory = factory;
	}

	//通过Proxy获取动态代理的对象
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), this);
	}

	/**
	 * 通过动态代理对象对方法进行增强
	 *
	 * @param proxy  代理对象
	 * @param method 优化或拦截方法
	 * @param args   方法所对应的参数
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		doSomethingBefore();
		Object object = method.invoke(factory, args);
		doSomethingAfter();
		return object;
	}


	private void doSomethingBefore() {
		System.out.println("开始选购！");
	}

	private void doSomethingAfter() {
		System.out.println("选购结束！");
	}
}
