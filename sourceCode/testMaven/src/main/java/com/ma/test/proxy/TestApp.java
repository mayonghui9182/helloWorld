package com.ma.test.proxy;

import org.junit.jupiter.api.Test;

public class TestApp {
	@Test
	public  void testProxy() {
        Target target = new Target();

        Target proxy = (Target)new proxyFactory(target).getProxyInstance();

        proxy.test1("test1");
        proxy.test2("test2");
        proxy.test3("test3");
	}

}
