package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.practice3.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes.SearchRecipesViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class HomeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _searchKeywordFlow = state
        .map { it.searchQuery }
        .debounce { 300 }

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val allRecipes = recipeRepository.getRecipes()
            _state.update { it.copy(
                recipes = allRecipes,
                allRecipes = allRecipes,
                isLoading = false
            ) }
        }

        viewModelScope.launch {
            _searchKeywordFlow.collect {
                searchRecipes(it)
            }
        }
    }

    fun onSelectCategory(category: String){
        _state.update { currentState ->
            val filteredList = if (category.equals("All", ignoreCase = true)) {
                currentState.allRecipes
            } else {
                currentState.allRecipes.filter { it.category.equals(category, ignoreCase = true) }
            }

            currentState.copy(
                recipes = filteredList,
                selectedCategory = category
            )
        }
    }

    fun searchRecipes(query: String) {
        _state.update { it.copy(
            recipes = it.recipes.filter { it.name.contains(query) }
        ) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as AppApplication
                val recipeRepository = application.recipeRepository
                HomeViewModel(recipeRepository)
            }
        }
    }
}