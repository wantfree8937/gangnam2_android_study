package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(FlowPreview::class)
class SearchRecipesViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val allRecipes = withContext(Dispatchers.IO) {
                recipeRepository.getRecipes()
            }
            _state.update {
                it.copy(
                    recipes = allRecipes,
                    allRecipes = allRecipes,
                    isLoading = false
                )
            }
        }

        viewModelScope.launch {
            state
                .map { current ->
                    Triple(
                        current.searchQuery,
                        current.appliedFilters,
                        current.allRecipes,
                    )
                }
                .debounce(300L)
                .distinctUntilChanged()
                .collectLatest { (searchQuery, filters, allRecipes) ->
                    val filteredList = withContext(Dispatchers.Default) {
                        var list = allRecipes

                        if (searchQuery.isNotBlank()) {
                            list = list.filter {
                                it.name.contains(searchQuery, ignoreCase = true)
                            }
                        }

                        filters.selectedCategory?.let { category ->
                            if (category != "All") {
                                list =
                                    list.filter { it.category.equals(category, ignoreCase = true) }
                            }
                        }

                        filters.selectedRate?.let { rateString ->
                            val rate = rateString.toIntOrNull() ?: 0
                            list = list.filter { it.rating >= rate }
                        }

                        list = when (filters.selectedTime) {
                            "Newest" -> list.sortedByDescending { it.id }
                            "Oldest" -> list.sortedBy { it.id }
                            else -> list
                        }

                        list
                    }

                    _state.update { it.copy(recipes = filteredList) }
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun onFilterChanged(filterState: FilterSearchState) {
        _state.update { it.copy(appliedFilters = filterState) }
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

