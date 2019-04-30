package streamtest;

import lombok.ToString;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class StreamTest {
    public static void main(String[] args) {
        // 1、数组
        String[] arr = new String[]{"ab", "cd", "ef"};
        Stream<String> arrStream = Arrays.stream(arr);
        // 2、集合
        List<String> list1 = Arrays.asList("ab", "cd", "ef");
        Stream<String> colStream = list1.stream();
        // 3、值
        Stream<String> stream = Stream.of("ab", "cd", "ef");

        List<User> list = Arrays.asList(
                // name，age
                new User("张三", 11),
                new User("王五", 20),
                new User("王五", 91),
                new User("张三", 8),
                new User("李四", 44),
                new User("李四", 44),
                new User("李四", 44)
        );

        System.out.println("java 8 前");
        for(User user: list){
            System.out.println(user);
        }
        // java 8 lambda
        System.out.println("java 8 lambda");
        list.forEach(user -> System.out.println(user));
        list.forEach(user -> System.out.println("after : " + user));

        // java 8 stream lambda
        System.out.println("java 8 stream lambda");
        list.stream().forEach(user -> System.out.println(user));
        list.stream().forEach(user -> System.out.println("after :" + user));


        list.stream().sorted(Comparator.comparing(User::getAge)).forEach(user -> System.out.println(user));


        list.stream().filter((User user) -> user.getAge() > 50).forEach(user -> System.out.println(user));

        System.out.println();
        list.stream().limit(3).forEach(user -> System.out.println(user));
        System.out.println();
        list.stream().forEach(user -> System.out.println(user));

        System.out.println();
        list.stream().distinct().forEach(user -> System.out.println(user));
        System.out.println();
        list.stream().forEach(user -> System.out.println(user));

        System.out.println();
        list.stream().distinct().filter(user -> user.getAge() > 40).sorted(
                Comparator.comparing(User::getAge)).limit(2).forEach(user -> System.out
                .println(user));

/*        list.stream().distinct().filter(user -> user.getAge() > 10).sorted(
                Comparator.comparing(User::getAge)).limit(2).forEach(user -> System.out.
                println(user));*/
        list.stream().distinct().filter(user -> user.getAge() > 40).sorted(
                Comparator.comparing(User::getAge)).limit(2).forEach(user -> System.out
                .println(user));
        IntSummaryStatistics num = list.stream().mapToInt(u -> u.getAge())
                .summaryStatistics();

        System.out.println("总共人数：" + num.getCount());
        System.out.println("平均年龄：" + num.getAverage());
        System.out.println("最大年龄：" + num.getMax());
        System.out.println("最小年龄：" + num.getMin());
        System.out.println("年龄之和：" + num.getSum());

        List<Integer> ages = list.stream().map(user -> user.getAge()).collect(toList());
        ages.forEach(age -> System.out.println(age));


        /*long count = */list.parallelStream().sorted().forEach(user -> System.out.println("end :" + user));
//        System.out.println(count);

    }
}

@ToString
class User implements Comparable{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return this.age > user.getAge() ? 1 : this.age == user.getAge() ? 0 : -1;
    }
}
