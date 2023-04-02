package com.example.calorietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_food.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var foods : List<Tracker>
    private lateinit var trackerAdapter: TrackerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foods = arrayListOf()
        trackerAdapter = TrackerAdapter(foods)
        linearLayoutManager = LinearLayoutManager(this)

        db = Room.databaseBuilder(this,AppDatabase::class.java,"foods").build()

recyclerview.apply {
    adapter = trackerAdapter
    layoutManager = linearLayoutManager
}

        addFoodBtn2.setOnClickListener{
            val intent = Intent(this, AddFood::class.java)
            startActivity(intent)
        }

    }
    private fun fetchAll(){
    GlobalScope.launch {
        foods = db.trackerDao().getAll()
        runOnUiThread{
            trackerAdapter.setData(foods)

        }
    }

        }

    override fun onResume() {
        super.onResume()
        fetchAll()
    }

}