package com.example.healthroutineapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.healthroutineapplication.databinding.ActivityMemoEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoEditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMemoEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_memo_edit)

        val position = intent.getIntExtra("position",0)
        val calendarDatabase = CalendarDatabase.getInstance(this)!!
        var calendarList = listOf<CalendarDataClass>()
        CoroutineScope(Dispatchers.IO).launch {
            calendarList = calendarDatabase.calendarDao().getAll()
        }
        var memo = calendarList[position].memo
        binding.memoEditText.setText(memo)

        binding.enter.setOnClickListener {
            val text = binding.memoEditText.text
            calendarList[position].memo=text.toString()
            calendarDatabase.calendarDao().update(calendarList[position])
            val intent = Intent(this,MainListActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}