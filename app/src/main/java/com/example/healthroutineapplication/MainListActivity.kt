package com.example.healthroutineapplication

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.healthroutineapplication.activities.WorkOutListActivity
import com.example.healthroutineapplication.activities.WorkOutStartActivity
import com.example.healthroutineapplication.databinding.ActivityMainListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainListActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityMainListBinding
    private var exerciseList = listOf<CalendarDataClass>()
    lateinit var mainFragment: Fragment
    private val permissionContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (!it) finish()
        }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list -> {
                    replaceFragment(mainFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.calendar -> {

                    replaceFragment(CalendarFragment(exerciseList))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.walks -> {
                    val intent = Intent(this,StepCounterActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = Intent(this,WorkOutStartActivity::class.java)
        supportFragmentManager.fragmentFactory = MainFragmentFactory(intent,exerciseList)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_list)


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            permissionContract.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val actionBar: ActionBar = supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(false)

        mainFragment = supportFragmentManager.fragmentFactory.instantiate(classLoader,MainFragment::class.java.name)
        replaceFragment(mainFragment)

        val calendarDatabase= CalendarDatabase.getInstance(applicationContext)!!
        CoroutineScope(Dispatchers.IO).launch {
            exerciseList = calendarDatabase.calendarDao().getAll()
        }

        startService(Intent(this,StepCounterService::class.java))

        binding.bottomNaviBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainLayout, fragment)
        fragmentTransaction.commit()
    }

}


