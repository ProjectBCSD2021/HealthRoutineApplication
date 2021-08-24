package com.example.healthroutineapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.*
import com.example.healthroutineapplication.adapters.ExerciseListAdapter
import com.example.healthroutineapplication.adapters.WorkOutStartAdapter
import com.example.healthroutineapplication.databinding.ActivityWorkOutListBinding
import com.example.healthroutineapplication.databinding.ActivityWorkOutStartBinding
import com.example.healthroutineapplication.fragments.ProverbFragment
import com.example.healthroutineapplication.models.ExerciseData
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.repositories.WorkOutListRepository
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class WorkOutStartActivity : AppCompatActivity() {
    lateinit var binding: ActivityWorkOutStartBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }
    private val exerciseRoutineViewModel: ExerciseRoutineViewModel by viewModels {
        ExerciseRoutineViewModelFactory((application as ExerciseRoutineApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_out_start)

        val getId = intent.getLongExtra("putId", 0)
        val getName = intent.getStringExtra("putName") ?: "null"

        val inflater = layoutInflater
        val recyclerView = binding.workOutStartRecyclerView
        val adapter = WorkOutStartAdapter(inflater)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        workOutListViewModel.choiceRList.observe(this) { exercise ->
            exercise?.let { adapter.submitList(it) }
        }

        binding.workOutStartBtn.setOnClickListener {
            showProverb()
            Handler().postDelayed({
                exerciseRoutineViewModel.updateRoutine(
                    ExerciseRoutineData(
                        getId, getName,
                        choiceRoutineList
                    )
                )
                intent = Intent(this, ExerciseRoutineActivity::class.java)
                intent.putExtra("exerciseRoutineTitle", getName)
                val exerciseList = ArrayList<ExerciseData>()
                choiceRoutineList?.let { it1 -> exerciseList.addAll(it1) }
                intent.putExtra("exerciseRoutine", exerciseList)
                startActivity(intent)
                finish()
            }, 2500L)
        }

    }

    private fun showProverb() {
        binding.workOutStartRecyclerView.visibility = View.INVISIBLE
        binding.workOutStartBtn.visibility = View.INVISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.show_fragment_layout, ProverbFragment())
        transaction.commit()
    }
}