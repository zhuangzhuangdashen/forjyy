package proxytest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 */
public class SubClass implements Inter1{
    @Override
    public void execute1() {
        System.out.println("继承类执行");
    }

    @Override
    public String toString() {
        return "SubClass{}";
    }
}
