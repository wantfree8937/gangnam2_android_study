package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            _recipes.value = repository.getRecipes()
            try {
                _isLoading.value = true
                _recipes.value = repository.getRecipes()
            } catch (e: Exception) {
                _error.value = e.message ?: "알 수 없는 오류가 발생했습니다"
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SavedRecipesViewModel(application.recipeRepository)
            }
        }
    }
}