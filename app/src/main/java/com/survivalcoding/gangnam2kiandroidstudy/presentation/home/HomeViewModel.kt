package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetHomeRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeRecipesUseCase: GetHomeRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = Channel<HomeEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.CategorySelected -> onSelectCategory(action.category)
            is HomeAction.RecipeBookmarked -> onRecipeBookmarked(action.recipe)
        }
    }

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val homeRecipes = getHomeRecipesUseCase.execute()
                val categories = listOf("All") + homeRecipes.map { it.recipe.category }.distinct()
                _state.update {
                    it.copy(
                        homeRecipes = homeRecipes,
                        filteredHomeRecipes = homeRecipes,
                        categories = categories,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                _event.send(HomeEvent.ShowError("Failed to load recipes: ${e.message}"))
            }
        }
    }

    private fun onRecipeBookmarked(recipe: Recipe) {
        viewModelScope.launch {
            try {
                toggleBookmarkUseCase.execute(recipe)
                _state.update { currentState ->
                    val newHomeRecipes = currentState.homeRecipes.map {
                        if (it.recipe.id == recipe.id) it.copy(isBookmarked = !it.isBookmarked) else it
                    }
                    currentState.copy(homeRecipes = newHomeRecipes)
                }
                onSelectCategory(_state.value.selectedCategory)
            } catch (e: Exception) {
                _event.send(HomeEvent.ShowError("Failed to bookmark: ${e.message}"))
            }
        }
    }

    private fun onSelectCategory(category: String) {
        _state.update { currentState ->
            val categoryFilteredList = if (category.equals("All", ignoreCase = true)) {
                currentState.homeRecipes
            } else {
                currentState.homeRecipes.filter { it.recipe.category.equals(category, ignoreCase = true) }
            }

            currentState.copy(
                filteredHomeRecipes = categoryFilteredList,
                selectedCategory = category
            )
        }
    }
}
