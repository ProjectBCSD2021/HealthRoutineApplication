package com.example.healthroutineapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.ExerciseRoutineRecyclerBinding

class ExerciseRoutineAdapter(private var context : Context) : RecyclerView.Adapter<ExerciseRoutineAdapter.ViewHolder>() {
    private var datas = mutableListOf<ExerciseRoutine>()
    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExerciseRoutineRecyclerBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(private val binding : ExerciseRoutineRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ExerciseRoutine){
            binding.exerciseName.text = item.exercise
            binding.set.text = item.setCount.toString()
            binding.exerciseTime.text = secToMinSec(item.exerciseTimeSec)
            binding.restTime.text = secToMinSec(item.restTimeSec)
        }
    }
    private fun secToMinSec(sec : Int) : String{
        return if(sec<60) sec.toString()+"초"
        else if(sec%60==0) (sec/60).toString()+"분"
        else (sec/60).toString()+"분 "+(sec%60)+"초"
    }
    fun setData(dataList : ArrayList<ExerciseRoutine>) {
        datas = dataList
        notifyDataSetChanged()
    }
}