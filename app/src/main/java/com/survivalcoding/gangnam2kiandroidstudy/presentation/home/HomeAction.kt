package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeAction {
    data object SearchClicked : HomeAction
    data class CategorySelected(val category: String) : HomeAction
    data class RecipeClicked(val recipeId: Int) : HomeAction
    data class RecipeBookmarked(val recipeId: Int) : HomeAction
}