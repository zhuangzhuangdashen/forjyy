package com.hzgroup.javers;

import com.hzgroup.javers.model.Employee;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class SnapshotTest {
    @Test
    public void test() {
        Javers javers = JaversBuilder.javers().build();
        Employee bob = new Employee("bob",
                1000,
                "Hero");
        bob.setBoss(new Employee("john"));
        javers.commit("author", bob);       // initial commit

        bob.setSalary(1200);                  // changes
        bob.setAge(30);                       //
        javers.commit("author", bob);       // second commit

        List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstance(bob).build());

        Changes changes = javers.findChanges(QueryBuilder.byInstance(QueryBuilder.byInstance(bob)).build());
        Changes changes1 = javers.findChanges(QueryBuilder.anyDomainObject().build());
        System.out.println(changes.prettyPrint());
        System.out.println(changes.get(0));

        assert snapshots.size() == 2;

        assert snapshots.get(0).getCommitMetadata().getId().getMajorId() == 2;
//        assert snapshots.get(0).getChanged() == new ArrayList<String>(("salary", "age"););
        assert snapshots.get(0).getPropertyValue("salary") == "1200";
        assert snapshots.get(0).getPropertyValue("age") == "30";
        // references are dehydrated
        assert snapshots.get(1).getPropertyValue("boss") == "Employee/john";

        assert snapshots.get(1).getCommitMetadata().getId().getMajorId()== 1;
        assert snapshots.get(1).getPropertyValue("salary") == "1000";
        assert snapshots.get(1).getPropertyValue("age") == "29";
        assert snapshots.get(1).getPropertyValue("boss") == "Employee/john";
    }
}
