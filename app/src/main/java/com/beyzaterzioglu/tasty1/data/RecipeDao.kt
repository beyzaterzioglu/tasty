package com.beyzaterzioglu.tasty1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beyzaterzioglu.tasty1.model.RecipeDetail

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeDetail: RecipeDetail)

    @Delete
    suspend fun deleteRecipe(recipeDetail: RecipeDetail)

    @Query("Select * FROM recipes")
    fun fetchRecipes() : List<RecipeDetail>


    @Query("Select * FROM recipes where id = :recipeId")
    fun fetchById(recipeId : Int) : List<RecipeDetail>

}