package com.staszek15.recordkeeper.cycling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.staszek15.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recordType = intent.getStringExtra("recordType")
        title = "$recordType Cycling Record"
    }
}