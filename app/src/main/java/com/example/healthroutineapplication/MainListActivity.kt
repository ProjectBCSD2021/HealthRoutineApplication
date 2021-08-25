package com.example.healthroutineapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthroutineapplication.activities.WorkOutListActivity
import com.example.healthroutineapplication.activities.WorkOutStartActivity
import com.example.healthroutineapplication.databinding.ActivityMainListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainListActivity : AppCompatActivity(), SensorEventListener {

    lateinit var binding: ActivityMainListBinding
    lateinit var calendarDatabase: CalendarDatabase
    lateinit var stepCounterViewModel: StepCounterViewModel
    private lateinit var sensorManager: SensorManager
    private lateinit var stepDetectorSensor: Sensor
    var isSensor = true

    //TYPE_STEP_DETECTOR 센서가 있는지 여부, StepCounterActivity 에 인텐트로 보내줘야함
    private var exerciseList = listOf<CalendarDataClass>()

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list -> {
                    replaceFragment(MainFragment(Intent(this, WorkOutStartActivity::class.java)))
                    return@OnNavigationItemSelectedListener true
                }

                R.id.calendar -> {
                    replaceFragment(CalendarFragment(exerciseList))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private val permissionContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (!it) finish()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        Log.d("Activity", "onCreate")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_list)
        val intent = Intent(this, WorkOutStartActivity::class.java)

        permissionContract.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val actionBar: ActionBar = supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(false)

        replaceFragment(MainFragment(intent))

        calendarDatabase = CalendarDatabase.getInstance(application)!!

        stepCounterViewModel = ViewModelProvider(
            this,
            StepCounterViewModelFactory(calendarDatabase)
        )[StepCounterViewModel::class.java]
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        try {
            stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        } catch (e: NullPointerException) {
            isSensor = false
        }


        CoroutineScope(Dispatchers.IO).launch {
            exerciseList = calendarDatabase.calendarDao().getAll()
        }

        binding.bottomNaviBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        binding.shoppingButton.setOnClickListener {
            startActivity(Intent(this, WorkOutListActivity::class.java))
        }
        binding.testButton.setOnClickListener {
            val intent = Intent(this, StepCounterActivity::class.java)
            intent.putExtra("isSensor", isSensor)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isSensor) {
            sensorManager.registerListener(
                this,
                stepDetectorSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                stepCounterViewModel.walking()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}


