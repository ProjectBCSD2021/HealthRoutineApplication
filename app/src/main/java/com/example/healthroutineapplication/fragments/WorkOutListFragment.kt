package com.example.healthroutineapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.repositories.WorkOutListRepository
import com.example.healthroutineapplication.WorkOutListViewModel
import com.example.healthroutineapplication.WorkOutListViewModelFactory
import com.example.healthroutineapplication.adapters.WorkOutListAdapter
import com.example.healthroutineapplication.adapters.WorkOutListPagerAdapter
import com.example.healthroutineapplication.databinding.WorkOutListFragmentBinding

class WorkOutListFragment(val i: Int,val test: WorkOutListPagerAdapter) : Fragment(){
    lateinit var binding: WorkOutListFragmentBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.work_out_list_fragment, container, false)
        val recyclerView = binding.workOutListFragmentRecyclerView
        val adapter = WorkOutListAdapter(test)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        when (i) {
            0 -> {
                workOutListViewModel.chestWorkOutList.observe(viewLifecycleOwner) { exercise ->
                    exercise?.let { adapter.submitList(it)
                    }
                }
            }
            1 -> {
                workOutListViewModel.letWorkOutList.observe(viewLifecycleOwner) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            2 -> {
                workOutListViewModel.legWorkOutList.observe(viewLifecycleOwner) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            3 -> {
                workOutListViewModel.shoulderWorkOutList.observe(viewLifecycleOwner) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            4 -> {
                workOutListViewModel.armWorkOutList.observe(viewLifecycleOwner) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            5 -> {
                workOutListViewModel.absWorkOutList.observe(viewLifecycleOwner) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
        }
        return binding.root
    }

}