package com.example.healthroutineapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.models.ExerciseData
import com.example.healthroutineapplication.models.ExerciseRoutineData

class CalendarDialogAdapter() : RecyclerView.Adapter<CalendarDialogAdapter.ExerciseViewHolder>() {

    private var exerciseList = listOf<ExerciseData>()

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseName : TextView = itemView.findViewById(R.id.dialog_list_title)
        private val exerciseSet : TextView = itemView.findViewById(R.id.dialog_set_number)
        private val exerciseWeight:TextView = itemView.findViewById(R.id.dialog_weight_number)

        fun bind(title : String, set: Int, weight:Int){
            exerciseName.text=title
            exerciseSet.text =set.toString()
            exerciseWeight.text = weight.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.calender_dialong_item,parent,false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = exerciseList[position]
        with(holder){
            bind(current.exercise,current.set,current.weight)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    fun setData(data : List<ExerciseData>){
        this.exerciseList = data
        notifyDataSetChanged()
    }

}