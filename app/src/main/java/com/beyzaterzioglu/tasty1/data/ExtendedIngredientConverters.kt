package com.beyzaterzioglu.tasty1.data

import androidx.room.TypeConverter
import com.beyzaterzioglu.tasty1.model.ExtendedIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExtendedIngredientConverters {
    @TypeConverter
    fun fromString(value: String): List<ExtendedIngredient>? {
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return Gson().fromJson<List<ExtendedIngredient>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ExtendedIngredient>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}