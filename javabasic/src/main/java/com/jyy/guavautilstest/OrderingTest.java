package com.jyy.guavautilstest;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.comparator.ComparableComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/25.
 */
public class OrderingTest {

    public static void main(String[] args) {

        List list = Lists.newArrayList("2333", "233333", "3", "33");
        Ordering ordering = new Ordering() {
            @Override
            public int compare(@Nullable Object o, @Nullable Object t1) {
                Preconditions.checkNotNull(o, "参数不能为空");
                Preconditions.checkNotNull(t1, "参数不能为空");
                return ((String) o).length() - ((String) t1).length();
            }
        };
        // 重写一个ordering
        Ordering ordering1 = new Ordering() {
            @Override
            public int compare(@Nullable Object o, @Nullable Object t1) {
                Preconditions.checkNotNull(o, "参数不能为空");
                Preconditions.checkNotNull(t1, "参数不能为空");
                return ((String) t1).length() - ((String) o).length();
            }
        };

        System.out.println(ordering.sortedCopy(list));
        System.out.println(ordering1.sortedCopy(list));

        ordering.thenComparing(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Preconditions.checkNotNull(o1, "参数不能为空");
                Preconditions.checkNotNull(o2, "参数不能为空");
                return ((String) o1).length() - ((String) o2).length();
            }
        });

        ordering.reverse();

        List list1 = ordering.sortedCopy(list);
        System.out.println(list1);
        // reverse() 返回相反的排序
        System.out.println(ordering.reverse().sortedCopy(list));
        // 干嘛排序后比较
        System.out.println(ordering.max(list));
        // 集合是否有序？
        System.out.println(ordering.isOrdered(list));

        System.out.println(ordering);

        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }
}