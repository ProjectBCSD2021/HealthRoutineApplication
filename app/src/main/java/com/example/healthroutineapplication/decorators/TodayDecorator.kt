package com.example.healthroutineapplication.decorators

import android.graphics.Typeface
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class TodayDecorator:DayViewDecorator {
    private val today = CalendarDay.today()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return today == day
    }

    override fun decorate(view: DayViewFacade?) {
        view?.apply {
            addSpan(object : StyleSpan(Typeface.BOLD){})
            addSpan(object : RelativeSizeSpan(1.2f){})
        }
    }
}