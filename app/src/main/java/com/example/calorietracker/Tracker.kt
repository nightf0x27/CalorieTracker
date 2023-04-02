package com.example.calorietracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Tracker (
   @PrimaryKey(autoGenerate = true) val id: Int,
    var food: String?,
    var calorie: String?
        ) {
}