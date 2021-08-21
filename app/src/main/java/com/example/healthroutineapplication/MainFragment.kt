package com.example.healthroutineapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.activities.WorkOutStartActivity
import com.example.healthroutineapplication.adapters.WorkOutRoutineAdapter
import com.example.healthroutineapplication.databinding.FragmentMainBinding
import com.example.healthroutineapplication.interfaces.GoActivity
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class MainFragment(val intent: Intent) : Fragment(), GoActivity {

    private lateinit var binding: FragmentMainBinding
    private val exerciseRoutineViewModel: ExerciseRoutineViewModel by viewModels {
        ExerciseRoutineViewModelFactory((activity?.application as ExerciseRoutineApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val recyclerView = binding.mainRecyclerView
        val adapter = WorkOutRoutineAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        exerciseRoutineViewModel.routines.observe(viewLifecycleOwner) { routines ->
            routines?.let { adapter.submitList(it) }
        }
        return binding.root
    }

    override fun goActivity(id: Long?, name: String?) {
        intent.putExtra("putId", id)
        intent.putExtra("putName", name)
        startActivity(intent)
    }


}