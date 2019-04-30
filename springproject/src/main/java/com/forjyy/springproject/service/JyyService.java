package com.forjyy.springproject.service;

import com.forjyy.springproject.entity.JyyEntity;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public interface JyyService<T> {

    JyyEntity insert(JyyEntity entity);

    T get(int age);
}
