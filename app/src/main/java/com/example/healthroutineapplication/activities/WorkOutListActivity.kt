package com.example.healthroutineapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.adapters.WorkOutListPagerAdapter
import com.example.healthroutineapplication.databinding.ActivityWorkOutListBinding
import com.google.android.material.tabs.TabLayout

class WorkOutListActivity : AppCompatActivity() {
    lateinit var binding: ActivityWorkOutListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_out_list)

        with(binding){

            workOutListTabLayout.addTab(workOutListTabLayout.newTab().setText("가슴"))
            workOutListTabLayout.addTab(workOutListTabLayout.newTab().setText("등"))
            workOutListTabLayout.addTab(workOutListTabLayout.newTab().setText("하체"))
            workOutListTabLayout.addTab(workOutListTabLayout.newTab().setText("어깨"))
            workOutListTabLayout.addTab(workOutListTabLayout.newTab().setText("팔"))
            workOutListTabLayout.addTab(workOutListTabLayout.newTab().setText("복근"))

            val pagerAdapter = WorkOutListPagerAdapter(supportFragmentManager,6)
            workOutListViewPager.adapter = pagerAdapter

            workOutListTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                //tab이 클릭 됬을 때
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    workOutListViewPager.setCurrentItem(tab!!.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }


                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
            //페이저를 이동했을 때 탭을 이동시키는 코드
            workOutListViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(workOutListTabLayout))

            workOutListBtn.setOnClickListener {
                val intent = Intent(this@WorkOutListActivity, ExerciseListActivity::class.java )
                startActivity(intent)
            }
        }

    }

}