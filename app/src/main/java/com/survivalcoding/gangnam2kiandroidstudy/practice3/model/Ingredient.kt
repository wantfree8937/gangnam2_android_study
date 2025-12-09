package com.survivalcoding.gangnam2kiandroidstudy.practice3.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val id: Int,
    val name: String,
    val image: String      // 이미지 URL
)
