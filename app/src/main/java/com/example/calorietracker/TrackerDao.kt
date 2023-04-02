package com.example.calorietracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TrackerDao {
    @Query("Select * from foods")
    fun getAll() : List<Tracker>

    @Insert
    fun insertAll(vararg tracker: Tracker)

    @Delete
    fun delete (tracker: Tracker)

    @Update
    fun Update (vararg tracker: Tracker)

}