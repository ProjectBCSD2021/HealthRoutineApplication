package com.example.healthroutineapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.healthroutineapplication.models.ExerciseData
import com.example.healthroutineapplication.interfaces.ExerciseListAdd
import com.example.healthroutineapplication.fragments.WorkOutListFragment
import com.example.healthroutineapplication.exerciseList

class WorkOutListPagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager), ExerciseListAdd {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return WorkOutListFragment(0,this)
            }
            1->{
                return WorkOutListFragment(1,this)
            }
            2->{
                return WorkOutListFragment(2,this)
            }
            3->{
                return WorkOutListFragment(3,this)
            }
            4->{
                return WorkOutListFragment(4,this)
            }
            5->{
                return WorkOutListFragment(5,this)
            }
            else -> return return WorkOutListFragment(0,this)
        }
    }

    override fun ExerciseAdd(exerciseName: String,exerciseMethod:String) {
        val name = exerciseName
        val method = exerciseMethod
        exerciseList.add(ExerciseData(name,method))
    }

}