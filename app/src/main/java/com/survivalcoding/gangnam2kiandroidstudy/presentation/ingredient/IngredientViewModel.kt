package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {

    private val _recipe = MutableStateFlow(
        Recipe(
            id = -1,
            category = "",
            name = "",
            image = "",
            chef = "",
            time = "",
            rating = 0.0,
            ingredients = emptyList()
        )
    )
    val recipe: StateFlow<Recipe> = _recipe.asStateFlow()

    private val _procedures = MutableStateFlow<List<Procedure>>(emptyList())
    val procedures: StateFlow<List<Procedure>> = _procedures.asStateFlow()

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            val details = getRecipeDetailsUseCase.execute(recipeId)
            _recipe.value = details.recipe
            _procedures.value = details.procedures
        }
    }
}
