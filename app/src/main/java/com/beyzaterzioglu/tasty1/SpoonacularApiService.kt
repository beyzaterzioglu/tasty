package com.beyzaterzioglu.tasty1
import com.beyzaterzioglu.tasty1.model.Recipe
import com.beyzaterzioglu.tasty1.model.RecipeDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

    interface SpoonacularApiService {
        @GET("https://api.spoonacular.com/recipes/complexSearch")
        suspend fun getRecipe(@Query("apiKey") apiKey: String): Response<Recipe>

        @GET("recipes/search")
        suspend fun searchRecipes(
            @Query("query") query: String,
            @Query("apiKey") apiKey: String
        ): Response<SearchResponse>
        data class SearchResponse(
            val results: List<Recipe>
        )

       @GET("https://api.spoonacular.com/recipes/{id}/information")
        suspend fun getRecipeDetail(@Path("id") id : Int, @Query("apiKey") apiKey: String): Response<RecipeDetail>
    }