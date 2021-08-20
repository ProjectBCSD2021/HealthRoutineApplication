package com.example.healthroutineapplication

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class CalendarDatabaseTypeConverters {
    @TypeConverter
    fun fromList(value: List<ExerciseRoutine>?): String = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<ExerciseRoutine>::class.java).toList()
}