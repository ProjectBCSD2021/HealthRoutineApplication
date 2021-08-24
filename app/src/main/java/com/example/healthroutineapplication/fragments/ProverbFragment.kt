package com.example.healthroutineapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.healthroutineapplication.Proverb
import com.example.healthroutineapplication.R
import com.example.healthroutineapplication.databinding.FragmentProverbBinding
import com.example.healthroutineapplication.getProverbs
import kotlin.random.Random

class ProverbFragment : Fragment() {
    lateinit var binding:FragmentProverbBinding
    private val proverbList:List<Proverb> = getProverbs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_proverb,container,false)
        val randomInt = Random.nextInt(proverbList.size)
        Log.d("datavvv",randomInt.toString())
        with(binding){
            proverbTv.setText(proverbList[randomInt].proverb)
            proverbPersonTv.setText(proverbList[randomInt].person)
        }
        return binding.root
    }

}