package com.beyzaterzioglu.tasty1.view.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzaterzioglu.tasty1.SpoonacularApiService
import com.beyzaterzioglu.tasty1.model.RecipeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {


    private val recipes = MutableLiveData<List<RecipeItem>>()
    val recipeList : LiveData<List<RecipeItem>>
        get() = recipes

    var spoonacularApi: SpoonacularApiService

    data class Recipe(
        val id: Int,
        val title: String,
        val image: String,
        val instructions: String
    )

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        spoonacularApi = retrofit.create(SpoonacularApiService::class.java)

        makeApiCall()
    }

    fun makeApiCall() {
        val apiKey = "10ccb00af5c343c4bfecb02176a7f268"
        GlobalScope.launch {
            withContext(Dispatchers.Main){

                val call = spoonacularApi.getRecipe(apiKey)

                if (call.isSuccessful){
                    recipes.value = call.body()!!.results
                }

                val call2 = spoonacularApi.getRecipeDetail(644387, apiKey)

                Log.d("TAG", "makeApiCall: ")
            }

        }

    }
}