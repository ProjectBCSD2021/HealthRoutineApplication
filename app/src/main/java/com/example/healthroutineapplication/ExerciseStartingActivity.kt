package com.example.healthroutineapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.healthroutineapplication.databinding.ActivityExerciseStartingBinding

class ExerciseStartingActivity : AppCompatActivity() {
    lateinit var binding : ActivityExerciseStartingBinding
    lateinit var viewModel : ExerciseStartingViewModel
    private var adapter = ExerciseStartingAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_exercise_starting)
        binding.recyclerView.adapter = adapter
        val dataList = intent.getSerializableExtra("exerciseStarting") as ArrayList<ExerciseRoutine>
        //Intent를 이용해 ArrayList<ExerciseRoutine>를 받아옴
        viewModel = ViewModelProvider(this, ExerciseStartingViewModelFactory(dataList)).get(ExerciseStartingViewModel::class.java)
        viewModel.time.observe(this,{
            binding.exerciseTimer.text = it
            if(it=="00:01"){
                val mediaPlayer = MediaPlayer.create(this,R.raw.alert)
                mediaPlayer.start()
            }
        })
        viewModel.exerciseList.observe(this,{
            adapter.highlightPosition = viewModel.nowIndex
            adapter.setData(it)
        })
        viewModel.resting.observe(this,{
            if(it) binding.exerciseState.text = getString(R.string.state_resting)
            else binding.exerciseState.text = getString(R.string.state_exercising)
        })
        viewModel.timerEnable.observe(this,{
            if(it){
                binding.startButton.visibility = View.INVISIBLE
                binding.pauseButton.visibility = View.VISIBLE
            } else{
                binding.pauseButton.visibility = View.INVISIBLE
                binding.startButton.visibility = View.VISIBLE
            }
        })
        binding.startButton.setOnClickListener {
            viewModel.startTimer()
        }
        binding.pauseButton.setOnClickListener {
            viewModel.stopTimer()
        }
        binding.nextButton.setOnClickListener {
            if(!viewModel.next()) Toast.makeText(this,"운동이 끝났습니다.",Toast.LENGTH_SHORT).show()
        }
    }
}