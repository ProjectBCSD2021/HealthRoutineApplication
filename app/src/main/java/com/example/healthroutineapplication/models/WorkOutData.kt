package com.example.healthroutineapplication

data class Exercise(
    val id:Long,
    val name:String,
    val test:Array<String>
)

data class Proverb(
    val proverb: String,
    val person:String
)