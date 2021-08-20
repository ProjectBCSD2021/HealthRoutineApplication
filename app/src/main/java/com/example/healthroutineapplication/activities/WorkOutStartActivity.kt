package com.example.healthroutineapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.WorkOutListViewModel
import com.example.healthroutineapplication.WorkOutListViewModelFactory
import com.example.healthroutineapplication.adapters.ExerciseListAdapter
import com.example.healthroutineapplication.databinding.ActivityWorkOutListBinding
import com.example.healthroutineapplication.databinding.ActivityWorkOutStartBinding
import com.example.healthroutineapplication.repositories.WorkOutListRepository

class WorkOutStartActivity : AppCompatActivity() {
    lateinit var binding:ActivityWorkOutStartBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_work_out_start)

        val inflater = layoutInflater
        val recyclerView = binding.workOutStartRecyclerView
        val adapter = ExerciseListAdapter(inflater)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        workOutListViewModel.choiceRList.observe(this) { exercise ->
            exercise?.let { adapter.submitList(it) }
        }
    }
}