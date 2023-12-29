package com.beyzaterzioglu.tasty1.view.Favorities

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzaterzioglu.tasty1.data.RecipeDao
import com.beyzaterzioglu.tasty1.data.RecipeDatabase
import com.beyzaterzioglu.tasty1.model.RecipeDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritiesViewModel : ViewModel() {


    private val recipes = MutableLiveData<List<RecipeDetail>>()
    val favoritiesList : LiveData<List<RecipeDetail>>
        get() = recipes


    data class Recipe(
        val id: Int,
        val title: String,
        val image: String,
        val instructions: String
    )


    fun fetchFavItems(context : Context){

        val db: RecipeDao = RecipeDatabase.getInstance(context)?.recipeDao()!!


        CoroutineScope(Dispatchers.Main).launch {
                recipes.postValue(db.fetchRecipes())
        }
    }

}