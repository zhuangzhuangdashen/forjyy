package com.jyy.基础数据类型;

/**
 * 修饰符
 */
public class Qualifer {

    static int i = 10;
    static public int j =10;
    static protected int m = 10;
    static private int n = 10;
    protected int o = 0;
    int p = 1;

    void testDefault() {
    }

    protected void testProtected() {
    }

    private void testPrivate() {
    }

    public void testPublic() {
    }

    public static void main(String[] args) {
        System.out.println("sssssss");
    }
}

class OuterClass {
    void test() {
        System.out.println(Qualifer.m);
        System.out.println(new Qualifer().o);
        System.out.println(new Qualifer().p);

        new Qualifer().testDefault();
        new Qualifer().testProtected();
        new Qualifer().testPublic();
    }
}

class QualifierSon extends Qualifer {

    public static void main(String[] args) {
        System.out.println(i);
        System.out.println(j);
        System.out.println(m);
        // default修饰符修饰的变量无法在子类被调用 即子类无法继承默认修饰符修饰的变量 方法同理
//        System.out.println(p);
        // 私有成员变量无法被继承
//        System.out.println(n);
    }

    void testQualifier() {
        /**
         * 子类会继承父类的所有方法
         */
        this.testDefault();
        this.testProtected();
        this.testPublic();
//        new QualifierSon().testPrivate();
//        this.testPrivate();
    }
}