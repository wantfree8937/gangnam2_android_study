package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState

data class SearchRecipesState(
    val searchQuery: String = "",
    val recipes: List<Recipe> = emptyList(),
    val allRecipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val appliedFilters: FilterSearchState = FilterSearchState()
)
