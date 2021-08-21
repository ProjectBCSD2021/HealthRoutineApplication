package com.example.healthroutineapplication

import androidx.room.TypeConverter
import com.example.healthroutineapplication.models.ExerciseData
import com.google.gson.Gson
import java.util.*

class CalendarDatabaseTypeConverters {
    @TypeConverter
    fun fromList(value: List<ExerciseData>?): String = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<ExerciseData>::class.java).toList()
}