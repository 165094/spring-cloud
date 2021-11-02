package com.test.rocketmqProvider.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("吃饭前洗手！");
        Object invoke = method.invoke(proxy, args);
        System.out.println("吃完饭后洗手！");
        return invoke;
    }
}
