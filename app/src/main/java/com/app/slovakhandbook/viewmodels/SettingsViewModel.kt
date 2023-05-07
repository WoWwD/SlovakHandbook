package com.app.slovakhandbook.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class SettingsViewModel(private val sharedPreferences: SharedPreferences):ViewModel() {
    private val showingPreviewCardKey = "showingPreviewCard"

    fun getShowingPreviewCardState(): Boolean {
        return sharedPreferences.getBoolean(showingPreviewCardKey, false)
    }
    fun setShowingPreviewCardState(state: Boolean) {
        sharedPreferences.edit().putBoolean(showingPreviewCardKey, state).apply()
    }
}