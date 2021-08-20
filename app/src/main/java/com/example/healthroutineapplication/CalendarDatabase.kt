package com.example.healthroutineapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CalendarDataClass::class], version = 1)
@TypeConverters(CalendarDatabaseTypeConverters::class)
abstract class CalendarDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao

    companion object {
        private var instance: CalendarDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CalendarDatabase? {
            if (instance == null) {
                synchronized(CalendarDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalendarDatabase::class.java,
                        "calendar-database"
                    ).build()
                }
            }
            return instance
        }
    }
}