package com.example.healthroutineapplication

import android.content.Context
import android.graphics.drawable.Drawable
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.text.SimpleDateFormat
import java.util.*


class ExerciseDayDecorator(val exerciseList: List<CalendarDataClass>) : DayViewDecorator {

    private val calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        val date = ArrayList<Date>()
        if (exerciseList.isEmpty())
            return false
        else {
            for (i in 0..exerciseList.size - 1) {
                val exerciseDate = SimpleDateFormat("yyyy-MM-dd").parse(exerciseList[i].date)!!
                date.add(exerciseDate)
            }
        }
        return date.contains(calendar.time)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(7f, R.color.pointColor))
    }

}
