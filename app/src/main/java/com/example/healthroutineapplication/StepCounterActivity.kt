package com.example.healthroutineapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.healthroutineapplication.databinding.ActivityStepCounterBinding

class StepCounterActivity : AppCompatActivity() {
    lateinit var binding : ActivityStepCounterBinding
    lateinit var viewModel: StepCounterViewModel
    lateinit var database : CalendarDatabase
    private var isSensor = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_step_counter)
        database = CalendarDatabase.getInstance(application)!!
        viewModel = ViewModelProvider(this,StepCounterViewModelFactory(database))[StepCounterViewModel::class.java]
        isSensor = intent.getBooleanExtra("isSensor",false)
        if(!isSensor){
            binding.stepCount.text = ""
            binding.textStep.text = getString(R.string.no_sensor)
        }
        viewModel.step.observe(this,{
            if(isSensor){
                binding.stepCount.text = it.toString()
            }
        })
    }
}