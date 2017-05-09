package com.ray.lib.android.util;

import org.json.JSONArray;

import java.util.Collection;

/**
 * Author      : leixing
 * Date        : 2016-12-29
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class JsonUtil {
    private JsonUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 转换为JSONArray
     *
     * @param collect 转换的集合
     * @return 返回转换的JSONArray
     */
    // TODO: 2017-04-27 FIXME
    public static JSONArray toJSONArray(Collection collect) {
        JSONArray jsonArray = new JSONArray();

        if (collect == null || collect.isEmpty()) {
            return jsonArray;
        }

        for (Object object : collect) {
            if (object == null) {
                continue;
            }

            jsonArray.put(object);
        }

        return jsonArray;
    }
}
