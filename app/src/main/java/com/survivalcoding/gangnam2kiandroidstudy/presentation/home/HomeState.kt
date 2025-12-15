package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class HomeState(
    val searchQuery: String = "",
    val recipes: List<Recipe> = emptyList(),
    val allRecipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCategory: String = "All"
)
