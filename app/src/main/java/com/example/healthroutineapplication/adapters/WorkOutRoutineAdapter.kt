package com.example.healthroutineapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.choiceRoutineList
import com.example.healthroutineapplication.interfaces.GoActivity
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import java.util.*

class WorkOutRoutineAdapter(val goActivity: GoActivity) :
    RecyclerView.Adapter<WorkOutRoutineAdapter.ExerciseRoutineViewHolder>() {

    private var exerciseRoutineList = listOf<ExerciseRoutineData>()

    override fun onBindViewHolder(holder: ExerciseRoutineViewHolder, position: Int) {
        val current = exerciseRoutineList[position]
        with(holder) {
            bind(current.routineName, position + 1)

            root.setOnClickListener {
                choiceRoutineList = current.exerciseRoutine
                goActivity.goActivity(current.id, current.routineName)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseRoutineViewHolder {
        return ExerciseRoutineViewHolder.create(parent)
    }

    class ExerciseRoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val routineName: TextView = itemView.findViewById(R.id.routineTitle)
        private val routineNumber: TextView = itemView.findViewById(R.id.routineNumber)
        val root: ConstraintLayout = itemView.findViewById(R.id.work_out_list_constraint_layout)

        fun bind(in_routineName: String?, in_routineNumber: Int) {
            routineName.text = in_routineName
            routineNumber.text = in_routineNumber.toString()
        }

        companion object {
            fun create(parent: ViewGroup): ExerciseRoutineViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_list_item, parent, false)
                return ExerciseRoutineViewHolder(view)
            }
        }
    }

    fun setData(user:List<ExerciseRoutineData>){
        this.exerciseRoutineList = user
        notifyDataSetChanged()
    }

    fun removeData(position: Int):ExerciseRoutineData{
        val temp = exerciseRoutineList[position]
        return ExerciseRoutineData(
            temp.id,temp.routineName,temp.exerciseRoutine
        )
    }

    override fun getItemCount(): Int {
        return exerciseRoutineList.size
    }

}