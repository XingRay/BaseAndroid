package com.ray.lib.java.util.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : leixing
 * @date : 2018/6/25 14:36
 * <p>
 * description : xxx
 */
public class CollectionUtilTest {

    @Test
    public void toList() {
        Integer[] integers = new Integer[]{
                1, 2, 3, 4, 5, 6
        };

        List<Integer> integerList = CollectionUtil.toList(integers);
        System.out.println(integerList);

        List<Object> objectList = CollectionUtil.toList(null);
        System.out.println(objectList.getClass() + "\n" + objectList.size());
    }

    @Test
    public void toArray() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Integer[] integers = CollectionUtil.toArray(list, Integer.class);
        for (Integer i : integers) {
            System.out.println(i);
        }

        Integer[] nulls = CollectionUtil.toArray(null, Integer.class);
        System.out.println(nulls.length);
    }

    @Test
    public void concat() {
        Integer[] integers = new Integer[]{
                1, 2, 3, 4
        };

        Integer[] integers2 = new Integer[]{
                5, 6, 7, 8
        };
        Integer[] concat = CollectionUtil.concat(Integer.class, null, integers, null, integers2, new Integer[]{13, 14});
        System.out.println(CollectionUtil.toList(concat));
    }
}