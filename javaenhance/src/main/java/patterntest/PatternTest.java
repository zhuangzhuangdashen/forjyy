package patterntest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/8.
 */
public class PatternTest {
    public static void main(String[] args) {

        // 要验证的字符串
        String str = "service@xsoftlab.net";
        // 邮箱验证规则
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);

        String str1 = "baike.xsoftlab.net";
        // 正则表达式规则
        String regEx1 = "baike.*";
        // 编译正则表达式
        Pattern pattern1 = Pattern.compile(regEx1);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(str1);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean rs1 = matcher1.find();
        System.out.println(rs1);
    }
}
