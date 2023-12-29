package com.beyzaterzioglu.tasty1.view.RecipeDetail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzaterzioglu.tasty1.SpoonacularApiService
import com.beyzaterzioglu.tasty1.data.RecipeDao
import com.beyzaterzioglu.tasty1.data.RecipeDatabase
import com.beyzaterzioglu.tasty1.model.RecipeDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



    class RecipeDetailViewModel : ViewModel() {

        private val recipe = MutableLiveData<RecipeDetail>()
        val recipeModel: LiveData<RecipeDetail>
            get() = recipe

        var spoonacularApi: SpoonacularApiService



        init {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            spoonacularApi = retrofit.create(SpoonacularApiService::class.java)

        }

        fun saveFav(context : Context, recipeDetail: RecipeDetail){

            var db: RecipeDao = RecipeDatabase.getInstance(context)?.recipeDao()!!


            CoroutineScope(Dispatchers.Main).launch {
                db.insertRecipe(recipeDetail)
            }
        }

        fun makeApiCall(id: Int) {
            val apiKey = "10ccb00af5c343c4bfecb02176a7f268"
            GlobalScope.launch {
                withContext(Dispatchers.Main) {

                    val call = spoonacularApi.getRecipeDetail(id, apiKey)

                    if (call.isSuccessful) {
                        recipe.value = call.body()!!
                      val detailedRecipe = call.body()
                        detailedRecipe?.let {
                            // İlgili malzemeleri alıp güncellenmiş RecipeDetail nesnesini oluşturun
                            val updatedRecipe = RecipeDetail(
                                it.cheap,
                                it.creditsText,
                                it.dairyFree,
                                it.dishTypes,
                                it.extendedIngredients,
                                it.gaps,
                                it.glutenFree,
                                it.healthScore,
                                it.id,
                                it.image,
                                it.imageType,
                                it.instructions,
                                it.ketogenic,
                                it.license,
                                it.lowFodmap,
                                it.pricePerServing,
                                it.readyInMinutes,
                                it.servings,
                                it.sourceName,
                                it.sourceUrl,
                                it.spoonacularScore,
                                it.spoonacularSourceUrl,
                                it.summary,
                                it.sustainable,
                                it.title,
                                it.vegan,
                                it.vegetarian,
                                it.veryHealthy,
                                it.veryPopular,
                                it.weightWatcherSmartPoints,
                                it.whole30,
                            )
                            recipe.value = updatedRecipe
                        }

                    }

                    Log.d("TAG", "makeApiCall: ")
                }

            }

        }

    }
