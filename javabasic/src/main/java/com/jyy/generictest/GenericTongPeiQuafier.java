package com.jyy.generictest;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 * 泛型通配符
 */
public class GenericTongPeiQuafier <T> {

    void test(List<?> param) {
        System.out.println(param);
    }

    void test1(Generator<?> param) {
        System.out.println(param);
    }

    public void showKeyValue1(Generic<Number> obj){
        System.out.println(obj.getKey());
    }

    public static void main(String[] args) {
        GenericTongPeiQuafier g = new GenericTongPeiQuafier();
        g.test(Lists.newArrayList("1"));
        GenericTongPeiQuafier g1 = new GenericTongPeiQuafier();
        g1.test(Lists.newArrayList("1"));

        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        g.showKeyValue1(gInteger);
        g.showKeyValue1(gNumber);
    }

}


class SubClass<T> extends GenericTongPeiQuafier<T> {


}

class Generic<T>{
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}

//定义一个泛型接口
interface Generator<T> {
    public T next();
}