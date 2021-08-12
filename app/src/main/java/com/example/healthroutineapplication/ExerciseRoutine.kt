package com.example.healthroutineapplication

import java.io.Serializable

class ExerciseRoutine (val exercise : String, var setCount : Int, val weight : Int, var exerciseTimeSec : Int = 0, var restTimeSec : Int = 0) : Serializable{
    fun setExerciseTime(exerciseTime : Int){
        exerciseTimeSec = exerciseTime
    }
    fun setRestTime(restTime : Int){
        restTimeSec = restTime
    }
}
/*
운동의 클래스
장바구니에서 이 클래스를 ArrayList를 통해 ExerciseStartingActivity로 보내주면 됨
exercise : 운동명
setCount : 해당 운동의 설정된 세트 수
weight : 중량
exerciseTimeSec : 한 세트 당 운동시간(초단위)
restTimeSec : 세트와 세트 사이 쉬는시간(초단위)
 */