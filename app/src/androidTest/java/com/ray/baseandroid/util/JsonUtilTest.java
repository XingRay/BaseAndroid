package com.ray.baseandroid.util;

import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Author      : leixing
 * Date        : 2017-04-27
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */
@RunWith(AndroidJUnit4.class)
public class JsonUtilTest {
    class TestEntity {
        String name;
        String code;
        int num;

        TestEntity(String name, String code, int num) {
            this.name = name;
            this.code = code;
            this.num = num;
        }
    }

    @Test
    public void toJSONArray() throws Exception {
        List<TestEntity> entities = new ArrayList<>();
        entities.add(new TestEntity("aaa", "111", 111));
        entities.add(new TestEntity("bbb", "222", 222));
        entities.add(new TestEntity("ccc", "333", 333));

        JSONArray jsonArray = JsonUtil.toJSONArray(entities);
        TraceUtil.log(jsonArray);

        System.out.println(jsonArray.toString());
        assertEquals(1, 1);
    }
}