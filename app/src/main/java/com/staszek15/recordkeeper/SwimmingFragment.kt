package com.staszek15.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    private fun setupClickListeners() {
        binding.containerSwimFiftyM.setOnClickListener { launchSwimmingEditScreen("50m") }
        binding.containerSwimHundredM.setOnClickListener { launchSwimmingEditScreen("100m") }
        binding.containerSwimTwoHundredM.setOnClickListener { launchSwimmingEditScreen("200m") }
        binding.containerSwimFourHundredM.setOnClickListener { launchSwimmingEditScreen("400m") }
        binding.containerSwimEightHundredM.setOnClickListener { launchSwimmingEditScreen("800m") }
        binding.containerSwimFifteenHundredM.setOnClickListener { launchSwimmingEditScreen("1500m") }
    }

    private fun launchSwimmingEditScreen(distance: String) {
        val intent = Intent(context, EditSwimmingRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
}