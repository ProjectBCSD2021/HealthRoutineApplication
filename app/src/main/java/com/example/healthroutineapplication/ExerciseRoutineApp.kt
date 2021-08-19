package com.example.healthroutineapplication

import android.app.Application
import com.example.healthroutineapplication.databases.ExerciseRoutineDatabase
import com.example.healthroutineapplication.repositories.ExerciseRoutineRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ExerciseRoutineApp :Application(){
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ExerciseRoutineDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { ExerciseRoutineRepository(database.exerciseRoutineDao()) }
}