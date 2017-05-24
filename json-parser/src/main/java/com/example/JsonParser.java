package com.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public <T> List<T> fromJson(Class<T> cls, Reader reader, int pageSize, int pageIndex) throws IOException {
        List<T> list = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(reader);
        Gson gson = new Gson();

        int from = pageSize * pageIndex;
        int to = from + pageSize;
        int index = 0;

        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            if (index < from) {
                jsonReader.skipValue();
                index++;
                continue;
            }
            if (index >= to) {
                return list;
            }

            T t = gson.fromJson(jsonReader, cls);
            list.add(t);
            index++;
        }
        jsonReader.endArray();
        reader.close();
        jsonReader.close();
        return list;
    }

    public <T> List<T> fromJson(String json, Class<T> cls, int pageSize, int pageIndex) throws IOException {
        InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(json.getBytes()));
        return fromJson(cls, reader, pageSize, pageIndex);
    }
}
