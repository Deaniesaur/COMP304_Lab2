package com.centennial.deanpinlac_comp304sec002_lab2.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class Common {
    public static <T> String convertToJson(T object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T convertJsonToObject(String jsonStr, Type type){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, type);
    }

    public static String formatDecPlace2(double value){
        DecimalFormat df = new DecimalFormat("$ #.00");
        return df.format(value);
    }
}
