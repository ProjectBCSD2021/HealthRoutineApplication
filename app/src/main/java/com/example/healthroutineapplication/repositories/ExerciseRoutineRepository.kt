package com.example.healthroutineapplication.repositories

import androidx.annotation.WorkerThread
import com.example.healthroutineapplication.interfaces.ExerciseRoutineDao
import com.example.healthroutineapplication.models.ExerciseRoutineData
import kotlinx.coroutines.flow.Flow

class ExerciseRoutineRepository (private val exerciseRoutineDao: ExerciseRoutineDao){
    val allRoutine: Flow<List<ExerciseRoutineData>> = exerciseRoutineDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(exerciseRoutineData: ExerciseRoutineData){
        exerciseRoutineDao.insert(exerciseRoutineData)
    }

    @WorkerThread
    suspend fun delete(exerciseRoutineData: ExerciseRoutineData){
        exerciseRoutineDao.delete(exerciseRoutineData)
    }
}