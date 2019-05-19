package juctest.atomictest;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
public class LongFieldTest {

    public static void main(String[] args) {

        // 获取Person的class对象
        Class cls = Person.class;
        // 新建AtomicLongFieldUpdater对象，传递参数是“class对象”和“long类型在类中对应的名称”
        AtomicLongFieldUpdater mAtoLong = AtomicLongFieldUpdater.newUpdater(cls, "id");
        Person1 person = new Person1(12345678L);

        // 比较person的"id"属性，如果id的值为12345678L，则设置为1000。
        mAtoLong.compareAndSet(person, 12345678L, 1000);
        System.out.println("id=" + person.getId());
    }
}

class Person1 {
    volatile long id;
    public Person1(long id) {
        this.id = id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}