package com.staszek15.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.staszek15.recordkeeper.EditRecordActivity
import com.staszek15.recordkeeper.ScreenData
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

    override fun onResume() {
        super.onResume()
        getRecords()
    }

    private fun getRecords() {
        val runningPreferences = requireContext().getSharedPreferences("runningPref", Context.MODE_PRIVATE)

        binding.hundredMTime.text = runningPreferences.getString("100m Running record", null)
        binding.hundredMDate.text = runningPreferences.getString("100m Running date", null)
        binding.oneKmTime.text = runningPreferences.getString("1km Running record", null)
        binding.oneKmDate.text = runningPreferences.getString("1km Running date", null)
        binding.fiveKmTime.text = runningPreferences.getString("5km Running record", null)
        binding.fiveKmDate.text = runningPreferences.getString("5km Running date", null)
        binding.tenKmTime.text = runningPreferences.getString("10km Running record", null)
        binding.tenKmDate.text = runningPreferences.getString("10km Running date", null)
        binding.halfMarathonTime.text = runningPreferences.getString("Half Marathon Running record", null)
        binding.halfMarathonDate.text = runningPreferences.getString("Half Marathon Running date", null)
        binding.marathonTime.text = runningPreferences.getString("Marathon Running record", null)
        binding.marathonDate.text = runningPreferences.getString("Marathon Running date", null)
    }

    private fun setupClickListeners() {
        binding.containerRunHundredM.setOnClickListener { launchRunningEditScreen("100m") }
        binding.containerRunOneKm.setOnClickListener { launchRunningEditScreen("1km") }
        binding.containerRunFiveKm.setOnClickListener { launchRunningEditScreen("5km") }
        binding.containerRunTenKm.setOnClickListener { launchRunningEditScreen("10km") }
        binding.containerRunHalfMarathon.setOnClickListener { launchRunningEditScreen("Half-Marathon") }
        binding.containerRunMarathon.setOnClickListener { launchRunningEditScreen("Marathon") }
    }

    private fun launchRunningEditScreen(record: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        val screenData = ScreenData("$record Running", "runningPref", "$record Time")
        intent.putExtra("screenData", screenData)
        startActivity(intent)
    }


}