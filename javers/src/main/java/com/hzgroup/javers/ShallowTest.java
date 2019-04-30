package com.hzgroup.javers;

import com.hzgroup.javers.model.Address;
import com.hzgroup.javers.model.Employee;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class ShallowTest {

    @Test
    public void test(){

        Javers javers = JaversBuilder.javers().build();

        Employee steve = new Employee("steve");
        Employee john = new Employee("john", 0, "boss");
        Employee bob = new Employee("bob", 10, "Hero");

        javers.commit("author", steve);  // commit 1.0 with snapshot of Steve
        javers.commit("author", bob);    // commit 2.0 with snapshots of Bob, Bob#address and John
        bob.setSalary(1200);               // the change
        javers.commit("author", bob);    // commit 3.0 with snapshot of Bob

        List<Shadow<Object>> shadows = javers.findShadows(QueryBuilder.byInstance(bob).build());
        Employee bobShadow = (Employee) shadows.get(0).get();  //get the latest version of Bob

        System.out.println(shadows.size() == 2);             //we have 2 shadows of Bob
        System.out.println(bobShadow.getName() == "bob");
                // referenced entities are outside the query scope so they are nulled
        System.out.println(bobShadow.getBoss() == null);
                // child Value Objects are always in scope
        assert bobShadow.getPrimaryAddress().getCity() == "London";

        shadows = javers.findShadows(QueryBuilder.byInstance(bob)
                .withScopeCommitDeep().build());
        bobShadow = (Employee) shadows.get(0).get();
        assert bobShadow.getBoss().getName() == "john"; // John is inside the query scope, so his
                // shadow is loaded and linked to Bob
        assert bobShadow.getBoss().getBoss() == null;   // Steve is still outside the scope
        assert bobShadow.getPrimaryAddress().getCity() == "London";

        shadows = javers.findShadows(QueryBuilder.byInstance(bob)
                .withScopeDeepPlus(2).build());
        bobShadow = (Employee) shadows.get(0).get();

        System.out.println(bobShadow.getBoss().getName() == "john");
        assert bobShadow.getBoss().getName() == "steve"; // Steve is loaded thanks to deep+2 scope
        assert bobShadow.getPrimaryAddress().getCity() == "London";
    }
}
