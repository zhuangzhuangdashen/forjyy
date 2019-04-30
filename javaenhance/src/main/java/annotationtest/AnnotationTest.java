package annotationtest;

import java.lang.annotation.*;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/4.
 */
@AnnotationDemo(value = "孤傲苍狼"/*a = "alen",*/ /*value = "ss"*/,
        arr = {2,3},
        meta = @MetaAnnotation("meta"),
        lamp = EumTrafficLamp.GREEN)
class AnnotationTest1 {

}
public class AnnotationTest{
    public static void main(String[] args) {
        if (AnnotationTest1.class.isAnnotationPresent(AnnotationDemo.class)){
            AnnotationDemo annotationDemo = AnnotationTest1.class.getAnnotation(AnnotationDemo.class);
            System.out.println(annotationDemo);
            System.out.println(annotationDemo.name());
//            System.out.println(annotationDemo.a());
            System.out.println(annotationDemo.value());
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR})
@interface AnnotationDemo{

    String name() default "alen";
    String value();

    EumTrafficLamp lamp() default EumTrafficLamp.RED;
    int[] arr() default {2, 3, 4};
    // 注解作为变量
    MetaAnnotation meta() default @MetaAnnotation("22");
}

 enum EumTrafficLamp {
 RED,//红
 YELLOW,//黄
 GREEN//绿
 }

 @interface MetaAnnotation{
     String value();
 }