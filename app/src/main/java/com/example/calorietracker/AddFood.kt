package com.example.calorietracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_food.*
import kotlinx.android.synthetic.main.tracker_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)


        addFoodBtn.setOnClickListener{
            val food: String = foodInput.text.toString()
            val calorie: String = calorieInput.text.toString()

            if (food.isEmpty())
                foodLayout.error = "Please enter a valid food"

           else if (calorie.isEmpty())
                calorieLayout.error = "Please enter calories"
            else{
                val tracker = Tracker(0, food, calorie)
                insert(tracker)
            }
        }

    }

    private fun insert(tracker: Tracker){
        val db:AppDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "foods").build()
        GlobalScope.launch {
            db.trackerDao().insertAll(tracker)
            finish()
        }
    }
}