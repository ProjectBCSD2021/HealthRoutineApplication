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
    val time : LiveData<String>
        get() = _time
    val exerciseList : LiveData<ArrayList<ExerciseRoutine>>
        get() = _exerciseList
    private var listItems = ArrayList<ExerciseRoutine>()
    private var timer : Timer? = null
    private var min = 0
    private var sec = 0
    private var resting = false //true면 쉬는시간 false면 운동시간
    var nowIndex = 0 //현재 진행중인 운동 인덱스
    private var dataSize = 0 //총 운동 수
    init{
        listItems = dataList
        _exerciseList.value = listItems
        secToMinSec(listItems[nowIndex].exerciseTimeSec)
        _time.value = minSecToTime(min,sec)
        dataSize = listItems.size
    }
    fun startTimer(){
        timer = timer(period = 1000){
            sec--
            if(sec<0){
                if(min>=1){
                    min--
                    sec = 59
                }
                else if(min == 0){ //0분0초인경우
                    if(resting){ // 쉬는시간이 끝난경우
                        resting = false
                        secToMinSec(listItems[nowIndex].exerciseTimeSec)
                    }
                    else{ // 운동시간이 끝난경우
                        resting = true
                        secToMinSec(listItems[nowIndex].restTimeSec)
                        listItems[nowIndex].setCount--
                        _exerciseList.postValue(listItems)
                        if(listItems[nowIndex].setCount==0){//마지막 세트가 끝나면
                            nowIndex++//다음 운동
                            if(nowIndex==dataSize){ //마지막운동이 끝나면
                                stopTimer()
                                min = 0
                                sec = 0
                            }
                        }
                    }
                }
            }
            _time.postValue(minSecToTime(min,sec))
        }
    }
    fun stopTimer(){
        timer?.cancel()
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