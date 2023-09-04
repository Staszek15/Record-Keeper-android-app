package com.staszek15.recordkeeper

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.staszek15.recordkeeper.databinding.ActivityEditRecordBinding

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    //private val screenData: ScreenData by lazy {  }
    private val recordPreferences by lazy { getSharedPreferences("running", Context.MODE_PRIVATE) }
    private val record by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "$record Running Record"

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
        binding.editTextRecord.setText(recordPreferences.getString("$record record", null))
        binding.editTextDate.setText(recordPreferences.getString("$record date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        val runningPreferences = getSharedPreferences("runningPref", Context.MODE_PRIVATE)

        runningPreferences.edit {
            putString("$record record", record)
            putString("$record date", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("$record record")
            remove("$record date")
        }
    }

}