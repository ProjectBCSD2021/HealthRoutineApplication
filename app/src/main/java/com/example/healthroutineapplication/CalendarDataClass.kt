package com.example.healthroutineapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.healthroutineapplication.models.ExerciseData

@Entity
data class CalendarDataClass(
    @PrimaryKey var date: String,
    val exerciseList: List<ExerciseData>
)
