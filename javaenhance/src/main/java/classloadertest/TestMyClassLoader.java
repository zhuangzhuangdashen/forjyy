package classloadertest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/7.
 */
public class TestMyClassLoader
{
    public static void main(String[] args) throws Exception
    {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> c1 = Class.forName("Person", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}