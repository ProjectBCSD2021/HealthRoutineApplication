package com.example.healthroutineapplication.interfaces

import androidx.room.*
import com.example.healthroutineapplication.Exercise
import com.example.healthroutineapplication.models.ExerciseData
import com.example.healthroutineapplication.models.ExerciseRoutineData
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseRoutineDao {
   @Query("SELECT * FROM routine")
   fun getAll(): Flow<List<ExerciseRoutineData>>

   @Update
   suspend fun updateRoutine(exerciseRoutineData: ExerciseRoutineData)

   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(exerciseRoutineData: ExerciseRoutineData)

   @Delete
   suspend fun delete(exerciseRoutineData: ExerciseRoutineData)
}
