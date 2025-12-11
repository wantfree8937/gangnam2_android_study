package com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.practice3.repository.RecipeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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
        .debounce(300L)

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val allRecipes = recipeRepository.getRecipes()
            _state.update {
                it.copy(
                    recipes = allRecipes,
                    allRecipes = allRecipes,
                    isLoading = false
                )
            }
        }

        viewModelScope.launch {
            _searchKeywordFlow.collect { query ->
                searchRecipes(query)
            }
        }
    }

    fun onQueryChanged(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun onSelectCategory(category: String) {
        _state.update { currentState ->
            val query = currentState.searchQuery

            val categoryFilteredList = if (category.equals("All", ignoreCase = true)) {
                currentState.allRecipes
            } else {
                currentState.allRecipes.filter { it.category.equals(category, ignoreCase = true) }
            }

            val finalFilteredList = categoryFilteredList.filter { it.name.contains(query, ignoreCase = true) }

            currentState.copy(
                recipes = finalFilteredList,
                selectedCategory = category
            )
        }
    }

    fun searchRecipes(query: String) {
        _state.update { currentState ->
            val category = currentState.selectedCategory

            val categoryFilteredList = if (category.equals("All", ignoreCase = true)) {
                currentState.allRecipes
            } else {
                currentState.allRecipes.filter { it.category.equals(category, ignoreCase = true) }
            }

            val finalFilteredList = categoryFilteredList.filter { it.name.contains(query, ignoreCase = true) }

            currentState.copy(
                recipes = finalFilteredList
            )
        }
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