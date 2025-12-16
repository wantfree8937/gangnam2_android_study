package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredient(
    val ingredient: Ingredient,
    val amount: Int
)
