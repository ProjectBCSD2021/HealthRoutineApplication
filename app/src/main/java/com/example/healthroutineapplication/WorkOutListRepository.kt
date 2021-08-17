package com.example.healthroutineapplication

import androidx.lifecycle.MutableLiveData

class WorkOutListRepository() {
    //운동 리스트를 livedata<list<Exercise>> 형식으로 변환한다.

    val chestExerciseList = MutableLiveData(getChestExerciseList())

    val letExerciseList= MutableLiveData(getLetExerciseList())

    val shoulderExerciseList= MutableLiveData(getShoulderExerciseList())

    val legExerciseList= MutableLiveData(getLegExerciseList())

    val armExerciseList= MutableLiveData(getArmExerciseList())

    val absExerciseList= MutableLiveData(getAbsExerciseList())

    val myExerciseList = MutableLiveData(exerciseList)

}