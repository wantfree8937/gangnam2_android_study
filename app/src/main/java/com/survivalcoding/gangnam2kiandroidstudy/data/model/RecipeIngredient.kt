package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredient(
    val ingredient: Ingredient,
    val amount: Int
)
