package com.example.healthroutineapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class ExerciseStartingViewModel(dataList : ArrayList<ExerciseRoutine>) : ViewModel() {
    private val _time = MutableLiveData<String>()
    private val _exerciseList = MutableLiveData<ArrayList<ExerciseRoutine>>()
    private val _resting = MutableLiveData<Boolean>() //true면 쉬는시간 false면 운동시간
    private val _timerEnable = MutableLiveData<Boolean>() //true면 타이머가동중
    val time : LiveData<String>
        get() = _time
    val exerciseList : LiveData<ArrayList<ExerciseRoutine>>
        get() = _exerciseList
    val resting : LiveData<Boolean>
        get() = _resting
    val timerEnable : LiveData<Boolean>
        get() = _timerEnable
    private var listItems = ArrayList<ExerciseRoutine>()
    private var timer : Timer? = null
    private var min = 0
    private var sec = 0
    var nowIndex = 0 //현재 진행중인 운동 인덱스
    private var dataSize = 0 //총 운동 수
    init{
        listItems = dataList
        _exerciseList.value = listItems
        secToMinSec(listItems[nowIndex].exerciseTimeSec)
        _time.value = minSecToTime(min,sec)
        dataSize = listItems.size
        _resting.value = false
        _timerEnable.value = false
    }
    fun startTimer(){
        _timerEnable.value = true
        timer = timer(period = 1000){
            sec--
            if(sec<0){
                if(min>=1){
                    min--
                    sec = 59
                }
                else if(min == 0){ //0분0초인경우
                    if(_resting.value == true){ // 쉬는시간이 끝난경우
                        _resting.postValue(false)
                        endRestTime()
                    }
                    else{ // 운동시간이 끝난경우
                        _resting.postValue(true)
                        secToMinSec(listItems[nowIndex].restTimeSec)
                        listItems[nowIndex].setCount--
                        if(listItems[nowIndex].setCount==0){//마지막 세트가 끝나면
                            nowIndex++//다음 운동
                            if(nowIndex==dataSize){ //마지막운동이 끝나면
                                stopTimer()
                                _timerEnable.postValue(false)
                                min = 0
                                sec = 0
                            }
                        }
                        _exerciseList.postValue(listItems)
                    }
                }
            }
            _time.postValue(minSecToTime(min,sec))
        }
    }
    fun stopTimer(){
        timer?.cancel()
    }
    fun next() : Boolean{
        stopTimer()
        _timerEnable.value = false
        if(_resting.value == true){
            if(nowIndex>=dataSize) return false
            _resting.value = false
            endRestTime()
        } else{
            _resting.value = true
            endExerciseTime()
            _exerciseList.value = listItems
        }
        _time.value = minSecToTime(min,sec)
        return true
    }
    private fun endRestTime(){
        secToMinSec(listItems[nowIndex].exerciseTimeSec)
    }
    private fun endExerciseTime(){
        secToMinSec(listItems[nowIndex].restTimeSec)
        listItems[nowIndex].setCount--
        if(listItems[nowIndex].setCount==0){//마지막 세트가 끝나면
            nowIndex++//다음 운동
            if(nowIndex==dataSize){ //마지막운동이 끝나면
                stopTimer()
                min = 0
                sec = 0
            }
        }
    }
    private fun minSecToTime(min : Int, sec : Int) : String{ //분과 초를 mm:ss 형태의 문자열 반환
        val secString : String = if (sec < 10) "0$sec"
        else sec.toString()
        val minString : String = if(min < 10) "0$min"
        else min.toString()
        return "$minString:$secString"
    }
    private fun secToMinSec(dataSec : Int){ //초단위 시간받아와서 분과 초 설정
        min = dataSec / 60
        sec = dataSec % 60
    }
}