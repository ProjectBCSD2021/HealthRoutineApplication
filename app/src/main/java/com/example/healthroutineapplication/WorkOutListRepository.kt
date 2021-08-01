package com.example.healthroutineapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.asFlow

class WorkOutListRepository() {
    val chestExerciseList = MutableLiveData(getChestExerciseList())

    val letExerciseList= MutableLiveData(getLetExerciseList())

    val shoulderExerciseList= MutableLiveData(getShoulderExerciseList())

    val legExerciseList= MutableLiveData(getLegExerciseList())

    val armExerciseList= MutableLiveData(getArmExerciseList())

    val absExerciseList= MutableLiveData(getAbsExerciseList())
}