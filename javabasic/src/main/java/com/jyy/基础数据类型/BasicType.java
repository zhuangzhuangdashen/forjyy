package com.jyy.基础数据类型;

public class BasicType {

    public static void main(String[] args) {
        /**
         * JAVA基本数据类型
         * 4类8种
         * 整型 byte short int long
         * 字符型 char
         * 浮点型 float double
         * 布尔型 boolean
         *
         *
         * 占用字节数
         *          boolean 1 byte 1 short 2 char 2 int 4 float 4
         *          long 8 double 8
         *          注意取值范围 浮点型是小数 不是2的次幂
         *
         * 数据类型小的赋值给数据类型大的变量会发生自动转型 无须显示转换；
         * 数据类型大的赋值给数据类型大的变量不会自动转型 需要手动强转 如int i3 = 19; char i2 = (char) i3;
         * 整形默认是int类型  浮点型默认是双精度float类型
         *
         */

        // 自动发生向下转型
        byte b = 1;
        /**
         * 等于 short s = (short)2;
         */
        short s = 2;
        char c = '3';
        int i = 4;
        long l = 5;
        // 默认数值是int型 此处等于float f = (float)6 自动发生向上转型
        float f = 6;
        float f1 = 6f;  // 或者6F

        double d = 7;
        double d1 = 7d; // 或者7D

        // boolean 类型只能是false|true
        boolean boo = false;
//        boolean boo1 = 1;
    }
}
