package com.example.healthroutineapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.databinding.ActivityWorkOutListBinding
import androidx.lifecycle.observe

class WorkOutListActivity : AppCompatActivity() {
    lateinit var binding: ActivityWorkOutListBinding
    private val workOutListViewModel: WorkOutListViewModel by viewModels {
        WorkOutListViewModelFactory(WorkOutListRepository())
    }
    val i: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_out_list)

        val recyclerView = binding.workOutListRecyclerView
        val adapter = WorkOutListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*
        * 메인에서 선택한 운동의 id를 받아서 운동을 나열한다.
        * */

        when (i) {
            1 -> {
                workOutListViewModel.chestWorkOutList.observe(owner = this) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            2->{
                workOutListViewModel.letWorkOutList.observe(owner = this) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            3->{
                workOutListViewModel.shoulderWorkOutList.observe(owner = this) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            4->{
                workOutListViewModel.legWorkOutList.observe(owner = this) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            5->{
                workOutListViewModel.armWorkOutList.observe(owner = this) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
            6->{
                workOutListViewModel.absWorkOutList.observe(owner = this) { exercise ->
                    exercise?.let { adapter.submitList(it) }
                }
            }
        }

    }
}