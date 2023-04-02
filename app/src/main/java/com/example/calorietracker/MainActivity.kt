package com.example.calorietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.text.BoringLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_food.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var deletedFood: Tracker
    private lateinit var foods : List<Tracker>
    private lateinit var oldfoods: List<Tracker>
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

        val apply = recyclerview.apply {
            adapter = trackerAdapter
            layoutManager = linearLayoutManager
        }

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
            return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteFood(foods[viewHolder.adapterPosition])
            }
        }
    val swipeHelper = ItemTouchHelper(itemTouchHelper)
        swipeHelper.attachToRecyclerView(recyclerview)

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
private fun deleteFood(tracker: Tracker){
    deletedFood= tracker
    oldfoods =foods

    GlobalScope.launch {
        db.trackerDao().delete(tracker)
        foods = foods.filter { it.id !=tracker.id }
        runOnUiThread{

        }


    }
}

    override fun onResume() {
        super.onResume()
        fetchAll()
    }

}