package com.hzgroup.javers;

import com.hzgroup.javers.model.Address;
import com.hzgroup.javers.model.Employee;
import com.hzgroup.javers.model.EmployeeBuilder;
import com.hzgroup.javers.model.Position;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.mongo.MongoRepository;
import org.javers.shadow.Shadow;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class DiffTest {


    @Test
    public void commitWithMongo() {
        MongoDatabase mongoDb = new MongoClient( "10.100.249.183" ).getDatabase("test1");

        MongoRepository mongoRepository = new MongoRepository(mongoDb);
        Javers javers = JaversBuilder.javers().registerJaversRepository(mongoRepository).build();
//        javers.compare(new Person("lzz", "jyy"), new Person("lzz1", "jyy1"));
//        javers.commit("snapshot1", new Person("lzz", "jyy"));

//        Javers javers = JaversBuilder.javers().build();

        Person robert = new Person("bob", "Robert Martin");
        javers.commit("user", robert);           // persist initial commit

        robert.setName("Robert C.");             // do some changes
        robert.setPosition(Position.Developer);
        javers.commit("user", robert);

        JqlQuery query = QueryBuilder.byInstanceId("bob", Person.class).build();

        JqlQuery query1 = QueryBuilder.byInstanceId("user", Person.class).build();
        Changes changes1 = javers.findChanges(query1);
        System.out.println("change1:" + changes1.prettyPrint());
        System.out.println("change1:" + changes1.toString());

        List<Shadow<Person>> shadows = javers.findShadows(query);

        shadows.stream().map(Shadow::get).forEach(System.out::println);

        List<CdoSnapshot> snapshots = javers.findSnapshots(query);
        snapshots.stream().map(cdoSnapshot -> snapshots).forEach(System.out::println);
        Changes changes = javers.findChanges(query);
    }

    @Test
    public void shouldCompareTwoEntities() {
        //given
        Javers javers = JaversBuilder.javers()
                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
                .build();

        Employee frodoOld = EmployeeBuilder.Employee("Frodo")
                .withAge(40)
                .withPosition("Townsman")
                .withSalary(10_000)
                .withPrimaryAddress(new Address("Shire"))
                .withSkills("management")
                .withSubordinates(new Employee("Sam"))
                .build();

        Employee frodoNew = EmployeeBuilder.Employee("Frodo")
                .withAge(41)
                .withPosition("Hero")
                .withBoss(new Employee("Gandalf"))
                .withPrimaryAddress(new Address("Mordor"))
                .withSalary(12_000)
                .withSkills("management", "agile coaching")
                .withSubordinates(new Employee("Sméagol"), new Employee("Sam"))
                .build();

        //when
        Diff diff = javers.compare(frodoOld, frodoNew);

        //then
//        Assertions.assertThat(diff.getChanges()).hasSize(9);

        // diff pretty print
        System.out.println(diff);

        //iterating over changes grouped by objects
        System.out.println("");
        diff.groupByObject().forEach(byObject -> {
            System.out.println("* changes on " +byObject.getGlobalId().value() + " : ");
            byObject.get().forEach(change -> System.out.println("  - " + change));
        });

        //iterating over changes
        System.out.println("");
        diff.getChanges().forEach(change -> System.out.println("- " + change));

        // diff as JSON
        System.out.println("");
        System.out.println(javers.getJsonConverter().toJson(diff));
    }
}
