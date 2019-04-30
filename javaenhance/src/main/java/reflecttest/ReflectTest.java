package reflecttest;

import org.springframework.cglib.core.ReflectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/27.
 * 反射
 *
 */
public class ReflectTest {

    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException,
            ClassNotFoundException {

        /**
         * 核心是通过反射获取构造器然后实例化
         */
        ReflectObject o = (ReflectObject) ReflectUtils.newInstance(ReflectObject.class);
        System.out.println(o.testFunction("LIZHAUNGZHUANG001"));

        try {
            Constructor<ReflectObject> declaredConstructor = ReflectObject.class.getDeclaredConstructor((new Class[0]));
            ReflectObject alen = declaredConstructor.newInstance(null);
            alen.testFunction("alen");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /**
         * 反射的三种方式
         */
        Class clazz = ReflectObject.class;
        Object o1 = clazz.newInstance();

        // 第二种
        Class clazz1 = new ReflectObject().getClass();
        ReflectObject reflectObject = (ReflectObject) clazz1.newInstance();

        // 第三种
        Class<?> aClass = Class.forName("reflecttest.ReflectObject");
        Object o2 = aClass.newInstance();
        System.out.println("o2 :" + o2);
    }
}

class ReflectObject{
    private String userName;

    String testFunction(String userName) {
        System.out.println("userName: " + userName);
        return userName;
    }
}