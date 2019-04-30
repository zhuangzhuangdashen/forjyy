package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.diff.EqualsFunction;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class EqualsFunctionImpl implements EqualsFunction {
    @Override
    public boolean nullSafeEquals(Object left, Object right) {
        System.out.println(left);
        System.out.println(right);

        return false;
    }
}
