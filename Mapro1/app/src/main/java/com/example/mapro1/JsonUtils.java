package com.example.mapro1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.*;
import java.util.*;
/**
 * Created by Duhao on 2017/6/9.
 */

public class JsonUtils {
    public static  Data parseStudentFromJson(String data) {
        Type listType = new TypeToken<Data>() {
        }.getType();
        Gson gson = new Gson();
        Data  list = gson.fromJson(data, listType);
        return list;
    }
}
