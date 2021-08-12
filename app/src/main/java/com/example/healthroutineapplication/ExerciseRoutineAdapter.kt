package com.example.healthroutineapplication

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.ExerciseRoutineRecyclerBinding
import java.lang.NumberFormatException

class ExerciseRoutineAdapter(private var context : Context) : RecyclerView.Adapter<ExerciseRoutineAdapter.ViewHolder>() {
    private var datas = mutableListOf<ExerciseRoutine>()
    lateinit var binding : ExerciseRoutineRecyclerBinding
    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ExerciseRoutineRecyclerBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder()
    }
    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ExerciseRoutine){
            binding.exerciseName.text = item.exercise
            binding.set.text = item.setCount.toString()
            binding.weight.text = item.weight.toString()+"kg"
            val position = adapterPosition
            binding.exerciseTimeEdit.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    try {
                        datas[position].setExerciseTime(s.toString().toInt())
                    } catch(e : NumberFormatException){
                        datas[position].setExerciseTime(-1)
                    }
                }
            })
            binding.restTimeEdit.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    try {
                        datas[position].setRestTime(s.toString().toInt())
                    } catch (e : NumberFormatException){
                        datas[position].setRestTime(-1)
                    }
                }
            })
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
    fun getData() : ArrayList<ExerciseRoutine>{
        return datas as ArrayList<ExerciseRoutine>
    }
}