package com.staszek15.recordkeeper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.staszek15.recordkeeper.cycling.CyclingFragment
import com.staszek15.recordkeeper.databinding.ActivityMainBinding
import com.staszek15.recordkeeper.running.RunningFragment
import com.staszek15.recordkeeper.swimming.SwimmingFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_running -> {
            resetRecords("runningPref")
            Toast.makeText(this, "Reseted running records.", Toast.LENGTH_LONG).show()
            true
        }
        R.id.reset_cycling -> {
            resetRecords("cyclingPref")
            Toast.makeText(this, "Reseted cycling records.", Toast.LENGTH_LONG).show()
            true
        }
        R.id.reset_swimming -> {
            resetRecords("swimmingPref")
            Toast.makeText(this, "Reseted swimming records.", Toast.LENGTH_LONG).show()
            true
        }
        R.id.reset_all -> {
            resetRecords("runningPref")
            resetRecords("cyclingPref")
            resetRecords("swimmingPref")
            Toast.makeText(this, "Reseted all records.", Toast.LENGTH_LONG).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun resetRecords(sharedPreferencesName: String) {
        getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE).edit {
            clear()
            refreshFragment(sharedPreferencesName)
        }
    }
    private fun refreshFragment(sharedPreferencesName: String) = when (sharedPreferencesName) {
        "runningPref" -> onRunningClicked()
        "cyclingPref" -> onCyclingClicked()
        "swimmingPref" -> onSwimmingClicked()
        else -> false
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        //return boolean is used in when statement in onNavigationItemSelected() function
        return true
    }

    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    private fun onSwimmingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, SwimmingFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_running -> onRunningClicked()
        R.id.nav_cycling -> onCyclingClicked()
        R.id.nav_swimming -> onSwimmingClicked()
        else -> false
    }
}