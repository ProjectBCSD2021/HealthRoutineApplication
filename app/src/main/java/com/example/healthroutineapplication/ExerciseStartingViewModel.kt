package com.example.healthroutineapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthroutineapplication.models.ExerciseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class ExerciseStartingViewModel(private val dataList: ArrayList<ExerciseData>) : ViewModel() {
    private val _time = MutableLiveData<String>()
    private val _resting = MutableLiveData<Boolean>() //true면 쉬는시간 false면 운동시간
    private val _timerEnable = MutableLiveData<Boolean>() //true면 타이머가동중
    private val _isEnd = MutableLiveData<Boolean>()//운동이 끝낫는지 확인
    private val _nowIndex = MutableLiveData<Int>()
    private val _setList = MutableLiveData<ArrayList<Int>>()
    val time: LiveData<String>
        get() = _time
    val resting: LiveData<Boolean>
        get() = _resting
    val timerEnable: LiveData<Boolean>
        get() = _timerEnable
    val isEnd: LiveData<Boolean>
        get() = _isEnd
    val nowIndex : LiveData<Int>
        get() = _nowIndex
    val setList : LiveData<ArrayList<Int>>
        get() = _setList
    private var timer: Timer? = null
    private var min = 0
    private var sec = 0
    private var tempSet = 0
    private var tempList = ArrayList<Int>()
    private var dataSize = 0 //총 운동 수

    init {
        _nowIndex.value = 0
        secToMinSec(dataList[0].exerciseTime)
        _time.value = minSecToTime(min, sec)
        dataSize = dataList.size
        tempSet = dataList[0].set
        for(dat in dataList){
            _setList.value?.add(dat.set)
            tempList.add(dat.set)
        }
        _resting.value = false
        _timerEnable.value = false
        _isEnd.value = false
    }

    fun startTimer() {
        _timerEnable.value = true
        timer = timer(period = 1000) {
            sec--
            if (sec < 0) {
                if (min >= 1) {
                    min--
                    sec = 59
                } else if (min == 0) { //0분0초인경우
                    if (_resting.value == true) { // 쉬는시간이 끝난경우
                        _resting.postValue(false)
                        endRestTime()
                    } else { // 운동시간이 끝난경우
                        _resting.postValue(true)
                        secToMinSec(dataList[_nowIndex.value!!].restTime)
                        tempSet--
                        tempList[_nowIndex.value!!] = tempSet
                        _setList.postValue(tempList)
                        if (tempSet == 0) {//마지막 세트가 끝나면
                            if (_nowIndex.value == dataSize-1) { //마지막운동이 끝나면
                                stopTimer()
                                _timerEnable.postValue(false)
                                min = 0
                                sec = 0
                                _isEnd.postValue(true)
                            }
                            else{
                                tempSet = dataList[_nowIndex.value!!].set
                            }
                            _nowIndex.postValue(_nowIndex.value!!+1)//다음 운동
                        }
                    }
                }
            }
            _time.postValue(minSecToTime(min, sec))
        }
    }

    fun stopTimer() {
        _timerEnable.postValue(false)
        timer?.cancel()
    }

    fun next(): Boolean {
        if(_isEnd.value == true) return true
        stopTimer()
        _timerEnable.value = false
        if (_resting.value == true) {
            if (_nowIndex.value!! >= dataSize) return false
            _resting.value = false
            endRestTime()
        } else {
            _resting.value = true
            endExerciseTime()
        }
        _time.value = minSecToTime(min, sec)
        return true
    }

    private fun endRestTime() {
        secToMinSec(dataList[_nowIndex.value!!].exerciseTime)
    }

    private fun endExerciseTime() {
        secToMinSec(dataList[_nowIndex.value!!].restTime)
        tempSet--
        tempList[_nowIndex.value!!] = tempSet
        _setList.value = tempList
        if (tempSet == 0) {//마지막 세트가 끝나면
            _nowIndex.value=_nowIndex.value!!+1//다음 운동
            if (_nowIndex.value!! == dataSize) { //마지막운동이 끝나면
                Log.d("catchError",_setList.value.toString())
                stopTimer()
                min = 0
                sec = 0
                _isEnd.value = true
            } else {
                tempSet = dataList[_nowIndex.value!!].set
            }
        }
    }

    private fun minSecToTime(min: Int, sec: Int): String { //분과 초를 mm:ss 형태의 문자열 반환
        val secString: String = if (sec < 10) "0$sec"
        else sec.toString()
        val minString: String = if (min < 10) "0$min"
        else min.toString()
        return "$minString:$secString"
    }

    private fun secToMinSec(dataSec: Int) { //초단위 시간받아와서 분과 초 설정
        min = dataSec / 60
        sec = dataSec % 60
    }

    fun saveCalendarData(database: CalendarDatabase) {
        val today = SimpleDateFormat("yyyy-MM-dd").format(Date())
        CoroutineScope(Dispatchers.IO).launch {
            val todayData = database.calendarDao().searchToday(today)
            if (todayData.isEmpty()) {
                database.calendarDao().insert(CalendarDataClass(today, dataList))
            } else {
                dataList.addAll(todayData[0].exerciseList)
                var tempData = todayData[0]
                tempData.exerciseList = dataList
                database.calendarDao().update(tempData)
            }
        }
    }
}