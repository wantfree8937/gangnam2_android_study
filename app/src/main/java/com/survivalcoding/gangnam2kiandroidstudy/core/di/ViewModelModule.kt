package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient.IngredientViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes.SearchRecipesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(recipeRepository = get()) }
    viewModel { IngredientViewModel(recipeId = get(), getRecipeDetailsUseCase = get()) }
    viewModel { SavedRecipesViewModel(getSavedRecipesUseCase = get()) }
    viewModel { SearchRecipesViewModel(recipeRepository = get()) }
}