package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
@OptIn(FlowPreview::class)
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.SearchClicked -> {}
            is HomeAction.CategorySelected -> onSelectCategory(action.category)
            is HomeAction.RecipeClicked -> {}
            is HomeAction.RecipeBookmarked -> {}
        }
    }

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
    }

    fun onClickSearch(){

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

}