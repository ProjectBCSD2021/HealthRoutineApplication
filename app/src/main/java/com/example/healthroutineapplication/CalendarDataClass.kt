package com.example.healthroutineapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class CalendarDataClass(
    @PrimaryKey var date: String,
    val exerciseName: ArrayList<String>,
    val exerciseSet: ArrayList<Int>,
    val exerciseWeight: ArrayList<Int>
)
