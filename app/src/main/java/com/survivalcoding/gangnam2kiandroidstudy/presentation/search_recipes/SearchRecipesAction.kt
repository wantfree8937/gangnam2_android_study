package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState

sealed interface SearchRecipesAction {
    data class SearchQueryChanged(val query: String) : SearchRecipesAction
    data class FilterChanged(val filters: FilterSearchState) : SearchRecipesAction
    data object ShowFilterSheet : SearchRecipesAction
    data object DismissFilterSheet : SearchRecipesAction
}
