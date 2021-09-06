package com.example.healthroutineapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.*
import com.example.healthroutineapplication.adapters.WorkOutStartAdapter
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
        val adapter = WorkOutStartAdapter(inflater,this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        workOutListViewModel.choiceRList.observe(this) { exercise ->
            exercise?.let { adapter.submitList(it) }
        }

        binding.workOutStartBtn.setOnClickListener {
            exerciseRoutineViewModel.updateRoutine(
                ExerciseRoutineData(
                    getId, getName,
                    choiceRoutineList
                )
            )
            if (checkSet()) {
                showProverb()
                Handler().postDelayed({
                    intent = Intent(this, ExerciseRoutineActivity::class.java)
                    intent.putExtra("exerciseRoutineTitle", getName)
                    val exerciseList = ArrayList<ExerciseData>()
                    choiceRoutineList?.let { it1 -> exerciseList.addAll(it1) }
                    intent.putExtra("exerciseRoutine", exerciseList)
                    startActivity(intent)
                    finish()
                }, 2200L)
            }
            else{
                Toast.makeText(this,"세트 수를 넣어주세요.",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showProverb() {
        binding.workOutStartRecyclerView.visibility = View.INVISIBLE
        binding.workOutStartBtn.visibility = View.INVISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.show_fragment_layout, ProverbFragment())
        transaction.commit()
    }

    private fun checkSet(): Boolean {
        val temp:List<ExerciseData> = choiceRoutineList!!
        for (i in temp) {
            if (i.set <= 0) {
                return false
            }
        }
        return true
    }
}