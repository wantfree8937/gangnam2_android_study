package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val recipeId: Int,
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

    init {
        viewModelScope.launch {
            val details = getRecipeDetailsUseCase.execute(recipeId)
            _recipe.value = details.recipe
            _procedures.value = details.procedures
        }
    }

    companion object {
        fun factory(recipeId: Int, application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val getRecipeDetailsUseCase = GetRecipeDetailsUseCase(
                        recipeRepository = application.recipeRepository,
                        ingredientRepository = application.ingredientRepository,
                        procedureRepository = application.procedureRepository
                    )
                    IngredientViewModel(
                        recipeId = recipeId,
                        getRecipeDetailsUseCase = getRecipeDetailsUseCase
                    )
                }
            }
    }
}
