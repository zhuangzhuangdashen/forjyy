package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.diff.appenders.CorePropertyChangeAppender;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.type.JaversProperty;
import org.javers.core.metamodel.type.JaversType;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class CustomerCorePropertyChangeAppenderImpl extends CorePropertyChangeAppender {

    @Override
    protected PropertyChange calculateChanges(Object o, Object o1, GlobalId globalId, JaversProperty javersProperty) {
        System.out.println(o);
        return null;
    }

    @Override
    public boolean supports(JaversType javersType) {
        return true;
    }
}
