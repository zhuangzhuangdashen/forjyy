package reflecttest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/4.
 */
public class TestReflectField{
    public static void main(String[] args) {
        List<String> outputFields = AbstractOutputFields.getOutputFields();
        System.out.println(outputFields);
    }
}
abstract class AbstractOutputFields {

    /**商户ID*/
    public static String SHOPID = "shopid";

    /**商户名*/
    public static String SHOP_NAME = "shopname";

    /**POI*/
    public static String POI = "poi";

    /**评分*/
    public static String SHOPPOWER = "shoppower";

    /**地址*/
    public static String ADDRESS = "address";

    /**
     * 获取所有定义的搜索输出字段
     * @return
     */
    public static List<String> getOutputFields() {
        Class clazz = AbstractOutputFields.class;
        Field[] fields = clazz.getFields();
        List<String> fieldList = new ArrayList<String>();
        if (fields.length == 0) {
            return fieldList;
        }
        for (Field field : fields) {
            try {
                fieldList.add((String)field.get(clazz));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return fieldList;
    }
}