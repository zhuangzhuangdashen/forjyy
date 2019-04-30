package gctest;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/29.
 */
public class TestSoftReference {
    public static void main(String[] args) throws InterruptedException {

        Test prime = new Test();
        SoftReference soft = new SoftReference(prime) ;
        Test pr = new Test();
        WeakReference weakReference = new WeakReference(pr);
        System.gc();
//        soft.fi
        Thread.sleep(10000);

        System.gc();
        System.out.println(prime);
        System.out.println(weakReference);
    }

    static class Test{
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("立即执行finalize方法");
        }
    }
}
