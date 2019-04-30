package com.hzgroup.javers;


import org.bson.types.ObjectId;
import org.javers.core.json.BasicStringTypeAdapter;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class ObjectIdTypeSerializer extends BasicStringTypeAdapter {

    @Override
    public String serialize(Object sourceValue) {
        return sourceValue.toString();
    }

    @Override
    public Object deserialize(String serializedValue) {
        return new ObjectId(serializedValue);
    }

    @Override
    public Class getValueType() {
        return ObjectId.class;
    }
}