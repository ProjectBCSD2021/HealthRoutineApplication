package com.example.healthroutineapplication

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.text.SimpleDateFormat
import java.util.*


class ExerciseDayDecorator(val dateList: List<Date>) : DayViewDecorator {

    private val calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        return dateList.contains(calendar.time)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(7f, R.color.pointColor))
    }

}
