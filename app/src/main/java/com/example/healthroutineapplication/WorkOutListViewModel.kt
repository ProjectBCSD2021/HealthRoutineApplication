package com.example.healthroutineapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


class WorkOutListViewModel(private val workOutListRepository: WorkOutListRepository) : ViewModel() {
    val chestWorkOutList = workOutListRepository.chestExerciseList
    val letWorkOutList = workOutListRepository.letExerciseList
    val shoulderWorkOutList = workOutListRepository.shoulderExerciseList
    val legWorkOutList = workOutListRepository.legExerciseList
    val armWorkOutList = workOutListRepository.armExerciseList
    val absWorkOutList = workOutListRepository.absExerciseList
}

class WorkOutListViewModelFactory(private val workOutListRepository: WorkOutListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkOutListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkOutListViewModel(workOutListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}