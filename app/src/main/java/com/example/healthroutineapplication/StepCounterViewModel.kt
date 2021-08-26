package com.example.healthroutineapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthroutineapplication.models.ExerciseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class StepCounterViewModel(database: CalendarDatabase) : ViewModel() {
    private var _stepCount = MutableLiveData<Int>()
    private val today = SimpleDateFormat("yyyy-MM-dd").format(Date())
    private val calendarDatabase = database
    val step: LiveData<Int>
        get() = _stepCount

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val todayData = calendarDatabase.calendarDao().searchToday(today)
            if (todayData.isEmpty()) {
                _stepCount.postValue(0)
                calendarDatabase.calendarDao()
                    .insert(CalendarDataClass(today, ArrayList<ExerciseData>(), 0, ""))
            } else {
                _stepCount.postValue(todayData[0].stepCount)
            }
        }
    }
}
class StepCounterViewModelFactory(private val database: CalendarDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(StepCounterViewModel::class.java)) {
            StepCounterViewModel(database) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}