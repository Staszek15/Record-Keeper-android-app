package com.staszek15.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.staszek15.recordkeeper.EditRecordActivity
import com.staszek15.recordkeeper.ScreenData
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

    override fun onResume() {
        super.onResume()
        getRecords()
    }

    private fun getRecords() {
        val runningPreferences = requireContext().getSharedPreferences("cyclingPref", Context.MODE_PRIVATE)

        binding.longestDistanceValue.text = runningPreferences.getString("Longest Distance Cycling record", null)
        binding.longestDistanceDate.text = runningPreferences.getString("Longest Distance Cycling date", null)
        binding.longestTimeValue.text = runningPreferences.getString("Longest Time Cycling record", null)
        binding.longestTimeDate.text = runningPreferences.getString("Longest Time Cycling date", null)
        binding.highestSpeedValue.text = runningPreferences.getString("Highest Speed Cycling record", null)
        binding.highestSpeedDate.text = runningPreferences.getString("Highest Speed Cycling date", null)
        binding.biggestClimbValue.text = runningPreferences.getString("Biggest Climb Cycling record", null)
        binding.biggestClimbDate.text = runningPreferences.getString("Biggest Climb Cycling date", null)
    }

    private fun setupClickListeners() {
        binding.containerDistance.setOnClickListener { launchCyclingEditScreen("Longest Distance") }
        binding.containerTime.setOnClickListener { launchCyclingEditScreen("Longest Time") }
        binding.containerSpeed.setOnClickListener { launchCyclingEditScreen("Highest Speed") }
        binding.containerClimb.setOnClickListener { launchCyclingEditScreen("Highest Climb") }
    }

    private fun launchCyclingEditScreen(record: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        val screenData = ScreenData("$record Cycling", "cyclingPref", "$record Value")
        intent.putExtra("screenData", screenData)
        startActivity(intent)
    }

}