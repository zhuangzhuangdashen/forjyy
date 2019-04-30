package proxytest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 */
public class TestStaticProxy implements Inter{
    @Override
    public void execute() {
        System.out.println("真正执行");
    }
    public static void main(String[] args) {
        Proxy1 p = new Proxy1(new TestStaticProxy());
        p.execute();
    }
}
class Proxy1 implements Inter{
    TestStaticProxy obj;
    public Proxy1(TestStaticProxy obj) {
        this.obj = obj;
    }
    @Override
    public void execute() {
        System.out.println("执行前");
        obj.execute();
        System.out.println("执行后");
    }
}
interface Inter{
    void execute();
}