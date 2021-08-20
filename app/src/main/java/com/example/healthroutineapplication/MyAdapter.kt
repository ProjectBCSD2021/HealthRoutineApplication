package com.example.healthroutineapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.MainListItemBinding

class MyAdapter() : RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

    private var datas = mutableListOf<Routines>()
    lateinit var binding : MainListItemBinding

    inner class CustomViewHolder () : RecyclerView.ViewHolder(binding.root) {
        fun bind(routinesData : Routines){
            binding.routineTitle.text= routinesData.exercise
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
                val routines : Routines = datas.get(currentPosition)
                Toast.makeText(parent.context,"운동명: ${routines.exercise} \n 세트수: ${routines.setCount} \n 중량: ${routines.weight}",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int =datas.size

    fun replaceList (newList : ArrayList<Routines>){
        datas=newList
        notifyDataSetChanged()
    }


}