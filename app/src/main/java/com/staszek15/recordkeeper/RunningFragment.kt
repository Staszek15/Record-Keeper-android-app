package com.staszek15.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.staszek15.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.containerRunHundredM.setOnClickListener { launchRunningEditScreen("100m") }
        binding.containerRunOneKm.setOnClickListener { launchRunningEditScreen("1km") }
        binding.containerRunFiveKm.setOnClickListener { launchRunningEditScreen("5km") }
        binding.containerRunTenKm.setOnClickListener { launchRunningEditScreen("10km") }
        binding.containerRunHalfMarathon.setOnClickListener { launchRunningEditScreen("Half-Marathon") }
        binding.containerRunMarathon.setOnClickListener { launchRunningEditScreen("Marathon") }
    }

    private fun launchRunningEditScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }


}