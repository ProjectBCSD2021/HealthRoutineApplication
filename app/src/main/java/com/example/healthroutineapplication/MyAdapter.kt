package com.example.healthroutineapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.FragmentMainBinding
import com.example.healthroutineapplication.databinding.MainListItemBinding

class MyAdapter() : RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

    private var datas = mutableListOf<Exercise>()
    lateinit var binding : MainListItemBinding

    inner class CustomViewHolder () : RecyclerView.ViewHolder(binding.root) {
        fun bind(exerciseData : Exercise){
            binding.routineTitle.text= exerciseData.exercise
            val position = adapterPosition
            binding.routineNumber.text=(position+1).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.main_list_item,parent,false)
        return CustomViewHolder().apply {
            itemView.setOnClickListener {
                val currentPosition : Int = adapterPosition
                val exercise : Exercise = datas.get(currentPosition)
                Toast.makeText(parent.context,"운동명: ${exercise.exercise} \n 세트수: ${exercise.setCount} \n 중량: ${exercise.weight}",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int =datas.size

    fun replaceList (newList : ArrayList<Exercise>){
        datas=newList
        notifyDataSetChanged()
    }


}