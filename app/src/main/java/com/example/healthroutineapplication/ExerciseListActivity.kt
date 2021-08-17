package com.example.healthroutineapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.databinding.ActivityExerciseListBinding

class ExerciseListActivity : AppCompatActivity(){
    lateinit var binding:ActivityExerciseListBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_exercise_list)
        val inflater = layoutInflater
        val recyclerView = binding.exerciseListRecyclerView
        val adapter = ExerciseListAdapter(inflater)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        workOutListViewModel.myWorkOutList.observe(this) { exercise ->
            exercise?.let { adapter.submitList(it) }
        }

    }

}