package com.staszek15.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.staszek15.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding
    private lateinit var runningPreferences: SharedPreferences
    private val distance by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        runningPreferences = getSharedPreferences("runningPref", MODE_PRIVATE)
        title = "$distance Running Record"

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonClearRecord.setOnClickListener {
            clearRecord()
            finish()
        }
    }



    private fun displayRecord() {
        binding.editTextRecord.setText(runningPreferences.getString("$distance record", null))
        binding.editTextDate.setText(runningPreferences.getString("$distance date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        val runningPreferences = getSharedPreferences("runningPref", Context.MODE_PRIVATE)

        runningPreferences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }
    }

    private fun clearRecord() {
        runningPreferences.edit {
            remove("$distance record")
            remove("$distance date")
        }
    }

}