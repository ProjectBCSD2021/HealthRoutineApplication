package com.example.healthroutineapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.*
import com.example.healthroutineapplication.adapters.ExerciseListAdapter
import com.example.healthroutineapplication.adapters.WorkOutStartAdapter
import com.example.healthroutineapplication.databinding.ActivityWorkOutListBinding
import com.example.healthroutineapplication.databinding.ActivityWorkOutStartBinding
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.repositories.WorkOutListRepository
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class WorkOutStartActivity : AppCompatActivity() {
    lateinit var binding:ActivityWorkOutStartBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }
    private val exerciseRoutineViewModel: ExerciseRoutineViewModel by viewModels{
        ExerciseRoutineViewModelFactory((application as ExerciseRoutineApp).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_work_out_start)

        val getId = intent.getLongExtra("putId",0)
        val getName = intent.getStringExtra("putName")?:"null"

        val inflater = layoutInflater
        val recyclerView = binding.workOutStartRecyclerView
        val adapter = WorkOutStartAdapter(inflater)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        workOutListViewModel.choiceRList.observe(this) { exercise ->
            exercise?.let { adapter.submitList(it) }
        }

        binding.workOutStartBtn.setOnClickListener {
            exerciseRoutineViewModel.updateRoutine(ExerciseRoutineData(getId,getName,
                choiceRoutineList))
        }

    }
}