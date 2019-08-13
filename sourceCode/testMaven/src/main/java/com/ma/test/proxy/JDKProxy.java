package com.ma.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    public static void main(String[] args) {
        targetInterface target = (targetInterface)Proxy.newProxyInstance(JDKTarget.class.getClassLoader(), JDKTarget.class.getInterfaces(), new JDKProxy());
//        target.getTargetField();
//        target.subMethod();
        target.interfaceMethod();

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy");
        method.invoke(proxy,args);
        return null;
    }
}

class JDKTarget implements targetInterface{
    private String targetField;

    public String getTargetField() {
        System.out.println("getTargetField");
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    @Override
    public void interfaceMethod() {
        System.out.println("interfaceMethod");
    }
    public void subMethod() {
        System.out.println("subMethod");
    }
}
interface targetInterface{
    void interfaceMethod();
}