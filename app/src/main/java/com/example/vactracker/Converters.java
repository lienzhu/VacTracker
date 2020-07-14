package com.example.vactracker;

import androidx.room.TypeConverter;

import com.example.vactracker.ui.data.Meta;
import com.example.vactracker.ui.data.Obj;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public Meta convertStringToObj(String s) {
        //convert string json to object
        Meta meta = new Meta();
        return meta;
    }

    @TypeConverter
    public static String convertObjToString(Meta meta) {
        return meta.toString();
    }
}
