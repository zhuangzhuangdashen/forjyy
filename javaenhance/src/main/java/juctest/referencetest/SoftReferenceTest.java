package juctest.referencetest;

import java.lang.ref.SoftReference;
import java.util.Date;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/14.
 */
public class SoftReferenceTest {
        public static void main(String[] args) {
            SoftReference ref = new SoftReference(new MyDate());
            ReferenceTest.drainMemory();
            System.out.println("ref:" + ref);
    }


}

class MyDate extends Date{
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }


}