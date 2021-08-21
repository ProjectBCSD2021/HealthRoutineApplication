package com.example.healthroutineapplication.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.exerciseList
import com.example.healthroutineapplication.models.ExerciseData

class WorkOutStartAdapter (val inflater: LayoutInflater) :
    ListAdapter<ExerciseData, WorkOutStartAdapter.WorkOutStartViewHolder>(WORKOUTSTART_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkOutStartViewHolder {
        return WorkOutStartViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WorkOutStartViewHolder, position: Int) {
        val current = getItem(position)
        with(holder) {

            bind(
                current.exercise,
                current.exerciseMethod,
                current.set.toString(),
                current.weight.toString()
            )

            root.setOnClickListener {
                val view: View = inflater.inflate(R.layout.exercise_list_weight_setting_frame, null)
                val builder = AlertDialog.Builder(itemView.context)

                builder.setView(view)
                builder.setPositiveButton("확인") { builder, which ->
                    val setWeight = view.findViewById<EditText>(R.id.weight_setting_edit)
                    val setSet = view.findViewById<EditText>(R.id.set_setting_edit)
                    current.set = Integer.parseInt(setSet.text.toString())
                    current.weight = Integer.parseInt(setWeight.text.toString())
                    notifyDataSetChanged()
                }
                builder.setNegativeButton("삭제"){builder, which ->

                    notifyDataSetChanged()
                }
                builder.setNeutralButton("돌아가기") { builder, which -> }
                builder.show()

            }
        }
    }

    class WorkOutStartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseName: TextView = itemView.findViewById(R.id.frame_exercise_name)
        private val exerciseMethod: TextView = itemView.findViewById(R.id.frame_exercise_method)
        private val exerciseSet: TextView = itemView.findViewById(R.id.frame_exercise_set)
        private val exerciseWeight: TextView = itemView.findViewById(R.id.frame_exercise_weight)
        val root: LinearLayout = itemView.findViewById(R.id.exercise_list_frame_root)

        fun bind(
            in_exerciseName: String?,
            in_exerciseMethod: String?,
            in_exerciseSet: String?,
            in_exerciseWeight: String?
        ) {
            exerciseName.text = in_exerciseName
            exerciseMethod.text = in_exerciseMethod
            exerciseSet.text = in_exerciseSet
            exerciseWeight.text = in_exerciseWeight
        }

        companion object {
            fun create(parent: ViewGroup): WorkOutStartViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.exercise_list_frame, parent, false)
                return WorkOutStartViewHolder(view)
            }
        }
    }

    companion object {
        private val WORKOUTSTART_COMPARATOR = object : DiffUtil.ItemCallback<ExerciseData>() {
            override fun areItemsTheSame(oldItem: ExerciseData, newItem: ExerciseData): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ExerciseData, newItem: ExerciseData): Boolean {
                return oldItem.exercise == newItem.exercise
            }
        }
    }


}