package com.example.healthroutineapplication

import androidx.room.*

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