package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState

data class SearchRecipesState(
    val recipes: List<Recipe> = emptyList(),
    val allRecipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val appliedFilters: FilterSearchState = FilterSearchState(),
    val isFilterSheetVisible: Boolean = false
)