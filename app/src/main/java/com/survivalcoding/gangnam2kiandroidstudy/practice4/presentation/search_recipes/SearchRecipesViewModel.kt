package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.practice3.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipesViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()

    private var allRecipes: List<Recipe> = emptyList()


    init {
        viewModelScope.launch {
            val recipes = recipeRepository.getRecipes()
            allRecipes = recipes
            _state.update { it.copy(recipes = recipes) }
        }
    }

    fun onSearchQueryChanged(query: String) {
        val filteredRecipes = if (query.isBlank()) {
            allRecipes
        } else {
            allRecipes.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _state.update { it.copy(searchQuery = query, recipes = filteredRecipes) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as AppApplication
                val recipeRepository = application.recipeRepository
                SearchRecipesViewModel(recipeRepository)
            }
        }
    }
}
