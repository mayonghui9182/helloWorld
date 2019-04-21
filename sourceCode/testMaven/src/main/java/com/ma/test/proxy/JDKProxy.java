package com.ma.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    public static void main(String[] args) {
        Proxy.newProxyInstance(JDKTarget.class.getClassLoader(), JDKTarget.class.getInterfaces(), new JDKProxy());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}

class JDKTarget implements targetInterface{
    private String targetField;

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    @Override
    public void interfaceMethod() {
        System.out.println("interfaceMethodImpl");
    }
}
interface targetInterface{
    void interfaceMethod();
}