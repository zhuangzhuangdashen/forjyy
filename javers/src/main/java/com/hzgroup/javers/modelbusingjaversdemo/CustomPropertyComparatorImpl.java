package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.diff.custom.CustomPropertyComparator;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;

import java.util.Optional;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class CustomPropertyComparatorImpl implements CustomPropertyComparator {
    @Override
    public Optional compare(Object left, Object right, GlobalId affectedId, Property property) {
        equals(left, right);
        System.out.println(right);

        return Optional.empty();
    }

    @Override
    public boolean equals(Object a, Object b) {
        HotelCodeAndBizDate o1 = (HotelCodeAndBizDate) a;
        HotelCodeAndBizDate o2 = (HotelCodeAndBizDate) b;
        if ((o1.getRoomType().equals(o2.getRoomType())) && !(o1.getRoomCount() == o2.getRoomCount())) {
            return false;
        }

        return false;
    }
}
