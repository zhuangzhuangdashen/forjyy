package com.hzgroup.javers.modelbusingjaversdemo;

import com.google.common.collect.Lists;
import org.javers.core.Changes;
import org.javers.core.ChangesByObject;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.commit.CommitMetadata;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class DemoTest1 {

    @Test
    public void testReflect(){
        QueryBuilder builder = null;
//        try {
//            builder = QueryBuilder.class.newInstance();
//            System.out.println("builder :" + builder);
//        } catch (Exception e) {
//            System.out.println("builder :" + builder);
//        }

        Object o = null;
        try {
//            Class<?> aClass = Class.forName(QueryBuilder.class.getPackage().getName() + ".QueryBuilder");
//            Constructor<QueryBuilder> declaredConstructor = QueryBuilder.class.getDeclaredConstructor(org.javers.repository.jql..class);
            Constructor<?>[] declaredConstructors = QueryBuilder.class.getDeclaredConstructors();
            declaredConstructors[0].setAccessible(true);

            Object o1 = declaredConstructors[0].newInstance(new Object());
//            o = declaredConstructor.newInstance(null);
            System.out.println("o : " + o);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("o : " + o);
        }
    }

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
//        list1.add(new SimpleClass("123", 10, date));
//        list1.add(new SimpleClass("123", 19, AbstractDateUtils.getDeltaDateByDays(date, -1)));
//
//        List list2 = new ArrayList();
//        list2.add(new SimpleClass("123", 12, date));
//        list2.add(new SimpleClass("123", -1, AbstractDateUtils.getDeltaDateByDays(date, -1)));
//        list2.add(new SimpleClass("1234", -1, AbstractDateUtils.getDeltaDateByDays(date, -2)));
//
//
//
//        Diff diff = javers.compareCollections(list1, list2, SimpleClass.class);
//        System.out.println("changes :" + diff.getChanges());
//        System.out.println("~~~~~~~~~~~~~");

//        list1.add(new SimpleClass("123", 12, date));
        SimpleClassCollection collection = null;
//
//        javers.commit("lzz", collection);
//        list1.clear();
//        list1.add(new SimpleClass("123", 14, AbstractDateUtils.getDeltaDateByDays(date, -1)));
//        list1.add(new SimpleClass("123", 15, AbstractDateUtils.getDeltaDateByDays(date, -2)));
//        list1.add(new SimpleClass("123", 23, date));
//        list1.add(new SimpleClass("12345", 23, date));
//        list1.remove(0);

//        collection = new SimpleClassCollection(list1);
//        Commit commitId = javers.commit("lzz11", collection);
//        System.out.println(commitId.getId());
//        System.out.println("commitIdChanges :" +commitId.getChanges());

//        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().limit(2).withCommitId(commitId.getId()).build());
//        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().byClass(SimpleClass.class).limit(2).build());
        Changes changes = javers.findChanges(QueryBuilder.byClass(SimpleClass.class).byAuthor("lzz11").limit(1).build());

        /**
         * byClass limit无用
         * byInstance limit无用
         * byInstanceId  需要改存的结构 [hotelCode(新增), List<RoomCountEntity>] 然后将hotelCode作为instanceId
         * withCommitId  不支持并发
         * withVersion 没有version
         * buAuthor支持并发 author需要自己定义 比如 agentcode + roomcode + hotelCode
         */
        System.out.println("changes :" +changes);
        List<ChangesByObject> changesByObjects = changes.groupByObject();
        System.out.println("groupByObject:" + changesByObjects);

        List<NewObject> newObjectList = Lists.newArrayList();
        List<ObjectRemoved> removeObjectList = Lists.newArrayList();
        List<PropertyChange> changeList = Lists.newArrayList();
        for (ChangesByObject object : changesByObjects) {
            newObjectList.addAll(object.getNewObjects());
            removeObjectList.addAll(object.getObjectsRemoved());
            changeList.addAll(object.getPropertyChanges());
        }

        System.out.println("newObjectList :" + newObjectList);
        System.out.println("removeObjectList :" + removeObjectList);
        System.out.println("changeList :" +changeList);

        for (PropertyChange change : changeList) {
            Optional<CommitMetadata> commitMetadata = change.getCommitMetadata();
            if (commitMetadata.isPresent()) {
                CommitMetadata commitMetadata1 = commitMetadata.get();
                Map<String, String> properties = commitMetadata1.getProperties();
//                System.out.println("commitMetadata : " +commitMetadata1);
                System.out.println(properties);
            }
        }
//
//        /**
//         * 处理移除的对象
//         */
//        for (NewObject newObject : newObjectList) {
//            Optional<CommitMetadata> commitMetadata = newObject.getCommitMetadata();
//            boolean present = commitMetadata.isPresent();
//            if (present) {
//                CommitMetadata commitMetadata1 = commitMetadata.get();
//                Map<String, String> properties = commitMetadata1.getProperties();
//                properties.get("");
//            }
//        }

    }
}
