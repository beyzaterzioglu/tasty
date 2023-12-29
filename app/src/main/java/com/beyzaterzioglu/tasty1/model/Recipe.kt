package com.beyzaterzioglu.tasty1.model



data class Recipe(
    val offset: String,
    val number: String,
    val results: List<RecipeItem>,
    val totalResults: String
)