package com.example.healthroutineapplication.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.*
import com.example.healthroutineapplication.adapters.ExerciseListAdapter
import com.example.healthroutineapplication.databinding.ActivityExerciseListBinding
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.repositories.WorkOutListRepository
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class ExerciseListActivity : AppCompatActivity(){
    lateinit var binding:ActivityExerciseListBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }
    private val exerciseRoutineViewModel:ExerciseRoutineViewModel by viewModels {
        ExerciseRoutineViewModelFactory((application as ExerciseRoutineApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_list)

        val inflater = layoutInflater
        val recyclerView = binding.exerciseListRecyclerView
        val adapter = ExerciseListAdapter(inflater)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        workOutListViewModel.myWorkOutList.observe(this) { exercise ->
            exercise?.let { adapter.submitList(it) }
        }

        binding.exerciseListBtn.setOnClickListener {

            val view: View =inflater.inflate(R.layout.exercise_routine_list_frame,null)
            val routineNameEditText = view.findViewById<EditText>(R.id.exercise_routine_list_edit_text)
            val builder = AlertDialog.Builder(this)

            builder.setView(view)
            builder.setPositiveButton("만들기"){builder, which ->
                val routineName:String = routineNameEditText.text.toString()
                val exerciseRoutineData = ExerciseRoutineData(null,routineName,exerciseList)
                exerciseRoutineViewModel.insert(exerciseRoutineData)
                startActivity(Intent(this,MainListActivity::class.java))
            }
            builder.setNeutralButton("돌아가기") { builder, which -> }
            builder.show()
        }

    }

}