package proxytest;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/27.
 */
public class TestCglib implements MethodInterceptor {

    private Object obj;

    public Object createProxy(Object obj) {
        // 把传进来的代理对象赋值给obj
        this.obj = obj;
        Enhancer enhancer = new Enhancer();
        // 需要为其实例指定一个父类，也就是我们 的目标对象，那么我们新创建出来的对象就是目标对象的子类，有目标对象的一样
        enhancer.setSuperclass(this.obj.getClass());
        // 除此之外，还要指定一个回调函数，这个函数就和Proxy的 invoke()类似
        enhancer.setCallback(this);
        Object o = enhancer.create();
        return o;
    }

    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        Object proxyObject = null;
        NormalClass um = (NormalClass) obj;
        System.out.println("实例化代理对象完成");
        proxyObject = methodProxy.invoke(um, objects);
        return proxyObject;
    }

    public static void main(String[] args) {
        NormalClass normalClass = (NormalClass) new TestCglib().createProxy(new NormalClass());
        normalClass.test();
    }
}

class NormalClass {
    void test() {
        System.out.println("我是被代理对象");
    }
}
