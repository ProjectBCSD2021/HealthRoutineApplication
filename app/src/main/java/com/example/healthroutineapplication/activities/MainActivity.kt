package com.example.healthroutineapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.ExerciseRoutineApp
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.adapters.WorkOutRoutineAdapter
import com.example.healthroutineapplication.databinding.ActivityMainBinding
import com.example.healthroutineapplication.interfaces.GoActivity
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class MainListActivity : AppCompatActivity(),GoActivity {
    lateinit var binding: ActivityMainBinding
    private val exerciseRoutineViewModel:ExerciseRoutineViewModel by viewModels{
        ExerciseRoutineViewModelFactory((application as ExerciseRoutineApp).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_list)

        val recyclerView=binding.mainRecyclerview
        val adapter=WorkOutRoutineAdapter(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(this)

        exerciseRoutineViewModel.routines.observe(this){routines->
            routines?.let { adapter.submitList(it) }
        }

    }

    override fun goActivity() {
        startActivity(Intent(this,WorkOutStartActivity::class.java))
    }
}