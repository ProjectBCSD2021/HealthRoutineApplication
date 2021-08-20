package com.example.healthroutineapplication

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class CalendarDatabaseTypeConverters {
    @TypeConverter
    fun fromStringList(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromIntList(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun toIntList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}