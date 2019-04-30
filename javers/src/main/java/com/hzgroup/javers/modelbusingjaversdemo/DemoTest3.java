package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.commit.CommitId;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/13.
 */
public class DemoTest3 {
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
        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE).registerJaversRepository(sqlRepository).build();
//
        List list1 = new ArrayList();
        Date date = new Date();
//        list1.add(new SimpleClass("1234", "BRR1", 23, AbstractDateUtils.getStartPointOfDate(date)));
//        SimpleClassCollection classCollection = new SimpleClassCollection(list1);
//        Commit lzz1111 = javers.commit("lzz1111", classCollection);

        Changes lzz111 = javers.findChanges(QueryBuilder.
                byClass(SimpleClass.class).
                byAuthor("lzz1111").
                withCommitId(CommitId.valueOf(new BigDecimal(13995))).
                limit(5).
                build());
        System.out.println(lzz111);

//        System.out.println(lzz1111);
//        for (int i = 1;i < 10; i++) {
//            /**
//             * commit性能很差
//             */
//            javers.commit("lzz11111", classCollection);
//        }
//        list1.clear();
/*
//        collection = new SimpleClassCollectionWithCode("123", list1);
        Commit commitId = javers.commit("lzz1111", list1);

        list1.add(new SimpleClass("1234", "HRR", 23, AbstractDateUtils.getStartPointOfDate(date)));
        javers.commit("lzz1111", list1);
        list1.add(new SimpleClass("1234", "HTR", 23, AbstractDateUtils.getStartPointOfDate(date)));
        javers.commit("lzz1111", list1);
        list1.add(new SimpleClass("1234", "MR", 23, AbstractDateUtils.getStartPointOfDate(date)));*/
//        javers.commit("lzz1111", list1);
        // changes 是否存下来
//        List<Change> changes = commitId.getChanges();
//        System.out.println(changes);
        Changes lzz11112 = javers.findChanges(QueryBuilder.
                byClass(SimpleClass.class).
                byAuthor("lzz1111").
                withCommitId(CommitId.valueOf(new BigDecimal(13995))).
                limit(5).
                build());
        System.out.println(lzz11112);
        /**
         * anyDomainObject的局限
         */
//        List<Shadow<Object>> lzzz111 = javers.findShadows(QueryBuilder.anyDomainObject().byAuthor("lzz111").build());
//        List<CdoSnapshot> lzz111 = javers.findSnapshots(QueryBuilder.anyDomainObject().byAuthor("lzz111").build());
        /**
         * commit的是全量
         *
         * byInstance好像只能得到CollectionChange
         */

//        javers.findChanges(QueryBuilder.anyDomainObject().byAuthor(""))
//        System.out.println(commitId.getId());
//        System.out.println("commitIdChanges :" +commitId.getChanges());

//        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().limit(2).withCommitId(commitId.getId()).build());
//        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().byClass(SimpleClass.class).limit(2).build());
//        Changes changes = javers.findChanges(QueryBuilder.byValueObjectId("123", List.class, ).byClass(SimpleClass.class).byAuthor("lzz11").limit(1).build());

        /**
         * byClass limit无用
         * byInstance limit无用
         * byInstanceId  需要改存的结构 [hotelCode(新增), List<RoomCountEntity>] 然后将hotelCode作为instanceId
         * withCommitId  不支持并发
         * withVersion 没有version
         * buAuthor支持并发 author需要自己定义 比如 agentcode + roomcode + hotelCode
         */
    }
}
