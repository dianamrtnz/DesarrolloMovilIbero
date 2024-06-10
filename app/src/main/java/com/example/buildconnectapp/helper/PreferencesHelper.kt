package com.example.buildconnectapp.helper

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_NAME = "user_name"
    }

    fun saveUserName(userName: String) {
        sharedPreferences.edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    fun clearUserName() {
        sharedPreferences.edit().remove(KEY_USER_NAME).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}