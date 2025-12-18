package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun IngredientRoot(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: IngredientViewModel = hiltViewModel()
) {
    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
    }

    val recipe by viewModel.recipe.collectAsStateWithLifecycle()
    val procedures by viewModel.procedures.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf("Ingredient") }

    IngredientScreen(
        recipe = recipe,
        procedures = procedures,
        onBack = onBack,
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }
    )
}
