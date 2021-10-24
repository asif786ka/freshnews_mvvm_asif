package com.asif.freshnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.preference.PreferenceManager
import com.asif.freshnews.R
import com.asif.freshnews.databinding.ActivityMainBinding
import com.asif.freshnews.util.PREF_MODE_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Ensures that the SharedPreferences file is properly initialized with
         * the default values when this method is called for the first time.
         */
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)
        setupMode()
    }

    /**
     * Get the user's mode settings from SharedPreferences.
     */
    private fun setupMode() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isNightMode = sharedPreferences.getBoolean(PREF_MODE_KEY, false)
        setDefaultNightMode(if (isNightMode) MODE_NIGHT_YES else MODE_NIGHT_NO)
    }
}