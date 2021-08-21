package com.example.healthroutineapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.healthroutineapplication.activities.WorkOutListActivity
import com.example.healthroutineapplication.activities.WorkOutStartActivity
import com.example.healthroutineapplication.databinding.ActivityMainListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainListActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainListBinding


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list -> {
                    replaceFragment(MainFragment(Intent(this, WorkOutStartActivity::class.java)))
                    return@OnNavigationItemSelectedListener true
                }

                R.id.calendar -> {
                    replaceFragment(CalendarFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_list)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val actionBar: ActionBar = supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(false)

        replaceFragment(MainFragment(Intent(this,WorkOutStartActivity::class.java)))

        binding.bottomNaviBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        binding.shoppingButton.setOnClickListener {
            startActivity(Intent(this,WorkOutListActivity::class.java))
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainLayout, fragment)
        fragmentTransaction.commit()
    }
}


