package com.survivalcoding.gangnam2kiandroidstudy.domain.model.chef

import kotlinx.serialization.Serializable

@Serializable
data class ChefResponse(
    val profiles: List<Chef>
)
