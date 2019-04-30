package javadiffer;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/8.
 */
public class JavaDifferTest {

    public static void main(String[] args) {

        Javers javers = JaversBuilder.javers().build();
        Person tommyOld = new Person("tommy", "Tommy Smart");
        Person tommyNew = new Person("tommy", "Tommy C. Smart");

        Diff diff = javers.compare(tommyOld, tommyNew);
        Person robert = new Person("bob", "Robert Martin");
        javers.commit("user", robert);

//        System.out.println(javers.compare(javers.getJsonConverter().toJson(tommyOld), javers.getJsonConverter().toJson(tommyNew)));
        System.out.println(diff);
    }
}

class Person{
    String firstName;
    String secondName;

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
}