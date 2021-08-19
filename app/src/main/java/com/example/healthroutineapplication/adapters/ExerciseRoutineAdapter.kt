package com.example.healthroutineapplication.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.choiceRoutineList
import com.example.healthroutineapplication.exerciseList
import com.example.healthroutineapplication.interfaces.GoActivity
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.repositories.WorkOutListRepository

class ExerciseRoutineAdapter(val goActivity: GoActivity) :
    ListAdapter<ExerciseRoutineData, ExerciseRoutineAdapter.ExerciseRoutineViewHolder>(
        ROUTINE_COMPARATOR
    ) {

    override fun onBindViewHolder(holder: ExerciseRoutineViewHolder, position: Int) {
        val current = getItem(position)
        with(holder){
            bind(current.routineName)

            root.setOnClickListener {
                choiceRoutineList = current.exerciseRoutine
                goActivity.goActivity()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseRoutineViewHolder {
        return ExerciseRoutineViewHolder.create(parent)
    }

    class ExerciseRoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val routineName:TextView = itemView.findViewById(R.id.work_out_list_frame_text_view)
        val root:FrameLayout = itemView.findViewById(R.id.work_out_list_frame_layout)

        fun bind(in_routineName:String?) {
            routineName.text = in_routineName
        }

        companion object {
            fun create(parent: ViewGroup): ExerciseRoutineViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_out_list_frame, parent, false)
                return ExerciseRoutineViewHolder(view)
            }
        }
    }

    companion object {
        private val ROUTINE_COMPARATOR = object : DiffUtil.ItemCallback<ExerciseRoutineData>() {
            override fun areItemsTheSame(
                oldItem: ExerciseRoutineData,
                newItem: ExerciseRoutineData
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: ExerciseRoutineData,
                newItem: ExerciseRoutineData
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}