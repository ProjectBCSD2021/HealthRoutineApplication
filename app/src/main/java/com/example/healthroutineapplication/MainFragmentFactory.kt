package com.example.healthroutineapplication

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MainFragmentFactory(private val intent : Intent,private val calendarDataClass: List<CalendarDataClass>) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name -> MainFragment(intent)
            CalendarFragment::class.java.name->CalendarFragment(calendarDataClass)
            else -> super.instantiate(classLoader, className)
        }
    }
}