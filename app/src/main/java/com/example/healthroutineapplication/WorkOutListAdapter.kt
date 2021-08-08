package com.example.healthroutineapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WorkOutListAdapter(val context: Context) :
    ListAdapter<Exercise, WorkOutListAdapter.WorkOutListViewHolder>(WORKOUTLIST_COMPARATOR) {
    private val workOutMethod = arrayOf("덤벨", "머신", "바벨", "스미스 머신", "케이블")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkOutListViewHolder {
        return WorkOutListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WorkOutListViewHolder, position: Int) {
        val current = getItem(position)
        with(holder) {
            bind(current.name)

            //운동을 선택하면 어떻게 운동할 것인지 체크하는 AlertDialog를 띄운다.
            root.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("운동 방식").setSingleChoiceItems(workOutMethod, -1) { dialogInterface, which ->
                    ExerciseData(current.name,workOutMethod[which])
                    Toast.makeText(context,"추가",Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                builder.setNeutralButton("돌아가기") { builder, which -> }
                builder.show()
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