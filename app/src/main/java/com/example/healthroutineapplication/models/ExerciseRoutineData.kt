package com.example.healthroutineapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "routine")
data class ExerciseRoutineData(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "routineName")
    val routineName: String?,
    @ColumnInfo(name = "exerciseRoutine")
    val exerciseRoutine: List<ExerciseData>?
)

class Converters {
    @TypeConverter
    fun listToJson(value: List<ExerciseData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ExerciseData>::class.java).toList()
}