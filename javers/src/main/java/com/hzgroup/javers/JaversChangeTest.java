package com.hzgroup.javers;

import com.hzgroup.javers.model.Address;
import com.hzgroup.javers.model.Employee;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.repository.jql.QueryBuilder;
import org.junit.jupiter.api.Test;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class JaversChangeTest {


    @Test
    public void testChange() {
        Javers javers = JaversBuilder.javers().build();
        Employee bob = new Employee("bob",
                1000);
        javers.commit("author", bob);       // initial commit

        bob.setSalary(1200);// changes
        bob.setPrimaryAddress(new Address("Paris"));
        javers.commit("author", bob);       // second commit

        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().build());

        assert changes.size() == 2;
//        ValueChange salaryChange = changes.find{it.propertyName == "salary"}
//        ValueChange cityChange = changes.find{it.propertyName == "city"}
//        assert salaryChange.left ==  1000
//        assert salaryChange.right == 1200
//        assert cityChange.left ==  "London"
//        assert cityChange.right == "Paris"
//
//        println changes.prettyPrint()

    }
}
