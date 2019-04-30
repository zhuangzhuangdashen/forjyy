package com.hzgroup.javers;

import org.bson.types.ObjectId;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class JaversSerializeValueObjectTest {

    @Test
    public void test() {

        //given
        Javers javers = JaversBuilder.javers()
                .registerValueTypeAdapter(new ObjectIdTypeSerializer())
                .build();

        //when
        ObjectId id = ObjectId.get();
        MongoStoredEntity entity1 = new MongoStoredEntity(id, "alg1", "1.0", "name");
        MongoStoredEntity entity2 = new MongoStoredEntity(id, "alg1", "1.0", "another");
        Diff diff = javers.compare(entity1, entity2);

        //then
        String json = javers.getJsonConverter().toJson(diff);
//        Assertions.assertThat(json).contains(id.toString());
        System.out.println(json);
    }
}
