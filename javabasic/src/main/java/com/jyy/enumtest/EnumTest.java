package com.jyy.enumtest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/25.
 */
public enum EnumTest {

    Spring(3,4,5),
    Summer(6,7,8),
    Autumn(9,10,11),
    Winter(12,1,2);

    int [] month;

    EnumTest(int... month) {
        this.month = month;
    }

    public int[] getMonth() {
        return month;
    }

    public void setMonth(int... month) {
        this.month = month;
    }
}
