package com.staszek15.recordkeeper.swimming

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.staszek15.recordkeeper.EditRecordActivity
import com.staszek15.recordkeeper.ScreenData
import com.staszek15.recordkeeper.databinding.FragmentSwimmingBinding

class SwimmingFragment : Fragment() {

    private lateinit var binding: FragmentSwimmingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSwimmingBinding.inflate(inflater, container, false)
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
        val runningPreferences = requireContext().getSharedPreferences("swimmingPref", Context.MODE_PRIVATE)

        binding.fiftyMTime.text = runningPreferences.getString("50m Swimming record", null)
        binding.fiftyMDate.text = runningPreferences.getString("50m Swimming date", null)
        binding.hundredMTime.text = runningPreferences.getString("100m Swimming record", null)
        binding.hundredMDate.text = runningPreferences.getString("100m Swimming date", null)
        binding.twoHundredMTime.text = runningPreferences.getString("200m Swimming record", null)
        binding.twoHundredMDate.text = runningPreferences.getString("200m Swimming date", null)
        binding.fourHundredMTime.text = runningPreferences.getString("400m Swimming record", null)
        binding.fourHundredMDate.text = runningPreferences.getString("400m Swimming date", null)
        binding.eightHundredMTime.text = runningPreferences.getString("800m Swimming record", null)
        binding.eightHundredMDate.text = runningPreferences.getString("800m Swimming date", null)
        binding.fifteenHundredMTime.text = runningPreferences.getString("1500m Swimming record", null)
        binding.fifteenHundredMDate.text = runningPreferences.getString("1500m Swimming date", null)
    }

    private fun setupClickListeners() {
        binding.containerSwimFiftyM.setOnClickListener { launchSwimmingEditScreen("50m") }
        binding.containerSwimHundredM.setOnClickListener { launchSwimmingEditScreen("100m") }
        binding.containerSwimTwoHundredM.setOnClickListener { launchSwimmingEditScreen("200m") }
        binding.containerSwimFourHundredM.setOnClickListener { launchSwimmingEditScreen("400m") }
        binding.containerSwimEightHundredM.setOnClickListener { launchSwimmingEditScreen("800m") }
        binding.containerSwimFifteenHundredM.setOnClickListener { launchSwimmingEditScreen("1500m") }
    }

    private fun launchSwimmingEditScreen(record: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        val screenData = ScreenData("$record Swimming", "swimmingPref", "$record Time")
        intent.putExtra("screenData", screenData)
        startActivity(intent)
    }
}