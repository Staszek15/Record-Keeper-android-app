package com.staszek15.recordkeeper

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.staszek15.recordkeeper.databinding.ActivityEditRecordBinding

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val screenData: ScreenData by lazy { intent.getSerializableExtra("screenData") as ScreenData }
    private val recordPreferences by lazy { getSharedPreferences(screenData.sharedPreferencesName, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.textInputRecord.hint = screenData.recordFieldHint
        recordPreferences.edit {
            remove("100m record")
            remove("1km record")
            remove("100m date")
            remove("1km date")
        }

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
        binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} record", null))
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        val runningPreferences = getSharedPreferences(screenData.sharedPreferencesName, Context.MODE_PRIVATE)

        runningPreferences.edit {
            putString("${screenData.record} record", record)
            putString("${screenData.record} date", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
    }

}