package xmltest;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/4.
 */
public class XStreamTest {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<String>();
        strList.add("alen");
        strList.add("xuyin");
        strList.add("lvhua");
        strList.add("laowang");

        ComplexBean bean = new ComplexBean(strList,
                new Object1(strList, new Object11(strList)),
                new Object2(strList, new Object22(strList), new Object23(strList)),
                new Object3(new Object33(new Object333(strList)), new Object34("Hello XSream")));

        String xml = beanToXml(bean);
        System.out.println(xml);
    }

    private static String beanToXml(Object object) {
        //创建xstream对象
        XStream xStream = new XStream();
        //给指定类起别名
//        xStream.alias("bank", Bank.class);
//        xStream.alias("account", Account.class);
        xStream.alias("complexBean", ComplexBean.class);
        //将对象转换成xml字符串
        String xml = xStream.toXML(object);
        return xml;
    }
}

class ComplexBean{

    List<String> stringList;

    Object1 object1;

    Object2 object2;

    Object3 object3;

    public ComplexBean(List<String> stringList, Object1 object1, Object2 object2, Object3 object3) {
        this.stringList = stringList;
        this.object1 = object1;
        this.object2 = object2;
        this.object3 = object3;
    }
}
class Object1 {
    List<String> stringList;
    Object11 object11;

    public Object1(List<String> stringList, Object11 object11) {
        this.stringList = stringList;
        this.object11 = object11;
    }
}
class Object11{
    List<String> stringList;

    public Object11(List<String> stringList) {
        this.stringList = stringList;
    }
}
class Object2 {
    List<String> stringList;
    Object22 object22;
    Object23 object23;

    public Object2(List<String> stringList, Object22 object22, Object23 object23) {
        this.stringList = stringList;
        this.object22 = object22;
        this.object23 = object23;
    }
}
class Object22{
    List<String> stringList;

    public Object22(List<String> stringList) {
        this.stringList = stringList;
    }
}
class Object23{
    List<String> stringList;

    public Object23(List<String> stringList) {
        this.stringList = stringList;
    }
}
class Object3{
    Object33 object33;
    Object34 object34;

    public Object3(Object33 object33, Object34 object34) {
        this.object33 = object33;
        this.object34 = object34;
    }
}
class Object33{
    Object333 object333;

    public Object33(Object333 object333) {
        this.object333 = object333;
    }
}
class Object333{
    List<String> stringList;

    public Object333(List<String> stringList) {
        this.stringList = stringList;
    }
}
class Object34{
    String string;

    public Object34(String string) {
        this.string = string;
    }
}