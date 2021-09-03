package com.example.healthroutineapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.databases.CalendarDatabase
import com.example.healthroutineapplication.databinding.ActivityMemoEditBinding
import com.example.healthroutineapplication.models.CalendarDataClass
import com.example.healthroutineapplication.models.ExerciseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class MemoEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_edit)

        var memo = intent.getStringExtra("memo")!!
        val date = intent.getStringExtra("date")!!
        val exerciseList = intent.getSerializableExtra("exerciseList") as ArrayList<ExerciseData>
        val step = intent.getIntExtra("step",0)
        val calendarDatabase = CalendarDatabase.getInstance(applicationContext)!!

        val noMemo = getString(R.string.no_memo)
        if (memo=="")
            memo=noMemo
        binding.memoEditText.setText(memo)

        binding.enter.setOnClickListener {
            val text = binding.memoEditText.text
            memo = text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                calendarDatabase.calendarDao().update(CalendarDataClass(date,exerciseList,step,memo))
            }
            val intent = Intent(this, MainListActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}