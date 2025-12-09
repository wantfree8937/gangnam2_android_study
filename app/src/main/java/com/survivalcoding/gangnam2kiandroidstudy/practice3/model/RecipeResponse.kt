package com.survivalcoding.gangnam2kiandroidstudy.practice3.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val recipes: List<Recipe>
)