package reflecttest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/30.
 */
public class ReflectPerformanceTest {

    public static void main(String[] args) {

        ObjectModel model = new ObjectModel();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            model.test1(i);
        }

        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        try {
            Method test1 = ObjectModel.class.getMethod("test1", int.class);
            for (int i = 0 ; i< 100000000; i++) {
                test1.invoke(model, i);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }
}

class ObjectModel{
    public void test1(int i) {
        i++;
    }
}
