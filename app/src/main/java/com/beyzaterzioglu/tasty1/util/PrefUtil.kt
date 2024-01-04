package com.beyzaterzioglu.tasty1.util

import android.content.Context
import androidx.preference.PreferenceManager

object PrefUtil {

    const val PREF_KEY_APP_AUTO_START = "PREF_KEY_APP_AUTO_START"

    fun getBooleanPrefence(context: Context?, key: String?, defaultValue: Boolean) : Boolean{
        var value = defaultValue

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null){
            value = preferences.getBoolean(key, defaultValue)
        }
        return value
    }

    fun writeBoolean(context: Context?, key: String?, value: Boolean) : Boolean{

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null){
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }
        return false
    }

    fun getStringPrefence(context: Context?, key: String?) : String?{
        var value : String? = "defaultValue"

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null){
            value = preferences.getString(key, "")
        }
        return value
    }

    fun writeString(context: Context?, key: String?, value: Boolean) : Boolean{
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null){
            val editor = preferences.edit()
            editor.putString(key, "")
            return editor.commit()
        }
        return false
    }


}