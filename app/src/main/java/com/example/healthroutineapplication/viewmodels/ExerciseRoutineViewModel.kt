package com.example.healthroutineapplication.viewmodels

import androidx.lifecycle.*
import com.example.healthroutineapplication.models.ExerciseData
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.repositories.ExerciseRoutineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ExerciseRoutineViewModel(private val exerciseRoutineRepository: ExerciseRoutineRepository) :
    ViewModel() {

    val routines: LiveData<List<ExerciseRoutineData>> =
        exerciseRoutineRepository.allRoutine.asLiveData()

    fun insert(exerciseRoutineData: ExerciseRoutineData) = viewModelScope.launch {
        exerciseRoutineRepository.insert(exerciseRoutineData)
    }

    fun updateRoutine(exerciseRoutineData: ExerciseRoutineData) = viewModelScope.launch {
        exerciseRoutineRepository.updateRoutine(exerciseRoutineData)
    }

    fun delete(exerciseRoutineData: ExerciseRoutineData) = viewModelScope.launch {
        exerciseRoutineRepository.delete(exerciseRoutineData)
    }
}

class ExerciseRoutineViewModelFactory(private val exerciseRoutineRepository: ExerciseRoutineRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseRoutineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseRoutineViewModel(exerciseRoutineRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
