package com.example.healthroutineapplication.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.databinding.ExerciseStartRecyclerBinding
import com.example.healthroutineapplication.models.ExerciseData

class ExerciseStartingAdapter(private val context: Context) : RecyclerView.Adapter<ExerciseStartingAdapter.ViewHolder>() {
    private var datas = mutableListOf<ExerciseData>()
    private var setList = mutableListOf<Int>()
    var highlightPosition = 0
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], setList[position])
        if (position == highlightPosition) {
            holder.binding.exercise.setTextColor(ContextCompat.getColor(context, R.color.pointColor))
            holder.binding.exercise.setTypeface(null, Typeface.BOLD)
        } else {
            holder.binding.exercise.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.binding.exercise.setTypeface(null, Typeface.NORMAL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExerciseStartRecyclerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ExerciseStartRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExerciseData, set : Int) {
            binding.exercise.text = item.exercise
            binding.set.text = set.toString()
            binding.exerciseTime.text = item.exerciseTime.toString() + "초"
            binding.restTime.text = item.restTime.toString() + "초"
            binding.weight.text = item.weight.toString() + "kg"
        }
    }

    fun setData(dataList: ArrayList<ExerciseData>) {
        datas = dataList
        setList.clear()
        for(dat in dataList){
            setList.add(dat.set)
        }
        notifyDataSetChanged()
    }
    fun setChange(changingSetList : ArrayList<Int>){
        setList = changingSetList
        notifyDataSetChanged()
    }
}