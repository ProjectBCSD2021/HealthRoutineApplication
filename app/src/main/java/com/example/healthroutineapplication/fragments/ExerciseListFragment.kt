package com.example.healthroutineapplication.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.*
import com.example.healthroutineapplication.activities.WorkOutListActivity
import com.example.healthroutineapplication.adapters.ExerciseListAdapter
import com.example.healthroutineapplication.databinding.FragmentExerciseListBinding
import com.example.healthroutineapplication.models.ExerciseData
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.repositories.WorkOutListRepository
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel


class ExerciseListFragment(val viewModel: ExerciseRoutineViewModel,val workOutListContainerView:FragmentContainerView) : Fragment() {
    lateinit var binding:FragmentExerciseListBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_list, container, false)

        val inflater = layoutInflater
        val recyclerView = binding.exerciseListRecyclerView
        val adapter = ExerciseListAdapter(inflater)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        workOutListViewModel.myWorkOutList.observe(requireActivity()) { exercise ->
            exercise?.let {
                adapter.submitList(it)
            }
        }

        binding.exerciseListBtn.setOnClickListener {

            val view: View =inflater.inflate(R.layout.exercise_routine_list_frame,null)
            val routineNameEditText = view.findViewById<EditText>(R.id.exercise_routine_list_edit_text)
            val builder = AlertDialog.Builder(requireContext())

            builder.setView(view)
            builder.setPositiveButton("만들기"){builder, which ->
                val routineName:String = routineNameEditText.text.toString()
                val exerciseRoutineData = ExerciseRoutineData(null,routineName, exerciseList)
                viewModel.insert(exerciseRoutineData)
                workOutListContainerView.visibility = View.GONE
                exerciseList = mutableListOf<ExerciseData>()
                activity?.finish()
            }
            builder.setNeutralButton("돌아가기") { builder, which -> }
            builder.show()
        }

        binding.exerciseListExitBtn.setOnClickListener {
            workOutListContainerView.visibility = View.GONE
        }

        return binding.root
    }

}