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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipesViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val recipes = recipeRepository.getRecipes()
            _state.update { it.copy(recipes = recipes, allRecipes = recipes, isLoading = false) }
        }

        viewModelScope.launch {
            _searchQuery
                .debounce(500L)
                .collect { query ->
                    _state.update { it.copy(searchQuery = query) }
                    filterRecipes()
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onFilterChanged(filterState: FilterSearchState) {
        _state.update { it.copy(appliedFilters = filterState) }
        filterRecipes()
    }

    private fun filterRecipes() {
        val currentState = _state.value
        var filteredList = currentState.allRecipes

        if (currentState.searchQuery.isNotBlank()) {
            filteredList = filteredList.filter {
                it.name.contains(currentState.searchQuery, ignoreCase = true)
            }
        }

        currentState.appliedFilters.selectedCategory?.let { category ->
            if (category != "All") {
                filteredList = filteredList.filter { it.category.equals(category, ignoreCase = true) }
            }
        }

        currentState.appliedFilters.selectedRate?.let { rateString ->
            val rate = rateString.toIntOrNull() ?: 0
            filteredList = filteredList.filter { it.rating >= rate }
        }

        when (currentState.appliedFilters.selectedTime) {
            "Newest" -> filteredList = filteredList.sortedByDescending { it.id }
            "Oldest" -> filteredList = filteredList.sortedBy { it.id }
            "All" -> Unit
        }

        _state.update { it.copy(recipes = filteredList) }
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
