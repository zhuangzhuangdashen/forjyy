package proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 */
public class DynamicProxy implements InvocationHandler{

    public Object target;

    // 绑定业务对象 并返回一个代理类
    public Object bind(Object target) {
        this.target = target;
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("先拦截...");
        Object result = method.invoke(target, args);
        System.out.println("后拦截...");
        return result;
    }
}