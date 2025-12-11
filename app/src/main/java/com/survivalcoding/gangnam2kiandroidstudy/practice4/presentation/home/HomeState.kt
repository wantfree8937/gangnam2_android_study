package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe

data class HomeState(
    val searchQuery: String = "",
    val recipes: List<Recipe> = emptyList(),
    val allRecipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCategory: String = "All"
)
