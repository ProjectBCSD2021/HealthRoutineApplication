package com.example.healthroutineapplication

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.healthroutineapplication.databinding.ActivityExerciseStartingBinding

class ExerciseStartingActivity : AppCompatActivity() {
    lateinit var binding: ActivityExerciseStartingBinding
    lateinit var viewModel: ExerciseStartingViewModel
    private var adapter = ExerciseStartingAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_starting)
        binding.recyclerView.adapter = adapter
        val database = CalendarDatabase.getInstance(applicationContext)
        val dataList = intent.getSerializableExtra("exerciseStarting") as ArrayList<ExerciseRoutine>
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.end_exercise))
            .setMessage(getString(R.string.end_routine))
        builder.setPositiveButton("확인", DialogInterface.OnClickListener() { dialog, which ->
            startActivity(Intent(this, MainListActivity::class.java))
            finish()
        })
        //Intent를 이용해 ArrayList<ExerciseRoutine>를 받아옴
        viewModel = ViewModelProvider(this, ExerciseStartingViewModelFactory(dataList)).get(
            ExerciseStartingViewModel::class.java
        )
        viewModel.time.observe(this, {
            binding.exerciseTimer.text = it
            if (it == "00:01") {
                val mediaPlayer = MediaPlayer.create(this, R.raw.alert)
                mediaPlayer.start()
            }
        })
        viewModel.exerciseList.observe(this, {
            adapter.highlightPosition = viewModel.nowIndex
            adapter.setData(it)
        })
        viewModel.resting.observe(this, {
            if (it) binding.exerciseState.text = getString(R.string.state_resting)
            else binding.exerciseState.text = getString(R.string.state_exercising)
        })
        viewModel.timerEnable.observe(this, {
            if (it) {
                binding.startButton.visibility = View.INVISIBLE
                binding.pauseButton.visibility = View.VISIBLE
            } else {
                binding.pauseButton.visibility = View.INVISIBLE
                binding.startButton.visibility = View.VISIBLE
            }
        })
        viewModel.isEnd.observe(this, {
            if (it) {
                database?.let { database -> viewModel.saveCalendarData(database, dataList) }
                builder.create().show()
            }
        })
        binding.startButton.setOnClickListener {
            if (viewModel.isEnd.value == true) builder.create().show()
            else viewModel.startTimer()
        }
        binding.pauseButton.setOnClickListener {
            viewModel.stopTimer()
        }
        binding.nextButton.setOnClickListener {
            if (viewModel.isEnd.value == true) builder.create().show()
            else viewModel.next()
        }
    }
}