package com.beyzaterzioglu.tasty1.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity("extendedgradient")
data class ExtendedIngredient(
    val aisle: String,
    val amount: Double,
    val consitency: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String
) : Parcelable