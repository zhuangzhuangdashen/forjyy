package com.jyy.基础数据类型;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public class ObjectTest {

    public static void main(String[] args) {

        Object[] studentArray = new Object[2];
        studentArray[0] = new Student(1);
        System.out.println(studentArray);

        List lsit = new ArrayList<>();
        lsit.add(new Student(2));
        System.out.println(lsit.toString());

        System.out.println(Arrays.toString(studentArray));
    }

}

class Student {
    private int age;

    public Student(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                '}';
    }
}
