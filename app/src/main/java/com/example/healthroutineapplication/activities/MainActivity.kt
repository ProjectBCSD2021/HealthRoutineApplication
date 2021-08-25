package com.example.healthroutineapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthroutineapplication.ExerciseRoutineApp
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.adapters.WorkOutRoutineAdapter
import com.example.healthroutineapplication.databinding.ActivityMainBinding
import com.example.healthroutineapplication.interfaces.GoActivity
import com.example.healthroutineapplication.models.ExerciseRoutineData
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModel
import com.example.healthroutineapplication.viewmodels.ExerciseRoutineViewModelFactory

class MainActivity : AppCompatActivity(),GoActivity {
    lateinit var binding:ActivityMainBinding
    private val exerciseRoutineViewModel:ExerciseRoutineViewModel by viewModels{
        ExerciseRoutineViewModelFactory((application as ExerciseRoutineApp).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val recyclerView=binding.mainRecyclerview1
        val adapter=WorkOutRoutineAdapter(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(this)


    }

    override fun goActivity(id:Long?,name:String?) {
        val intent = Intent(this,WorkOutStartActivity::class.java)
        intent.putExtra("putId",id)
        intent.putExtra("putName",name)
        startActivity(intent)
    }
}
