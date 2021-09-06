package com.example.healthroutineapplication.decorators

import com.example.healthroutineapplication.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
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
