package com.staszek15.recordkeeper.cycling

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.staszek15.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.containerDistance.setOnClickListener { launchCyclingEditScreen("Longest Distance") }
        binding.containerTime.setOnClickListener { launchCyclingEditScreen("Longest Time") }
        binding.containerSpeed.setOnClickListener { launchCyclingEditScreen("Highest Speed") }
        binding.containerClimb.setOnClickListener { launchCyclingEditScreen("Highest Climb") }
    }

    private fun launchCyclingEditScreen(recordType: String) {
        val intent = Intent(context, EditCyclingRecordActivity::class.java)
        intent.putExtra("recordType", recordType)
        startActivity(intent)
    }

}