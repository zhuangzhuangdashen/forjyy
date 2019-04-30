package com.forjyy.springproject.entity;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public class JyyEntity {
    public int age;

    public JyyEntity(int i) {
        this.age = i;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JyyEntity{" +
                "age=" + age +
                '}';
    }
}
