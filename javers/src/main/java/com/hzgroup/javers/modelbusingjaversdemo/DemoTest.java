package com.hzgroup.javers.modelbusingjaversdemo;

import com.google.common.collect.Lists;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.javers.shadow.Shadow;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class DemoTest {
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
//                .withSchema("my_schema") //optionally, provide the schame name
                .withConnectionProvider(connectionProvider)
                .withDialect(DialectName.MYSQL).build();
        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE).registerJaversRepository(sqlRepository).build();

        OTA_HotelAvailNotifRQ rq1 = new OTA_HotelAvailNotifRQ(new Date(),
                "target",
                "1.0",
                "111",
                "xmls",
                new Pos(new Source(new RequestorID("type3", "id", "id_context"))),
                new AvailStatusMessages("hotelId123",
                        Lists.newArrayList(
                                new AvailStatusMessage("", null, null))));

//        rq1.add(new OTA_HotelAvailNotifRQ());
        javers.commit("lzz", rq1);

        // 第二次提交
        rq1 = new OTA_HotelAvailNotifRQ(new Date(),
                "target",
                "1.0",
                "1111",
                "xmls",
                new Pos(new Source(new RequestorID("type1", "id", "id_context"))),
                new AvailStatusMessages("hotelId123",
                        Lists.newArrayList(
                                new AvailStatusMessage("", null, null))));

        javers.commit("lzz", rq1);
//        System.out.println(javers.compare(rq1, rq2));

        Changes changes = javers.findChanges(QueryBuilder.byInstance(rq1).build());
//        Changes changes = javers.findChanges(QueryBuilder.byClass(OTA_HotelAvailNotifRQCollection.class).build());
        System.out.println("changes :" +changes.prettyPrint());

        List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstance(rq1).build());
        System.out.println("snapshots :" + snapshots);

        List<Shadow<Object>> shadows = javers.findShadows(QueryBuilder.byInstance(rq1).build());
        System.out.println("shadows :" + shadows);

//        System.out.println(compare.getChangesByType(OTA_HotelAvailNotifRQ.class));
    }
}
