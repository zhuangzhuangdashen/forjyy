package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.diff.NodePair;
import org.javers.core.diff.appenders.PropertyChangeAppender;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.metamodel.type.JaversProperty;
import org.javers.core.metamodel.type.JaversType;
import org.javers.core.metamodel.type.MapType;

import java.util.Map;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class CustomerPropertyChangeAppender implements PropertyChangeAppender {

    @Override
    public boolean supports(JaversType propertyType) {
        return false;
    }

    @Override
    public PropertyChange calculateChanges(NodePair pair, JaversProperty property) {
        Map left = (Map) pair.getLeftDehydratedPropertyValueAndSanitize(property);
        Map right = (Map) pair.getRightDehydratedPropertyValueAndSanitize(property);

        MapType mapType = ((JaversProperty) property).getType();
//        MapContentType mapContentType = typeMapper.getMapContentType(mapType);

//        List<EntryChange> changes = calculateEntryChanges(left, right, mapContentType);

//        if (!changes.isEmpty()){
//            renderNotParametrizedWarningIfNeeded(mapContentType.getKeyType().getBaseJavaType(), "key", "Map", property);
//            renderNotParametrizedWarningIfNeeded(mapContentType.getValueType().getBaseJavaType(), "value", "Map", property);
//            return new MapChange(pair.getGlobalId(), property.getName(), changes);
//        }
//        else {
//            return null;
//        }
        return null;
    }
}
