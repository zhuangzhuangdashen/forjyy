package com.hzgroup.javers;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class JaversStart {
    public static void main(String[] args) {

        Javers javers = JaversBuilder.javers().build();

        Person tommyOld = new Person("tommy", "Tommy Smart");
        Person tommyNew = new Person("tommy1", "Tommy C. Smart");

        Diff diff = javers.compare(tommyOld, tommyNew);
        System.out.println(diff.toString());
        System.out.println(diff.changesSummary());
        System.out.println(diff.countByType());
//        System.out.println(diff.getChangesByType(Person.class));
        System.out.println(diff.getObjectsWithChangedProperty("name"));
        System.out.println(diff.getObjectsWithChangedProperty("favor"));
        System.out.println(diff.groupByObject());
        System.out.println(diff.hasChanges());
        System.out.println(diff.getObjectsByChangeType(ValueChange.class));

        /**
         * Object Audit
         */
        Person robert = new Person("bob", "Robert Martin");
        javers.commit("user", robert);

    }
}
