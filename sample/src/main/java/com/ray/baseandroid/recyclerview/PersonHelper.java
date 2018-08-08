package com.ray.baseandroid.recyclerview;

import com.ray.lib.java.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      : leixing
 * @date        : 2017-06-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class PersonHelper {
    private PersonHelper() {
        throw new UnsupportedOperationException();
    }

    public static List<Person> makePersons(int size) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Person person = new Person();
            person.setAge(RandomUtil.getRandomInt(18, 30));
            person.setName(i + "_" + RandomUtil.getRandomString(8));
            person.setSex(RandomUtil.getRandomBoolean() ? "male" : "female");
            persons.add(person);
        }

        return persons;
    }
}
