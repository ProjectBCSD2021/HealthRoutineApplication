package com.example.healthroutineapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.adapters.CalendarDialogAdapter
import com.example.healthroutineapplication.databinding.FragmentCalendarBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment(exerciseList : List<CalendarDataClass>) : Fragment() {

    lateinit var binding : FragmentCalendarBinding
    private val exerciseDataList = exerciseList
    private val dialogAdapter = CalendarDialogAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment","onCreateView")

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_calendar,container,false)

        var startTimeCalendar = Calendar.getInstance()
        var endTimeCalender = Calendar.getInstance()

        val dateList = ArrayList<Date>()
        if (exerciseDataList.isNotEmpty()) {
            for (i in 0..exerciseDataList.size - 1) {
                val exerciseDate = SimpleDateFormat("yyyy-MM-dd").parse(exerciseDataList[i].date)!!
                if(exerciseDataList[i].exerciseList[0].exerciseTime > 0)
                    dateList.add(exerciseDate)
            }
        }

        val currentYear = startTimeCalendar.get(Calendar.YEAR)
        val currentMonth = startTimeCalendar.get(Calendar.MONTH)
        val currentDate = startTimeCalendar.get(Calendar.DATE)
        val sundayDecorator = SundayDecorator()
        val saturdayDecorator = SaturdayDecorator()
        val todayDecorator = TodayDecorator()
        val exerciseDayDecorator = ExerciseDayDecorator(dateList)

        endTimeCalender.set(Calendar.MONTH,currentMonth+3)

        binding.materialCalendar.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMinimumDate(CalendarDay.from(currentYear-5,currentMonth,1))
            .setMaximumDate(CalendarDay.from(currentYear+5,currentMonth,endTimeCalender.getActualMaximum(Calendar.DAY_OF_MONTH)))
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()

        val dialongView = inflater.inflate(R.layout.calendar_dialog,null)
        val alertDialogBuilder = AlertDialog.Builder(context).create()
        alertDialogBuilder.setView(dialongView)
        val recyclerView = dialongView.findViewById<RecyclerView>(R.id.recyclerView)

            binding.materialCalendar.setOnDateChangedListener { view, date, selected ->
                val strDate = SimpleDateFormat("yyyy-MM-dd").format(date.date)
                var pos = -1
                for(i in 0..exerciseDataList.size-1) {
                    if(exerciseDataList[i].date==strDate && exerciseDataList[i].exerciseList[0].exerciseTime>0){
                        pos=i
                    }
                }
                    if(pos>-1) {
                        val workOutList = exerciseDataList[pos].exerciseList
                        with(recyclerView) {
                            adapter = dialogAdapter
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            recyclerView.setHasFixedSize(true)
                        }
                        dialogAdapter.setData(workOutList)
                    }
                    else{
                        with(recyclerView){
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            recyclerView.setHasFixedSize(true)
                            val dialogAdapter = CalendarDialogAdapter()
                            adapter = dialogAdapter
                        }
                    }
                val memo = dialongView.findViewById<TextView>(R.id.memo)
                memo.setText(exerciseDataList[pos].memo)
                alertDialogBuilder.show()
                val okButton = dialongView.findViewById<Button>(R.id.ok_memo)
                okButton.setOnClickListener {
                    alertDialogBuilder.dismiss()
                }

                val editButton = dialongView.findViewById<Button>(R.id.edit_memo)
                editButton.setOnClickListener {
//                    Toast.makeText(context,"EDIT",Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity,MemoEditActivity::class.java)
                    intent.putExtra("position",pos)
                    startActivity(intent)
                    alertDialogBuilder.dismiss()
                }
            }

        binding.materialCalendar.addDecorators(sundayDecorator,saturdayDecorator,todayDecorator,exerciseDayDecorator)
        return binding.root
    }

}