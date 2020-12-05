package hu.bead.dinosaurs.dao;

import androidx.room.TypeConverter;

import hu.bead.dinosaurs.model.Diet;

public class Converters {
    @TypeConverter
    public static String fromdiet(Diet value){
        return value == null ? null : value.toString();
    }
    @TypeConverter
    public static Diet toDiet(String value){
        return value == null ? null : Diet.valueOf(value);
    }

}
