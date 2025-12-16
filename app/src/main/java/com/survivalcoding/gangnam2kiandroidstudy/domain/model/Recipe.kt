package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val category: String,
    val name: String,
    val image: String,     // 이미지 URL
    val chef: String,
    val time: String,
    val rating: Double,    // 평점 (예: 4.0)
    val ingredients: List<RecipeIngredient>
)
