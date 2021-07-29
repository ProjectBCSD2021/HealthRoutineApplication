package com.example.healthroutineapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.ExerciseStartRecyclerBinding

class ExerciseStartingAdapter(private val context : Context) : RecyclerView.Adapter<ExerciseStartingAdapter.ViewHolder>(){
    private var datas = mutableListOf<ExerciseRoutine>()
    var highlightPosition = -1
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        if(position == highlightPosition){
            holder.binding.exercise.background = getDrawable(context,R.drawable.exercise_starting_list_now)
            holder.binding.set.background = getDrawable(context,R.drawable.exercise_starting_list_now)
        }
        else{
            holder.binding.exercise.background = getDrawable(context,R.drawable.exercise_starting_list)
            holder.binding.set.background = getDrawable(context,R.drawable.exercise_starting_list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExerciseStartRecyclerBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding : ExerciseStartRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ExerciseRoutine){
            binding.exerciseName = item.exercise
            binding.exerciseSet = item.setCount.toString()
        }
    }
    fun setData(dataList : ArrayList<ExerciseRoutine>){
        datas = dataList
        notifyDataSetChanged()
    }
}