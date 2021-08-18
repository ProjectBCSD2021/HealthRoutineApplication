package com.example.healthroutineapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthroutineapplication.databinding.ActivityMainBinding
import com.example.healthroutineapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.mainRecyclerView.setHasFixedSize(true)
        val myAdapter = MyAdapter()
        val mData = ArrayList<Exercise>()

        mData.apply {
            add(Exercise("운동1",1,10))
            add(Exercise("운동2",2,20))
            add(Exercise("운동3",3,30))
            add(Exercise("운동4",4,40))
            add(Exercise("운동5",5,50))
            add(Exercise("운동6",6,60))
        }

        myAdapter.replaceList(mData)

        binding.mainRecyclerView.adapter = myAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }
}