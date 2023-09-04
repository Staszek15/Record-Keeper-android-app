package com.staszek15.recordkeeper.running

import android.content.Context
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

    override fun onResume() {
        super.onResume()
        getRecords()
    }

    private fun getRecords() {
        val runningPreferences = requireContext().getSharedPreferences("runningPref", Context.MODE_PRIVATE)

        binding.hundredMTime.text = runningPreferences.getString("100m record", null)
        binding.hundredMDate.text = runningPreferences.getString("100m date", null)
        binding.oneKmTime.text = runningPreferences.getString("1km record", null)
        binding.oneKmDate.text = runningPreferences.getString("1km date", null)
        binding.fiveKmTime.text = runningPreferences.getString("5km record", null)
        binding.fiveKmDate.text = runningPreferences.getString("5km date", null)
        binding.tenKmTime.text = runningPreferences.getString("10km record", null)
        binding.tenKmDate.text = runningPreferences.getString("10km date", null)
        binding.halfMarathonTime.text = runningPreferences.getString("half marathon record", null)
        binding.halfMarathonDate.text = runningPreferences.getString("half marathon date", null)
        binding.marathonTime.text = runningPreferences.getString("marathon record", null)
        binding.marathonDate.text = runningPreferences.getString("marathon date", null)
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