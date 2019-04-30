package gctest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/29.
 */
public class Demo1 {
    public static Demo1 obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("CanReliveObj finalize called");

        obj = this;// 把obj复活了！！！
    }

    @Override
    public String toString(){
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException{
        obj = new Demo1();// 强引用
        obj = null;   //不会被立即回收，是可复活的对象

        System.gc();// 主动建议JVM做一次GC，GC之前会调用finalize方法，而我在里面把obj复活了！！！
        Thread.sleep(1000);

        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }

        System.out.println("第二次gc");
        obj = null;    //不可复活
        System.gc();
        Thread.sleep(1000);

        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }
}