package com.example.healthroutineapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ExerciseStartingViewModelFactory(private val dataList : ArrayList<ExerciseRoutine>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ExerciseStartingViewModel::class.java)){
            ExerciseStartingViewModel(dataList) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}