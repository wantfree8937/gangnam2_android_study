package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SavedRecipesViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase
) : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _navigateToDetail = MutableStateFlow<Int?>(null)
    val navigateToDetail: StateFlow<Int?> = _navigateToDetail.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _recipes.value = getSavedRecipesUseCase.execute()
            } catch (e: Exception) {
                _error.value = e.message ?: "알 수 없는 오류가 발생했습니다"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onRecipeClick(recipeId: Int) {
        _navigateToDetail.value = recipeId
    }

    fun toggleBookmark(recipe: Recipe) {
        viewModelScope.launch {
            getSavedRecipesUseCase.toggleBookmark(recipe)
            _recipes.value = getSavedRecipesUseCase.execute()
        }
    }
}
