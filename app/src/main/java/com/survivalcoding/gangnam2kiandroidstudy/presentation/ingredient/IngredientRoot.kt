package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun IngredientRoot(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: IngredientViewModel = viewModel(
        factory = IngredientViewModel.factory(
            recipeId = recipeId,
            application = LocalContext.current.applicationContext as AppApplication
        )
    )
) {
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
