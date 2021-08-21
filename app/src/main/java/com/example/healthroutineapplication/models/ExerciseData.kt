package com.example.healthroutineapplication.models

import java.io.Serializable

data class ExerciseData(
    val exercise:String,
    val exerciseMethod:String,
    var set:Int = 0,
    var weight:Int = 0,
    var exerciseTime : Int = 0,
    var restTime : Int = 0
) : Serializable