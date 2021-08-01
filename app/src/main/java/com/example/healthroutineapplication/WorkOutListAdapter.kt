package com.example.healthroutineapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WorkOutListAdapter() :
    ListAdapter<Exercise, WorkOutListAdapter.WorkOutListViewHolder>(WORKOUTLIST_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkOutListViewHolder {
        return WorkOutListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WorkOutListViewHolder, position: Int) {
        val current = getItem(position)
        with(holder){
            bind(current.name)
            root.setOnClickListener {

            }
        }
    }

    class WorkOutListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exercise: TextView = itemView.findViewById(R.id.work_out_list_frame_text_view)
        val root = itemView.findViewById<FrameLayout>(R.id.work_out_list_frame_layout)
        fun bind(in_exercise: String?) {
            exercise.text = in_exercise
        }

        companion object {
            fun create(parent: ViewGroup): WorkOutListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_out_list_frame, parent, false)
                return WorkOutListViewHolder(view)
            }
        }
    }

    companion object {
        private val WORKOUTLIST_COMPARATOR = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}