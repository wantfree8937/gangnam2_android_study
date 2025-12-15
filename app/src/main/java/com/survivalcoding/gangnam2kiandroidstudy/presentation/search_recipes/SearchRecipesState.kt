package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState

data class SearchRecipesState(
    val searchQuery: String = "",
    val recipes: List<Recipe> = emptyList(),
    val allRecipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val appliedFilters: FilterSearchState = FilterSearchState()
)
