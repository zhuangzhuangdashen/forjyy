package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.commit.Commit;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class TestComplexWithHotelCodeBizDateOutside {
    @Test
    public void test() throws SQLException {
        final Connection dbConnection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/forjyy?user=root&password=root&serverTimezone=UTC");
        ConnectionProvider connectionProvider = new ConnectionProvider() {
            @Override
            public Connection getConnection() {
                //suitable only for testing!
                return dbConnection;
            }
        };

        JaversSqlRepository sqlRepository = SqlRepositoryBuilder
                .sqlRepository()
                .withConnectionProvider(connectionProvider)
                .withDialect(DialectName.MYSQL).build();
        Javers javers = JaversBuilder.javers().
//                registerCustomComparator(new CustomPropertyComparatorImpl(), SimpleClass.class).
        withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE).
                        registerJaversRepository(sqlRepository).build();

        List list1 = new ArrayList();
        Date date = new Date();

        Date today = AbstractDateUtils.getStartPointOfDate(date);
        Date zuotian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, -1));
        Date mingtian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, 1));

        list1.add(new RoomCountAndRoomType(141, "BR"));
        list1.add(new RoomCountAndRoomType(141, "SR"));
//        list1.add(new SimpleClass("1234", "A", 23, AbstractDateUtils.getStartPointOfDate(date)));
        SimpleClassCollection classCollection = new SimpleClassCollection("123", today, list1);
        Commit commitId1 = javers.commit("alen11111", classCollection);
        Changes change1 = javers.findChanges(QueryBuilder.
//                byClass(SimpleClass.class).
        anyDomainObject().
                        byAuthor("alen11111").
                        withCommitId(commitId1.getId()).
                        build());
        List<CdoSnapshot> snapshotList = javers.findSnapshots(QueryBuilder.anyDomainObject().withCommitId(commitId1.getId()).byAuthor("alen11111").build());
        System.out.println("snapshotList :" +snapshotList);

        System.out.println("change1------" + change1);
        list1.clear();
        list1.add(new RoomCountAndRoomType(141, "BR"));
        list1.add(new RoomCountAndRoomType(141, "SSR"));
        classCollection = new SimpleClassCollection("123", today, list1);
        Commit commitId2 = javers.commit("alen11111", classCollection);
        Changes change2 = javers.findChanges(QueryBuilder.
//                byClass(SimpleClass.class).
                anyDomainObject().
                        byAuthor("alen11111").
        withCommitId(commitId2.getId()).
                        build());
        List<CdoSnapshot> snapshotList2 = javers.findSnapshots(QueryBuilder.anyDomainObject().withCommitId(commitId2.getId()).byAuthor("alen11111").build());
        System.out.println("snapshotList2 :" +snapshotList2);

        System.out.println("change2------" + change2);

        list1.clear();
        list1.add(new RoomCountAndRoomType(141, "BR"));
        list1.add(new RoomCountAndRoomType(141, "SR"));
//        list1.add(new SimpleClass("1234", "A", 23, AbstractDateUtils.getStartPointOfDate(date)));
//        list1.add(new SimpleClass("1234", "A", 23, AbstractDateUtils.getStartPointOfDate(date)));
        classCollection = new SimpleClassCollection("123", today, list1);
        Commit commitId3 = javers.commit("alen11111", classCollection);
        Changes change3 = javers.findChanges(QueryBuilder.
                /**
                 * 当用对象包装的时候用byClass
                 */
//                byClass(SimpleClass.class).
        anyDomainObject().
                        byAuthor("alen11111").
                        withCommitId(commitId3.getId()).
                        build());


        List<CdoSnapshot> snapshotList3 = javers.findSnapshots(QueryBuilder.anyDomainObject().withCommitId(commitId3.getId()).byAuthor("alen11111").build());
        System.out.println("snapshotList3 :" +snapshotList3);
        System.out.println("change3------" + change3);

        list1.clear();
        list1.add(new RoomCountAndRoomType(141, "BR"));
        list1.add(new RoomCountAndRoomType(141, "SS"));
        list1.add(new RoomCountAndRoomType(141, "SRR"));
        classCollection = new SimpleClassCollection("123", zuotian, list1);
        Commit commitId4 = javers.commit("alen11111", classCollection);
        Changes change4 = javers.findChanges(QueryBuilder.
                anyDomainObject().
//                byClass(SimpleClass.class).
        byAuthor("alen11111").
                        withCommitId(commitId4.getId()).
                        build());
        System.out.println("change4------" + change4);
    }
}
