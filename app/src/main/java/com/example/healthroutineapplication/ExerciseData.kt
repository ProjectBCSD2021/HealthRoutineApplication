package com.example.healthroutineapplication

data class ExerciseData(
    val exercise:String,
    val exerciseMethod:String,
    var set:Int = 0,
    var weight:Int = 0
)