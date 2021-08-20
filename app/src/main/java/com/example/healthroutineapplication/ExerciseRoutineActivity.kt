package com.example.healthroutineapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.healthroutineapplication.databinding.ActivityExerciseRoutineBinding

class ExerciseRoutineActivity : AppCompatActivity() {
    lateinit var binding: ActivityExerciseRoutineBinding
    private var adapter = ExerciseRoutineAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_routine)
        binding.recyclerView.adapter = adapter
        //val routineTitle = intent.getStringExtra("exerciseRoutineTitle")
        //루틴의 제목 intent로 받아옴
        //val dataList = intent.getSerializableExtra("exerciseRoutine") as ArrayList<ExerciseRoutine>
        //루틴의 운동들 ArrayList<ExerciseRoutine> 받아옴
        val routineTitle = "루틴 이름"
        var dataList = ArrayList<ExerciseRoutine>()
        dataList.add(ExerciseRoutine("운동1",1,10))
        dataList.add(ExerciseRoutine("운동2",2,10))
        dataList.add(ExerciseRoutine("운동3",1,20))
        binding.exerciseRoutineTitle.text = routineTitle
        adapter.setData(dataList)
        binding.exerciseStartButton.setOnClickListener {
            if (editCorrect(adapter.getData())) {
                val intent = Intent(this, ExerciseStartingActivity::class.java)
                intent.putExtra("exerciseStarting", adapter.getData())
                startActivity(intent)
            } else {
                val toast = Toast.makeText(this, "운동시간과 쉬는시간을 제대로 입력해주세요.", Toast.LENGTH_SHORT)
                toast.show()
            }
        } //운동시작 버튼 누르면 ArrayList<ExerciseRoutine>을 ExerciseStartingActivity로 보내고 액티비티 이동
    }

    private fun editCorrect(dataList: ArrayList<ExerciseRoutine>): Boolean {
        for (data in dataList) {
            if (data.exerciseTimeSec <= 0) return false
        }
        return true
    }
}

//테스트용
/*val routineTitle = "루틴 이름"
        var dataList = ArrayList<ExerciseRoutine>()
        dataList.add(ExerciseRoutine("운동1",1,10))
        dataList.add(ExerciseRoutine("운동2",2,10))
        dataList.add(ExerciseRoutine("운동3",1,20))*/