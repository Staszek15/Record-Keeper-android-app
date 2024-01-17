package com.staszek15.recordkeeper

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
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
            showAlertDialog("running", arrayOf("runningPref"))
            true
        }
        R.id.reset_cycling -> {
            showAlertDialog("cycling", arrayOf("cyclingPref"))
            true
        }
        R.id.reset_swimming -> {
            showAlertDialog("swimming", arrayOf("swimmingPref"))
            true
        }
        R.id.reset_all -> {
            showAlertDialog("all", arrayOf("cyclingPref","swimmingPref","runningPref"))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(sport: String, sharedPreferencesName: Array<String>) {
        AlertDialog.Builder(this)
            .setTitle("Warning!")
            .setMessage("You are about to reset $sport records.")
            .setPositiveButton("Reset") { _, _ ->
                resetRecords(sharedPreferencesName)
                showConfirmation(sport)
            }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .show()
    }

    private fun showConfirmation(sport: String) {
        val snackbar = Snackbar.make(binding.root, "Successfully cleared $sport records.", Snackbar.LENGTH_LONG)
        snackbar.anchorView = binding.bottomNav
        snackbar.show()
    }

    private fun resetRecords(sharedPreferencesNames: Array<String>) {
        sharedPreferencesNames.forEach { name ->
            getSharedPreferences(name, Context.MODE_PRIVATE).edit {
                clear()
                refreshFragment(name) }
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