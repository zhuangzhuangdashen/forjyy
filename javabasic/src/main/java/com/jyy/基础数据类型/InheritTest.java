package com.jyy.基础数据类型;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public class InheritTest extends InheritTestFather{

    static {
        System.out.println("父类static块");
    }

    String number;
    // 修饰符必须是是非private的才能保证子类能够访问得到
    InheritTest() {
        System.out.println("父类实例化完毕!");
    }

    public InheritTest(String number) {
        this.number = number;
    }
}

class InheritTestFather {
    static {
        System.out.println("爷类static块");
    }

    public InheritTestFather() {
        System.out.println("爷类实例化完毕");
    }
}

class InheritSon extends InheritTest{

    static {
        System.out.println("子类static块");
    }

    int age;
    // 子类的构造方法默认会调用super()方法，目的是为了实例化父类
    // 因此必须提供父类的无参构造
    public InheritSon() {
//        this(111);
        System.out.println("实例化子类构造方法完毕");
    }

    public InheritSon(int age) {
        this.age = age;
        System.out.println("子类有参构造方法实例化完毕");
    }

    public static void main(String[] args) {
        new InheritSon();
    }
}
