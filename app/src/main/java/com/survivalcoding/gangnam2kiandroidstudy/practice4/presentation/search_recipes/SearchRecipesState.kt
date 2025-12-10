package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe

data class SearchRecipesState(
    val searchQuery: String = "",
    val recipes: List<Recipe> = emptyList()
)
