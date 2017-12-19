package com.test.KnowledgePoint.proxy;

import org.junit.Test;

public class TestApp {
	@Test
	public  void testProxy() {
		//目标对象
        Target target = new Target();

        //代理对象
        Target proxy = (Target)new proxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.test1("test1");
        proxy.test2("test2");
        proxy.test3("test3");
	}

}
