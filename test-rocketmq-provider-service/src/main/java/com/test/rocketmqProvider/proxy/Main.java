package com.test.rocketmqProvider.proxy;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        EatDaoImpl eatDaoImpl = new EatDaoImpl();
        ClassLoader classLoader = eatDaoImpl.getClass().getClassLoader();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        EatDao eatDao = (EatDao)Proxy.newProxyInstance(classLoader, eatDaoImpl.getClass().getInterfaces(), myInvocationHandler);
        eatDao.action();
    }
}
