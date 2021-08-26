package com.example.healthroutineapplication

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class StepCounterService : Service(), SensorEventListener {

    lateinit var database: CalendarDatabase
    private lateinit var sensorManager: SensorManager
    private lateinit var stepDetectorSensor: Sensor
    companion object{
        var isSensor = true
    }


    override fun onCreate() {
        super.onCreate()
        Log.d("serviceTest","서비스 활성화")
        database = CalendarDatabase.getInstance(applicationContext)!!
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        try {
            stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        } catch (e: NullPointerException) {
            isSensor = false
            stopService(Intent(this,MainListActivity::class.java))
        }
        if (isSensor) {
            sensorManager.registerListener(
                this,
                stepDetectorSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("serviceTest","서비스 시작")
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("serviceTest","서비스 종료")
    }
    override fun onSensorChanged(event: SensorEvent?) {

        event?.let {
            if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                val today = SimpleDateFormat("yyyy-MM-dd").format(Date())
                Log.d("sensorTest","센서작동")
                CoroutineScope(Dispatchers.IO).launch {
                    Log.d("databaseTest","데이터베이스에 저장")
                    val todayData = database.calendarDao().searchToday(today)
                    if(todayData.isEmpty()){
                        Log.d("databaseTest","비어있음")
                        database.calendarDao().insert(CalendarDataClass(today,listOf(),1,""))
                    }
                    else{
                        Log.d("databaseTest","데이터 있음")
                        var temp = todayData[0]
                        temp.stepCount++
                        database.calendarDao().update(temp)
                    }
                }
            }
        }
    }
}