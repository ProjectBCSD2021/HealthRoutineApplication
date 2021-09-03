package com.example.healthroutineapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.healthroutineapplication.models.ExerciseData

@Entity
data class CalendarDataClass(
    @PrimaryKey var date: String,
    var exerciseList: List<ExerciseData>,
    var stepCount : Int = 0,
    var memo : String = ""
)
