package com.example.healthroutineapplication.databases

import android.content.Context
import androidx.room.*
import com.example.healthroutineapplication.interfaces.ExerciseRoutineDao
import com.example.healthroutineapplication.models.Converters
import com.example.healthroutineapplication.models.ExerciseRoutineData
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ExerciseRoutineData::class],version = 1)
@TypeConverters(Converters::class)
abstract class ExerciseRoutineDatabase : RoomDatabase(){

    abstract fun exerciseRoutineDao():ExerciseRoutineDao

    companion object{
        @Volatile
        private var INSTANCE: ExerciseRoutineDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope):ExerciseRoutineDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoutineDatabase::class.java,"exerciseRoutine_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}