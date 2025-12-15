package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val recipes: List<Recipe>
)