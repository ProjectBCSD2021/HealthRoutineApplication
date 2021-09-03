package com.example.healthroutineapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthroutineapplication.models.ExerciseData
import java.lang.IllegalArgumentException

class ExerciseStartingViewModelFactory(private val dataList: ArrayList<ExerciseData>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExerciseStartingViewModel::class.java)) {
            ExerciseStartingViewModel(dataList) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}