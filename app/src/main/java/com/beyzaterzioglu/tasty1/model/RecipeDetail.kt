package com.beyzaterzioglu.tasty1.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.beyzaterzioglu.tasty1.data.ExtendedIngredientConverters
import com.beyzaterzioglu.tasty1.data.StringListConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class RecipeDetail(
    val cheap: Boolean?,
    val creditsText: String?,
    val dairyFree: Boolean?,
    @TypeConverters(StringListConverter::class)
    val dishTypes: List<String>,
    @TypeConverters(ExtendedIngredientConverters::class)
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String?,
    val glutenFree: Boolean?,
    val healthScore: Double?,
    @PrimaryKey
    val id: Int?,
    val image: String?,
    val imageType: String?,
    val instructions: String?,
    val ketogenic: Boolean?,
    val license: String?,
    val lowFodmap: Boolean?,
    val pricePerServing: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularScore: Double?,
    val spoonacularSourceUrl: String?,
    val summary: String?,
    val sustainable: Boolean?,
    val title: String?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val weightWatcherSmartPoints: Int?,
    val whole30: Boolean?,
    var isFav : Boolean = false
) : Parcelable