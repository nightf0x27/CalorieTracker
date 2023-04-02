package com.example.calorietracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TrackerAdapter (private var foods: List<Tracker>): RecyclerView.Adapter<TrackerAdapter.TrackerHolder>() {

    class TrackerHolder (view: View) : RecyclerView.ViewHolder(view){
        val food : TextView = view.findViewById(R.id.food)
        val calorie : TextView = view.findViewById(R.id.calorie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tracker_layout, parent, false)
        return TrackerHolder(view)
    }

    override fun onBindViewHolder(holder: TrackerHolder, position: Int) {
        val food = foods[position]
        //val context = holder.calorie
        holder.food.text = food.food
        holder.calorie.text = food.calorie
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    fun setData(foods: List<Tracker>) {
        this.foods = foods
        notifyDataSetChanged()
    }


}