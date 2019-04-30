package com.hzgroup.javers.modelbusingjaversdemo;

import com.google.common.collect.Lists;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.commit.Commit;
import org.javers.core.diff.Diff;
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
import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class TestJaversCompare {

    @Test
    public void test() throws SQLException {

        Date date = new Date();

        Date today = AbstractDateUtils.getStartPointOfDate(date);
        Date zuotian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, -1));
        Date mingtian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, 1));

//        Javers javers = JaversBuilder.javers().build();

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

        List<RoomCount> roomCountList = Lists.newArrayList();
        roomCountList.add(new RoomCount("123", zuotian, 1, "SR"));
        roomCountList.add(new RoomCount("123", today, 1, "SR"));
        roomCountList.add(new RoomCount("123", today, 1, "SRR"));

        List<RoomCount> roomCountList1 = Lists.newArrayList();
        roomCountList1.add(new RoomCount("123", zuotian, 1, "SR"));
        roomCountList1.add(new RoomCount("123", today, 2, "SR"));
        roomCountList1.add(new RoomCount("123", today, 1, "SQR"));

        Commit commit = javers.commit("123458", roomCountList);
        Commit commit1 = javers.commit("123458", roomCountList1);
        System.out.println(commit1);
        List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.anyDomainObject().byAuthor("123458").limit(1).build());
        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().byAuthor("123458").limit(1).build());
        System.out.println("snapshots :" +snapshots);


        /**
         * 普通的Value比较 不加@Id
         * 比较的是对象的equals方法，前提是Class相同
         * 多重@Id不生效
         * compare直接判断集合中对象的equals方法，即使Entity加了@Id注解也不生效
         */
//        Diff diff = javers.compare(roomCountList, roomCountList1);
//        Diff diff = javers.compare(roomCountList, roomCountList1);

        /**
         *
         * 前提是@Id 在集合中必须唯一
         * 否则比较equals方法
         *
         * 且如果没有@Id 也是比较equals方法
         */
        Diff diff = javers.compareCollections(roomCountList, roomCountList1, RoomCount.class);
        System.out.println(diff);

    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Date date = new Date();

        Date today = AbstractDateUtils.getStartPointOfDate(date);
        Date zuotian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, -1));
        Date mingtian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, 1));

        Javers javers = JaversBuilder.javers().
//                registerCustomComparator(new CustomPropertyComparatorImpl(), HotelCodeAndBizDate.class).
                build();

        List<HotelCodeAndBizDate> roomCountList = Lists.newArrayList();
        roomCountList.add(new HotelCodeAndBizDate("123", today, 1,"SR"));
        roomCountList.add(new HotelCodeAndBizDate("123", today,1, "SRR"));
        roomCountList.add(new HotelCodeAndBizDate("123", today,1, "DR"));

        Commit alen = javers.commit("alenn", roomCountList);
        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().withCommitId(alen.getId()).build());
        System.out.println(changes);

        List<HotelCodeAndBizDate> roomCountList1 = Lists.newArrayList();
        roomCountList1.add(new HotelCodeAndBizDate("123", today, 1,"SR"));
        roomCountList1.add(new HotelCodeAndBizDate("123", today, 2,"SRH"));
        roomCountList1.add(new HotelCodeAndBizDate("123", today, 2,"DR"));

        Commit alenn = javers.commit("alenn", roomCountList1);
        Changes changes1 = javers.findChanges(QueryBuilder.anyDomainObject().withCommitId(alenn.getId()).build());
        System.out.println(changes1);

//        DiffFactory diffFactory = DiffFactory.class.newInstance();
//        Field propertyChangeAppender = DiffFactory.class.getDeclaredField("propertyChangeAppender");

//        propertyChangeAppender.setAccessible(true);
//        propertyChangeAppender.set(diffFactory, new CustomerPropertyChangeAppender());

        Diff diff = javers.compareCollections(roomCountList, roomCountList1, HotelCodeAndBizDate.class);
//        Diff compare = javers.compare(roomCountList, roomCountList1);
        System.out.println(diff);



    }


    @Test
    public void test2() throws SQLException {
        Date date = new Date();

        Date today = AbstractDateUtils.getStartPointOfDate(date);
        Date zuotian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, -1));
        Date mingtian = AbstractDateUtils.getStartPointOfDate(AbstractDateUtils.getDeltaDateByDays(today, 1));

//        Javers javers = JaversBuilder.javers().build();

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

        List list = Lists.newArrayList();
        list.add(new RoomCount("123", zuotian, 1, "SR"));
        list.add(new RoomCount("123", zuotian, 1, "SR"));
        new ComplexObjectWithCollection("123", list);

        List list1 = Lists.newArrayList();



        List<RoomCount> roomCountList = Lists.newArrayList();
        roomCountList.add(new RoomCount("123", today, 1, "SR"));
        roomCountList.add(new RoomCount("123", today, 1, "SRR"));

        List<RoomCount> roomCountList1 = Lists.newArrayList();
        roomCountList1.add(new RoomCount("123", zuotian, 1, "SR"));
        roomCountList1.add(new RoomCount("123", today, 2, "SR"));
        roomCountList1.add(new RoomCount("123", today, 1, "SQR"));

        Commit commit = javers.commit("123458", roomCountList);
        Commit commit1 = javers.commit("123458", roomCountList1);
        System.out.println(commit1);
        List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.anyDomainObject().byAuthor("123458").limit(1).build());
        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().byAuthor("123458").limit(1).build());
        System.out.println("snapshots :" +snapshots);


        Diff diff = javers.compareCollections(roomCountList, roomCountList1, RoomCount.class);
        System.out.println(diff);

    }
}
