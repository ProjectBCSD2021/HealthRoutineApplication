package com.example.healthroutineapplication.interfaces

import androidx.room.*
import com.example.healthroutineapplication.models.CalendarDataClass

@Dao
interface CalendarDao {
    @Query("SELECT * FROM CalendarDataClass")
    fun getAll(): List<CalendarDataClass>

    @Query("SELECT * FROM CalendarDataClass WHERE date = :today")
    fun searchToday(today: String): List<CalendarDataClass>

    @Insert
    fun insert(dataClass: CalendarDataClass)

    @Update
    fun update(dataClass: CalendarDataClass)

    @Delete
    fun delete(dataClass: CalendarDataClass)
}