package com.staszek15.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.staszek15.recordkeeper.databinding.ActivityEditSwimmingRecordBinding

class EditSwimmingRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditSwimmingRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSwimmingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val distance = intent.getStringExtra("Distance")
        title = "$distance Swimming Record"

    }
}