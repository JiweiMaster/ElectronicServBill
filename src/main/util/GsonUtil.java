package main.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtil {
    public static <T> T JsonToObject(String jsonStr, Type typeOfT){
        Gson gson = new Gson();
        T object = gson.fromJson(jsonStr,typeOfT);
        return object;
    }
    public static String ObjectToJson(Object object){
        Gson gson = new Gson();
        String jsonstr = gson.toJson(object);
        return jsonstr;
    }
}
