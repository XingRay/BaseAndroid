package com.ray.baseandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ray.baseandroid.util.JsonUtil;
import com.ray.baseandroid.util.TraceUtil;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ray.baseandroid", appContext.getPackageName());
    }

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
    public void useToJSONArray() throws Exception {
        List<TestEntity> entities = new ArrayList<>();
        entities.add(new TestEntity("aaa", "111", 111));
        entities.add(new TestEntity("bbb", "222", 222));
        entities.add(new TestEntity("ccc", "333", 333));

        JSONArray jsonArray = JsonUtil.toJSONArray(entities);
        TraceUtil.log(jsonArray);

        assertEquals(1, 1);
    }
}
