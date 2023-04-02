package com.example.calorietracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = arrayOf(Tracker::class), version =1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun trackerDao(): TrackerDao
}