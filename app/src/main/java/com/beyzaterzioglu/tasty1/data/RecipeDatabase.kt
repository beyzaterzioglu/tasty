package com.beyzaterzioglu.tasty1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beyzaterzioglu.tasty1.model.ExtendedIngredient
import com.beyzaterzioglu.tasty1.model.RecipeDetail

@Database(entities = [RecipeDetail::class, ExtendedIngredient::class],version = 1)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class), (ExtendedIngredientConverters::class)])
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao


    companion object {
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase? {
            if (INSTANCE == null) {
                synchronized(RecipeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RecipeDatabase::class.java, "recipeapp.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}