package com.example.healthroutineapplication

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.ExerciseStartRecyclerBinding

class ExerciseStartingAdapter(private val context : Context) : RecyclerView.Adapter<ExerciseStartingAdapter.ViewHolder>(){
    private var datas = mutableListOf<ExerciseRoutine>()
    var highlightPosition = -1
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        if(position == highlightPosition){
            holder.binding.exercise.setTextColor(ContextCompat.getColor(context,R.color.pointColor))
            holder.binding.exercise.setTypeface(null, Typeface.BOLD)
        }
        else{
            holder.binding.exercise.setTextColor(ContextCompat.getColor(context,R.color.black))
            holder.binding.exercise.setTypeface(null, Typeface.NORMAL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExerciseStartRecyclerBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding : ExerciseStartRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ExerciseRoutine){
            binding.exercise.text = item.exercise
            binding.set.text = item.setCount.toString()
            binding.exerciseTime.text = item.exerciseTimeSec.toString()+"초"
            binding.restTime.text = item.restTimeSec.toString()+"초"
            binding.weight.text = item.weight.toString()+"kg"
        }
    }
    fun setData(dataList : ArrayList<ExerciseRoutine>){
        datas = dataList
        notifyDataSetChanged()
    }
}