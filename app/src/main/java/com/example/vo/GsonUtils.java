package com.example.vo;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by thinking on 2016/6/10.
 */
public class GsonUtils {

    public <T> List<T> getJSONList(String jsonStr,Type type){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr,type);
    }

}
